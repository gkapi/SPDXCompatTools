<<<<<<< HEAD
package ac.ucy.cs.spdx.common;

import java.io.File;

import ac.ucy.cs.spdx.license.License;
import ac.ucy.cs.spdx.license.License.Category;

/**
 * This is an interface with the constants of the standard graph licenses. It
 * generates the licenses in the license graph as shown in the picture found in
 * /resources/LicenseCompatGraph.png.
 * 
 * @author gkapi
 * @author dpasch01
 * 
 */
public interface LicenseConsts {
	String location = "licensesText/";

	License Apache10 = new License("Apache License 1.0", "Apache-1.0",
			new File(location + "Apache-1.0" + ".txt"), Category.PERMISSIVE);
	License AFL30 = new License("Academic Free License v3.0", "AFL-3.0",
			new File(location + "AFL-3.0" + ".txt"), Category.PERMISSIVE);
	License MIT = new License("MIT License", "MIT", new File(location + "MIT"
			+ ".txt"), Category.PERMISSIVE);
	License X11 = new License("X11 License", "X11", new File(location + "X11"
			+ ".txt"), Category.PERMISSIVE);
	License BSD2Clause = new License("BSD 2-clause 'Simplified' License",
			"BSD-2-Clause", new File(location + "BSD-2-Clause" + ".txt"),
			Category.PERMISSIVE);
	License BSD2ClauseFreeBSD = new License("BSD 2-clause FreeBSD License",
			"BSD-2-Clause-FreeBSD", new File(location + "BSD-2-Clause-FreeBSD"
					+ ".txt"), Category.PERMISSIVE);
	License BSD3Clause = new License("BSD 3-clause 'New' or 'Revised' License",
			"BSD-3-Clause", new File(location + "BSD-3-Clause" + ".txt"),
			Category.PERMISSIVE);
	License Apache20 = new License("Apache License 2.0", "Apache-2.0",
			new File(location + "Apache-2.0" + ".txt"), Category.PERMISSIVE);
	License Zlib = new License("zlib License", "Zlib", new File(location
			+ "Zlib" + ".txt"), Category.PERMISSIVE);
	License Libpng = new License("libpng License", "Libpng", new File(location
			+ "Libpng" + ".txt"), Category.PERMISSIVE);

	License CDDL10 = new License(
			"Common Development and Distribution License 1.0", "CDDL-1.0",
			new File(location + "CDDL-1.0" + ".txt"), Category.WEAK_COPYLEFT);
	License CDDL11 = new License(
			"Common Development and Distribution License 1.1", "CDDL-1.1",
			new File(location + "CDDL-1.1" + ".txt"), Category.WEAK_COPYLEFT);
	License MPL11 = new License("Mozilla Public License 1.1", "MPL-1.1",
			new File(location + "MPL-1.1" + ".txt"), Category.WEAK_COPYLEFT);
	License Artistic20 = new License("Artistic License 2.0", "Artistic-2.0",
			new File(location + "Artistic-2.0" + ".txt"),
			Category.WEAK_COPYLEFT);
	License MPL20 = new License("Mozilla Public License 2.0", "MPL-2.0",
			new File(location + "MPL-2.0" + ".txt"), Category.WEAK_COPYLEFT);
	License LGPL21 = new License("GNU Lesser General Public License v2.1 only",
			"LGPL-2.1", new File(location + "LGPL-2.1" + ".txt"),
			Category.WEAK_COPYLEFT);
	License LGPL21plus = new License(
			"GNU Lesser General Public License v2.1 or later", "LGPL-2.1+",
			new File(location + "LGPL-2.1+" + ".txt"), Category.WEAK_COPYLEFT);
	License LGPL30 = new License("GNU Lesser General Public License v3.0 only",
			"LGPL-3.0", new File(location + "LGPL-3.0" + ".txt"),
			Category.WEAK_COPYLEFT);
	License LGPL30plus = new License(
			"GNU Lesser General Public License v3.0 or later", "LGPL-3.0+",
			new File(location + "LGPL-3.0+" + ".txt"), Category.WEAK_COPYLEFT);

	License GPL20 = new License("GNU General Public License v2.0 only",
			"GPL-2.0", new File(location + "GPL-2.0" + ".txt"),
			Category.STRONG_COPYLEFT);
	License GPL20plus = new License("GNU General Public License v2.0 or later",
			"GPL-2.0+", new File(location + "GPL-2.0+" + ".txt"),
			Category.STRONG_COPYLEFT);
	License GPL30 = new License("GNU General Public License v3.0 only",
			"GPL-3.0", new File(location + "GPL-3.0" + ".txt"),
			Category.STRONG_COPYLEFT);
	License GPL30plus = new License("GNU General Public License v3.0 or later",
			"GPL-3.0+", new File(location + "GPL-3.0+" + ".txt"),
			Category.STRONG_COPYLEFT);
	License AGPL30 = new License("GNU Affero General Public License v3.0",
			"AGPL-3.0", new File(location + "AGPL-3.0" + ".txt"),
			Category.STRONG_COPYLEFT);
	License OSL30 = new License("Open Software License 3.0", "OSL-3.0",
			new File(location + "OSL-3.0" + ".txt"), Category.STRONG_COPYLEFT);
	License EUPL11 = new License("European Union Public License 1.1",
			"EUPL-1.1", new File(location + "EUPL-1.1" + ".txt"),
			Category.STRONG_COPYLEFT);
	License CeCILL20 = new License(
			"CeCILL Free Software License Agreement v2.0", "CECILL-2.0",
			new File(location + "CECILL-2.0" + ".txt"),
			Category.STRONG_COPYLEFT);
	License AGPL10 = new License("GNU Affero General Public License v1.0",
			"AGPL-1.0", new File(location + "AGPL-1.0" + ".txt"),
			Category.STRONG_COPYLEFT);

}
=======

