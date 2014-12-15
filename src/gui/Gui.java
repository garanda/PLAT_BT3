package gui;

import gui.graphical.Pantalla;
import gui.behavs.ReceiveRadio;
import gui.behavs.ReceiveScreen;
import environment.brain.BtList;

import javax.swing.JFrame;

import config.Config;




import jade.core.Agent;

@SuppressWarnings("serial")
public class Gui extends Agent {

	@Override
	protected void setup() {
		super.setup();

		System.out.println("Graphical Interface: " + getAID().getLocalName());

		BtList btlist = new BtList();
		
		Pantalla jj = new Pantalla("Bluetooth Simulation v.0.2",btlist);
		jj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jj.setSize(Config.DIMX, Config.DIMY);
		jj.setVisible(true);
	
		addBehaviour(new ReceiveRadio(btlist,jj));
		addBehaviour(new ReceiveScreen(btlist,jj));
		
		
		
		
	}


}
