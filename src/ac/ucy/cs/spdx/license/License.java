package ac.ucy.cs.spdx.license;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ac.ucy.cs.spdx.graph.LicenseNode;

/**
 * This is License object class. It consists of several constructors and methods
 * for editing and accessing data. The object consists of the license name,
 * identifier and text.
 * 
 * @author dpasch01
 * @author gkapi
 * 
 */
public class License {
	public enum Category {
		PERMISSIVE, WEAK_COPYLEFT, STRONG_COPYLEFT, UNCATEGORIZED
	};

	private String licenseName;
	private String identifier;
	private String licenseText;
	private Category category = Category.UNCATEGORIZED;
	private static boolean error;
	private static HashSet<License> licenses = new HashSet<License>();

	/**
	 * License constructor that creates a new License based on the license name,
	 * identifier and File containing the License text that are passed as
	 * parameter. You can enter the category of the license but it is optional.
	 * 
	 * @param String
	 * @param String
	 * @param File
	 * @param Category
	 */
	public License(String licenseName, String identifier, File licenseText,
			Category... category) {
		this.setLicenseName(licenseName);
		this.setIdentifier(identifier);
		if (category.length != 0)
			this.setCategory(category[0]);
		String text = null;

		try {
			text = new String(Files.readAllBytes(Paths.get(licenseText
					.getAbsolutePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setLicenseText(WordUtils.wrap(text, 80));
		licenses.add(this);
	}

	/**
	 * License constructor that creates a new License object based on the
	 * license url and also the category parameters, which is optional.
	 * 
	 * @param String
	 * @param Category
	 */
	public License(String url, Category... category) throws IOException {
		if (category.length != 0)
			this.setCategory(category[0]);
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Element fullName = doc.getElementsByAttributeValue("property",
				"rdfs:label").get(0);

		this.setLicenseName(fullName.text());

		Element identifier = doc.getElementsByAttributeValue("property",
				"spdx:licenseId").get(0);
		this.setIdentifier(identifier.text());

		Element licenseText = doc.getElementsByAttributeValue("property",
				"spdx:licenseText").get(0);
		this.setLicenseText(WordUtils.wrap(licenseText.text(), 80));
		licenses.add(this);
		saveLicense(this);
	}
	
	/**
	 * Saves the License passed as parameter in a text file inside the standard
	 * directory.
	 * 
	 * @param License
	 */
	public static void saveLicense(License l) {
		File text = new File("licensesText/" + l.getIdentifier() + ".txt");
		if (text.exists())
			return;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			text.createNewFile();
			fw = new FileWriter(text);
			bw = new BufferedWriter(fw);
			bw.write(l.getLicenseName() + "\n");
			bw.write(l.getIdentifier() + "\n");
			bw.write(l.getLicenseText());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
			}

		}

	}

	/**
	 * Loads all the licenses that are saved in the standard directory into
	 * License objects.
	 * 
	 */
	public static void loadLicenses() {
		File licensesText = new File("licensesText/");
		if (licensesText.isDirectory()) {
			File[] files = licensesText.listFiles();
			for (File f : files) {
				FileReader fr;
				try {
					fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String lName = br.readLine();
					String lIdentifier = br.readLine();
					if (licenseExists(lIdentifier))
						continue;
					new License(lName, lIdentifier, f);
					br.close();
					fr.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Returns true if the license based on the identifier passed as parameter
	 * exists in the licenses list. Returns false otherwise.
	 * 
	 * @param String
	 * @return boolean
	 */
	private static boolean licenseExists(String lIdentifier) {
		License license = null;
		for (License l : licenses) {
			if (l.getIdentifier().equals(lIdentifier))
				license = l;
		}
		if (license != null)
			return true;
		return false;
	}

	/**
	 * Getter for the License list.
	 * 
	 * @param ArrayList<License>
	 */
	public static ArrayList<License> getLicenses() {
		return new ArrayList<License>(licenses);
	}

	/**
	 * Search the license list to find the license specified by the license
	 * identifier passed as parameter. Returns that license if so or null.
	 * 
	 * @param String
	 * @return License
	 */
	public static License findLicense(String licenseIdentifier) {
		License license = null;
		for (License l : licenses) {
			if (l.getIdentifier().equals(licenseIdentifier))
				license = l;
		}
		if (license == null) {
			try {
				new License(findLicenseUrl(licenseIdentifier));
			} catch (IOException e) {
				if (!error) {
					System.err.println(licenseIdentifier + " not found");
					error = !error;
				}
			}
		}
		return license;
	}

	/**
	 * Finds and returns the url for the specified License by the license
	 * identifier passed as parameter.
	 * 
	 * @param String
	 * @return Strings
	 */
	public static String findLicenseUrl(String code) {
		return "http://www.spdx.org/licenses/" + code + "#licenseText";
	}

	/**
	 * Removes the License object specified by the License Identifier passed as
	 * parameter.
	 * 
	 * @param String
	 */
	public static void removeLicense(String licenseIdentifier)
			throws IOException {
		for (LicenseNode ln : LicenseNode.getLicenseNodes())
			if (ln.getLicenses().contains(findLicense(licenseIdentifier))) {
				System.err
						.println("Cannot execute remove. One or more License Nodes contain this license:"
								+ licenseIdentifier);
				throw new IOException();
			}
		licenses.remove(findLicense(licenseIdentifier));
	}

	/**
	 * The toString() method implementation for License object.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return this.getIdentifier();
	}

	/**
	 * Getter for the License name.
	 * 
	 * @return String
	 */
	public String getLicenseName() {
		return licenseName;
	}

	/**
	 * Setter for the License name.
	 * 
	 * @param String
	 */
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	/**
	 * Getter for the License text.
	 * 
	 * @return String
	 */
	public String getLicenseText() {
		return licenseText;
	}

	/**
	 * Setter for the License text.
	 * 
	 * @param String
	 */
	public void setLicenseText(String licenseText) {
		this.licenseText = licenseText;
	}

	/**
	 * Getter for the License identifier.
	 * 
	 * @return String
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Setter for the License identifier.
	 * 
	 * @param String
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * Getter for the License Category.
	 * 
	 * @return Category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Setter for the License Category.
	 * 
	 * @param Category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
