package agents.behavs;

import config.Config;
import agents.Bee;
import agents.brain.BtMessage;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class EnvioFinal extends OneShotBehaviour {

	Bee agent;
	BtMessage MENSAJE;
	String MEDIO;
	
	
	public EnvioFinal(Agent a, BtMessage _btm, String _dest) {
		super(a);
		agent = (Bee)a;
		MENSAJE = _btm;
		MEDIO = _dest;
	}
	
	
	@Override
	public void action() {

//		System.out.println("---> " + MENSAJE.toString());
		
		ACLMessage m = new ACLMessage(ACLMessage.INFORM);
		m.setContent(MEDIO + "*" + MENSAJE.toString());
		m.setConversationId("MENSAJERIA");
		m.addReceiver(new AID(Config.Environment,AID.ISLOCALNAME));
		myAgent.send(m);
		

		
		
	}

}
