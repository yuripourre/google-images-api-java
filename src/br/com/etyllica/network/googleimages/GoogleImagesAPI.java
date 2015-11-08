package br.com.etyllica.network.googleimages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import br.com.etyllica.network.googleimages.api.Restriction;
import br.com.etyllica.network.googleimages.models.GoogleImageResponse;
import br.com.etyllica.network.googleimages.models.GsearchResult;

import com.google.gson.Gson;

public class GoogleImagesAPI {

	private static final String GOOGLE_IMAGES_ENDPOINT = "https://ajax.googleapis.com/ajax/services/search/images?";
	public static final int FIRST_PAGE = 0;
	public static final int MAX_PAGE_SIZE = 8;
	
	public static List<GsearchResult> queryImages(String query) throws IOException {
		return requestResults(query, FIRST_PAGE, MAX_PAGE_SIZE);
	}

	public static List<GsearchResult> queryImages(int page, int pageSize, String query) throws IOException {
		return requestResults(query, page, pageSize);
	}
	
	public static List<GsearchResult> queryImagesByType(int page, int pageSize, String query, String imageType) throws IOException {
		return requestResultsWithParams(query, page, pageSize, "&"+Restriction.PARAM_IMAGE_TYPE+"="+imageType);
	}
	
	private static String requestJson(String query, int page, int pageSize)
			throws MalformedURLException, IOException {
		int start = page*pageSize;
		
		String fixedQuery = fixQuery(query);
		
		URL url = new URL(GOOGLE_IMAGES_ENDPOINT+"v=1.0&start="+start+"&rsz="+pageSize+"&q="+fixedQuery);
		URLConnection connection = url.openConnection();

		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((line = reader.readLine()) != null) {
			builder.append(line);
		}

		String json = builder.toString();
		return json;
	}
	
	private static String fixQuery(String query) {
		return query.replaceAll(" ", "%20");
	}

	public static List<GsearchResult> requestResults(String query, int page, int pageSize)
			throws MalformedURLException, IOException {
		String json = requestJson(query, page, pageSize);
		
		GoogleImageResponse response = new Gson().fromJson(json, GoogleImageResponse.class);
		return response.getResponseData().getResults();
	}
	
	private static List<GsearchResult> requestResultsWithParams(String query, int page, int pageSize, String extraParams)
			throws MalformedURLException, IOException {
		
		return requestResults(query+extraParams, page, pageSize);
	}
	
}