package ac.ucy.cs.spdx.common;

/**
 * Contains useful constants for the licenses considered in the license graph.
 * @author gkapi
 */

public interface LicenseConsts {
	
	// public domain
	public static final String PUBLIC_DOMAIN = "Public-domain";
	public static final String PUBLIC_DOMAIN_REF = "Public-domain-ref";
	
	//permissive licenses
	public static final String AFL30 = "AFL-3.0";
	//next two are used as the same license/node
	public static final String MIT = "MIT";
	public static final String X11 = "X11";
	//next two are used as the same license/node
	public static final String BSD2Clause = "BSD-2-Clause";
	public static final String BSD2ClauseFreeBSD = "BSD-2-Clause-FreeBSD";
	public static final String BSD3Clause = "BSD-3-Clause";
	public static final String BSD4Clause = "BSD-4-Clause";
	public static final String Apache20 = "Apache-2.0";
	//next two are used as the same license/node
	public static final String Zlib = "Zlib";
	public static final String Libpng = "Libpng";
	
	public static final String Apache10 = "Apache-1.0";
	public static final String Apache11 = "Apache-1.1";
	
	//weak copyleft licenses
	public static final String Artistic20 = "Artistic-2.0";
	
	public static final String MSPL = "MS-PL";
	public static final String MSRL = "MS-RL";
	// the same behavior will be considered for the two next licenses
	public static final String CDDL10 = "CDDL-1.0";
	public static final String CDDL11 = "CDDL-1.1";
	public static final String MPL11 = "MPL-1.1";
	public static final String MPL20 = "MPL-2.0";
	//next three are used as the same license/node
	public static final String LGPL20 = "LGPL-2.0";
	public static final String LGPL20plus = "LGPL-2.0+";
	public static final String LGPL21 = "LGPL-2.1";
	public static final String LGPL21plus = "LGPL-2.1+";
	//next two are used as the same license/node
	public static final String LGPL30 = "LGPL-3.0";
	public static final String LGPL30plus = "LGPL-3.0+";
	public static final String CPL10 = "CPL-1.0";	
	public static final String EPL10 = "EPL-1.0";
	
	//strong copyleft licenses
	//next two are used as the same license/node
	public static final String GPL20 = "GPL-2.0";
	public static final String GPL20plus = "GPL-2.0+";
	//next two are used as the same license/node
	public static final String GPL30 = "GPL-3.0";
	public static final String GPL30plus = "GPL-3.0+";
	public static final String OSL30 = "OSL-3.0";
	public static final String AGPL10 = "AGPL-1.0";
	public static final String AGPL30 = "AGPL-3.0";

	// uncategorised
	public static final String Artistic10 = "Artistic-1.0";
	public static final String Artistic10Perl = "Artistic-1.0-Perl";
	public static final String CPOL102 = "CPOL-1.02";
	
	// ignore license list
	public static final String SAME_AS = "Same-license-as";
	public static final String NO_LICENSE_FOUND = "No_license_found";
	public static final String DUAL_LICENSE = "Dual-license";
	public static final String UNCLASSIFIED = "UnclassifiedLicense";
	public static final String TRADEMARK_REF = "Trademark-ref";
	public static final String OTHER = "See-doc(OTHER)";
	public static final String FSF = "FSF";
	public static final String FSF_REF = "FSF-ref";
	
	// not found in the SPDX license list
	public static final String USL_EUROPE = "Dual-USL-Europe";
	
	// synonyms license list
	public static final String MIT_STYLE = "MIT-style";
	public static final String X11_STYLE = "X11-style";
	public static final String BSD_STYLE = "BSD-style";
	public static final String BSD = "BSD";
	public static final String GPL = "GPL";
	public static final String GPL_EXCEPTION = "GPL-exception";
	public static final String MPL = "MPL";
	public static final String LGPL = "LGPL";
	public static final String GPL20_CLASSPATH_EXCEPTION = "GPL-2.0-with-classpath-exception";
	public static final String MPL11plus = "MPL-1.1+";
}
>>>>>>> a8729a78a240f07727ce53fc86ff98987cdee02a
