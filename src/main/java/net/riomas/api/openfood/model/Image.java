package net.riomas.api.openfood.model;

import java.util.List;

public class Image {

	List<String> categories;
	String thumb;
	String medium;
	String large;
	String xlarge;
	
	
	@Override
	public String toString() {
		return "Image [categories=" + categories + ", thumb=" + thumb + ", medium=" + medium + ", large=" + large
				+ ", xlarge=" + xlarge + "]";
	}
	
	
}
