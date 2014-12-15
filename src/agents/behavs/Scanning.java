package agents.behavs;

import java.util.StringTokenizer;

import config.Config;

import agents.Bee;
import agents.brain.MacRecord;
import agents.brain.MacTable;
import agents.brain.States;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Scanning extends Behaviour {

	private int ESTADO = 0;
	Bee agent;
	
	public Scanning(Agent a) {
		super(a);
		agent = (Bee)a;
		
	}


	@Override
	public void action() {

		switch(ESTADO) {
		
		case 0:
			ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
			m.setContent("SCAN");
			m.setConversationId("SCAN");
			m.addReceiver(new AID(Config.Environment,AID.ISLOCALNAME));
			myAgent.send(m);
			ESTADO = 1;
			break;
			
		case 1:	
			ACLMessage msg = myAgent.receive(MessageTemplate.MatchConversationId("SCAN"));
			if (msg != null) {
				String contenido = msg.getContent();
				MacTable mt = agent.getMacTable();
				//mt.cleanState();
				
				StringTokenizer st = new StringTokenizer(contenido,",");
				while (st.hasMoreTokens()) {
					String nombre = st.nextToken();
					nombre = nombre.substring(0,nombre.indexOf(':'));
					MacRecord ma = mt.get(nombre);
					if (ma == null) {
						ma = new MacRecord(nombre);
						mt.put(nombre, ma);
					}
					ma.setState(States.ACCESIBLE);
				}
				
				ESTADO = 2;
				
 			} else 
 				block();
		
			break;
				
		
		}
	}


	@Override
	public boolean done() {
		return ESTADO == 2;
	}
	
	
}



