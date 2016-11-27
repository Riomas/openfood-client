package net.riomas.api.openfood.model;

import java.util.Map;

public class Nutrient {

	String name;
	Map<String, String> nameTranslation;
	String unit;
	Integer order;
	Double perHundred;
	Double perDay;
	Double perPortion;
	
	
	@Override
	public String toString() {
		return "Nutrient [name=" + name + ", nameTranslation=" + nameTranslation + ", unit=" + unit + ", order=" + order
				+ ", perHundred=" + perHundred + ", perDay=" + perDay + ", perPortion=" + perPortion + "]";
	}
	
	
}
