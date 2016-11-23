package net.riomas.api.openfood;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {
	final static Logger logger = Logger.getLogger(HttpUtil.class);

	public static String doGetHttp(URL url) throws IOException, URISyntaxException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String responseBody = null;
		try {
			HttpGet httpget = new HttpGet(url.toURI());

			logger.info("Executing request " + httpget.getRequestLine());

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
			logger.info("----------------------------------------");
			logger.info(responseBody);
		} finally {
			httpclient.close();
		}
		return responseBody;
	}
}
