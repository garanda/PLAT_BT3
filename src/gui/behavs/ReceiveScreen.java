package gui.behavs;

import gui.graphical.Pantalla;

import java.util.StringTokenizer;

import environment.brain.BtList;
import environment.brain.BtModule;


import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiveScreen extends CyclicBehaviour {

	Pantalla scr;
	BtList Lista;
	int contador = 0;
	public ReceiveScreen(BtList _btl, Pantalla p) {
		super();
		Lista = _btl;
		scr = p;
	}
	
	@Override
	public void action() {
//		System.out.println("Waiting for message.");
		ACLMessage msj = myAgent.receive(MessageTemplate.MatchConversationId("POS"));
		if (msj != null) {
			StringTokenizer st = new StringTokenizer(msj.getContent(),", ");
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			BtModule bt = Lista.findBt(msj.getSender().getLocalName());
			bt.setLocation(x, y);
//			bt.repaint();
			scr.repaint();
		} else 
			block();
		
		
		
		
		
		
		
	}

}
