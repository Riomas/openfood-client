package net.riomas.api.openfood.model;

import java.util.Map;

public class Nutrient implements Comparable<Nutrient>{

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


	@Override
	public int compareTo(Nutrient o) {
		if (order==null) {
			if (o==null) {
				return -1;
			} else if (o.order==null) {
				return 0;
			}
		} else {
			if (o==null || o.order==null) {
				return 1;
			}
			
			return Integer.compare(order, o.order);
		}
		
		return 0;
	}
	
	
}
