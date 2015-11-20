import java.io.IOException;
import java.util.List;

import br.com.etyllica.network.FileDownloader;
import br.com.etyllica.network.googleimages.GoogleImagesAPI;
import br.com.etyllica.network.googleimages.api.Restriction;
import br.com.etyllica.network.googleimages.models.GsearchResult;


public class ApiExample {
	
	public static void main(String[] args) throws IOException {
		String query = "couch";
		
		queryFor(query, 100);
	}
	
	private static void queryFor(String query, int quantity) throws IOException {
		int count = 0;
		queryFor(query, count, quantity);
	}
	
	private static void queryFor(String query, int offset, int total) throws IOException {
		int remaining = total;
		
		while (remaining > 0) {
			int pageCount = 0;
			
			int size = remaining > GoogleImagesAPI.MAX_PAGE_SIZE ? GoogleImagesAPI.MAX_PAGE_SIZE : remaining;
			
			List<GsearchResult> results = GoogleImagesAPI.queryImagesByType(
					offset, size, query, Restriction.IMAGE_TYPE_PHOTO);
			
			if(results.isEmpty()) {
				System.err.println("Out of range");
				break;
			}
			
			for(GsearchResult result: results) {
				String url = result.getUrl();
				
				String ext = extension(url);
				if(ext.length() > 5) {
					continue;
				}
				try {
					System.out.println(url);
					FileDownloader.downloadAsFile(url, fixQuery(query)+(offset+pageCount+1)+ext);
					pageCount++;
				} catch (IOException e) {
					//Try again
				}
			}
			
			offset += pageCount;
			remaining -= pageCount;
		}
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
		    
		    if (extension.contains("?")) {
			    extension.substring(0, extension.indexOf('?'));
		    }
		}
		
		return extension;
	}
}

