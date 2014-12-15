package data.behavs;

import config.Config;
import data.Registro;
import data.Store;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

@SuppressWarnings("serial")
public class InsertResults extends OneShotBehaviour {

	Registro reg;
	Store ALMACEN;
	
	public InsertResults(Store _st,Agent a,String _idmessage, String _source, String _connection, String _message, boolean _ack) {
		super(a);
		reg = new Registro(_idmessage,_source,_connection,_message,Config.PENDIENTE,_ack,myAgent.getLocalName());
		ALMACEN = _st;
	}
	
	public InsertResults(Store _st,Agent a,String _idmessage, String _source, String _connection, String _message, int _codigo, boolean _ack) {
		super(a);
		reg = new Registro(_idmessage,_source,_connection,_message,_codigo,_ack,myAgent.getLocalName());
		ALMACEN = _st;
	}
	
	
	@Override
	public void action() {
		if (reg != null)
			ALMACEN.add(reg);
	}
}



