package edu.mum.cs.salmans.config;

import edu.mum.cs.salmans.utility.PageUrlLocator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessfulLoginRedirect implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect(PageUrlLocator.ADMIN_DASHBOARD_URL);
        } else if(roles.contains("ROLE_HAIRSTYLIST")){
            httpServletResponse.sendRedirect(PageUrlLocator.HAIRSTYLIST_VIEW_APPOINTMENTS_URL);
        } else {
            httpServletResponse.sendRedirect(PageUrlLocator.CUSTOMER_DASHBOARD_URL);
        }

    }
}
