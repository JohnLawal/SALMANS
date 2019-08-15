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


}
