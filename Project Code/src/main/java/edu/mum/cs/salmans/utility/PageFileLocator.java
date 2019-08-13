package edu.mum.cs.salmans.utility;

public enum PageFileLocator {
    // PUBLIC
    HOME_PAGE("public/home/index"),
    LOGIN_PAGE("public/auth/login"),
    LOGOUT_PAGE("public/auth/logout"),
    SIGN_UP_PAGE("public/auth/sign_up"),
    //    ADMIN
    ADMIN_DASHBOARD_PAGE("private/admin/dashboard"),
    ADMIN_ADD_HAIRSTYLE_PAGE("private/admin/add_hairstyle"),
    ADMIN_REGISTER_HAIRSTYLIST_PAGE("private/admin/register_hairstylist"),
    ADMIN_CREATE_TIMETABLE_PAGE("private/admin/create_timetable"),
    ADMIN_VIEW_APPOINTMENTS_PAGE("private/admin/view_appointments"),
    ADMIN_VIEW_BUSINESS_PERFORMANCE_PAGE("private/admin/view_business_performance"),
    //    HAIRSTYLIST
    HAIRSTYLIST_VIEW_APPOINTMENTS_PAGE("private/hairstylist/view_appointments"),
    //    CUSTOMER
    CUSTOMER_DASHBOARD_PAGE("private/customer/dashboard"),
    CUSTOMER_VIEW_APPOINTMENTS_PAGE("private/customer/view_appointments"),
    CUSTOMER_MAKE_APPOINTMENT_PAGE("private/customer/make_appointment");

    private String val;

    PageFileLocator(String string) {
        this.val = string;
    }

    @Override
    public String toString(){
        return this.val;
    }

}
