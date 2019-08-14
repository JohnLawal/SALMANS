package edu.mum.cs.salmans.utility;

import java.util.HashMap;
import java.util.Map;

public class AppHelper {
    public static final Map<String, String> publicPageLinks = new HashMap<String, String>() {
        {
            put("siteRoot", PageUrlLocator.HOME_URL);
            put("login", PageUrlLocator.LOGIN_URL);
            put("logout", PageUrlLocator.LOGOUT_URL);
            put("signUp", PageUrlLocator.SIGN_UP_URL);
        }
    };

    public static final Map<String, String> adminPageLinks = new HashMap<String, String>() {
        {
            put("siteRoot", PageUrlLocator.HOME_URL);
            put("dashboard", PageUrlLocator.ADMIN_DASHBOARD_URL);
            put("registerHairstyle", PageUrlLocator.ADMIN_ADD_HAIRSTYLE_URL);
            put("registerHairstylist", PageUrlLocator.ADMIN_REGISTER_HAIRSTYLIST_URL);
            put("createTimeTable", PageUrlLocator.ADMIN_CREATE_TIMETABLE_URL);
            put("viewAppointments", PageUrlLocator.ADMIN_VIEW_APPOINTMENTS_URL);
            put("viewBusinessPerformance", PageUrlLocator.ADMIN_VIEW_BUSINESS_PERFORMANCE_URL);
            put("logout", PageUrlLocator.LOGOUT_URL);
        }
    };

    public static final Map<String, String> hairstylistPageLinks = new HashMap<String, String>() {
        {
            put("siteRoot", PageUrlLocator.HOME_URL);
            put("dashboard", PageUrlLocator.HAIRSTYLIST_VIEW_APPOINTMENTS_URL);
            put("logout", PageUrlLocator.LOGOUT_URL);
        }
    };

    public static final Map<String, String> customerPageLinks = new HashMap<String, String>() {
        {
            put("siteRoot", PageUrlLocator.HOME_URL);
            put("dashboard", PageUrlLocator.CUSTOMER_DASHBOARD_URL);
            put("viewAppointments", PageUrlLocator.CUSTOMER_VIEW_APPOINTMENTS_URL);
            put("makeAppointment", PageUrlLocator.CUSTOMER_MAKE_APPOINTMENT_URL);
            put("logout", PageUrlLocator.LOGOUT_URL);
        }
    };

    public static final String[] daysInAWeek =
            new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

}
