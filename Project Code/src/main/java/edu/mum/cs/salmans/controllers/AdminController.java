package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.models.Review;
import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.Seat;
import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.HairStyleServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.UserServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.AppValues;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    private AppointmentServiceImplementation appointmentServiceImplementation;
    private UserServiceImplementation userServiceImplementation;
    private HairStyleServiceImplementation hairStyleServiceImplementation;

    public AdminController(AppointmentServiceImplementation appointmentServiceImplementation, UserServiceImplementation userServiceImplementation, HairStyleServiceImplementation hairStyleServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
        this.hairStyleServiceImplementation = hairStyleServiceImplementation;
    }


    @GetMapping(value = {PageUrlLocator.ADMIN_DASHBOARD_URL})
    public ModelAndView displayAdminDashboardPage(Principal principal) {
        String userEmail = principal.getName();
        User admin = userServiceImplementation.getUserByEmail(userEmail).get();

        ModelAndView modelAndView = getDefaultAdminModelAndView(admin
                , "Dashboard"
                , PageFileLocator.ADMIN_DASHBOARD_PAGE.toString());

        modelAndView.addObject("appointmentsCount", appointmentServiceImplementation.getNumberOfAllAppointments());
        modelAndView.addObject("customersCount", userServiceImplementation.getNumberOfCustomers());
        modelAndView.addObject("hairstylistCount", userServiceImplementation.getNumberOfHairstylists());
        modelAndView.addObject("reviewsCount", userServiceImplementation.getNumberOfAllReviews());

        return modelAndView;
    }


    @GetMapping(value = {PageUrlLocator.ADMIN_VIEW_APPOINTMENTS_URL})
    public ModelAndView displayAllAppointments(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultAdminModelAndView(user
                , "List of All Appointments"
                , PageFileLocator.ADMIN_VIEW_APPOINTMENTS_PAGE.toString());

        modelAndView.addObject("appointments", appointmentServiceImplementation.getAllAppointmentsPaged(page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    @GetMapping(value = {PageUrlLocator.ADMIN_CANCEL_APPOINTMENT_URL})
    public String deleteAppointment(@PathVariable Integer appointmentId, Model model) {
        appointmentServiceImplementation.deleteAppointmentById(appointmentId);
        return AppValues.REDIRECT.val() + PageUrlLocator.ADMIN_VIEW_APPOINTMENTS_URL;
    }

    @GetMapping(value = {PageUrlLocator.ADMIN_VIEW_REVIEWS_URL})
    public ModelAndView displayAllReviews(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultAdminModelAndView(user
                , "List of All Reviews"
                , PageFileLocator.ADMIN_VIEW_REVIEWS_PAGE.toString());

        modelAndView.addObject("reviews", userServiceImplementation.getAllReviewsPaged(page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    @GetMapping(value = {PageUrlLocator.ADMIN_VIEW_CUSTOMERS})
    public ModelAndView displayAllCustomers(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultAdminModelAndView(user
                , "List of All Customers"
                , PageFileLocator.ADMIN_VIEW_CUSTOMERS_PAGE.toString());

        modelAndView.addObject("customers", userServiceImplementation.getAllCustomersPaged(page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    @GetMapping(value = {PageUrlLocator.ADMIN_REGISTER_HAIRSTYLIST_URL})
    public ModelAndView displayAdminRegisterHairstylistPage(Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultAdminModelAndView(user
                , "Register A Hairstylists"
                , PageFileLocator.ADMIN_REGISTER_HAIRSTYLIST_PAGE.toString());

        User newHairstylist = new User();
        newHairstylist.setSeat(new Seat());
        modelAndView.addObject("hairstylist", newHairstylist);

        return modelAndView;
    }

    @PostMapping(value = {PageUrlLocator.ADMIN_REGISTER_HAIRSTYLIST_URL})
    public String registerNewHairstylist(@Valid @ModelAttribute("hairstylist") User hairstylist,
                                         BindingResult bindingResult, Model model, Principal principal) {
        String userEmail = principal.getName();
        User admin = userServiceImplementation.getUserByEmail(userEmail).get();
        if (!bindingResult.hasErrors()) {
            Seat seat = hairstylist.getSeat();
            hairstylist.setSeat(appointmentServiceImplementation.saveSeat(seat));

            Role hairstylistRole = userServiceImplementation.getRole(AppValues.ROLE_HAIRSTYLIST.toString());
            hairstylist.setRole(hairstylistRole);
            hairstylist.setDateRegistered(LocalDate.now());
            userServiceImplementation.saveUser(hairstylist);

            model.addAttribute("creationInfo", "Success, This Hairstylist has been registered");
            model.addAllAttributes(getAdminModelMap(admin, "Register A Hairstylists"));
            User newHairstylist = new User();
            newHairstylist.setSeat(new Seat());
            model.addAttribute("hairstylist", newHairstylist);

            return PageFileLocator.ADMIN_REGISTER_HAIRSTYLIST_PAGE.toString();
        } else {
            System.out.println(bindingResult.getAllErrors());

            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAllAttributes(getAdminModelMap(admin, "Register A Hairstylists"));
            return PageFileLocator.ADMIN_REGISTER_HAIRSTYLIST_PAGE.toString();
        }

    }


    @GetMapping(value = {PageUrlLocator.ADMIN_VIEW_HAIRSTYLISTS_URL})
    public ModelAndView displayAllHairstylists(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultAdminModelAndView(user
                , "List of All Hairstylists"
                , PageFileLocator.ADMIN_VIEW_HAIRSTYLISTS_PAGE.toString());

        modelAndView.addObject("hairstylists", userServiceImplementation.getAllHairstylistsPaged(page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    @GetMapping(value = {PageUrlLocator.ADMIN_VIEW_HAIRSTYLES_URL})
    public ModelAndView displayAllHairstyles(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultAdminModelAndView(user
                , "List of All Hairstyles"
                , PageFileLocator.ADMIN_VIEW_HAIRSTYLES_PAGE.toString());

        modelAndView.addObject("hairstyles", hairStyleServiceImplementation.getAllHairstylesPaged(page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }


    private ModelAndView getDefaultAdminModelAndView(User admin, String pageTitle, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(getAdminModelMap(admin, pageTitle));
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    private Map<String, Object> getAdminModelMap(User admin, String pageTitle) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("pageTitle", pageTitle);
        modelMap.put("pageLinks", AppHelper.adminPageLinks);
        modelMap.put("user", admin);

        return modelMap;
    }

}