package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.models.*;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.HairStyleServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.UserServiceImplementation;
import edu.mum.cs.salmans.transfer.AppointmentDateAndServiceTimeForVerification;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.AppValues;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    private AppointmentServiceImplementation appointmentServiceImplementation;
    private UserServiceImplementation userServiceImplementation;
    private HairStyleServiceImplementation hairStyleServiceImplementation;

    public UserController(AppointmentServiceImplementation appointmentServiceImplementation, UserServiceImplementation userServiceImplementation, HairStyleServiceImplementation hairStyleServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
        this.hairStyleServiceImplementation = hairStyleServiceImplementation;
    }

    @GetMapping(value = {PageUrlLocator.CUSTOMER_DASHBOARD_URL})
    public ModelAndView displayCustomerDashboardPage(Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();

        return getDefaultCustomerModelAndView(user
                , "Dashboard"
                , PageFileLocator.CUSTOMER_DASHBOARD_PAGE.toString());
    }

    @GetMapping(value = {PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL})
    public ModelAndView displayCustomerAppointments(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultCustomerModelAndView(user
                , "List of Your Appointments"
                , PageFileLocator.CUSTOMER_VIEW_APPOINTMENTS_PAGE.toString());

        modelAndView.addObject("appointments", appointmentServiceImplementation.getAllAppointmentsBookedByUserPaged(user, page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    @GetMapping(value = {PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL})
    public ModelAndView displayCustomerMakeAppointmentPage(Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultCustomerModelAndView(user
                , "Book An Appointment"
                , PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString());

        Appointment appointment = new Appointment();
        appointment.setServiceTime(new ServiceTime());
        appointment.setHairStyle(new HairStyle());
        appointment.setHairstylist(new User());
        appointment.setCustomer(user);

        modelAndView.addObject("appointment", appointment);
        modelAndView.addAllObjects(getMakeAppointmentModelMap());

        return modelAndView;
    }

    @PostMapping(PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL)
    public String makeAppointment(@Valid @ModelAttribute("appointment") Appointment appointment, BindingResult bindingResult, Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        if (!bindingResult.hasErrors()) {
            try {
                LocalDate appointmentDate = appointment.getAppointmentDate();
                Integer serviceTimeId = appointment.getServiceTime().getTimeId();
                Optional<ServiceTime> optionalServiceTime = appointmentServiceImplementation.getServiceTimeWithId(serviceTimeId);
                if (optionalServiceTime.isPresent()) {
                    ServiceTime serviceTime = optionalServiceTime.get();
                    Integer hairstylistUserId = appointment.getHairstylist().getUserId();
                    Optional<User> optionalHairstylist = userServiceImplementation.getHairstylistWithId(hairstylistUserId);
                    if (optionalHairstylist.isPresent()) {
                        User hairstylist = optionalHairstylist.get();

                        boolean seatIsAvailable = appointmentServiceImplementation.seatIsAvailable(appointmentDate, serviceTime, hairstylist);
                        if (seatIsAvailable) {
                            Integer hairstyleId = appointment.getHairStyle().getHairstyleId();
                            Optional<HairStyle> optionalHairStyle = hairStyleServiceImplementation.getHairstyleWithId(hairstyleId);
                            if (optionalHairStyle.isPresent()) {
                                HairStyle hairStyle = optionalHairStyle.get();

                                appointment.setCustomer(user);
                                appointment.setHairstylist(hairstylist);
                                appointment.setHairStyle(hairStyle);
                                appointment.setServiceTime(serviceTime);
                                appointment.setDateScheduled(LocalDate.now());

                                appointmentServiceImplementation.saveAppointment(appointment);
                                return AppValues.REDIRECT.val() + PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL;
                            } else {
                                model.addAttribute("creationException", "Sorry, This Hairstyle is Unavailable");
                                model.addAllAttributes(getCustomerModelMap(user, "Book An Appointment"));
                                model.addAllAttributes(getMakeAppointmentModelMap());
                                return PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString();
                            }
                        } else {
                            model.addAttribute("creationException", "Sorry, This Spot is Unavailable. Please try another set of options");
                            model.addAllAttributes(getCustomerModelMap(user, "Book An Appointment"));
                            model.addAllAttributes(getMakeAppointmentModelMap());
                            return PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString();
                        }
                    } else {
                        model.addAttribute("creationException", "Sorry, This Hairstylist does not exist in our Salon");
                        model.addAllAttributes(getCustomerModelMap(user, "Book An Appointment"));
                        model.addAllAttributes(getMakeAppointmentModelMap());
                        return PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString();
                    }
                } else {
                    model.addAttribute("creationException", "Sorry, This Service Time is Unavailable");
                    model.addAllAttributes(getCustomerModelMap(user, "Book An Appointment"));
                    model.addAllAttributes(getMakeAppointmentModelMap());
                    return PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString();
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("creationException", "Sorry, An Error Occurred While Trying To Create An Appointment");
                model.addAllAttributes(getCustomerModelMap(user, "Book An Appointment"));
                model.addAllAttributes(getMakeAppointmentModelMap());
                return PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString();
            }
        } else {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAllAttributes(getCustomerModelMap(user, "Book An Appointment"));
            model.addAllAttributes(getMakeAppointmentModelMap());
            return PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString();
        }
    }

    @GetMapping(value = {PageUrlLocator.CUSTOMER_CANCEL_APPOINTMENT_URL})
    public String cancelAppointment(@PathVariable Integer appointmentId, Model model) {
        appointmentServiceImplementation.deleteAppointmentById(appointmentId);
        return AppValues.REDIRECT.val() + PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL;
    }


    @GetMapping(value = {PageUrlLocator.CUSTOMER_MAKE_REVIEW_URL})
    public ModelAndView displayCustomerMakeReviewPage(Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultCustomerModelAndView(user
                , "Make A Review"
                , PageFileLocator.CUSTOMER_MAKE_REVIEW_PAGE.toString());

        modelAndView.addObject("review", new Review());

        return modelAndView;
    }

    @PostMapping(value = {PageUrlLocator.CUSTOMER_MAKE_REVIEW_URL})
    public String makeReview(@Valid @ModelAttribute("review") Review review,
                             BindingResult bindingResult, Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        if (!bindingResult.hasErrors()) {
            review.setDateOfReview(LocalDate.now());
            review.setCustomer(user);
            userServiceImplementation.saveReview(review);

            model.addAttribute("creationInfo", "Success, This Review has been submitted");
            model.addAllAttributes(getCustomerModelMap(user, "Make A Review"));
            model.addAttribute("review", new Review());

            return PageFileLocator.CUSTOMER_MAKE_REVIEW_PAGE.toString();

        } else {
            System.out.println(bindingResult.getAllErrors());

            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAllAttributes(getCustomerModelMap(user, "Make A Review"));
            return PageFileLocator.CUSTOMER_MAKE_REVIEW_PAGE.toString();
        }
    }


    private ModelAndView getDefaultCustomerModelAndView(User user, String pageTitle, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(getCustomerModelMap(user, pageTitle));
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    private Map<String, Object> getCustomerModelMap(User user, String pageTitle) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("pageTitle", pageTitle);
        modelMap.put("pageLinks", AppHelper.customerPageLinks);
        modelMap.put("user", user);

        return modelMap;
    }

    private Map<String, Object> getMakeAppointmentModelMap() {
        Map<String, Object> modelMap = new HashMap<>();

        List<ServiceTime> serviceTimes = appointmentServiceImplementation.getAllServiceTimes();
        List<HairStyle> hairstyles = hairStyleServiceImplementation.getAllHairStyles();
        List<User> hairstylists = userServiceImplementation.getAllHairStylists();

        modelMap.put("serviceTimes", serviceTimes);
        modelMap.put("hairstyles", hairstyles);
        modelMap.put("hairstylists", hairstylists);

        return modelMap;

    }
}
