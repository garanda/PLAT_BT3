package environment.behavs;

import java.util.StringTokenizer;

import environment.brain.BtList;
import environment.brain.BtModule;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ReceivePos extends CyclicBehaviour {

	BtList MTable;
	int contador = 0;
	
	public ReceivePos(BtList mt) {
		super();
		MTable = mt;
	}
	
	@Override
	public void action() {

		ACLMessage msj = myAgent.receive(MessageTemplate.MatchConversationId("POS"));
		if (msj != null) {
			BtModule bt = MTable.findBt(msj.getSender().getLocalName());
			StringTokenizer st = new StringTokenizer(msj.getContent(),", ");
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			bt.setLocation(x, y);
		} else 
			block();
		
		
		
		
		
		
		
	}

}
