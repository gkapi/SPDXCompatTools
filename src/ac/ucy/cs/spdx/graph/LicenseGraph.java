package ac.ucy.cs.spdx.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.DOTExporter;
//import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.graph.SimpleDirectedGraph;

import ac.ucy.cs.spdx.common.LicenseNodeConsts;
import ac.ucy.cs.spdx.license.License;

/**
 * This is License Graph class. It is a simple implementation of
 * SimpleDirectedGraph from jgrapht library. It consists of several methods of
 * traversing, creating and editing the licenseGrapht of LicenseUtils class.
 * 
 * @author dpasch01
 * 
 */
public class LicenseGraph {

	public static void exportGraph() {
		IntegerNameProvider<LicenseNode> p0 = new IntegerNameProvider<>();
		VertexNameProvider<LicenseNode> p1 = new VertexNameProvider<LicenseNode>() {

			@Override
			public String getVertexName(LicenseNode v) {
				return v.toString();
			}

		};
		//EdgeNameProvider<LicenseEdge> p2 = new EdgeNameProvider<LicenseEdge>() {

		// @Override
		// public String getEdgeName(LicenseEdge e) {
		// if (e.isTransitive())
		// return "Transitive";
		// return "Non-Transitive";
		// }
		// };

		ComponentAttributeProvider<LicenseEdge> p4 = new ComponentAttributeProvider<LicenseEdge>() {

			@Override
			public Map<String, String> getComponentAttributes(LicenseEdge e) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				if (e.isTransitive()) {
					map.put("color", "maroon");
				} else {
					map.put("color", "black");
				} 
				return map;
			}
		};

		DOTExporter<LicenseNode, LicenseEdge> exporter = new DOTExporter<LicenseNode, LicenseEdge>(
				p0, p1, null, null, p4);
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File("graph.dot"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		Writer w = bw;
		exporter.export(w, licenseGraph);
	}

	public static SimpleDirectedGraph<LicenseNode, LicenseEdge> licenseGraph = createLicenseGraph();

	/**
	 * Prints the license Graph.
	 * 
	 */
	public static void printGraph() {
		for (LicenseEdge e : licenseGraph.edgeSet()) {
			System.out.println(e);
		}
	}

