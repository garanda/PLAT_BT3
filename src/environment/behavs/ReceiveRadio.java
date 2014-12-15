package environment.behavs;

import java.util.StringTokenizer;

import environment.brain.BtList;
import environment.brain.BtModule;

import gui.graphical.Pantalla;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ReceiveRadio extends CyclicBehaviour {

	BtList MTable;
	int contador = 0;
	
	public ReceiveRadio(BtList mt) {
		super();
		MTable = mt;
	}
	
	@Override
	public void action() {

		ACLMessage msj = myAgent.receive(MessageTemplate.MatchConversationId("RADIO"));
		if (msj != null) {
			MTable.
				findBt(msj.getSender().getLocalName()).
					setRadio(Integer.parseInt(msj.getContent()));
		} else 
			block();
		
		
		
		
		
		
		
	}

}
