package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.UserServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.AppValues;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.SysexMessage;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    AppointmentServiceImplementation appointmentServiceImplementation;
    @Autowired
    UserServiceImplementation userServiceImplementation;

    @GetMapping(value = {PageUrlLocator.CUSTOMER_DASHBOARD_URL})
    public ModelAndView displayCustomerDashboardPage(Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();

        return getDefaultCustomerModelAndView(user
                , "Welcome To Our Appointment Scheduling App!"
                , PageFileLocator.CUSTOMER_DASHBOARD_PAGE.toString());
    }


    @GetMapping(value = {PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL})
    public ModelAndView displayCustomerAppointments(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User user = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultCustomerModelAndView(user
                , "List of Your Appointments"
                , PageFileLocator.CUSTOMER_VIEW_APPOINTMENTS_PAGE.toString());

        modelAndView.addObject("appointments", appointmentServiceImplementation.getAllAppointmentsBookedByUserPaged(user,page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    private ModelAndView getDefaultCustomerModelAndView(User user, String pageTitle, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", pageTitle);
        modelAndView.addObject("pageLinks", AppHelper.customerPageLinks);
        modelAndView.addObject("user", user);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
