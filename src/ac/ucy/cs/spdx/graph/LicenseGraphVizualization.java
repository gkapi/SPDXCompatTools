package ac.ucy.cs.spdx.graph;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;

import java.awt.*;

import javax.swing.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;


public class LicenseGraphVizualization
    extends JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<LicenseNode, LicenseEdge> jgxAdapter;

    public static void main(String [] args)
    {
    	LicenseGraphVizualization applet = new LicenseGraphVizualization();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void init()
    {
        ListenableGraph<LicenseNode, LicenseEdge> g =
            new ListenableDirectedGraph<LicenseNode, LicenseEdge>(
               LicenseGraph.licenseGraph);

        jgxAdapter = new JGraphXAdapter<LicenseNode, LicenseEdge>(g);
  
        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);

        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
    }
}

