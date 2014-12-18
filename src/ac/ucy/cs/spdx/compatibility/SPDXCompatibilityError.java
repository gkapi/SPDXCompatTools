package ac.ucy.cs.spdx.compatibility;

import java.util.ArrayList;
import java.util.HashSet;
import org.spdx.rdfparser.InvalidSPDXAnalysisException;
import org.spdx.rdfparser.SPDXDocument;

import ac.ucy.cs.spdx.common.LicenseUtils;
import ac.ucy.cs.spdx.conflict.LicensePairCompatibilityError;
import ac.ucy.cs.spdx.conflict.ViolationAnalysisInfo;
import ac.ucy.cs.spdx.license.License;

/**
 * This is SPDX Compatibility Error class. It is based on the results of
 * areCompatible() method of SPDXCompatibility class and provides info on
 * whether the .rdf files are compatible and if not it gives the error message
 * and possible solutions on it.
 * 
 * @author dpasch01
 * 
 */
public class SPDXCompatibilityError {
	private ViolationAnalysisInfo error;
	public static HashSet<LicensePairCompatibilityError> errors;
	private SPDXDocument doc;
	public static HashSet<SPDXCompatibilityError> SPDXerrors = new HashSet<>();
	public static HashSet<String> proposals = new HashSet<>();

	/**
	 * Constructor for SPDX Analysis Info.
	 * 
	 * @param ViolationAnalysisInfo
	 * @param SPDXDocument
	 * @return ArrayList<SPDXCompatibilityError>
	 */
	public SPDXCompatibilityError(ViolationAnalysisInfo vai, SPDXDocument doc) {
		this.setDoc(doc);
		this.setError(vai);
		SPDXerrors.add(this);
	}

	/**
	 * Getter for the SPDX Compatibility Errors list.
	 * 
	 * @return ArrayList<SPDXCompatibilityError>
	 */
	public static ArrayList<SPDXCompatibilityError> getSPDXErrors() {
		return new ArrayList<>(SPDXerrors);
	}

	/**
	 * Getter for the SPDX Document.
	 * 
	 * @return SPDXDocument
	 */
	public SPDXDocument getDoc() {
		return doc;
	}

	/**
	 * Setter for the SPDX Document.
	 * 
	 * @param SPDXDocument
	 */
	public void setDoc(SPDXDocument doc) {
		this.doc = doc;
	}

	/**
	 * Getter for the License Pair Compatibility Errors list.
	 * 
	 * @return ArrayList<LicensePairCompatibilityError>
	 */
	public ArrayList<LicensePairCompatibilityError> getErrors() {
		return new ArrayList<>(errors);
	}

	/**
	 * Setter for the License Pair Compatibility Errors list.
	 * 
	 * @param ArrayList
	 *            <LicensePairCompatibilityError>
	 */
	public static void setErrors(ArrayList<LicensePairCompatibilityError> err) {
		errors = new HashSet<>(err);
	}

	/**
	 * It checks each SPDX Compatibility Error whether or not the problems found
	 * are adjustable..
	 * 
	 * @return boolean
	 */
	public static boolean isAdjustable() throws InvalidSPDXAnalysisException {
		boolean adj = true;
		HashSet<String> licenses = new HashSet<>();

		for (SPDXCompatibilityError e : SPDXerrors) {
			adj = e.getError().isAdjustFeasible();
			if (!adj)
				return false;
			licenses.addAll(LicenseUtils.getLicenses(e.getDoc()
					.getSpdxPackage().getLicenseInfoFromFiles()));
		}
		return LicensePairCompatibilityError.isAdjustable(LicenseUtils
				.areCompatible(new ArrayList<>(licenses)));
	}

	/**
	 * Getter for the Violation Analysis Info.
	 * 
	 * @return ViolationAnalysisInfo
	 */
	public ViolationAnalysisInfo getError() {
		return error;
	}

	/**
	 * Setter for the Violation Analysis Info.
	 * 
	 * @param ViolationAnalysisInfo
	 */
	public void setError(ViolationAnalysisInfo error) {
		this.error = error;
	}

	/**
	 * Getter for the possible proposals for the SPDX Compatibility Errors list.
	 * 
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getProposals() {
		HashSet<String> proposals = new HashSet<>();
		for (LicensePairCompatibilityError e : errors)
			for (License l : e.getElementProposals())
				proposals.add(l.getIdentifier());
		return new ArrayList<>(proposals);
	}

	/**
	 * It prints the message containing the errors, warnings and possible
	 * adjustments the can be made in order to solve the problems.
	 * 
	 */
	public static void printErrorMessage() {
		String s = "";
		for (SPDXCompatibilityError e : SPDXCompatibilityError.SPDXerrors) {
			s = s + e.getError() + "\n";
		}
		s += "In total of: ";
		for (SPDXCompatibilityError e : SPDXerrors) {
			try {
				s += e.getDoc().getSpdxPackage().getFileName() + " ";
			} catch (InvalidSPDXAnalysisException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			s += "the violations detected as a whole can "
					+ (isAdjustable() == true ? "" : "not")
					+ " be corrected.\n"
					+ (isAdjustable() == true ? "The proposed concluded licenses to avoid violations should be any of the: \n"
							+ getProposals().toString() + "\n"
							: "");
		} catch (InvalidSPDXAnalysisException e1) {
			e1.printStackTrace();
		}
		System.out.println(s);
	}
}
