package environment.brain;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class BtMessage {

	private BtModule SOURCE, TARGET,FINALTARGET;
//	private List<BtModule> VISITORS;
	private String CONTENT;
	private int IDMESSAGE;
	private boolean ACK;
	
	public BtMessage() {
//		VISITORS = new LinkedList<BtModule>(); 
	}

	/**
	 * @return the sOURCE
	 */
	public BtModule getSOURCE() {
		return SOURCE;
	}

	/**
	 * @param sOURCE the sOURCE to set
	 */
	public void setSOURCE(BtModule sOURCE) {
		SOURCE = sOURCE;
	}

	/**
	 * @return the tARGET
	 */
	public BtModule getTARGET() {
		return TARGET;
	}

	/**
	 * @param tARGET the tARGET to set
	 */
	public void setTARGET(BtModule tARGET) {
		TARGET = tARGET;
	}

//	/**
//	 * @return the vISITORS
//	 */
//	public List<BtModule> getVISITORS() {
//		return VISITORS;
//	}
//
//	/**
//	 * @param vISITORS the vISITORS to set
//	 */
//	public void setVISITORS(List<BtModule> vISITORS) {
//		VISITORS = vISITORS;
//	}

	/**
	 * @return the cONTENT
	 */
	public String getCONTENT() {
		return CONTENT;
	}

	/**
	 * @param cONTENT the cONTENT to set
	 */
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	
	
	public int getIDMessage() {
		return IDMESSAGE;
	}

	
	/**
	 * 
	 * @param mens
	 * @return
	 */
	public static BtMessage parseMessage(String mens) {
		BtMessage devolver = new BtMessage();

//		System.out.println("**** => " + mens);

		StringTokenizer st = new StringTokenizer(mens,";");
		//devolver.TARGET = new BtModule(st.nextToken());
		devolver.SOURCE = new BtModule(st.nextToken());
		devolver.FINALTARGET = new BtModule(st.nextToken());

//		StringTokenizer m = new StringTokenizer(st.nextToken(),",[]");
//		while (m.hasMoreTokens()) {
//			devolver.VISITORS.add(new BtModule(m.nextToken()));
//		}
		if (st.hasMoreTokens())
			devolver.CONTENT = st.nextToken();
		else
			devolver.CONTENT = "";
		if (st.hasMoreTokens())
			devolver.IDMESSAGE = Integer.parseInt(st.nextToken());
		else
			devolver.IDMESSAGE = -1;

		if (st.hasMoreTokens())
			devolver.ACK = (Integer.parseInt(st.nextToken())==1?true:false);
		else
			devolver.ACK = false;

		
		
		
		
		
		
		return devolver;
	}

	/**
	 * @return the fINALTARGET
	 */
	public BtModule getFINALTARGET() {
		return FINALTARGET;
	}

	/**
	 * @param fINALTARGET the fINALTARGET to set
	 */
	public void setFINALTARGET(BtModule fINALTARGET) {
		FINALTARGET = fINALTARGET;
	}
	
	public String prettyPrint() {
		StringBuffer sb = new StringBuffer();
		sb.append("SOURCE:  " + SOURCE + "\n"); 
		//sb.append("TARGET:  " + TARGET + "\n"); 
		sb.append("FTARGET: " + FINALTARGET + "\n"); 
//		sb.append("VISITOR: " + VISITORS + "\n"); 
		sb.append("CONTENT: " + CONTENT); 
		sb.append("ID: " + IDMESSAGE); 
		return sb.toString();
	}
	
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(SOURCE.getName() + ";");
		sb.append(FINALTARGET.getName() + ";");
//		sb.append(VISITORS + ";");
		sb.append(CONTENT + ";");
		sb.append(IDMESSAGE + ";");
		sb.append((ACK?1:0) + ";");
		return sb.toString();
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
