package net.riomas.api.openfood;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProductRepository {

	final static Logger logger = Logger.getLogger(ProductRepository.class);

	public String getAll() {
		try {
			URL url = new URL(OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS);
			
			String jsonData = HttpUtil.doGetHttp(url);
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray jsonArrayProducts = jsonObject.getJSONArray("data");
			JSONObject jsonLinks = jsonObject.getJSONObject("links");
			
			String nextUrl = jsonLinks.getString("next");
			logger.info("nextUrl: "+nextUrl);
			logger.info("results: "+jsonArrayProducts.length());
			for (Object jsonObj: jsonArrayProducts.toList()) {
				logger.debug("jsonObj: "+jsonObj.toString());
			}
				
			return jsonArrayProducts.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	

}
