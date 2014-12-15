package app.behavs;

import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;

@SuppressWarnings("serial")
public class CreateAgent extends OneShotBehaviour {

	String NAME;
	String CLASS; 
	Object[] ARGS;
	
	public CreateAgent(String name, String clas, Object[] arg) {
		super();
		NAME = name;
		CLASS = clas;
		ARGS = arg;
	}
	
	
	public void action() {
		PlatformController plataforma = myAgent.getContainerController();
		AgentController control;
		
		try {
			control = plataforma.createNewAgent(NAME, CLASS, ARGS);
			control.start();
		} catch (ControllerException e) {
			e.printStackTrace();
		}	
		
	}

}
