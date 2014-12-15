package agents.brain;


import java.util.*;

public class BtMessage_OLD {
	private static int CONTADOR = 0;
	private static int getNewId() {
		return ++CONTADOR;
	}
	
	
	
	
	
	private String SOURCE, TARGET;
	private List<String> VISITED;
	private String CONTENT;
	private String IDMESSAGE = "-1";
	private boolean ACK  = false;
	
	
	
	
	private BtMessage_OLD() {
		VISITED = new LinkedList<String>();
		CONTENT = "null";
		IDMESSAGE = "-1";
	}
	
	public BtMessage_OLD(String _source, String _target) {

		SOURCE = _source;
		TARGET = _target;
		CONTENT = "null";
		
		VISITED = new LinkedList<String>();
		VISITED.add(_source);
		IDMESSAGE = "-1";
	}

	
	public void addVisitor(String v) {
		VISITED.add(v);
	}
	
	public String getSource() {
		return SOURCE;
	}
	
	public String getTarget() {
		return TARGET;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(SOURCE + ";");
		sb.append(TARGET + ";");
		sb.append(VISITED + ";");
		sb.append(CONTENT + ";");
		sb.append(IDMESSAGE + ";");
		return sb.toString();
	}
	
	public static BtMessage_OLD parseMessage(String mens) {
		BtMessage_OLD devolver = new BtMessage_OLD();
		
		StringTokenizer st = new StringTokenizer(mens,";");
		devolver.SOURCE = st.nextToken();
		devolver.TARGET = st.nextToken();

		StringTokenizer m = new StringTokenizer(st.nextToken(),",[]");
		while (m.hasMoreTokens()) {
			devolver.VISITED.add(m.nextToken());
		}
		if (st.hasMoreTokens())
			devolver.CONTENT = st.nextToken();
		else 
			devolver.CONTENT = "";
		devolver.IDMESSAGE = st.nextToken();

		return devolver;
	}

	/**
	 * @return the iDMESSAGE
	 */
	public String getIDMESSAGE() {
		return IDMESSAGE;
	}

	/**
	 * @param iDMESSAGE the iDMESSAGE to set
	 */
	public void setIDMESSAGE() {
		IDMESSAGE = BtMessage_OLD.getNewId()+"";
	}

	/**
	 * @return the aCK
	 */
	public boolean isACK() {
		return ACK;
	}

	/**
	 * @param aCK the aCK to set
	 */
	public void setACK(boolean aCK) {
		ACK = aCK;
	}
	
	
	
	
	
	
	
}
