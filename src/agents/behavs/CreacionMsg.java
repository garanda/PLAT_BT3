package agents.behavs;

import config.Config;
import data.behavs.InsertResults;
import agents.Bee;
import agents.brain.BtMessage;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

@SuppressWarnings("serial")
public class CreacionMsg extends TickerBehaviour {

	Bee agent;
	int contador = 1;
	
	public CreacionMsg(Agent a, long period) {
		super(a, period);
		agent = (Bee)a;
	}

	@Override
	protected void onTick() {

		BtMessage btm = new BtMessage(agent.getLocalName(), agent.getTarget());
		btm.setIDMESSAGE();
		//agent.getMessageList().addConnection(btm.getIDMESSAGE(), btm.getIDMESSAGE());
		myAgent.addBehaviour(new ProcesoEnvio(myAgent,btm,""));
		myAgent.addBehaviour(new InsertResults(agent.ALMACEN,myAgent, btm.getIDMESSAGE(), btm.getSource(), btm.getTarget(), "mensaje unico",false));

		
		// TODO BORRAR CUANDO CERTIFIQUEMOS EL ENVIO IDA Y VUELTA y el uso de la tabla mac
		if (contador == Config.REPETICIONMSG)	{
			myAgent.removeBehaviour(this);
			
		}
		else
			contador++;
		System.out.println("Eliminar contador: " + contador);
		
		
		
	}

}
