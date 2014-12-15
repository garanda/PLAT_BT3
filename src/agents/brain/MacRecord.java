package agents.brain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"rawtypes","unchecked"})
public class MacRecord {

	String NAME;
	String MEDIO;
	int    STATE;

	public MacRecord(String _n) {
		NAME = _n;
		MEDIO = "";
		STATE = States.INACCESIBLE;
	}
	public MacRecord(String _target, String _medio, int _state) {
		NAME = _target;
		MEDIO = "";
		STATE = _state;
	}

	
	public String getMEDIO() {
		return MEDIO;
	}


	public void setMEDIO(String mEDIO) {
		MEDIO = mEDIO;
	}


	public String getNAME() {
		return NAME;
	}

	public int getState() {
		return STATE;
	}
	
	public void setState(int _state) {
		STATE = _state;
	}
	
	
	public String toString() {
		String devolver =  NAME + "(" + STATE + ")[" + MEDIO + "]";
		return devolver;
	}
	
	

}
