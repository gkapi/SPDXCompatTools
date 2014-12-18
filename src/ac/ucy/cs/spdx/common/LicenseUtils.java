package ac.ucy.cs.spdx.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.util.MathUtil;
import org.spdx.rdfparser.InvalidSPDXAnalysisException;
import org.spdx.rdfparser.SPDXDocument;
import org.spdx.rdfparser.SPDXLicenseInfo;
import org.spdx.rdfparser.SPDXNonStandardLicense;

import ac.ucy.cs.spdx.conflict.LicensePairCompatibilityError;
import ac.ucy.cs.spdx.conflict.ViolationAnalysisInfo;
import ac.ucy.cs.spdx.graph.LicenseEdge;
import ac.ucy.cs.spdx.graph.LicenseGraph;
import ac.ucy.cs.spdx.graph.LicenseNode;
import ac.ucy.cs.spdx.license.License;

/**
 * This is License Utilities class. It provides the user with several methods of
 * accessing the .rdf file and checking Licenses for violation or compatibility
 * based on the license graph.
 * 
 * @author dpasch01
 * 
 */
public class LicenseUtils {
	private final static Logger LOGGER = Logger.getLogger(LicenseUtils.class.getName()); 
	
	// This is the standard static graph on which we can add or remove nodes
	// etc.
	private static SimpleDirectedGraph<LicenseNode, LicenseEdge> licenseGraph = LicenseGraph.licenseGraph;

