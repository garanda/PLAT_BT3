package environment.brain;


public class SortedModule implements Comparable {

	String Name;
	double Value;
	
	
	public String getName() {
		return Name;
	}
	public double getValue() {
		return Value;
	}
	public SortedModule(String n, double v) {
		Name = n;
		Value = v;
	}
	

	@Override
	public String toString() {
		
		return Name + "(" + Value + ")";
	}
	@Override
	public int compareTo(Object o) {
		double t = ((SortedModule)o).Value - this.Value;
		if (t>0) return -1;
		if (t<0) return 1;
		return 0;
		
	}

}

