package net.riomas.api.openfood;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ProductRepository {

	final static Logger logger = Logger.getLogger(ProductRepository.class);

	public List<?> getAll() {
		logger.info("-------------------- getAll ----------------------");
		try {
			URL url = new URL(OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS);
			
			String jsonData = HttpUtil.doGetHttp(url);
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray jsonArrayProducts = jsonObject.getJSONArray("data");
			JSONObject jsonLinks = jsonObject.getJSONObject("links");
			
			String nextUrl = jsonLinks.getString("next");
			if (logger.isDebugEnabled()) {
				logger.debug("nextUrl: "+nextUrl);
			}
			
//			logger.info("results: "+jsonArrayProducts.length());
//			for (Object jsonObj: jsonArrayProducts.toList()) {
//				logger.debug("jsonObj: "+jsonObj.toString());
//			}
			
			List<?> products = new Gson().fromJson(jsonArrayProducts.toString(), List.class);
			if (logger.isDebugEnabled()) {
				logger.debug("product: "+products.size());
				for (Object product: products) {
					logger.debug("product: "+product.toString());
				}
			}
			return products;
			
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
		return new ArrayList<>();
	}

	

}
