package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryMomentUtils {

	public static String dateToString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}
	
}
