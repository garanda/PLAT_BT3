package data;

public class Registro {
	public String IDMESSAGE,SOURCE,CONNECTION,MESSAGE;
	public int SUCCESS;
	public boolean ACK;
	public String FUENTE;

	
	public Registro(String _idmessage,String _source, String _connection,String _message, int _success,boolean _ack, String _FUENTE) {
		IDMESSAGE = _idmessage;
		SOURCE = _source;
		CONNECTION = _connection;
		MESSAGE = _message;
		SUCCESS = _success;
		ACK = _ack;
		FUENTE = _FUENTE;
	}
	
	public String toString() {
		return IDMESSAGE + " ; " + SOURCE + " ; " + CONNECTION + " ; " + MESSAGE + " ; " + SUCCESS + " ; " + ACK + " ; " + FUENTE;
	}
	
}
