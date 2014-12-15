package environment.brain;

import java.awt.Graphics;
import javax.swing.JComponent;

import config.Config;



@SuppressWarnings("serial")
public class BtModule extends JComponent {

	private String LocalName;
	private double X, Y;
	private int RADIO;

	
	public BtModule(String n) {
		LocalName = n;
	}
	
	
	public String getName() {
		return LocalName;
	}

	public double getXPos() {
		return X;
	}
	public double getYPos() {
		return Y;
	}
	public int getRadio() {
		return RADIO;
	}
	
	public void setLocation(double _x, double _y) {
		X = _x;
		Y = _y;
	}
	
	public void setRadio(int _r) {
		RADIO = _r;
	}

	@Override
	public String toString() {
		return LocalName + "(" + X + " , " + Y + " , " + RADIO + ")";
	}
	public void mover(int _x, int _y) {
		X = _x;
		Y = _y;
	}
	
	public void setName(String _n) {
		LocalName = _n;
	}
	

	// dibujar figuras con la API Java2D
	public void paint( Graphics g )
	{
		super.paint( g );  // llamar al método paint de la superclase

		int Xi = (int)X;
		int Yi = (int)Y;
		
		
		g.drawOval(Xi-3,Yi-3,6,6);
		if (Config.VISIBLERADIO)
			g.drawOval(Xi-RADIO,Yi-RADIO,RADIO*2,RADIO*2);
		
		g.drawString(LocalName, Xi, Yi-3);
		
	} // fin del método paint

}
