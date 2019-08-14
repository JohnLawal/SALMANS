package edu.mum.cs.salmans.controllers;

import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.PageFileLocator;
import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class AuthenticationController {

    @RequestMapping(value= PageUrlLocator.LOGIN_URL)
    public String displayLogin(Model model) {
        model.addAttribute("page_links", AppHelper.publicPageLinks);
        model.addAttribute("now", LocalDate.now());
        return PageFileLocator.LOGIN_PAGE.toString();
    }

    @RequestMapping(value= PageUrlLocator.HOME_URL)
    public String displayHome(Model model) {
        model.addAttribute("page_links", AppHelper.publicPageLinks);
        model.addAttribute("now", LocalDate.now());
        return PageFileLocator.HOME_PAGE.toString();
    }
}
