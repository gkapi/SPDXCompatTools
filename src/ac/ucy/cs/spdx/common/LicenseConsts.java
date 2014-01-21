
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
