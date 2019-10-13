package com.springdemo.batch.utils;

import org.springframework.context.annotation.Description;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Description("Date Utility")
public final class DateUtils {

    private static final String DATABASE_FORMAT = "yyyy-MM-dd";

    /**
     * Method for converting date in string format to java.util.Date object
     *
     * @param strDate given date in string format
     * @return java.util.Date Object
     * @throws ParseException custom parse exception
     */
    public static Date fromString(String strDate) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATABASE_FORMAT);
        return simpleDateFormat.parse(strDate);
    }

    /**
     * Private constructor
     * objects can not be instantiated
     */
    private DateUtils() { }
}
