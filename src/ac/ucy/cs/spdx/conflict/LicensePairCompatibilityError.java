package ac.ucy.cs.spdx.conflict;

import java.util.ArrayList;
import java.util.HashSet;
import ac.ucy.cs.spdx.common.LicenseUtils;
import ac.ucy.cs.spdx.license.License;

/**
 * This is License Pair Compatibility Error class. It is based on the results of
 * areCompatible() method of LicenseUtils class and provides info on whether the
 * two given Licenses are compatible and if not it gives the error message and
 * possible solutions on it.
 * 
 * @author dpasch01
 * 
 */
public class LicensePairCompatibilityError {
	public static HashSet<LicensePairCompatibilityError> errors = new HashSet<>();
	private License license1;
	private License license2;
	private ArrayList<License> proposals;

	/**
	 * It checks whether two license identifiers passed as parameters can be
	 * adjusted to a single license to prevent compatibility issues.
	 * 
	 * @param String
	 * @param String
	 * @return boolean
	 */
	private static boolean isAdjustable(String licenseIdentifier1,
			String licenseIdentifier2) {
		boolean adj = false;
		for (License l : License.getLicenses()) {
			if (LicenseUtils.areCompatible(licenseIdentifier1,
					l.getIdentifier())) {
				if (LicenseUtils.areCompatible(licenseIdentifier2,
						l.getIdentifier()))
					adj = true;
			}
		}
		return adj;
	}

	/**
	 * It checks whether the licenses can be adjusted so to prevent
	 * compatibility issues.
	 * 
	 * @return boolean
	 */
	public static boolean isAdjustable() {
		boolean adj = true;
		for (LicensePairCompatibilityError e : errors) {
			adj = isAdjustable(e.getLicense1().getIdentifier(), e.getLicense2()
					.getIdentifier());
			if (!adj)
				return false;
		}
		return true;
	}

	/**
	 * It checks whether the licenses passed as parameters can be adjusted so to
	 * prevent compatibility issues.
	 * 
	 * @param ArrayList
	 *            <LicensePairCompatibilityError>
	 * @return boolean
	 */
	public static boolean isAdjustable(
			ArrayList<LicensePairCompatibilityError> errors) {
		boolean adj = true;
		for (LicensePairCompatibilityError e : errors) {
			adj = isAdjustable(e.getLicense1().getIdentifier(), e.getLicense2()
					.getIdentifier());
			if (!adj)
				return false;
		}
		return true;
	}

	/**
	 * Constructor for License Pair Compatibility Error.
	 */
	public LicensePairCompatibilityError() {
		errors.add(this);
	}

	/**
	 * It returns the possible license proposals for solving any compatibility
	 * matters of the License Pair Compatibility Errors.
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
	 * It prints the possible proposals from the getProposals().
	 * 
	 */
	private String printProposals() {
		if (proposals.isEmpty())
			return "No proposals found.";
		return proposals.toString();
	}

	/**
	 * Getter for license1 of this License Pair Compatibility Error.
	 * 
	 * @return License
	 */
	public License getLicense1() {
		return license1;
	}

	/**
	 * Setter for license1 of this License Pair Compatibility Error.
	 * 
	 * @param License
	 */
	public void setLicense1(License license1) {
		this.license1 = license1;
	}

	/**
	 * Getter for license2 of this License Pair Compatibility Error.
	 * 
	 * @return License
	 */
	public License getLicense2() {
		return license2;
	}

	/**
	 * Setter for license2 of this License Pair Compatibility Error.
	 * 
	 * @param License
	 */
	public void setLicense2(License license2) {
		this.license2 = license2;
	}

	/**
	 * Getter for license proposals of this License Pair Compatibility Error.
	 * 
	 * @return ArrayList<License>
	 */
	public ArrayList<License> getElementProposals() {
		return proposals;
	}

	/**
	 * Setter for license proposals of this License Pair Compatibility Error.
	 * 
	 * @param ArrayList
	 *            <License>
	 */
	public void setProposals(ArrayList<License> proposals) {
		this.proposals = proposals;
	}

	/**
	 * The toString() method implementation for License Pair Compatibility Error
	 * object.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "License Pair: " + license1.getIdentifier() + " , "
				+ license2.getIdentifier() + "\nProposals: " + printProposals()
				+ "\n";
	}

	/**
	 * Method for clearing the errors list of License Pair Compatibility Error
	 * class.
	 * 
	 */
	public static void clear() {
		errors.clear();
	}
}
