package com.jn.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YearPlus {
	
	public static String year(){
		Calendar calendar = Calendar.getInstance(); 
	    Date date = new Date(System.currentTimeMillis()); 
	    calendar.setTime(date); 
	    calendar.add(Calendar.YEAR, 1); 
	    date = calendar.getTime(); 
	    SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");
	    return form.format(date);
	}
	
}
