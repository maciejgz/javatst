package pl.mg.javatst.unba;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {



public static void main(String[] args) {
	try {
		// data formater
		SimpleDateFormat sdf = new SimpleDateFormat("");
		// data formats (for sdf)
		String DATE_FORMAT_UNBA = "HH:mm yyyy-MM-dd";
		String DATE_FORMAT_VO   = "dd-MM-yyyy-HH-mm";
		
		String attrValue = "09:44 2007-06-21";
		// convertion of date format UNBA -> VO
		System.out.println("Date (XML): "+attrValue);
		sdf.applyPattern(DATE_FORMAT_UNBA);
		Date tmp = sdf.parse(attrValue);
		System.out.println("Date (DATE_FORMAT_UNBA): "+tmp.toString());	        		
		sdf.applyPattern(DATE_FORMAT_VO);	        		
		attrValue = sdf.format(tmp);
		System.out.println("Date (DATE_FORMAT_VO): "+attrValue);
	}
	catch (Exception ex){
		ex.printStackTrace();
	}
	
}

}