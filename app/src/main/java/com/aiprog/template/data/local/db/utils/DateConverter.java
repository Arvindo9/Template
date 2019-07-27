package com.aiprog.template.data.local.db.utils;


import androidx.room.TypeConverter;

import java.util.Date;


/**
 * Author       : Arvindo Mondal
 * Created on   : 17-01-2019
 * Email        : arvindomondal@gmail.com
 * Designation  : Programmer
 * Project URL  :
 */
public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
