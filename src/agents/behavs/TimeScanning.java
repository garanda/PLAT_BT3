package agents.behavs;

import agents.Bee;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

@SuppressWarnings("serial")
public class TimeScanning extends TickerBehaviour {

	Bee agent;
	
	
	public TimeScanning(Agent a, long period) {
		super(a, period);
		agent = (Bee)a;
	}

	@Override
	protected void onTick() {
		
		myAgent.addBehaviour(new Scanning(myAgent));
	}

}
