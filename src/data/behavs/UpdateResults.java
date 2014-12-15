package data.behavs;

import data.Registro;
import data.Store;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

@SuppressWarnings("serial")
public class UpdateResults extends OneShotBehaviour {

	String idmessage;
	int success;
	boolean ack;
	Store ALMACEN;

	
	public UpdateResults(Store _st,Agent a,String _idmessage, int _result, boolean _ack) {
		super(a);
		idmessage = _idmessage;
		success = _result;
		ack = _ack;
		ALMACEN = _st;
	}
	
	
	@Override
	public void action() {
		Registro r = ALMACEN.search(idmessage, ack);
		if (r != null) {
			r.SUCCESS = success;
		}
				
	}
}



