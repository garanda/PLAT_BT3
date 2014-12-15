package environment.behavs;

import config.Config;
import data.Store;
import data.behavs.InsertResults;

import environment.brain.BtList;
import environment.brain.BtMessage;
import environment.brain.BtModule;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class MessageSystem extends CyclicBehaviour {

	BtList MTable;
	int contador = 0;
	Store ALMACEN;
	
	public MessageSystem(BtList mt, Store _st) {
		super();
		MTable = mt;
		ALMACEN = _st;
	}
	
	@Override
	public void action() {

		ACLMessage msj = myAgent.receive(MessageTemplate.MatchConversationId("MENSAJERIA"));
		if (msj != null) {
			
			String mensaje = msj.getContent();
			
			
			int pos =  mensaje.indexOf('*');
			
			if (pos<0) {
				System.out.println("MENSAJE ERRONEO: " + mensaje);
				myAgent.addBehaviour(new InsertResults(ALMACEN,myAgent, "", "", "", mensaje, Config.FORMATOERROR,false));
				
			} else {
				BtModule medio  =  MTable.findBt(msj.getSender().getLocalName());
				BtModule destino = MTable.findBt(mensaje.substring(0,pos));
				String resto   =   mensaje.substring(pos+1);
								
				if (Config.distance2(medio, destino) <= medio.getRadio() * medio.getRadio()) {
					ACLMessage nuevo = new ACLMessage(ACLMessage.INFORM);
					nuevo.setConversationId("MENSAJERIA");
					nuevo.addReceiver(new AID(destino.getName(),AID.ISLOCALNAME));
					
					//TODO: ESTO ES PARA LA lista de conexiones
					nuevo.setContent(medio.getName() +"*" + resto);
					myAgent.send(nuevo);
	
//					System.out.println(medio.getName() + " =======> " + destino.getName());
					
					BtMessage nnn = BtMessage.parseMessage(resto.toString());
					myAgent.addBehaviour(new InsertResults(ALMACEN,myAgent, nnn.getIDMessage() +"" , medio.getName(), destino.getName(), resto, Config.CORRECTO,nnn.isACK()));
					
					
				} else {
					BtMessage nnn = BtMessage.parseMessage(resto.toString());
					myAgent.addBehaviour(new InsertResults(ALMACEN,myAgent, nnn.getIDMessage() + "" , medio.getName(), destino.getName(), resto, Config.FUERARANGO,nnn.isACK()));

				}
			}
			
		} else 
			block();
		

	}
	
	
	
	
	
	
	
	
	
	
	

}
