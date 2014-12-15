package environment.behavs;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import config.Config;


import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import environment.brain.BtList;
import environment.brain.BtModule;
import environment.brain.SortedModule;


@SuppressWarnings("serial")
public class ReceiveScan extends CyclicBehaviour {

	BtList MTable;
	int contador = 0;
	
	public ReceiveScan(BtList mt) {
		super();
		MTable = mt;
	}
	
	@Override
	public void action() {
		
		ACLMessage msj = myAgent.receive(MessageTemplate.MatchConversationId("SCAN"));
		if (msj != null) {
			
			// Coger Posicion y radio del agente
			BtModule ap = MTable.findBt(msj.getSender().getLocalName());

			String answer = "";
			if (ap != null) {
				
				double r2 = ap.getRadio() * ap.getRadio(); 
				
				List<SortedModule> ss = new LinkedList<SortedModule>();
				Enumeration<BtModule> e = MTable.elements();
				while (e.hasMoreElements()) {
					BtModule pos = e.nextElement();
					double d = Config.distance2(pos,ap);
					if (pos != ap && d<r2) {
						ss.add(new SortedModule(pos.getName(),d));
					}
					
				}
				Collections.sort(ss);
				Iterator<SortedModule> it = ss.iterator();
				while (it.hasNext()) {
					SortedModule op = it.next();
					answer += op.getName() + ":" + op.getValue() + ",";
					
					
					
				}
				
				
			}
//			System.out.println("--> " + answer);
			ACLMessage ans = msj.createReply();
			ans.setContent(answer);
			myAgent.send(ans);
			
			
			
			
			
		} else 
			block();
		
		
		
		
		
		
		
	}


	
	
	
	
	
	
	
	
	
}
