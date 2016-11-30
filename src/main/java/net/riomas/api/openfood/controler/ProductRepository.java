package net.riomas.api.openfood.controler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import net.riomas.api.openfood.HttpUtil;
import net.riomas.api.openfood.OpenfoodConstants;
import net.riomas.api.openfood.model.Product;

public class ProductRepository {

	final static Logger logger = Logger.getLogger(ProductRepository.class);

	/**
	 * 
	 * @param pageSize default 1
	 * @param pageNumber default 50 (max is 200)
	 * @return
	 */
	public List<?> fetchProducts(int pageSize, int pageNumber, String language, long[] barcodes) {
		logger.info("-------------------- getAll ----------------------");
		
		try {
			
			String strUrl = OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS;
			
			if (pageSize>0) {
				strUrl = HttpUtil.addParameter(strUrl, "page[size]", String.valueOf(pageSize));	
			}
			if (pageNumber>0) {
				strUrl = HttpUtil.addParameter(strUrl, "page[pageNumber]", String.valueOf(pageNumber));	
			}
			if (language!=null && language.length()==2) {
				strUrl = HttpUtil.addParameter(strUrl, "locale", language);	
			}
			for (long barecode: barcodes) {
				strUrl = HttpUtil.addParameter(strUrl, "barcodes[]", String.valueOf(barecode));
			}
			
			URL url = new URL(strUrl);

			String jsonData = HttpUtil.doGetHttp(url);
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray jsonArrayProducts = jsonObject.getJSONArray("data");
			JSONObject jsonLinks = jsonObject.getJSONObject("links");

			String nextUrl = jsonLinks.getString("next");

			if (logger.isDebugEnabled()) {
				logger.debug("nextUrl: " + nextUrl);
			}

			List<?> products = new Gson().fromJson(jsonArrayProducts.toString(), List.class);
			if (logger.isDebugEnabled()) {
				logger.debug("product: " + products.size());
				for (Object product : products) {
					logger.debug("product: " + product.toString());
				}
			}
			return products;

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} catch (URISyntaxException e) {
			logger.error("URISyntaxException", e);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (CertificateException e) {
			logger.error("CertificateException", e);
		}
		return new ArrayList<>();
	}

	public Product fetchById(long id) {
		logger.info("-------------------- fetchById ----------------------");
		try {
			URL url = new URL(OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS + "/"+id);

			String jsonData = HttpUtil.doGetHttp(url);
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONObject jsonArrayProduct = jsonObject.getJSONObject("data");
			Product product = new Gson().fromJson(jsonArrayProduct.toString(), Product.class);

			return product;

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} catch (URISyntaxException e) {
			logger.error("URISyntaxException", e);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (CertificateException e) {
			logger.error("CertificateException", e);
		}

		return null;
	}

	public Product update(Product product) {
		logger.info("-------------------- update ----------------------");
		try {
			URL url = new URL(OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS + "/"+product.getId());
			String jsonData = HttpUtil.doUpdateHttp(url, new Gson().toJson(product));
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONObject jsonArrayProduct = jsonObject.getJSONObject("data");
			product = new Gson().fromJson(jsonArrayProduct.toString(), Product.class);

			return product;

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} catch (URISyntaxException e) {
			logger.error("URISyntaxException", e);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (CertificateException e) {
			logger.error("CertificateException", e);
		}

		return product;
	}
	
	public Product create(Product product) {
		logger.info("-------------------- create ----------------------");
		try {
			
			String strUrl = OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS;
			
			if (product.getBarcode()>0) {
				strUrl = HttpUtil.addParameter(strUrl, "product[barcode]", String.valueOf( product.getBarcode()));
			}
			
			// product[name_translations][de]: 
			for (String language: product.getLanguages()) {
				strUrl = HttpUtil.addParameter(strUrl, "product[name_translations]["+language+"]", product.getName(language));
			}
			
			// product[ingredients_translations][de]:
			for (String language: product.getIngredientsLanguages()) {
				strUrl = HttpUtil.addParameter(strUrl, "product[ingredients_translations]["+language+"]", product.getIngredients(language));
			}
			
			// product[nutrition_informations_translations][de]:
			for (String language: product.getNutritionInformationLanguages()) {
				strUrl = HttpUtil.addParameter(strUrl, "product[nutrition_informations_translations]["+language+"]", product.getNutritionInformation(language));
			}
			
			// product[origin_translations][de]:
			for (String language: product.getOriginTranslationsLanguages()) {
				strUrl = HttpUtil.addParameter(strUrl, "product[origin_translations]["+language+"]", product.getOrigin(language));
			}
			// product[weight_g]:
			if (product.getUnity().equals("g")) {
				strUrl = HttpUtil.addParameter(strUrl, "product[weight_g]", String.valueOf( product.getWeight()));
			}
			// product[volume_ml]:
			else if (product.getUnity().equals("ml")) {
				strUrl = HttpUtil.addParameter(strUrl, "product[volume_ml]", String.valueOf( product.getVolume()));
			}
			
			// product[images_attributes][\"unique_identifier"\][data]: (file path)
			
			// product[product_nutrients_attributes][\"unique_identifier"\][nutrient_id]: required 
			
			// product[product_nutrients_attributes][\"unique_identifier"\][per_day]:
			
			// product[product_nutrients_attributes][\"unique_identifier"\][per_hundred]:
			
			// product[product_nutrients_attributes][\"unique_identifier"\][per_portion]:
			
			
			URL url = new URL(strUrl);
			
			//URL url = new URL(OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS + "/"+product.getId());
			String jsonData = HttpUtil.doUpdateHttp(url, new Gson().toJson(product));
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONObject jsonArrayProduct = jsonObject.getJSONObject("data");
			product = new Gson().fromJson(jsonArrayProduct.toString(), Product.class);

			return product;

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} catch (URISyntaxException e) {
			logger.error("URISyntaxException", e);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (CertificateException e) {
			logger.error("CertificateException", e);
		}

		return product;
	}
	
	public String delete(long id) {
		logger.info("-------------------- delete ----------------------");
		try {
			URL url = new URL(OpenfoodConstants.HOST + OpenfoodConstants.API_V1 + OpenfoodConstants.PRODUCTS + "/"+id);
			
			return HttpUtil.doDeleteHttp(url);

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} catch (URISyntaxException e) {
			logger.error("URISyntaxException", e);
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException", e);
		} catch (CertificateException e) {
			logger.error("CertificateException", e);
		}

		return null;
	}
}
