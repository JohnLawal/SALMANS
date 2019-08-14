package edu.mum.cs.salmans.utility;

public enum AppValues {
    SITE_ROOT("home"),
    USERS_SORT_BY("fullName"),
    APPOINTMENT_SORT_BY("dateScheduled"),
    ENTRIES_PER_PAGE(5),
    SITE_NAME("salmans"),
    REDIRECT("redirect:"),
    SUCCESS("success"),
    FAILURE("failure"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_HAIRSTYLIST("ROLE_HAIRSTYLIST"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");


    private String val;
    private int iVal;

    AppValues(String string) {
        this.val = string;
    }

    AppValues(int iVal) {
        this.iVal = iVal;
    }

    public String val(String... appendable) {
        StringBuilder value = new StringBuilder(this.val);
        for (String string : appendable) {
            value.append("/").append(string);
        }
        return value.toString();
    }

    public int iVal() {
        return this.iVal;
    }
}
