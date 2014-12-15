package agents.behavs;

import config.Config;
import agents.Bee;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class MovePause extends DelayBehaviour2{

	Bee P;
	
	
	private double finalX, finalY;
	private double actualX, actualY,angle;
	private double incx,incy;
	private int quad;

	
	
	
	
	public MovePause(Agent a, long timeout) {
		super(a, timeout);
		P = (Bee)a;
	}

	@Override
	protected void handleElapsedTimeout() {

		if (meta()){
//			System.out.println("Arranco el pause");
			myAgent.addBehaviour(new Espera(myAgent, P.getPauseTime()));
		}
		else {
			
//			System.out.println(" --> ( " + P.getPosX() + " , " + P.getPosY() + " )");
			
			
			movimiento();
			
			// ENVIO DE LA POSICION A ENTORNO Y GUI
			ACLMessage m = new ACLMessage(ACLMessage.INFORM);
			m.setContent(P.getPosX() + ", " + P.getPosY());
			m.addReceiver(new AID(Config.Gui,AID.ISLOCALNAME));
			m.addReceiver(new AID(Config.Environment,AID.ISLOCALNAME));
			m.setConversationId("POS");
			myAgent.send(m);


			
			
			this.reset(P.getMoveTime());
		}
		
	}

	private boolean meta() {
		return (Math.abs(actualX-finalX)<P.getStep()*2 && Math.abs(actualY-finalY)<P.getStep()*2);
	}

	@Override
	public void onStart() {
		super.onStart();
//		System.out.println("Arranca a andar!");
		
		finalX = (Math.random()*Config.DIMX);
		finalY = (Math.random()*Config.DIMY);

		actualX = P.getPosX();
		actualY = P.getPosY();
		
		angle = Math.atan((finalY-actualY)/(finalX-actualX));
		incx = P.getStep() * Math.abs(Math.cos(angle));
		incy = P.getStep() * Math.abs(Math.sin(angle));
		if ((finalY-actualY)>0)
			quad = ((finalX-actualX)>0?1:2);
		else
			quad = ((finalX-actualX)>0?4:3);
	}	
	
	private void movimiento() {

		switch(quad) {
		case 1:
			actualX += incx;
			actualY += incy;
			break;
		case 2:
			actualX -= incx;
			actualY += incy;
			break;
		case 3:
			actualX -= incx;
			actualY -= incy;
			break;
		case 4:
			actualX += incx;
			actualY -= incy;
			break;

		}


		P.setPosX(actualX);
		P.setPosY(actualY);

	}
	
	
}

@SuppressWarnings("serial")
class Espera extends DelayBehaviour2 {

	Bee P;
	public Espera(Agent a,long time) {
		super(a,time);
		P = (Bee)a;
	}
	protected void handleElapsedTimeout() {
//		System.out.println("Termina ESPERA");
		myAgent.addBehaviour(new MovePause(myAgent, P.getMoveTime()));
			
		
	}

	
	
}


@SuppressWarnings("serial")
abstract class DelayBehaviour2 extends SimpleBehaviour 
{
	private long    timeout, 
	                wakeupTime;
	private boolean finished;
	
	public DelayBehaviour2(Agent a, long timeout) 
	{
		super(a);
		this.timeout = timeout;
		finished = false;
	}
	
	public void onStart() {
		wakeupTime = System.currentTimeMillis() + timeout;
	}
		
	public void action() 
	{
		long dt = wakeupTime - System.currentTimeMillis();
		if (dt <= 0) {
			finished = true;
			handleElapsedTimeout();
		} else 
			block(dt);
			
	} //end of action
	
	protected abstract void handleElapsedTimeout();
	
	public void reset(long timeout) {
	  wakeupTime = System.currentTimeMillis() + timeout ;
	  finished = false;
	}
	
	public boolean done() {
	  return finished;
	}
}
