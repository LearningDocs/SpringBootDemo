package com.keepgulp.springbootfileonline.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

    public static String formatSimpleDate(Long timestamp) {
        return sdf.format(new Date(timestamp));
    }
}
