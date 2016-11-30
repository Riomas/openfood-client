package net.riomas.api.openfood.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Attributes {

	String name;
	Map<String, String> nameTranslations;
	Long barcode;
	String status;
	String unit;
	Double quantity;
	Double portionuality;
	List<Image> images;
	List<Nutrient> nutrients;
	String origins;
	Map<String,String> originTranslations;
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

	public Set<String> getNutrientsLanguages() {
		Set<String> languages = new HashSet<>();
		for (Nutrient nutrient: nutrients) {
			languages.addAll(nutrient.nameTranslation.keySet());
		}
		return languages;
	}

	public String getNutrients(String language) {
		StringBuilder strNutrients = new StringBuilder();
		for (Nutrient nutrient: getNutrients()) {
			strNutrients.append(nutrient.nameTranslation.get(language)+' ');
			strNutrients.append(nutrient.unit+' ');
			strNutrients.append(nutrient.perHundred+'\n');
		}
		return strNutrients.toString();
	}

	public List<Nutrient> getNutrients() {
		
		return nutrients;
	}
	
	
}
