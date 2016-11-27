package net.riomas.api.openfood.model;

import java.util.List;
import java.util.Map;

public class Attributes {

	String name;
	Map<String, String> nameTranslations;
	Long barcode;
	String status;
	String unit;
	Integer quantity;
	Integer portionuality;
	List<Image> images;
	List<Nutrient> nutrients;
	String origins;
	String originTranslations;
	String ingredients;
	Map<String, String> ingredientsTranslations;
	
	@Override
	public String toString() {
		return "Attributes [name=" + name + ", nameTranslations=" + nameTranslations + ", barcode=" + barcode
				+ ", status=" + status + ", unit=" + unit + ", quantity=" + quantity + ", portionuality="
				+ portionuality + ", images=" + images + ", nutrients=" + nutrients + ", origins=" + origins
				+ ", originTranslations=" + originTranslations + ", ingredients=" + ingredients
				+ ", ingredientsTranslations=" + ingredientsTranslations + "]";
	}
	
	
}
