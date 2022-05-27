package com.weather.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class Util {
	private static String dateFormat="yyyy-mm-dd";
	
	public final static String  WIND_SPEED_KEY ="wind_spd";
	public final static String  TEMP_KEY ="temp";
	public final static String  VALID_DATE_KEY ="valid_date";
	public final static String  CITY_NAME_KEY ="city_name";
	public final static String  LAT_KEY ="lat";
	public final static String  LON_KEY ="lon";
	
	public static boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
