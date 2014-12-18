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