package edu.mum.cs.salmans.utility;

public class PageUrlLocator {
    // PUBLIC
    public static final String HOME_URL = "/salmans/home";
    public static final String LOGIN_URL = "/salmans/login";
    public static final String LOGOUT_URL = "/salmans/logout";
    public static final String SIGN_UP_URL = "/salmans/sign_up";
    //    ADMIN
    public static final String ADMIN_DASHBOARD_URL = "/salmans/admin/dashboard";
    public static final String ADMIN_ADD_HAIRSTYLE_URL = "/salmans/admin/add_hairstyle";
    public static final String ADMIN_REGISTER_HAIRSTYLIST_URL = "/salmans/admin/register_hairstylist";
    public static final String ADMIN_CREATE_TIMETABLE_URL = "/salmans/admin/create_timetable";
    public static final String ADMIN_VIEW_APPOINTMENTS_URL = "/salmans/admin/view_appointments";
    public static final String ADMIN_VIEW_BUSINESS_PERFORMANCE_URL = "/salmans/admin/view_business_performance";
    //    HAIRSTYLIST
    public static final String HAIRSTYLIST_VIEW_APPOINTMENTS_URL = "/salmans/hairstylist/view_appointments";
    //    CUSTOMER
    public static final String CUSTOMER_DASHBOARD_URL = "/salmans/customer/dashboard";
    public static final String CUSTOMER_VIEW_APPOINTMENTS_URL = "/salmans/customer/view_appointments";
    public static final String CUSTOMER_MAKE_APPOINTMENT_URL = "/salmans/customer/make_appointment";
    public static final String CUSTOMER_CANCEL_APPOINTMENT_URL = "/salmans/customer/cancel_appointment/{appointmentId}";
    public static final String CUSTOMER_MAKE_REVIEW_URL = "/salmans/customer/make_review";

    ///AJAX CALLS
    public static final String CUSTOMER_VERIFY_DATE_URL = "/salmans/customer/verify_date";


    //home pages
    public static final String HOME_ABOUT_URL = "/salmans/customer/dashboard";
    public static final String HOME_CONTACT_URL = "/salmans/customer/view_appointments";
    public static final String HOME_APPOINTMENT_URL = "/salmans/customer/make_appointment";
}
