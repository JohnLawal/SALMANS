package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.models.HairStyle;
import edu.mum.cs.salmans.models.ServiceTime;
import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.HairStyleServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.UserServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HairstylistController {
    private AppointmentServiceImplementation appointmentServiceImplementation;
    private UserServiceImplementation userServiceImplementation;
//    private HairStyleServiceImplementation hairStyleServiceImplementation;

    public HairstylistController(AppointmentServiceImplementation appointmentServiceImplementation, UserServiceImplementation userServiceImplementation, HairStyleServiceImplementation hairStyleServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
//        this.hairStyleServiceImplementation = hairStyleServiceImplementation;
    }


    @GetMapping(value = {PageUrlLocator.HAIRSTYLIST_VIEW_APPOINTMENTS_URL})
    public ModelAndView displayHairstylistsAppointments(@RequestParam(defaultValue = "0") int page, Principal principal) {
        String userEmail = principal.getName();
        User hairstylist = userServiceImplementation.getUserByEmail(userEmail).get();
        ModelAndView modelAndView = getDefaultHairstylistModelAndView(hairstylist
                , "List of Your Appointments"
                , PageFileLocator.HAIRSTYLIST_VIEW_APPOINTMENTS_PAGE.toString());

        modelAndView.addObject("appointments", appointmentServiceImplementation.getAllAppointmentsBookedForHairstylistPaged(hairstylist, page));
        modelAndView.addObject("currentPageNo", page);

        return modelAndView;
    }

    private ModelAndView getDefaultHairstylistModelAndView(User user, String pageTitle, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(getHairstylistModelMap(user, pageTitle));
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    private Map<String, Object> getHairstylistModelMap(User user, String pageTitle) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("pageTitle", pageTitle);
        modelMap.put("pageLinks", AppHelper.hairstylistPageLinks);
        modelMap.put("user", user);

        return modelMap;
    }


}
