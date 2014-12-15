package agents;

import java.io.PrintWriter;

import config.Config;
import data.Store;

import agents.behavs.CreacionMsg;
import agents.behavs.Listening;
import agents.behavs.MovePause;
import agents.behavs.SendRadio;
import agents.behavs.TimeScanning;
import agents.brain.ConnectionList;
import agents.brain.MacTable;
import jade.core.Agent;

@SuppressWarnings("serial")
public class Bee extends Agent {


	private double posx, posy;
	private int radio;
	private boolean source = false;
	private String TARGET;
	private MacTable   MTable;
	
	private ConnectionList MessageList;
	
	private int PauseTime = -1;
	private int MoveTime = -1;
	private double step = -1d;
	
	private int MINUMERO = -1;
	
	public Store ALMACEN;

	@Override
	protected void setup() {
		super.setup();

		ALMACEN = new Store();
		
		if (Config.DEBUG) System.out.println("Creating agent " + getLocalName() + "...");
		
		MTable = new MacTable();
		
		posx = Math.random()*Config.DIMX;
		posy = Math.random()*Config.DIMY;

		// Valores por defecto
		radio = Config.RADIOAGENT;
		step = Config.STEPAGENT;
		
		Config.read_params_Agents(this);

		MINUMERO = Integer.parseInt(getAID().getLocalName().substring(Config.ROOTNAMES.length()));
		
		if (TARGET == null) {
			TARGET = Config.ROOTNAMES + (int)(Math.random()*Config.SOURCES);
		}
		
		
		
		System.out.println(MINUMERO + " -- " + Config.SOURCES + " -- " + TARGET);
		
		
		if (MINUMERO < Config.SOURCES) 
			source  = true;
		
		MessageList = new ConnectionList();
		this.addBehaviour(new SendRadio());
		this.addBehaviour(new Listening(this));
		this.addBehaviour(new TimeScanning(this,1000));
		if (source)
			this.addBehaviour(new CreacionMsg(this, 1000));

		if (Config.DEBUG) System.out.println(getLocalName() + "... created");
		
		this.addBehaviour(new MovePause(this, this.PauseTime));
	}

	
	
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		super.takeDown();
		PrintWriter writer;
		try {
			writer = new PrintWriter(Config.DIR_EXPERIMENTA_SALIDA + this.getLocalName() + ".log", "UTF-8");

			writer.println("Este es el takedown de " + getLocalName() + ": ");
			writer.println("SOURCE: " + this.source);
			writer.println("TARGET: " + this.TARGET);
						
			writer.println("MAC TABLE");
			writer.print(getMacTable().toString());
			writer.close();

			ALMACEN.save(this.getLocalName() + ".store.log");
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



//	public String getNAME() {
//		return NAME;
//	}

	public MacTable getMacTable() {
		return MTable;
	}


	public double getPosX() {
		return posx;
	}
	
	public double getPosY() {
		return posy;
	}
	
	public String getTarget() {
		return TARGET;
	}
	
	public int getRadio() {
		return radio;
	}
	
	public boolean isSource() {
		return source;
	}
	
	public void setPosX(double x) {
		posx = x;
	}
	
	public void setPosY(double y) {
		posy = y;
	}
	
	public void setRadio(int r) {
		radio = r;
	}
	
	public void setStep(double s) {
		step = s;
	}
	
	public void setSource(String _target) {
		source = true;
		TARGET = _target;
	}

	/**
	 * @return the messageList
	 */
	public ConnectionList getMessageList() {
		return MessageList;
	}


	/**
	 * if 
	 * @return the pauseTime
	 */
	public int getPauseTime() {
		if (this.PauseTime<0)
			return Config.PAUSETIME;
		return PauseTime;
	}

	/**
	 * @param pauseTime the pauseTime to set
	 */
	public void setPauseTime(int pauseTime) {
		PauseTime = pauseTime;
	}


	/**
	 * @return the moveTime
	 */
	public int getMoveTime() {
		if (this.MoveTime<0)
			return Config.MOVETIME;
		return MoveTime;
	}
	/**
	 * @param moveTime the moveTime to set
	 */
	public void setMoveTime(int moveTime) {
		MoveTime = moveTime;
	}

	public double getStep() {
		if (this.step<0)
			return Config.STEPAGENT;
		return this.step;
	}
	

	
}