	/**
	 * It checks if the declared license or licenses are correct based on the
	 * current licenses used in the SPDX file or the proposals given for solving
	 * any compatibility warnings. Returns true if the declaration is correct or
	 * false otherwise.
	 * 
	 * @param SPDXDocument
	 * @return boolean
	 */
	public static boolean isDeclarationCorrect(SPDXDocument doc)
			throws InvalidSPDXAnalysisException {
		SPDXLicenseInfo decl = null;
		try {
			decl = doc.getSpdxPackage().getDeclaredLicense();
		} catch (InvalidSPDXAnalysisException e) {
			e.printStackTrace();
		}
		StringTokenizer strTok = new StringTokenizer(decl.toString(), " ()");

		if (strTok.countTokens() > 1) {

			ArrayList<String> disjunctive = new ArrayList<>();
			ArrayList<String> conjunctive = new ArrayList<>();
			String firstLicense = null;

			while (strTok.hasMoreTokens()) {
				if (disjunctive.isEmpty() && conjunctive.isEmpty())
					firstLicense = strTok.nextToken();
				String token = strTok.nextToken();
				if (token.equals("OR"))
					disjunctive.add(strTok.nextToken());
				if (token.equals("AND"))
					conjunctive.add(strTok.nextToken());
			}
			if (!disjunctive.isEmpty()) {
				if (LicensePairCompatibilityError.getProposals().contains(
						firstLicense)
						|| getLicenses(
								doc.getSpdxPackage().getLicenseInfoFromFiles())
								.contains(firstLicense)) {
					return true;
				}
				for (String s : disjunctive)
					if (LicensePairCompatibilityError.getProposals()
							.contains(s)
							|| getLicenses(
									doc.getSpdxPackage()
											.getLicenseInfoFromFiles())
									.contains(s)) {
						return true;
					}
			}
			if (!conjunctive.isEmpty()) {
				if (!LicensePairCompatibilityError.getProposals().contains(
						firstLicense)
						|| !getLicenses(
								doc.getSpdxPackage().getLicenseInfoFromFiles())
								.contains(firstLicense)) {
					return false;
				}
				for (String s : disjunctive)
					if (!LicensePairCompatibilityError.getProposals().contains(
							s)
							|| getLicenses(
									doc.getSpdxPackage()
											.getLicenseInfoFromFiles())
									.contains(s)) {
						return false;
					}
			}
		} else {
			if (LicensePairCompatibilityError.getProposals().contains(
					decl.toString())
					|| getLicenses(
							doc.getSpdxPackage().getLicenseInfoFromFiles())
							.contains(decl.toString())) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	/**
	 * It checks the List of edges passed as parameters for the presence of a
	 * nonTransive edge. There can be only one nonTransive edge in the List and
	 * that one must be the last. If these specifications are met, the method
	 * returns true, otherwise false.
	 * 
	 * @param List
	 *            <LicenseEdge>
	 * @return boolean
	 */
	private static boolean checkEdgesForTransitive(List<LicenseEdge> edgeList) {
		for (int i = 0; i < edgeList.size() - 1; i++) {
			if (edgeList.get(i).isTransitive())
				return false;
		}
		return true;
	}

	/**
	 * It checks if the two license identifiers passed as parameters are
	 * compatible with each other. It checks that by getting the licenseNode
	 * that matches the license identifiers and calculates all the possible
	 * paths between them in the licenseGraph. If one of paths that have been
	 * found returns true in the checkEdgesForTransitive then they are
	 * compatible, else if all the paths are exhausted it returns false.
	 * 
	 * @param String
	 * @param String
	 * @return boolean
	 */
	public static boolean areCompatible(String licenseIdentifier1,
			String licenseIdentifier2) {
		LicenseNode v1 = null;
		LicenseNode v2 = null;

		for (LicenseNode ln : LicenseNode.getLicenseNodes()) {
			if (ln.containsLicense(licenseIdentifier1)) {
				v1 = ln;
				break;
			}
		}
		for (LicenseNode ln : LicenseNode.getLicenseNodes()) {
			if (ln.containsLicense(licenseIdentifier2)) {
				v2 = ln;
				break;
			}
		}

		if (v1 == null) {
			System.err.println(licenseIdentifier1
					+ " is not contained in any License Node.");
			System.exit(-1);
		}

		if (v2 == null) {
			System.err.println(licenseIdentifier2
					+ " is not contained in any License Node.");
			System.exit(-1);
		}

		if (v1.equals(v2)) {
			LOGGER.info("The two licenses are the same.");
			return true;
		}

		List<GraphPath<LicenseNode, LicenseEdge>> paths = null;
		try {
			paths = new KShortestPaths<LicenseNode, LicenseEdge>(licenseGraph,
					v1,
					(int) (LicenseNode.getLicenseNodes().size() * MathUtil
							.factorial(LicenseNode.getLicenseNodes().size())))
					.getPaths(v2);
		} catch (IllegalArgumentException iae) {
			return false;
		} catch (NullPointerException npe) {
			return false;
		}

		if (paths != null) {
			int pathsWithClosure = 0;

			for (GraphPath<LicenseNode, LicenseEdge> gp : paths) {
				List<LicenseEdge> edgeList = gp.getEdgeList();

				if (edgeList.size() > 1) {
					for (LicenseEdge le : edgeList)
						if (le.isTransitive()) {
							pathsWithClosure++;
							break;
						}
				}
			}

			if (pathsWithClosure == paths.size()) {
				for (GraphPath<LicenseNode, LicenseEdge> gp : paths) {
					if (checkEdgesForTransitive(gp.getEdgeList()))
						return true;
				}
			} else
				return true;
		}
		return false;
	}

	/**
	 * It checks if the two license identifiers passed as parameters have an
	 * identical compatible license based on a traversal of the licenseGrap.
	 * Each time the traversal finds a possible license, it adds it in the
	 * proposals List. After all the licenses are exhausted, it returns the
	 * proposal List.
	 * 
	 * @param String
	 * @param String
	 * @return ArrayList<License>
	 */
	public static ArrayList<License> proposeLicense(String licenseIdentifier1,
			String licenseIdentifier2) {
		HashSet<License> proposals = new HashSet<>();
		for (License l : License.getLicenses()) {
			if (areCompatible(licenseIdentifier1, l.getIdentifier())) {
				if (areCompatible(licenseIdentifier2, l.getIdentifier()))
					proposals.add(l);
			}
		}

		return new ArrayList<License>(proposals);
	}

	/**
	 * It checks if the List of license identifiers passed as parameter consists
	 * of licenses that are compatible with each other. If not it reports the
	 * result of the compatibility error and the possible proposals for solving
	 * the matter. For each pair of licenses that is checked it reports in an
	 * License Compatibility Error and adds it in the List of those that, after
	 * the checking, will be return.
	 * 
	 * @param List
	 *            <String>
	 * @return ArrayList<LicensePairCompatibilityError>
	 */
	public static ArrayList<LicensePairCompatibilityError> areCompatible(
			List<String> lIdentifiers) {
		LicensePairCompatibilityError.clear();
		boolean comp[][] = new boolean[lIdentifiers.size()][lIdentifiers.size()];
		HashSet<LicensePairCompatibilityError> pairs = new HashSet<>();

		for (int i = 0; i < lIdentifiers.size(); i++) {
			for (int j = 0; j < lIdentifiers.size(); j++) {
				if (areCompatible(lIdentifiers.get(i), lIdentifiers.get(j)))
					comp[i][j] = true;
			}
		}

		for (int i = 0; i < lIdentifiers.size(); i++) {
			for (int j = i; j < lIdentifiers.size(); j++) {
				if (!comp[i][j] && !comp[j][i]) {
					LicensePairCompatibilityError lpce = new LicensePairCompatibilityError();
					lpce.setLicense1(License.findLicense(lIdentifiers.get(i)));
					lpce.setLicense2(License.findLicense(lIdentifiers.get(j)));
					lpce.setProposals(proposeLicense(lIdentifiers.get(i),
							lIdentifiers.get(j)));
					pairs.add(lpce);
				}
			}
		}
		return new ArrayList<>(pairs);
	}

	/**
	 * It checks the syntax of SPDX document passed as parameter as well as the
	 * Declared and infoFromFiles licenses. It reports any errors found and also
	 * whether those are adjustable, as well as the possible solutions of those.
	 * All the info is stored in a Violation Analysis info object which is
	 * returned.
	 * 
	 * @param SPDXDocument
	 * @return ViolationAnalysisInfo
	 */
	public static ViolationAnalysisInfo checkForViolation(SPDXDocument spdxFile)
			throws InvalidSPDXAnalysisException {
		ViolationAnalysisInfo vai = new ViolationAnalysisInfo();
		vai.setFileName(spdxFile.getSpdxPackage().getFileName());
		vai.setLicenseDeclared(spdxFile.getSpdxPackage().getDeclaredLicense()
				.toString());
		if (spdxFile.getSpdxPackage().getDeclaredLicense().verify().isEmpty())
			vai.setLicenseDeclaredCorrect(true);
		vai.setLicenseConcluded(spdxFile.getSpdxPackage()
				.getConcludedLicenses().toString());
		String infoLicenses[] = new String[spdxFile.getSpdxPackage()
				.getLicenseInfoFromFiles().length];
		for (int i = 0; i < infoLicenses.length; i++)
			infoLicenses[i] = spdxFile.getSpdxPackage()
					.getLicenseInfoFromFiles()[i].toString();
		vai.setInfoLicenses(infoLicenses);
		if (spdxFile.getSpdxPackage().getConcludedLicenses().verify().isEmpty())
			vai.setLicenseConcludedCorrect(true);

		SPDXLicenseInfo[] sli = spdxFile.getSpdxPackage()
				.getLicenseInfoFromFiles();
		ArrayList<LicensePairCompatibilityError> errors = areCompatible(getLicenses(sli));

		if (!errors.isEmpty())
			vai.setViolationDetected(true);
		vai.setAdjustFeasible(LicensePairCompatibilityError.isAdjustable());
		vai.setProposedApplicableLicenses(LicensePairCompatibilityError
				.getProposals());
		vai.setErrors(errors);
		return vai;
	}

	/**
	 * It returns the licenses that are found in the SPDXLicenseInfo table that
	 * is extracted from the SPDXDocument in an ArrayLista.
	 * 
	 * @param SPDXLicenseInfo
	 *            []
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getLicenses(SPDXLicenseInfo[] sli) {
		ArrayList<String> licensesFound = new ArrayList<>();
		for (SPDXLicenseInfo sl : sli) {
			if (sl instanceof SPDXNonStandardLicense) {
				if (((SPDXNonStandardLicense) sl).getLicenseName() != null) {
					licensesFound.add(((SPDXNonStandardLicense) sl)
							.getLicenseName());
				}
			} else {
				licensesFound.add(sl.toString());
			}
		}
		ArrayList<String> licenses = new ArrayList<>();
		for (String s : licensesFound) {
			License l = License.findLicense(s);
			if (l != null)
				licenses.add(l.getIdentifier());
		}
		return licenses;
	}
}