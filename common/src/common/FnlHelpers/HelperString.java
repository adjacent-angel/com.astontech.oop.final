package common.FnlHelpers;

public class HelperString {
    // checks string if null or empty, returns true.
    //otherwise, returns false
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

}
