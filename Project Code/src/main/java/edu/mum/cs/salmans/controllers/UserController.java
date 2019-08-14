package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.AppValues;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    AppointmentServiceImplementation appointmentServiceImplementation;

    @GetMapping(value = {PageUrlLocator.CUSTOMER_DASHBOARD_URL})
    public ModelAndView displayCustomerDashboardPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Welcome To Our Appointment Scheduling App!");
        modelAndView.addObject("pageLinks", AppHelper.customerPageLinks);
        modelAndView.setViewName(PageFileLocator.CUSTOMER_DASHBOARD_PAGE.toString());
        return modelAndView;
    }


    @GetMapping(value = { PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL})
    public ModelAndView listStudents(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "List of Your Appointments");
        modelAndView.addObject("pageLinks", AppHelper.customerPageLinks);
//        modelAndView.addObject("appointments", appointmentServiceImplementation.get);
        modelAndView.addObject("currentPageNo", page);
        modelAndView.setViewName(PageFileLocator.CUSTOMER_VIEW_APPOINTMENTS_PAGE.toString());
        return modelAndView;
    }
}
