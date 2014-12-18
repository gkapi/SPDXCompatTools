package ac.ucy.cs.spdx.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.spdx.rdfparser.InvalidSPDXAnalysisException;
import org.spdx.rdfparser.SPDXDocument;
import org.spdx.rdfparser.SPDXDocumentFactory;

import ac.ucy.cs.spdx.common.LicenseUtils;
import ac.ucy.cs.spdx.compatibility.SPDXCompatibility;
import ac.ucy.cs.spdx.compatibility.SPDXCompatibilityError;
import ac.ucy.cs.spdx.conflict.ViolationAnalysisInfo;
import ac.ucy.cs.spdx.graph.LicenseGraph;

public class Main {

	/**
	 * This is the main method of the project
	 * 
	 * @param String
	 *            []
	 */
	public static void main(String args[]) throws IOException {
		try {
			if (args.length > 1) {
				ArrayList<SPDXDocument> docs = new ArrayList<>();
				for (String s : args) {
					docs.add(SPDXDocumentFactory.creatSpdxDocument(System
							.getProperty("user.dir") + File.separator + s));
				}
				SPDXCompatibility.areCompatible(docs
						.toArray(new SPDXDocument[docs.size()]));
			} else if (args.length == 1) {
				SPDXDocument doc = SPDXDocumentFactory.creatSpdxDocument(System
						.getProperty("user.dir") + File.separator + args[0]);
				ViolationAnalysisInfo vai = LicenseUtils.checkForViolation(doc);
				new SPDXCompatibilityError(vai, doc);
				if (!LicenseUtils.isDeclarationCorrect(doc)) {
					System.err
							.println("The "
									+ doc.getSpdxPackage().getFileName()
									+ " file is not declared correctly.\nPlease check included licenses to be compatible with declared ones.");
					System.exit(-1);
				}
				System.out.println(vai);
			} else {
				System.err
						.println("Please enter rdf files as parameters to render the operation.");
				System.exit(-1);
			}

		} catch (InvalidSPDXAnalysisException e) {
			System.out.println(e.getLocalizedMessage());
		}
		LicenseGraph.exportGraph();
	}
}
