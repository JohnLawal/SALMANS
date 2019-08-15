package edu.mum.cs.salmans.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
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

        return getDefaultAdminModelAndView(admin
                , "Dashboard"
                , PageFileLocator.ADMIN_DASHBOARD_PAGE.toString());
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
