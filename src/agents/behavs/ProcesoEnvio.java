package agents.behavs;

import java.util.Enumeration;

import config.Config;

import agents.Bee;
import agents.brain.BtMessage;
import agents.brain.MacRecord;
import agents.brain.MacTable;
import agents.brain.States;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class ProcesoEnvio extends Behaviour {

	Bee agent;
	String Connect;
	int estado = 0;
	BtMessage MENSAJE;
	MacTable MT;
	
	public ProcesoEnvio(Agent a,BtMessage _btm, String _conn) {
		super(a);
		agent = (Bee)a;
		MENSAJE = _btm;
		MT = agent.getMacTable();
		Connect = _conn;
	}
	
	
	@Override
	public void action() {

//		String origen  = MENSAJE.getSource();
		String destino = MENSAJE.getTarget();

		MacRecord mr = MT.get(destino);
		if (mr == null) {
			
			/**
			 * CASO 1: FUERA DE LA TABLA 
			 ***/
			if (Config.DEBUG) System.out.println("DESTINO FUERA DE TABLA");
			Enumeration<MacRecord> e = agent.getMacTable().elements();
			while (e.hasMoreElements()) {
				MacRecord temp = e.nextElement();
				if (temp.getNAME().equalsIgnoreCase(Connect)) {
				} else					
					if (temp.getState() == States.ACCESIBLE) {
						agent.addBehaviour(new EnvioFinal(agent,MENSAJE,temp.getNAME()));
					}
			}
			
		} else {

			if (mr.getState() == States.ACCESIBLE) {
				agent.addBehaviour(new EnvioFinal(agent,MENSAJE,mr.getNAME()));

			} else if (mr.getState() == States.REMOTO) {
				agent.addBehaviour(new EnvioFinal(agent,MENSAJE,mr.getMEDIO()));
				
			} else {
				if (Config.DEBUG) System.out.println("Unexpected Exception: Node state not avaliable");
			}
			
		}
		
	}

	
	@Override
	public boolean done() {
		return true;
	}

}
