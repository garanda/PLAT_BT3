package gui.behavs;

import gui.graphical.Pantalla;
import environment.brain.BtList;
import environment.brain.BtModule;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ReceiveRadio extends CyclicBehaviour {

	BtList Lista;
	Pantalla scr;
	
	public ReceiveRadio(BtList _list, Pantalla p) {
		super();
		Lista = _list;
		scr = p;
	}
	
	@Override
	public void action() {
		ACLMessage msj = myAgent.receive(MessageTemplate.MatchConversationId("RADIO"));
		if (msj != null) {
			BtModule b = Lista.findBt(msj.getSender().getLocalName());
			b.setRadio(Integer.parseInt(msj.getContent()));
//			b.repaint();
			scr.repaint();
		} else 
			block();
		
	}

}