	/**
	 * Returns the initialization state of the license graph.
	 * 
	 * @return SimpleDirectedGraph<LicenseNode, LicenseEdge>
	 */
	public static SimpleDirectedGraph<LicenseNode, LicenseEdge> createLicenseGraph() {
		SimpleDirectedGraph<LicenseNode, LicenseEdge> licenseGraph = new SimpleDirectedGraph<LicenseNode, LicenseEdge>(
				LicenseEdge.class);

		licenseGraph.addVertex(LicenseNodeConsts.afl30);
		licenseGraph.addVertex(LicenseNodeConsts.mit_x11);
		licenseGraph.addVertex(LicenseNodeConsts.bsd2c_bsd2cfree);
		licenseGraph.addVertex(LicenseNodeConsts.bsd3c);
		licenseGraph.addVertex(LicenseNodeConsts.apache20);
		licenseGraph.addVertex(LicenseNodeConsts.libpng_zlib);
		licenseGraph.addVertex(LicenseNodeConsts.cddl);
		licenseGraph.addVertex(LicenseNodeConsts.mpl11);
		licenseGraph.addVertex(LicenseNodeConsts.artistic20);
		licenseGraph.addVertex(LicenseNodeConsts.mpl20);
		licenseGraph.addVertex(LicenseNodeConsts.lgpl21);
		licenseGraph.addVertex(LicenseNodeConsts.lgpl21plus);
		licenseGraph.addVertex(LicenseNodeConsts.lgpl30_lgpl30plus);
		licenseGraph.addVertex(LicenseNodeConsts.gpl20);
		licenseGraph.addVertex(LicenseNodeConsts.gpl20plus);
		licenseGraph.addVertex(LicenseNodeConsts.gpl30_gpl30plus);
		licenseGraph.addVertex(LicenseNodeConsts.agpl30);
		licenseGraph.addVertex(LicenseNodeConsts.osl30);
		licenseGraph.addVertex(LicenseNodeConsts.eupl11);
		licenseGraph.addVertex(LicenseNodeConsts.cecill20);
		licenseGraph.addVertex(LicenseNodeConsts.agpl10);
		licenseGraph.addVertex(LicenseNodeConsts.apache10);

		licenseGraph.addEdge(LicenseNodeConsts.afl30, LicenseNodeConsts.osl30,
				new LicenseEdge(LicenseNodeConsts.afl30,
						LicenseNodeConsts.osl30, false));
		licenseGraph.addEdge(LicenseNodeConsts.mit_x11,
				LicenseNodeConsts.bsd2c_bsd2cfree, new LicenseEdge(
						LicenseNodeConsts.mit_x11,
						LicenseNodeConsts.bsd2c_bsd2cfree, false));
		licenseGraph.addEdge(LicenseNodeConsts.mit_x11,
				LicenseNodeConsts.bsd3c, new LicenseEdge(
						LicenseNodeConsts.mit_x11, LicenseNodeConsts.bsd3c,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.apache10,
				LicenseNodeConsts.apache20, new LicenseEdge(
						LicenseNodeConsts.apache10, LicenseNodeConsts.apache20,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd2c_bsd2cfree,
				LicenseNodeConsts.lgpl21, new LicenseEdge(
						LicenseNodeConsts.bsd2c_bsd2cfree,
						LicenseNodeConsts.lgpl21, false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd2c_bsd2cfree,
				LicenseNodeConsts.lgpl21plus, new LicenseEdge(
						LicenseNodeConsts.bsd2c_bsd2cfree,
						LicenseNodeConsts.lgpl21plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd2c_bsd2cfree,
				LicenseNodeConsts.lgpl30_lgpl30plus, new LicenseEdge(
						LicenseNodeConsts.bsd2c_bsd2cfree,
						LicenseNodeConsts.lgpl30_lgpl30plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd2c_bsd2cfree,
				LicenseNodeConsts.bsd3c, new LicenseEdge(
						LicenseNodeConsts.bsd2c_bsd2cfree,
						LicenseNodeConsts.bsd3c, false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd3c, LicenseNodeConsts.lgpl21,
				new LicenseEdge(LicenseNodeConsts.bsd3c,
						LicenseNodeConsts.lgpl21, false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd3c,
				LicenseNodeConsts.lgpl21plus, new LicenseEdge(
						LicenseNodeConsts.bsd3c, LicenseNodeConsts.lgpl21plus,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd3c,
				LicenseNodeConsts.lgpl30_lgpl30plus, new LicenseEdge(
						LicenseNodeConsts.bsd3c,
						LicenseNodeConsts.lgpl30_lgpl30plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.bsd3c,
				LicenseNodeConsts.apache20, new LicenseEdge(
						LicenseNodeConsts.bsd3c, LicenseNodeConsts.apache20,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.apache20,
				LicenseNodeConsts.lgpl30_lgpl30plus, new LicenseEdge(
						LicenseNodeConsts.apache20,
						LicenseNodeConsts.lgpl30_lgpl30plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.libpng_zlib,
				LicenseNodeConsts.apache20, new LicenseEdge(
						LicenseNodeConsts.libpng_zlib,
						LicenseNodeConsts.apache20, false));
		licenseGraph.addEdge(LicenseNodeConsts.libpng_zlib,
				LicenseNodeConsts.gpl20, new LicenseEdge(
						LicenseNodeConsts.libpng_zlib, LicenseNodeConsts.gpl20,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.libpng_zlib,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.libpng_zlib,
						LicenseNodeConsts.gpl20plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.mpl11, LicenseNodeConsts.cddl,
				new LicenseEdge(LicenseNodeConsts.mpl11,
						LicenseNodeConsts.cddl, false));
		licenseGraph.addEdge(LicenseNodeConsts.artistic20,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.artistic20,
						LicenseNodeConsts.gpl20plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.artistic20,
				LicenseNodeConsts.gpl20, new LicenseEdge(
						LicenseNodeConsts.artistic20, LicenseNodeConsts.gpl20,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.mpl20, LicenseNodeConsts.gpl20,
				new LicenseEdge(LicenseNodeConsts.mpl20,
						LicenseNodeConsts.gpl20, false));
		licenseGraph.addEdge(LicenseNodeConsts.mpl20,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.mpl20, LicenseNodeConsts.gpl20plus,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.mpl20, LicenseNodeConsts.lgpl21,
				new LicenseEdge(LicenseNodeConsts.mpl20,
						LicenseNodeConsts.lgpl21, false));
		licenseGraph.addEdge(LicenseNodeConsts.mpl20,
				LicenseNodeConsts.lgpl21plus, new LicenseEdge(
						LicenseNodeConsts.mpl20, LicenseNodeConsts.lgpl21plus,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.lgpl21, LicenseNodeConsts.gpl20,
				new LicenseEdge(LicenseNodeConsts.lgpl21,
						LicenseNodeConsts.gpl20, false));
		licenseGraph.addEdge(LicenseNodeConsts.lgpl21,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.lgpl21, LicenseNodeConsts.gpl20plus,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.lgpl21plus,
				LicenseNodeConsts.lgpl21, new LicenseEdge(
						LicenseNodeConsts.lgpl21plus, LicenseNodeConsts.lgpl21,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.lgpl21plus,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.lgpl21plus,
						LicenseNodeConsts.gpl20plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.lgpl21plus,
				LicenseNodeConsts.lgpl30_lgpl30plus, new LicenseEdge(
						LicenseNodeConsts.lgpl21plus,
						LicenseNodeConsts.lgpl30_lgpl30plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.lgpl30_lgpl30plus,
				LicenseNodeConsts.gpl30_gpl30plus, new LicenseEdge(
						LicenseNodeConsts.lgpl30_lgpl30plus,
						LicenseNodeConsts.gpl30_gpl30plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.gpl20plus,
				LicenseNodeConsts.gpl20, new LicenseEdge(
						LicenseNodeConsts.gpl20plus, LicenseNodeConsts.gpl20,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.gpl20plus,
				LicenseNodeConsts.gpl30_gpl30plus, new LicenseEdge(
						LicenseNodeConsts.gpl20plus,
						LicenseNodeConsts.gpl30_gpl30plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.gpl30_gpl30plus,
				LicenseNodeConsts.agpl30, new LicenseEdge(
						LicenseNodeConsts.gpl30_gpl30plus,
						LicenseNodeConsts.agpl30, false));
		licenseGraph.addEdge(LicenseNodeConsts.eupl11, LicenseNodeConsts.osl30,
				new LicenseEdge(LicenseNodeConsts.eupl11,
						LicenseNodeConsts.osl30, false));
		licenseGraph.addEdge(LicenseNodeConsts.eupl11, LicenseNodeConsts.gpl20,
				new LicenseEdge(LicenseNodeConsts.eupl11,
						LicenseNodeConsts.gpl20, false));
		licenseGraph.addEdge(LicenseNodeConsts.eupl11,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.eupl11, LicenseNodeConsts.gpl20plus,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.eupl11,
				LicenseNodeConsts.cecill20, new LicenseEdge(
						LicenseNodeConsts.eupl11, LicenseNodeConsts.cecill20,
						false));
		licenseGraph.addEdge(LicenseNodeConsts.cecill20,
				LicenseNodeConsts.gpl20plus, new LicenseEdge(
						LicenseNodeConsts.cecill20,
						LicenseNodeConsts.gpl20plus, false));
		licenseGraph.addEdge(LicenseNodeConsts.cecill20,
				LicenseNodeConsts.agpl30, new LicenseEdge(
						LicenseNodeConsts.cecill20, LicenseNodeConsts.agpl30,
						false));

		licenseGraph.addEdge(LicenseNodeConsts.apache20,
				LicenseNodeConsts.mpl20, new LicenseEdge(
						LicenseNodeConsts.apache20, LicenseNodeConsts.mpl20,
						true));
		licenseGraph.addEdge(LicenseNodeConsts.mpl11, LicenseNodeConsts.mpl20,
				new LicenseEdge(LicenseNodeConsts.mpl11,
						LicenseNodeConsts.mpl20, true));

		return licenseGraph;
	}

	/**
	 * Finds the License base on the url passed as parameter and adds it in the
	 * license Graph.
	 * 
	 * @param String
	 */
	public static void addLicense(String url) throws IOException {
		new License(url);
	}

	/**
	 * Creates a new License base identifier,license name and Text File passed
	 * as parameter and adds it in the license Graph.
	 * 
	 * @param String
	 * @param String
	 * @param File
	 */
	public static void addLicense(String identifier, String license,
			File licenseText) {
		new License(identifier, license, licenseText);
	}

	/**
	 * Creates a new License Node with the Licenses passed as parameter and
	 * defines whether or not is transitive.
	 * 
	 * @param boolean
	 * @param String
	 * @param String
	 *            []
	 */
	public static void addLicenseNode(boolean nonTransitivity,
			String nodeIdentifier, String... licenseIdentifiers) {
		License[] licenseArray = new License[licenseIdentifiers.length];
		for (int i = 0; i < licenseArray.length; i++) {
			licenseArray[i] = License.findLicense(licenseIdentifiers[i]);
		}
		LicenseNode ln = new LicenseNode(nodeIdentifier, licenseArray);
		licenseGraph.addVertex(ln);
	}

	/**
	 * Connects the License Node passed as parameter with the other Nodes also
	 * passed as parameters by creating new edge between them and defining
	 * whether or not is transitive
	 * 
	 * @param boolean
	 * @param String
	 * @param String
	 *            []
	 */
	public static void connectNode(boolean nonClosure, String nodeIdentifier,
			String... nodeIdentifiers) {
		LicenseNode ln = LicenseNode.findLicenseNode(nodeIdentifier);
		for (int i = 0; i < nodeIdentifiers.length; i++) {
			licenseGraph.addEdge(
					ln,
					LicenseNode.findLicenseNode(nodeIdentifiers[i]),
					new LicenseEdge(ln, LicenseNode
							.findLicenseNode(nodeIdentifiers[i]), nonClosure));
		}
	}

}
