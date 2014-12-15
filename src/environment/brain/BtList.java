package environment.brain;

import java.util.Enumeration;
import java.util.Hashtable;

@SuppressWarnings("serial")
public class BtList extends Hashtable<String,BtModule> {

	@Override
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer("[");
		Enumeration<BtModule> e = this.elements();
		if (e.hasMoreElements())   sb.append(e.nextElement().toString());
		while(e.hasMoreElements()) 
			 sb.append(", " + e.nextElement().toString());
		sb.append("]");
		return sb.toString();
	}
	
	
	public BtModule findBt(String name) {
		BtModule r = this.get(name);
		if (r==null) {
			r = new BtModule(name);
			this.put(name, r);
		}
		return r;
	}
	
	
}