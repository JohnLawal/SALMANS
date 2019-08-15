package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.service.UserService;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeController {

    private UserService userService;
    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value= PageUrlLocator.HOME_URL)
    public String displayHome(Model model) {
        model.addAttribute("page_links", AppHelper.publicPageLinks);
        model.addAttribute("now", LocalDate.now());
        return PageFileLocator.HOME_PAGE.toString();
    }

    @RequestMapping(value= PageUrlLocator.HOME_ABOUT_URL)
    public String displayAbout(Model model) {
        model.addAttribute("page_links", AppHelper.publicPageLinks);
        model.addAttribute("now", LocalDate.now());
        return PageFileLocator.ABOUT_PAGE.toString();
    }


    @RequestMapping(value= PageUrlLocator.LOGIN_URL)
    public String displayLogin(Model model) {
        model.addAttribute("page_links", AppHelper.publicPageLinks);
        model.addAttribute("now", LocalDate.now());
        return PageFileLocator.LOGIN_PAGE.toString();
    }


    @GetMapping(value = PageUrlLocator.SIGN_UP_URL)
    public String displayRegisterCustomerForm(Model model) {
        model.addAttribute("page_links", AppHelper.publicPageLinks);
        model.addAttribute("customer", new User());
        model.addAttribute("now", LocalDate.now());
        return PageFileLocator.SIGN_UP_PAGE.toString();
    }

    @PostMapping(value = PageUrlLocator.SIGN_UP_URL)
    public String registerNewCustomer(@Valid @ModelAttribute("customer") User customer,
                                      BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByEmail(customer.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", null,
                    "There is already an account registered with that email");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("page_links", AppHelper.publicPageLinks);
            return PageFileLocator.SIGN_UP_PAGE.toString();
        }
        try {
            customer = userService.saveCustomer(customer);
        } catch (RoleNotFoundException e) {
            model.addAttribute("errors", "Customer role not found");
            model.addAttribute("page_links", AppHelper.publicPageLinks);
            return PageFileLocator.SIGN_UP_PAGE.toString();
        }
        return "redirect:" + PageFileLocator.CUSTOMER_DASHBOARD_PAGE;
    }



}
