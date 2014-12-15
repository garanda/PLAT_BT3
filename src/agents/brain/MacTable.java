package agents.brain;


import java.util.Enumeration;
import java.util.Hashtable;

@SuppressWarnings("serial")
public class MacTable extends Hashtable<String, MacRecord> {


	
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		Enumeration<MacRecord> e = this.elements();
		if (e.hasMoreElements())   sb.append(e.nextElement().toString());
		while(e.hasMoreElements()) 
			 sb.append(", " + e.nextElement().toString());
		sb.append("]");
		return sb.toString();
	}

	public void cleanState() {
		// TODO LIMPIAR LA MACTABLE
			
		
	}
	
}
