package agents.brain;


import java.util.*;

public class BtMessage {
	
	
	private static int CONTADOR = 0;
	private static int getNewId() {
		return ++CONTADOR;
	}
	
	
	private String SOURCE, TARGET;
	private String CONTENT="(empty)";
	private String IDMESSAGE = "-1";
	private boolean ACK  = false;
		
	public BtMessage(String _source, String _target) {
		SOURCE = _source;
		TARGET = _target;
		CONTENT = "(empty)	";
		IDMESSAGE = "-1";
		ACK  = false;
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
		sb.append(CONTENT + ";");
		sb.append(IDMESSAGE + ";");
		sb.append((ACK?1:0) + ";");
		return sb.toString();
	}
	
	public static BtMessage parseMessage(String mens) {
		StringTokenizer st = new StringTokenizer(mens,";");
		BtMessage devolver = new BtMessage(st.nextToken(),st.nextToken());		
		devolver.CONTENT = (st.hasMoreTokens()?st.nextToken():"");
		devolver.IDMESSAGE = (st.hasMoreTokens()?st.nextToken():"");
		devolver.ACK = (st.hasMoreTokens()?Integer.parseInt(st.nextToken())==1:false);
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
		IDMESSAGE = BtMessage.getNewId()+"";
	}
	/**
	 * @param iDMESSAGE the iDMESSAGE to set
	 */
	public void setIDMESSAGE(String id) {
		IDMESSAGE = id;
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
