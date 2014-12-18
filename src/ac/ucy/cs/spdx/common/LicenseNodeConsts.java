package ac.ucy.cs.spdx.common;

import ac.ucy.cs.spdx.graph.LicenseNode;

/**
 * This is an interface with the constants of the standard graph licenseNodes.
 * It generates the licenseNodes as appear in the license graph that can be seen
 * in the picture /resources/LicenseCompatGraph.png.
 * 
 * @author dpasch01
 * 
 */
public interface LicenseNodeConsts {

	LicenseNode afl30 = new LicenseNode("afl30", LicenseConsts.AFL30);
	LicenseNode mit_x11 = new LicenseNode("mit_x11", LicenseConsts.MIT,
			LicenseConsts.X11);
	LicenseNode bsd2c_bsd2cfree = new LicenseNode("bsd2c_bsd2cfree",
			LicenseConsts.BSD2Clause, LicenseConsts.BSD2ClauseFreeBSD);
	LicenseNode bsd3c = new LicenseNode("bsd3c", LicenseConsts.BSD3Clause);
	LicenseNode apache20 = new LicenseNode("apache20", LicenseConsts.Apache20);
	LicenseNode libpng_zlib = new LicenseNode("libpng_zlib",
			LicenseConsts.Libpng, LicenseConsts.Zlib);
	LicenseNode cddl = new LicenseNode("cddl", LicenseConsts.CDDL10,
			LicenseConsts.CDDL11);
	LicenseNode mpl11 = new LicenseNode("mpl11", LicenseConsts.MPL11);
	LicenseNode artistic20 = new LicenseNode("artistic20",
			LicenseConsts.Artistic20);
	LicenseNode mpl20 = new LicenseNode("mpl20", LicenseConsts.MPL20);
	LicenseNode lgpl21 = new LicenseNode("lgpl21", LicenseConsts.LGPL21);
	LicenseNode lgpl21plus = new LicenseNode("lgpl21plus",
			LicenseConsts.LGPL21plus);
	LicenseNode lgpl30_lgpl30plus = new LicenseNode("lgpl30_lgpl30plus",
			LicenseConsts.LGPL30, LicenseConsts.LGPL30plus);
	LicenseNode gpl20 = new LicenseNode("gpl20", LicenseConsts.GPL20);
	LicenseNode gpl20plus = new LicenseNode("gpl20plus",
			LicenseConsts.GPL20plus);
	LicenseNode gpl30_gpl30plus = new LicenseNode("gpl30_gpl30plus",
			LicenseConsts.GPL30, LicenseConsts.GPL30plus);
	LicenseNode agpl30 = new LicenseNode("agpl30", LicenseConsts.AGPL30);
	LicenseNode osl30 = new LicenseNode("osl30", LicenseConsts.OSL30);
	LicenseNode eupl11 = new LicenseNode("eupl11", LicenseConsts.EUPL11);
	LicenseNode cecill20 = new LicenseNode("cecill20", LicenseConsts.CeCILL20);
	LicenseNode agpl10 = new LicenseNode("agpl10", LicenseConsts.AGPL10);
	LicenseNode apache10 = new LicenseNode("apache10", LicenseConsts.Apache10);
}
