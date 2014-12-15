package agents.behavs;

import config.Config;
import agents.Bee;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class SendRadio extends OneShotBehaviour {

	@Override
	public void action() {
		ACLMessage m = new ACLMessage(ACLMessage.INFORM);
		m.setContent(((Bee)myAgent).getRadio() + "");
		m.addReceiver(new AID(Config.Gui,AID.ISLOCALNAME));
		m.addReceiver(new AID(Config.Environment,AID.ISLOCALNAME));
		m.setConversationId("RADIO");
		myAgent.send(m);
	}

}
