import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class ArtPanel extends JPanel
{
	static Graphics G;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int PANEL_HEIGHT = (int) screenSize.getHeight()-150;
	static int PANEL_WIDTH = PANEL_HEIGHT;
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
	}
	
	protected void paintComponent(Graphics g) 
	{
		G = g;
		
		// always call the base class paintComponent() first
		super.paintComponent(G);    
		
		drawColors(G);
	}
	
	public static void drawColors(Graphics g)
	{
		// cast the Graphics object to a Graphics2D object
		Graphics2D myGraphics = (Graphics2D) g;
		
		//draw Color 1
		Rectangle2D.Double C1sq = new Rectangle.Double(0, 0, PANEL_WIDTH/2, PANEL_HEIGHT/2); 
		Color C1 = new Color(ColorMixer.r1, ColorMixer.g1, ColorMixer.b1);
		myGraphics.setPaint(C1);
		myGraphics.fill(C1sq);
		ColorMixer.C1P.setBackground(C1);
		
		//draw Color 2
		Rectangle2D.Double C2sq = new Rectangle.Double(PANEL_WIDTH/2, 0, PANEL_WIDTH/2, PANEL_HEIGHT/2); 
		Color C2 = new Color(ColorMixer.r2, ColorMixer.g2, ColorMixer.b2);
		myGraphics.setPaint(C2);
		myGraphics.fill(C2sq);
		ColorMixer.C2P.setBackground(C2);
		
		//draw Mixed Color
		Rectangle2D.Double Mixsq = new Rectangle.Double(0, PANEL_HEIGHT/2, PANEL_WIDTH, PANEL_HEIGHT); 
		Color MixC = new Color(ColorMixer.Mr, ColorMixer.Mg, ColorMixer.Mb);
		myGraphics.setPaint(MixC);
		myGraphics.fill(Mixsq);
		ColorMixer.MixP.setBackground(MixC);
		
	}
}
