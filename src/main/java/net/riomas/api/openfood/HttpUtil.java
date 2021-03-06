package net.riomas.api.openfood;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {
	final static Logger logger = Logger.getLogger(HttpUtil.class);

	public static String doGetHttp(URL url)
			throws IOException, URISyntaxException, KeyManagementException, CertificateException {

		CloseableHttpClient httpclient = getHttpsClient();
		String responseBody = null;
		try {
			HttpGet httpget = new HttpGet(url.toURI());

			if (logger.isDebugEnabled()) {
				logger.debug("Executing request " + httpget.getRequestLine());
			}

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			responseBody = httpclient.execute(httpget, responseHandler);
			if (logger.isDebugEnabled()) {
				logger.debug("----------------------------------------");
				logger.debug(responseBody);
			}
		} finally {
			httpclient.close();
		}
		return responseBody;
	}

	public static String doCreateHttp(URL url, String strEntity)
			throws IOException, URISyntaxException, KeyManagementException, CertificateException {

		CloseableHttpClient httpclient = getHttpsClient();
		String responseBody = null;
		try {

			
			HttpPost httpPost = new HttpPost(url.toURI());

			if (strEntity != null) {
				StringEntity entity = new StringEntity(strEntity, ContentType.APPLICATION_JSON);
				entity.setChunked(true);
				httpPost.setEntity(entity);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing request " + httpPost.getRequestLine());
			}

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};

			httpPost.setHeader("Authorization", "token=DyRvF9Rb75-UGozqmXTc, email=email@openfood.com");
			responseBody = httpclient.execute(httpPost, responseHandler);
			if (logger.isDebugEnabled()) {
				logger.debug("----------------------------------------");
				logger.debug(responseBody);
			}
		} finally {
			httpclient.close();
		}
		return responseBody;
	}

	
	public static String doUpdateHttp(URL url, String strEntity)
			throws IOException, URISyntaxException, KeyManagementException, CertificateException {

		CloseableHttpClient httpclient = getHttpsClient();
		String responseBody = null;
		try {

			HttpPut httppatch = new HttpPut(url.toURI());

			if (strEntity != null) {
				StringEntity entity = new StringEntity(strEntity, ContentType.APPLICATION_JSON);
				entity.setChunked(true);
				httppatch.setEntity(entity);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Executing request " + httppatch.getRequestLine());
			}

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};

			responseBody = httpclient.execute(httppatch, responseHandler);
			if (logger.isDebugEnabled()) {
				logger.debug("----------------------------------------");
				logger.debug(responseBody);
			}
		} finally {
			httpclient.close();
		}
		return responseBody;
	}

	public static String doDeleteHttp(URL url)
			throws IOException, URISyntaxException, KeyManagementException, CertificateException {

		CloseableHttpClient httpclient = getHttpsClient();
		String responseBody = null;
		try {
			HttpDelete httpDelete = new HttpDelete(url.toURI());

			if (logger.isDebugEnabled()) {
				logger.debug("Executing request " + httpDelete.getRequestLine());
			}

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						return String.valueOf(status);
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			responseBody = httpclient.execute(httpDelete, responseHandler);
			if (logger.isDebugEnabled()) {
				logger.debug("----------------------------------------");
				logger.debug("Delete response code: "+responseBody);
			}
		} finally {
			httpclient.close();
		}
		return responseBody;
	}

	private static CloseableHttpClient getHttpsClient()
			throws KeyManagementException, CertificateException, IOException {

		try {

			logger.debug("keystore: " + new File("store/keystore.jks").getAbsolutePath());

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new File("store/keystore.jks"),
					"nopassword".toCharArray(), new TrustSelfSignedStrategy()).build();

			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();

		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmEx", e);
		} catch (KeyStoreException e) {
			logger.error("KeyStoreException", e);
		}

		return HttpClients.createDefault();
	}

	public static String addParameter(String strUrl, String name, String value) {
		if (strUrl.indexOf("?") >= 0) {
			return strUrl + "&" + name + "=" + value;
		}
		return strUrl + "?" + name + "=" + value;
	}

}
