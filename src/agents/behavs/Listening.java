package agents.behavs;

import config.Config;
import data.behavs.InsertResults;
import data.behavs.UpdateResults;
import agents.Bee;
import agents.brain.BtMessage;
import agents.brain.ConnectionList;
import agents.brain.MacRecord;
import agents.brain.MacTable;
import agents.brain.States;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Listening extends CyclicBehaviour implements States {

	private Bee agent;
//	private MacTable MT;
	private ConnectionList ML;
	
	public Listening(Agent a) {
		super(a);
		agent = ((Bee)a);
//		MT = agent.getMacTable();
		ML = agent.getMessageList();
	}

	
	@Override
	public void action() {

		ACLMessage mens = myAgent.receive(MessageTemplate.MatchConversationId("MENSAJERIA"));
		if (mens != null) {
			
			// ESTOS SON LOS DATOS DE LA CONEXION
			int pos = mens.getContent().indexOf("*");
			String btconectado = mens.getContent().substring(0,pos);
			String resto = mens.getContent().substring(pos+1);
			BtMessage btm = BtMessage.parseMessage(resto);
			actualizaMacTable(agent,btconectado,btm.getSource()); 

			if (!btm.isACK()) {
				String ids = ML.getConnectionMessage(btm.getIDMESSAGE());
				if (ids == null){
					ML.addConnection(btm.getIDMESSAGE(), btconectado);
					if (btm.getTarget().equalsIgnoreCase(agent.getLocalName())) {
						// ES PARA MI
						myAgent.addBehaviour(new UpdateResults(agent.ALMACEN,myAgent, btm.getIDMESSAGE(), Config.RECIBIDO,false));
						//ENVIO ACK
						BtMessage ack = new BtMessage(btm.getTarget(), btm.getSource());
						ack.setACK(true);
						ack.setIDMESSAGE(btm.getIDMESSAGE());
						agent.addBehaviour(new ProcesoEnvio(agent, ack,btconectado));
						myAgent.addBehaviour(new InsertResults(agent.ALMACEN,myAgent, btm.getIDMESSAGE(), btm.getTarget(), btm.getSource(), "mensaje vuelta",true));

					} else {
						agent.addBehaviour(new ProcesoEnvio(agent, btm,btconectado));
					}
				} else {
					//	MENSAJE REPETIDO

				}
			} else {
				if (btm.getTarget().equalsIgnoreCase(agent.getLocalName())) {
					// ES PARA MI
					myAgent.addBehaviour(new UpdateResults(agent.ALMACEN,myAgent, btm.getIDMESSAGE(), Config.RECIBIDO,true));

				} else {
					// RECIBO UN ACK Y NO ES PARA MI!....
					String ids = ML.getConnectionMessage(btm.getIDMESSAGE());
					agent.addBehaviour(new EnvioFinal(agent,btm,ids));
//					agent.addBehaviour(new ProcesoEnvio(agent,btm,ids));

				}
				
				
			}
			
		} else
			block();
		
	}


	private void actualizaMacTable(Bee agent2, String btconectado, String source) {

		MacTable mt = agent2.getMacTable();

		MacRecord mr = mt.get(source); 
		if ( mr == null) {
			mr = new MacRecord(source, btconectado, REMOTO);
			mr.setMEDIO(btconectado);
			mt.put(source, mr);
		} else {
			mr.setMEDIO(btconectado);
			mr.setState(REMOTO);
		}

		mr = mt.get(btconectado); 
		if ( mr == null) {
			mr = new MacRecord(btconectado);
			mr.setState(ACCESIBLE);
			mt.put(source, mr);
		} else {
			mr.setMEDIO("");
			mr.setState(ACCESIBLE);
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
