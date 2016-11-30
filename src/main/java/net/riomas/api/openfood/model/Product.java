package net.riomas.api.openfood.model;

import java.util.Set;

/**
 * 
 * @author Mario
 *
 */
public class Product {
	
	long id;
	String type;
	Attributes attributes;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + ", attributes=" + attributes + "]";
	}

	public long getId() {
		return id;
	}

	public long getBarcode() {
		return attributes.barcode;
	}

	public Set<String> getLanguages() {
		return attributes.nameTranslations.keySet();
	}

	public String getName() {
		return attributes.name;
	}
	
	public String getName(String language) {
		return attributes.nameTranslations.get(language);
	}

	public Set<String> getIngredientsLanguages() {
		return attributes.ingredientsTranslations.keySet();
	}
	
	public String getIngredients(String language) {
		return attributes.ingredientsTranslations.get(language);
	}
	
	public Set<String> getNutritionInformationLanguages() {
		return attributes.getNutrientsLanguages();
	}
	
	public String getNutritionInformation(String language) {
		return attributes.getNutrients(language);
	}

	public Set<String> getOriginTranslationsLanguages() {
		return attributes.originTranslations.keySet();
	}

	public String getOrigin(String language) {
		return attributes.originTranslations.get(language);
	}

	public Double getWeight() {
		return attributes.quantity;
	}
	
	public Double getVolume() {
		return attributes.quantity;
	}

	public String getUnity() {
		return attributes.unit;
	}
}
