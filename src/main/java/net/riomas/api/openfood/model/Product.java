package net.riomas.api.openfood.model;

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
	
	
}
