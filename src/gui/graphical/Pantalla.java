package gui.graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.Config;



import environment.brain.BtList;
import environment.brain.BtModule;

	

@SuppressWarnings("serial")
public class Pantalla extends JFrame {

	
	private Container cliente;
	private Area      dibujo;
	private BtList BTL;
	
	public Pantalla(String titulo, BtList btl) {
		super(titulo);		
		BTL = btl;
		dibujo = new Area(BTL);
		cliente = getContentPane();
    	cliente.add( dibujo, BorderLayout.CENTER );
	}
	
	public void mover(String name, double x, double y) {
		BtModule r = BTL.findBt(name);
		r.setLocation(x,y);
		dibujo.repaint();
	}

	public void setRadio(String name, int _r) {
		BtModule ai = BTL.findBt(name);
		ai.setRadio(_r);
		ai.setName(name);
		dibujo.repaint();
	}
	
	
}

@SuppressWarnings("serial")
class Area extends JPanel {

	BtList Agents;

	public Area(BtList _btl) {
		super();
		Agents = _btl;
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawRect(0, 0, Config.DIMX, Config.DIMY);
		
		Enumeration<BtModule> e = Agents.elements();
		while (e.hasMoreElements())
			(e.nextElement()).paint(g);
	}

	
	
	
	
}
