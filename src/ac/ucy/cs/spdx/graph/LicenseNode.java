package ac.ucy.cs.spdx.graph;

import java.util.Comparator;
import java.util.HashSet;

import ac.ucy.cs.spdx.license.License;
import ac.ucy.cs.spdx.license.License.Category;

/**
 * This is License Node class. It is the representation of vertices in the
 * licenseGraph as seen in /resources/LicenseCompatGraph.png. It consists of
 * several useful methods for creating the nodes and accessing data of them.
 * 
 * @author dpasch01
 * 
 */

public class LicenseNode {
	private static HashSet<LicenseNode> licenseNodes = new HashSet<LicenseNode>();
	private HashSet<License> licenses;
	private String nodeIdentifier;
	private Category category;

	/**
	 * Getter for the category of the License Node.
	 * 
	 * @return Category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Constructor for the License Node based on the identifier and the licenses
	 * passed as parameters.
	 * 
	 * @param String
	 * @param License
	 *            []
	 */
	public LicenseNode(String nodeIdentifier, License... licencesArray) {
		this.setNodeIdentifier(nodeIdentifier);
		this.category = licencesArray[0].getCategory();
		licenses = new HashSet<License>();
		for (License l : licencesArray) {
			licenses.add(l);
		}
		licenseNodes.add(this);
	}

	/**
	 * Getter for the licenses in the License Node.
	 * 
	 * @return HashSet<License>
	 */
	public HashSet<License> getLicenses() {
		return licenses;
	}

	/**
	 * The toString() method implementation for Violation Analysis Info object.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String s = new String();
		s += "[ ";
		for (License l : this.getLicenses()) {
			s += l.toString();
			s += ' ';
		}
		s += ']';
		return s;
	}

	/**
	 * Getter for the License Node identifier.
	 * 
	 * @return String
	 */
	public String getNodeIdentifier() {
		return nodeIdentifier;
	}

	/**
	 * Setter for the License Node identifier.
	 * 
	 * @param String
	 */
	public void setNodeIdentifier(String nodeIdentifier) {
		this.nodeIdentifier = nodeIdentifier;
	}

	/**
	 * Searches and returns the licenseNodes for the License Node with the
	 * identifier equal to the one passed as parameter.
	 * 
	 * @param String
	 * @return LicenseNode
	 */
	public static LicenseNode findLicenseNode(String nodeIdentifier) {
		LicenseNode licenseNode = null;
		for (LicenseNode ln : licenseNodes) {
			if (ln.getNodeIdentifier().equals(nodeIdentifier))
				licenseNode = ln;
		}
		return licenseNode;
	}

	/**
	 * Searches the License Node for the specified License passed as parameter
	 * and returns true or false whether it finds it or not
	 * 
	 * @param String
	 * @return boolean
	 */
	public boolean containsLicense(String licenseIdentifier) {
		if (licenses.contains(License.findLicense(licenseIdentifier)))
			return true;
		return false;
	}

	/**
	 * Getter for the set of all the License Nodes
	 * 
	 * @return HashSet<LicenseNode>
	 */
	public static HashSet<LicenseNode> getLicenseNodes() {
		return licenseNodes;
	}

	/**
	 * Searches the License Node for the specified License passed as parameter
	 * and returns it.
	 * 
	 * @param String
	 * @return License
	 */
	public License findLicense(String licenseIdentifier) {
		License license = null;
		for (License l : licenses) {
			if (l.getIdentifier().equals(licenseIdentifier))
				license = l;
		}
		return license;
	}

	/**
	 * Searches the License Nodes for the specified License Node passed as
	 * parameter and removes it.
	 * 
	 * @param String
	 */
	public static void removeLicenseNode(String nodeIdentifier) {
		licenseNodes.remove(findLicenseNode(nodeIdentifier));
	}

	/**
	 * Searches the License Node for the specified License passed as parameter
	 * and removes it.
	 * 
	 * @param String
	 */
	public void removeLicense(String licenseIdentifier) {
		this.licenses.remove(this.findLicense(licenseIdentifier));
	}

	/**
	 * Returns true if the specified License Node passed as parameter is equal
	 * to the specific License Node the this method is called upon.
	 * 
	 * @param LicenseNode
	 * @return boolean
	 */
	public boolean equals(LicenseNode ln) {
		Comparator<LicenseNode> cmp = new Comparator<LicenseNode>() {
			@Override
			public int compare(LicenseNode o1, LicenseNode o2) {
				for (License l1 : o1.getLicenses()) {
					if (!o2.containsLicense(l1.getIdentifier())) {
						return -1;
					}
				}
				return 0;
			}
		};
		if (cmp.compare(this, ln) == 0)
			return true;
		return false;
	}
}
