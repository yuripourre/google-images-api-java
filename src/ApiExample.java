import java.io.IOException;
import java.util.List;

import br.com.etyllica.network.FileDownloader;
import br.com.etyllica.network.googleimages.GoogleImagesAPI;
import br.com.etyllica.network.googleimages.api.Restriction;
import br.com.etyllica.network.googleimages.models.GsearchResult;


public class ApiExample {
	
	public static void main(String[] args) throws IOException {
		String query = "couch";
		
		queryFor(query, 10);
	}
	
	private static void queryFor(String query, int quantity) throws IOException {
		
		int count = 0;
		int failed = 0;
		
		int remaining = quantity;
		
		while (remaining > 0) {
			
			int pageFailed = 0;
			int pageCount = 0;
			
			int size = remaining > GoogleImagesAPI.MAX_PAGE_SIZE ? GoogleImagesAPI.MAX_PAGE_SIZE : remaining;
			
			List<GsearchResult> results = GoogleImagesAPI.queryImagesByType(
					count, size, query, Restriction.IMAGE_TYPE_PHOTO);
			
			for(GsearchResult result: results) {
				String url = result.getUrl();
				System.out.println(url);
				
				String ext = extension(url);
				try {
					FileDownloader.downloadAsFile(url, fixQuery(query)+(count+1)+ext);
					pageCount++;
				} catch (IOException e) {
					pageFailed++;
					System.out.println("Failed downloading: "+url);
				}
			}
			
			failed += pageFailed;
			count += pageCount;
			remaining -= pageCount;
			
			pageCount = 0;
			pageFailed = 0;
			
			System.out.println("Remaining: "+remaining);
		}
		
		System.out.println("Finished");
	}
	
	private static String fixQuery(String query) {
		return query.replaceAll(" ", "_");
	}
	
	private static String extension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			//including "."
		    extension = fileName.substring(i);
		}
		
		return extension;
	}
}

