
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ColorMixer implements ActionListener, ChangeListener, KeyListener
{
	static int r1 = 64;
	static int g1 = 0;
	static int b1 = 255;
	
	JSpinner r1Sp = new JSpinner(new SpinnerNumberModel(r1, 0, 255, 1));
	JSpinner g1Sp = new JSpinner(new SpinnerNumberModel(g1, 0, 255, 1));
	JSpinner b1Sp = new JSpinner(new SpinnerNumberModel(b1, 0, 255, 1));
	JTextField h1Tf = new JTextField(7);
	
	static int r2 = 255;
	static int g2 = 128;
	static int b2 = 64;
	
	JSpinner r2Sp = new JSpinner(new SpinnerNumberModel(r2, 0, 255, 1));
	JSpinner g2Sp = new JSpinner(new SpinnerNumberModel(g2, 0, 255, 1));
	JSpinner b2Sp = new JSpinner(new SpinnerNumberModel(b2, 0, 255, 1));
	JTextField h2Tf = new JTextField(7);
	
	static boolean TF;
	static int Mr = 160;
	static int Mg = 64;
	static int Mb = 160;
	
	JTextArea MixTA = new JTextArea(2, 14);
	String MixSh;
	String MixSc;
	
	ArtPanel APanel = new ArtPanel();
	static JPanel C1P = new JPanel();
	static JPanel C2P = new JPanel();
	static JPanel MixP = new JPanel();

	
	public static void main(String[] args)
	{
		// create instance of ColorMixer that will do all the work!
		new ColorMixer();
	}

	// ColorMixer constructor
	public ColorMixer() 
	{
		// create the JFrame window
		JFrame TheMainFrame = new JFrame(); 
		TheMainFrame.setTitle("Color Mixer");
		TheMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		APanel.setLayout(new BorderLayout(0, ArtPanel.PANEL_HEIGHT-150));
		
		//Color 1
		C1P.setLayout(new BoxLayout(C1P, BoxLayout.Y_AXIS));
		C1P.add(r1Sp);
		r1Sp.addChangeListener(this);
		C1P.add(g1Sp);
		g1Sp.addChangeListener(this);
		C1P.add(b1Sp);
		b1Sp.addChangeListener(this);
		h1Tf.setText("#4000ff");
		C1P.add(h1Tf);
		h1Tf.addActionListener(this);
		APanel.add(C1P, BorderLayout.LINE_START);
		
		//Color 2
		C2P.setLayout(new BoxLayout(C2P, BoxLayout.Y_AXIS));
		C2P.add(r2Sp);
		r2Sp.addChangeListener(this);
		C2P.add(g2Sp);
		g2Sp.addChangeListener(this);
		C2P.add(b2Sp);
		b2Sp.addChangeListener(this);
		h2Tf.setText("#ff8040");
		h2Tf.addActionListener(this);
		C2P.add(h2Tf);
		APanel.add(C2P, BorderLayout.LINE_END);
		
		//Mixed Color
		MixTA.setEditable(false);
		MixP.add(MixTA);
		APanel.add(MixP, BorderLayout.PAGE_END);
		
		TheMainFrame.add(APanel);
		TheMainFrame.pack();
		TheMainFrame.setVisible(true);
		
		itsHappening();
	}
	
	public void itsHappening()
	{
		r1 = (Integer) r1Sp.getValue();
		g1 = (Integer) g1Sp.getValue();
		b1 = (Integer) b1Sp.getValue();
		
		r2 = (Integer) r2Sp.getValue();
		g2 = (Integer) g2Sp.getValue();
		b2 = (Integer) b2Sp.getValue();
		
		Mr = (int) ((r1+r2)/2d + .5);
		Mg = (int) ((g1+g2)/2d + .5);
		Mb = (int) ((b1+b2)/2d + .5);
		
		MixSh = Integer.toHexString((Mr*65536) + (Mg*256) + (Mb));
		while (MixSh.length() < 6)
			MixSh = "0" + MixSh;
		MixSc = String.format("%s, %s, %s",Mr, Mg, Mb);
		MixTA.setText("#" + MixSh + "\n" + MixSc);
		MixTA.setColumns(MixSc.length()/2 + 1);
		
		ArtPanel.drawColors(ArtPanel.G);
		APanel.setSize(ArtPanel.PANEL_WIDTH, ArtPanel.PANEL_HEIGHT+1);
		APanel.setSize(ArtPanel.PANEL_WIDTH, ArtPanel.PANEL_HEIGHT-1);
	}
	
	
	public void stateChanged(ChangeEvent e)
	{
		itsHappening();
		
		String h1S = Integer.toHexString((r1*65536) + (g1*256) + (b1));
		while (h1S.length() < 6)
			h1S = "0" + h1S;
		h1Tf.setText("#" + h1S);
		
		String h2S = Integer.toHexString((r2*65536) + (g2*256) + (b2));
		while (h2S.length() < 6)
			h2S = "0" + h2S;
		h2Tf.setText("#" + h2S);
	}

	public void actionPerformed(ActionEvent e)
	{
		TF = true;
		
		String h1S = h1Tf.getText();
		if (h1S.charAt(0) == '#')
			h1S = h1S.substring(1);
		while (h1S.length() < 6)
			h1S = "0" + h1S;
		h1S = h1S.substring(0, 6);
		int h1r = decimal(h1S.substring(0, 2));
		int h1g = decimal(h1S.substring(2, 4));
		int h1b = decimal(h1S.substring(4, 6));
		
		String h2S = h2Tf.getText();
		if (h2S.charAt(0) == '#')
			h2S = h2S.substring(1);
		while (h2S.length() < 6)
			h2S = "0" + h2S;
		h2S = h2S.substring(0, 6);
		int h2r = decimal(h2S.substring(0, 2));
		int h2g = decimal(h2S.substring(2, 4));
		int h2b = decimal(h2S.substring(4, 6));
		
		if(TF)
		{
			r1Sp.setValue(h1r);
			g1Sp.setValue(h1g);
			b1Sp.setValue(h1b);
			h1Tf.setText("#" + h1S);
			
			r2Sp.setValue(h2r);
			g2Sp.setValue(h2g);
			b2Sp.setValue(h2b);
			h2Tf.setText("#" + h2S);
			
			itsHappening();
		}
	}
	
	
	public static int decimal(String d)
	{
		int dec = 0; //number we will be building on
		int len = d.length();
		int pow =  len - 1; //the power the base needs to be raised to
		int index = 0;// initializes index to start at the begining
		int curcharval;
		while (index < len) //true when the index stays in the scope of the number
		{
			curcharval = Character.getNumericValue(d.charAt(index));
			if ((curcharval==-1) || (curcharval>15))
			{
				TF = false;
				return (-1);
			}
			dec += Math.pow(16, pow) * curcharval; //adds to the decimal number
			pow--; //lowers power by 1
			index++; //moves to the next place in the hex number
		}
		
		return dec;
	}
	
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e){}
}
