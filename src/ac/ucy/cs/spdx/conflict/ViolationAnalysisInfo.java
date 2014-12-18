package ac.ucy.cs.spdx.conflict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains information about what the violation analysis of SPDX
 * files gives as output.
 * 
 * @author gkapi
 * @author dpasch01
 * @version 1.0
 * 
 */
public class ViolationAnalysisInfo {

	private String fileName;
	private String licenseDeclared;
	// does the package contain the correct declared license
	private boolean licenseDeclaredCorrect;
	private String licenseConcluded;
	// all the info licenses contained in the file
	private String[] infoLicenses;
	// does the license contain the correct concluded license (if different from
	// declared)
	private boolean licenseConcludedCorrect;
	// is there a license violation in the package
	private boolean violationDetected;
	// can the license information be adjusted so that no conflict arises
	private boolean isAdjustFeasible;
	// which is the concluded license proposed so that no conflict arises
	private List<LicensePairCompatibilityError> errors = new ArrayList<LicensePairCompatibilityError>();
	// licenses that can be applied on the SPDX package without causing
	// violations
	private List<String> proposedApplicableLicenses = new ArrayList<String>();

	/**
	 * Getter for this Violation Analysis Info's file name.
	 * 
	 * @return String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter for this Violation Analysis Info's file name.
	 * 
	 * @param String
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Returns true if a violation has been detected..
	 * 
	 * @return boolean
	 */
	public boolean isViolationDetected() {
		return violationDetected;
	}

	/**
	 * Setter for this Violation Analysis Info's Violation Detected field.
	 * 
	 * @param boolean
	 */
	public void setViolationDetected(boolean violationDetected) {
		this.violationDetected = violationDetected;
	}

	/**
	 * Returns true if the errors found in this ViolationAnalysis Info are
	 * adjustable.
	 * 
	 * @return boolean
	 */
	public boolean isAdjustFeasible() {
		return isAdjustFeasible;
	}

	/**
	 * Sets whether the errors found in this ViolationAnalysis Info are
	 * adjustable.
	 * 
	 * @param boolean
	 */
	public void setAdjustFeasible(boolean isAdjustFeasible) {
		this.isAdjustFeasible = isAdjustFeasible;
	}

	/**
	 * Returns the License Concluded.
	 * 
	 * @return String
	 */
	public String getLicenseConcluded() {
		return licenseConcluded;
	}

	/**
	 * Sets the License Concluded.
	 * 
	 * @param String
	 */
	public void setLicenseConcluded(String licenseConcluded) {
		this.licenseConcluded = licenseConcluded;
	}

	/**
	 * Returns whether the Licenses Concluded are in the correct syntax.
	 * 
	 * @return boolean
	 */
	public boolean isLicenseConcludedCorrect() {
		return licenseConcludedCorrect;
	}

	/**
	 * Sets whether the Licenses Concluded are in the correct syntax.
	 * 
	 * @param boolean
	 */
	public void setLicenseConcludedCorrect(boolean licenseConcludedCorrect) {
		this.licenseConcludedCorrect = licenseConcludedCorrect;
	}

	/**
	 * Returns the Licenses Declared.
	 * 
	 * @return String
	 */
	public String getLicenseDeclared() {
		return licenseDeclared;
	}

	/**
	 * Sets the Licenses Declared.
	 * 
	 * @param String
	 */
	public void setLicenseDeclared(String licenseDeclared) {
		this.licenseDeclared = licenseDeclared;
	}

	/**
	 * Returns whether the Licenses Declared are in the correct syntax.
	 * 
	 * @return boolean
	 */
	public boolean isLicenseDeclaredCorrect() {
		return licenseDeclaredCorrect;
	}

	/**
	 * Sets whether the Licenses Declared are in the correct syntax.
	 * 
	 * @param boolean
	 */
	public void setLicenseDeclaredCorrect(boolean licenseDeclaredCorrect) {
		this.licenseDeclaredCorrect = licenseDeclaredCorrect;
	}

	/**
	 * Returns the possible proposal Licenses to be applied.
	 * 
	 * @return List<String>
	 */
	public List<String> getProposedApplicableLicenses() {
		return proposedApplicableLicenses;
	}

	/**
	 * Sets the possible proposal Licenses to be applied.
	 * 
	 * @param List
	 *            <String>
	 */
	public void setProposedApplicableLicenses(
			List<String> proposedApplicableLicenses) {
		this.proposedApplicableLicenses = proposedApplicableLicenses;
	}

	/**
	 * Adds any the possible proposal Licenses passed as parameter.
	 * 
	 * @param String
	 */
	public void addProposedApplicableLicense(String license) {
		this.proposedApplicableLicenses.add(license);
	}

	/**
	 * Removes any the possible proposal Licenses passed as parameter.
	 * 
	 * @param String
	 */
	public void removeProposedApplicableLicense(String license) {
		this.proposedApplicableLicenses.remove(license);
	}

	/**
	 * Returns the Info Licenses table.
	 * 
	 * @return String[]
	 */
	public String[] getInfoLicenses() {
		return infoLicenses;
	}

	/**
	 * Sets the Info Licenses table.
	 * 
	 * @param String
	 *            []
	 */
	public void setInfoLicenses(String[] infoLicenses) {
		this.infoLicenses = infoLicenses;
	}

	/**
	 * Getter for the License Pair Compatibility Error list.
	 * 
	 * @return List<LicensePairCompatibilityError>
	 */
	public List<LicensePairCompatibilityError> getErrors() {
		return errors;
	}

	/**
	 * Setter for the License Pair Compatibility Error list.
	 * 
	 * @param List
	 *            <LicensePairCompatibilityError>
	 */
	public void setErrors(List<LicensePairCompatibilityError> errors) {
		this.errors = errors;
	}
	
	/**
	 * The toString() method implementation for Violation Analysis Info object.
	 * 
	 * @return String
	 */
	public String toString() {
		List<String> infoLicsAsList = Arrays.asList(infoLicenses);
		String err = "";
		for (LicensePairCompatibilityError lpce : errors)
			err = lpce + "\n";

		return "The license declared in the package "
				+ this.fileName
				+ " ("
				+ this.licenseDeclared
				+ ")"
				+ " is "
				+ (this.licenseDeclaredCorrect == true ? "correct" : "wrong")
				+ "\n"
				+ "The license concluded in the package "
				+ " ("
				+ this.licenseConcluded
				+ ")"
				+ " is "
				+ (this.licenseConcludedCorrect == true ? "correct" : "wrong")
				+ "\n"
				+ "The package contains the following licenses from files "
				+ infoLicsAsList
				+ "\n"
				+ "There are "
				+ (this.violationDetected == true ? "" : "no")
				+ " violations in the package.\n"
				+ (err == "" ? "" : "\nViolations:\n")
				+ err
				+ (this.violationDetected == true ? "The violations detected can "
						+ (this.isAdjustFeasible == true ? "" : "not")
						+ " be corrected.\n"
						+ (this.isAdjustFeasible == true ? "The proposed concluded licenses to avoid violations should be any of the: "
								+ this.proposedApplicableLicenses.toString()
								+ "\n"
								: "")
						: "");
	}

}
