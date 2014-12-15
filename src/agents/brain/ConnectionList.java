package agents.brain;

import java.util.Hashtable;

public class ConnectionList {

	// IDMESSAGE , CONNECTED-DEVICE 
	Hashtable<String, String> LISTA;
	
	public ConnectionList() {
		LISTA = new Hashtable<String, String>();
	}
	
	public void addConnection(String idMessage, String device) {
		LISTA.put(idMessage, device);
	}
	
	public String getConnectionMessage(String idMessage) {
		return LISTA.get(idMessage);
	}
	
}
