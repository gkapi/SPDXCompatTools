package ac.ucy.cs.spdx.license;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This is HTML Extractor class and it is used to download and store in local
 * storage the License .html page for offline accessing.
 * 
 * @author dpasch01
 * 
 */
public class HTMLExtractor {

	private static final String SAVE_LOCATION = "/resources/stdlicense";
	
	/**
	 * Connects on the url passed as parameter with connect() method of jsoup
	 * library and returns the contents of the html file.
	 * 
	 * @param String
	 * @return StringBuilder
	 */
	public static StringBuilder fetchHTMLContent(String url) throws IOException {
		StringBuilder xmlContent = new StringBuilder();
		Document document = Jsoup.connect(url).get();
		xmlContent.append(document.body().html());
		return xmlContent;
	}

	/**
	 * Saves the content of the html file that was fetched using
	 * fetchHTMLContent() in a new file and store it locally.
	 * 
	 * @param StringBuilder
	 * @param String
	 */
	public static void saveHTMLFile(StringBuilder xmlContent,
			String saveLocation) throws IOException {
		FileWriter fileWriter = new FileWriter(saveLocation);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(xmlContent.toString());
		bufferedWriter.close();
	}

	/**
	 * Combines the fetchHTMLContent() and saveHTMLFile() methods in one method
	 * for more easy usage.
	 * 
	 * @param String
	 */
	public static void downloadHTML(String url) throws IOException {
		String saveLocation = System.getProperty("user.dir") + SAVE_LOCATION;
		HTMLExtractor.saveHTMLFile(HTMLExtractor.fetchHTMLContent(url),
				saveLocation);
	}
}