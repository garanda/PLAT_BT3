package data;

import java.io.PrintWriter;
import java.util.*;

import java.util.Iterator;
import java.util.List;

import config.Config;
import es.us.tad.Ficheros;

/**
 * Create a store of messages 
 * 
 * @author gonzaloaranda
 *
 */
public class Store {

	private String S = config.Config.SALTOLINEA;
	
	private Hashtable<String, Registro> ALMACEN; 
	private List<Registro> HISTORICO;
	
	
	/**
	 * Constructor.
	 * Initalize Store
	 */
	public Store() {
		ALMACEN    = new Hashtable<String,Registro>();		
		HISTORICO  = new LinkedList<Registro>();
	}
	
	/**
	 * Save all registers into the filename file.
	 * Experiment directory 
	 * 
	 * @param filename name of file
	 */
	public void save(String filename) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(Config.DIR_EXPERIMENTA_SALIDA + filename, "UTF-8");
			writer.print("Total mensajes:" + HISTORICO.size() + S + S);
			writer.print("IDMESSAGE ; SOURCE ; CONNECTION ; MESSAGE ; SUCCESS ; ACK ; FUENTE"+S);
			
			Iterator<Registro> it = HISTORICO.iterator();
			while(it.hasNext()) {
				Registro r = it.next();
				writer.print(r.toString() + S);
			} 
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Search a message inside the store and returns it, if exists
	 * @param _id   idmessage
	 * @param _ack  boolean for acknowledge 
	 * @return the selected register, or null if it is not exists.
	 */
	public Registro search(String _id, boolean _ack) {
		return ALMACEN.get(_id+_ack);
	}

	public boolean add(Registro _r) {
		ALMACEN.put(_r.IDMESSAGE+_r.ACK, _r);
		HISTORICO.add(_r);
		return true;
	}
	
	public Iterator<Registro> iterator() {
		return HISTORICO.iterator();
	}
	
	public void saveDot() {
		int contador=1;
		StringBuffer sb = new StringBuffer("digraph GRAFO {" + S);
		Iterator<Registro> it = HISTORICO.iterator();
		while(it.hasNext()){
			Registro r = it.next();
			
			sb.append(r.SOURCE + "_" + r.IDMESSAGE + " -> " + r.CONNECTION + "_" + r.IDMESSAGE + " [label=" + contador++ + "];"+S);
			
		}
		
		sb.append("}" + S);
		
		Ficheros f = new Ficheros(Config.DIR_EXPERIMENTA_SALIDA + "xxx.dot");
		f.Grabar(sb.toString());

		
		System.out.println(sb.toString());
		
	}

	
	
}
