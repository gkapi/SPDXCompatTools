package ac.ucy.cs.spdx.graph;

import org.jgrapht.graph.DefaultEdge;

/**
 * This is License Edge class. It is custom graph edge implementation that
 * extends standard jgraph's DefaultEdge. It will be used in the labeled license
 * graph to separate transitive edges with non-transitive ones by the use of a
 * boolean field nonTransitive.
 * 
 * @author dpasch01
 * 
 */
@SuppressWarnings("serial")
public class LicenseEdge extends DefaultEdge {
	private LicenseNode v1;
	private LicenseNode v2;
	private boolean nonTransitive;

	/**
	 * Constructor for License Edge.
	 * 
	 */
	public LicenseEdge(LicenseNode v1, LicenseNode v2, boolean nonTransitive) {
		this.setV1(v1);
		this.setV2(v2);
		this.setTransitive(nonTransitive);
	}

	/**
	 * Returns true if this edge is Transitive.
	 * 
	 * @return boolean
	 */
	public boolean isTransitive() {
		return nonTransitive;
	}

	/**
	 * Sets true if this edge is Transitive.
	 * 
	 * @pass boolean
	 */
	public void setTransitive(boolean transitive) {
		this.nonTransitive = transitive;
	}

	/**
	 * Getter for the second license node.
	 * 
	 * @return LicenseNode
	 */
	public LicenseNode getV2() {
		return v2;
	}

	/**
	 * Setter for the second license node.
	 * 
	 * @param LicenseNode
	 */
	public void setV2(LicenseNode v2) {
		this.v2 = v2;
	}

	/**
	 * Getter for the first license node.
	 * 
	 * @return LicenseNode
	 */
	public LicenseNode getV1() {
		return v1;
	}

	/**
	 * Setter for the first license node.
	 * 
	 * @param LicenseNode
	 */
	public void setV1(LicenseNode v1) {
		this.v1 = v1;
	}

}
