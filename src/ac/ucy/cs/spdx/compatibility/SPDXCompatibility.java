package ac.ucy.cs.spdx.compatibility;

import java.util.ArrayList;
import java.util.HashSet;
import org.spdx.rdfparser.InvalidSPDXAnalysisException;
import org.spdx.rdfparser.SPDXDocument;

import ac.ucy.cs.spdx.common.LicenseUtils;
import ac.ucy.cs.spdx.conflict.LicensePairCompatibilityError;
import ac.ucy.cs.spdx.conflict.ViolationAnalysisInfo;

/**
 * This is SPDX Compatibility class. Using its methods the user can check
 * multiple .rdf files for compatibility issues and receive possible solutions.
 * 
 * @author dpasch01
 * 
 */
public class SPDXCompatibility {
	// This is a List of all the license identifiers found in the SPDXDocuments.
	private static ArrayList<String> licenses = new ArrayList<>();

	/**
	 * It checks the SPDX documents passed as parameters for any
	 * LicenseViolations in each one and as a whole providing any possible
	 * solutions for any errors and whether or not it is possible for combining
	 * these SPDX documents.
	 * 
	 * @param SPDXDocument
	 *            []
	 * @return ArrayList<SPDXCompatibilityError>
	 */
	public static ArrayList<SPDXCompatibilityError> areCompatible(
			SPDXDocument... documents) throws InvalidSPDXAnalysisException {
		LicensePairCompatibilityError.clear();
		HashSet<SPDXCompatibilityError> spdxErrors = new HashSet<>();
		setLicenses(getLicenses(documents));

		for (SPDXDocument doc : documents) {
			ViolationAnalysisInfo vai = LicenseUtils.checkForViolation(doc);
			spdxErrors.add(new SPDXCompatibilityError(vai, doc));
			if (!LicenseUtils.isDeclarationCorrect(doc)) {
				System.err
						.println("The "
								+ doc.getSpdxPackage().getFileName()
								+ " file is not declared correctly.\nPlease check included licenses to be compatible with declared ones.");
				System.exit(-1);
			}
		}
		SPDXCompatibilityError.setErrors(LicenseUtils.areCompatible(licenses));
		SPDXCompatibilityError.printErrorMessage();
		return new ArrayList<>(spdxErrors);
	}

	/**
	 * It returns an ArrayList of the License Identifiers that the SPDX
	 * documents passed as parameters are containing.
	 * 
	 * @param SPDXDocument
	 *            []
	 * @return ArrayList<String>
	 */
	private static ArrayList<String> getLicenses(SPDXDocument... documents)
			throws InvalidSPDXAnalysisException {
		HashSet<String> licenses = new HashSet<>();
		for (SPDXDocument doc : documents) {
			licenses.addAll(LicenseUtils.getLicenses(doc.getSpdxPackage()
					.getLicenseInfoFromFiles()));
		}
		return new ArrayList<>(licenses);
	}

	/**
	 * It checks the SPDX documents passed as parameters for any
	 * LicenseViolations in each one and as a whole providing any possible
	 * solutions for any errors and whether or not it is possible for combining
	 * these SPDX documents.
	 * 
	 * @param SPDXDocument
	 *            []
	 * @return ArrayList<SPDXCompatibilityError>
	 */
	public static ArrayList<String> getLicenses() {
		return licenses;
	}

	/**
	 * This is a setter for the licenses ArrayList in SPDXCompatibility class.
	 * 
	 * @param ArrayList
	 *            <String>
	 */
	public static void setLicenses(ArrayList<String> licenses) {
		SPDXCompatibility.licenses = licenses;
	}

}
