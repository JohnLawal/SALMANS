package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.models.Appointment;
import edu.mum.cs.salmans.models.HairStyle;
import edu.mum.cs.salmans.models.ServiceTime;
import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.transfer.AppointmentDateAndServiceTimeForVerification;
import edu.mum.cs.salmans.transfer.ResponseTransfer;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {
    @Autowired
    AppointmentServiceImplementation appointmentServiceImplementation;


    @GetMapping(value = {PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL})
    public ModelAndView getNewAppointmentPage() {
        Appointment appointment = new Appointment();
        appointment.setServiceTime(new ServiceTime());
        appointment.setHairStyle(new HairStyle());
        appointment.setHairstylist(new User());
        appointment.setCustomer(new User());

        AppointmentDateAndServiceTimeForVerification appointmentDateAndServiceTimeForVerification = new AppointmentDateAndServiceTimeForVerification();

        List<ServiceTime> serviceTimes = appointmentServiceImplementation.getAllServiceTimes();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(getMakeAppointmentModelMap());
        modelAndView.addObject("appointment", appointment);
        modelAndView.addObject("appointmentDateAndServiceTimeForVerification", appointmentDateAndServiceTimeForVerification);
        modelAndView.addObject("serviceTimes", serviceTimes);

        modelAndView.setViewName(PageFileLocator.CUSTOMER_MAKE_APPOINTMENT_PAGE.toString());
        return modelAndView;
    }

    //the request comes as json
    @PostMapping(PageUrlLocator.CUSTOMER_VERIFY_DATE_URL)
    @ResponseBody
    public ResponseTransfer verifyDateForSelection(@RequestBody AppointmentDateAndServiceTimeForVerification appointmentDateAndServiceTimeForVerification) {
        boolean dateIsAvailable = appointmentServiceImplementation
                .seatIsAvailable(appointmentDateAndServiceTimeForVerification.getAppointmentDate()
                        , appointmentDateAndServiceTimeForVerification.getServiceTime());

        if (dateIsAvailable) {
            return new ResponseTransfer(AppValues.SUCCESS.toString(), "The selected date is available for selection");
        } else {
            return new ResponseTransfer(AppValues.FAILURE.toString(), "The selected date is not available for selection");
        }
    }

    @PostMapping(PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL)
    public String makeAppointment(@Valid @ModelAttribute("appointment") Appointment appointment, BindingResult bindingResult, Model model){
        if (!bindingResult.hasErrors()) {
            try {
                //register the citizen
//                String stateCode = citizen.getState().getStateCode();
//                State stateOfOrigin = concreteStateService.getStateByStateCode(stateCode).get();
//
//                citizen.setState(stateOfOrigin);
//
//                stateOfOrigin.addCitizen(citizen);
//
//                concreteStateService.saveState(stateOfOrigin);

                return AppValues.REDIRECT.val() + PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("creationException", "Failed! An Error Occurred While Trying To Create An Appointment");
                return PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL;
            }
        } else {
            System.out.println(bindingResult.getAllErrors());

            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAllAttributes(getMakeAppointmentModelMap());

            return PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL;
        }
    }

    private Map<String, Object> getMakeAppointmentModelMap() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("pageTitle", "Make An Appointment");
        modelMap.put("pageLinks", AppHelper.customerPageLinks);
        return modelMap;
    }
}
