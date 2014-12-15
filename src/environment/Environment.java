package environment;

import java.util.StringTokenizer;

import config.Config;
import data.Store;
import environment.brain.BtList;
import environment.behavs.MessageSystem;
import environment.behavs.ReceivePos;
import environment.behavs.ReceiveRadio;
import environment.behavs.ReceiveScan;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class Environment extends Agent {

	
	private BtList MTable;
	Store ALMACEN;
	
	
	@Override
	protected void setup() {
		super.setup();

		MTable = new BtList();
		ALMACEN = new Store();
		
		System.out.println("Building environment: " + getAID().getLocalName());
		System.out.println("Environment size: " + Config.DIMX + ", " + Config.DIMY);
			
		/*****  SCAN LISTENING  ***/
		this.addBehaviour(new MessageSystem(MTable,ALMACEN));
		this.addBehaviour(new ReceiveRadio(MTable));
		this.addBehaviour(new ReceiveScan(MTable));
		this.addBehaviour(new ReceivePos(MTable));
		
	}

	
	
	@Override
	protected void takeDown() {
		super.takeDown();
		System.out.print("Env: ");
		System.out.println(MTable.toString());
		ALMACEN.save(this.getLocalName() + "_log.csv");
	}

	
	public BtList getBtList() {
		return MTable;
	}
	

	

}
