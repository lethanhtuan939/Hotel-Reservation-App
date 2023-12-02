package com.hotelreservationapp.utils;

import java.util.regex.Pattern;

public class Constant {
    public static final String PRE_FIX = "http://192.168.157.216:8080/api/v1";
    public static boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static final String STATUS_PENDING = "ĐANG CHỜ";
    public static final String STATUS_COMPLETED = "ĐÃ XONG";
    public static final String STATUS_CANCELED = "ĐÃ HỦY";
    public static final String STATUS_ACCEPTED = "ĐÃ CHẤP NHẬN";
    public static final String STATUS_DONE = "ĐÃ XONG";
}
