package common.FnlHelpers;

public class HelperDate {
    public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        if(date != null) return new java.sql.Date(date.getTime());
        else return null;
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date date) {
        if(date != null) return new java.util.Date(date.getTime());
        else return null;
    }
}
