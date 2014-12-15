package config;

import java.io.File;
import java.util.StringTokenizer;

import agents.Bee;

import environment.brain.BtModule;
import es.us.tad.Ficheros;

public class Config {

	public static String SALTOLINEA = System.getProperty("line.separator");
	public static String BARRA = System.getProperty("file.separator");
	public static final boolean DEBUG = false;
	
	public static int NAGENTES = 2;
	
	public static int DIMX = 200;
	public static int DIMY = 200;
	
	public static int RADIOAGENT = 300;
	public static int PAUSETIME= 500;
	public static final int SPEEDSIMULATION= 5;
	public static double STEPAGENT = 2d;
	public static int MOVETIME  = 100;


	/***    REUNION TOMAS  ****/
	public static int SOURCES = 10;   // 10, 20 o 30
	public static final int CBR     = 2;    // 1, 4, u 8  Constant bit rate

	
	/***    MENSAJERIA   ****/
	public static final int FORMATOERROR = -3;
	public static final int FUERARANGO   = -2;
	public static final int CORRECTO     = 1;

	public static final int PENDIENTE = -1;
	public static final int RECIBIDO = 1;
	
	public static final int REPETICIONMSG = 3;
	
	
	
	
	
	/*** COSAS DE LA EXPERIMENTACION  ***/
	
	public static String DIR_EXPERIMENTA = "./";
	public static String DIR_EXPERIMENTA_SALIDA = "./";
	public static String DIR_AGENTS      = "./";

	
	public static String ROOTNAMES = "P_";
	public static boolean VISIBLERADIO = false;
	
	
	public static final String Environment = "Envir";
	public static final String Gui         = "Gui";
	

	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double distance2(BtModule a1, BtModule a2) {
		
		double dx = a1.getXPos()-a2.getXPos();
		double dy = a1.getYPos()-a2.getYPos();
		
		return dx*dx + dy*dy;
	}
	
	public static boolean CreateDir(String name) {
		boolean devolver = false;
		
		File theDir = new File(name);

		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
		    System.out.println("creating directory: " + name);
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		     } catch(SecurityException se){
		        //handle it
		     }        
		     if(result) {    
		       System.out.println("DIR created");  
		     }
		  }
		
		return devolver;
	}
	
	
	

	
	/*** PASAR CONFIGURACION A LA PLATAFORMA  ***/
	public static String export_Config() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("NAGENTES " + NAGENTES + ";");
		sb.append("DIMX " + DIMX + ";");
		sb.append("DIMY " + DIMY + ";");
		sb.append("RADIOAGENT " + RADIOAGENT + ";");
		sb.append("PAUSETIME " + PAUSETIME + ";");
		sb.append("MOVETIME " + MOVETIME + ";");
		sb.append("STEPAGENT " + STEPAGENT + ";");
		sb.append("SOURCES " + SOURCES + ";");
		sb.append("CBR     " + CBR      + ";");
		sb.append("DIR_EXPERIMENTA " + DIR_EXPERIMENTA + ";");
		sb.append("DIR_AGENTS " + DIR_AGENTS + ";");
		sb.append("ROOTNAMES " + ROOTNAMES + ";");
		sb.append("VISIBLERADIO " + (VISIBLERADIO?"ON":"OFF") + ";");
		sb.append("Environment " + Environment + ";");
		sb.append("Gui " + Gui + ";");
		return sb.toString();
	}
	
	public static void read_params_Simulation() {
		
		Ficheros f = new Ficheros(Config.DIR_EXPERIMENTA + "config.txt");
		
		if (!f.existe()) {
			System.out.println("Fichero de configuraci—n de la simulaci—n INEXISTENTE");
			System.exit(-111);
		}
		
		String cont = f.LeerFichero();
		StringTokenizer st = new StringTokenizer(cont,"\n\r");
		while (st.hasMoreTokens()) {
			String linea = st.nextToken();
			
			if (!linea.trim().equalsIgnoreCase("") && !linea.startsWith("%")) {
				String orden = linea.substring(0,linea.indexOf('=')).trim();
				
				if (orden.equalsIgnoreCase("NAGENTES")) {
					Config.NAGENTES = Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
	
				} else if (orden.equalsIgnoreCase("DIMX")) {
					Config.DIMX =  Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
					
				} else if (orden.equalsIgnoreCase("DIMY")) {
					Config.DIMY = Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
					
				} else if (orden.equalsIgnoreCase("RADIO")) {
					Config.RADIOAGENT = Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
					
				} else if (orden.equalsIgnoreCase("DIRAGENTS")) {
					Config.DIR_AGENTS = linea.substring(linea.indexOf('=')+1).trim();
					
				} else if (orden.equalsIgnoreCase("ROOTNAMES")) {
					Config.ROOTNAMES = linea.substring(linea.indexOf('=')+1).trim();
					
				} else if (orden.equalsIgnoreCase("SOURCES")) {

					try {
						Config.SOURCES = Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
					} catch (Exception e) {
						Config.SOURCES = 0;
					}
					
					
					
//					String valor = linea.substring(linea.indexOf('=')+1).trim();
//					if (valor.equalsIgnoreCase("OFF")) {
//						
//					}
//					Config.SOURCES = ;
					
					
	
				} else if (orden.equalsIgnoreCase("STEP")) {
					Config.STEPAGENT = Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
	
				} else if (orden.equalsIgnoreCase("PAUSETIME")) {
					Config.PAUSETIME = Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim());
	
				} else if (orden.equalsIgnoreCase("MOVETIME")) {
					Config.MOVETIME = Integer.parseInt((linea.substring(linea.indexOf('=')+1).trim()));

					
					
					
				} else if (orden.equalsIgnoreCase("VISIBLERADIO")) {
					String valor = linea.substring(linea.indexOf('=')+1).trim();
					if (valor.equalsIgnoreCase("on"))
						Config.VISIBLERADIO = true;
					else 
						Config.VISIBLERADIO = false;
				} else { 
					
				}
			}
			
		}
	}

	public static void read_params_Agents(Bee P) {
		
		Ficheros f = new Ficheros(Config.DIR_EXPERIMENTA + P.getAID().getLocalName() + ".cfg");
		
		if (f.existe()) { 
			
		
		String cont = f.LeerFichero();
		StringTokenizer st = new StringTokenizer(cont,"\n\r");
		while (st.hasMoreTokens()) {
			String linea = st.nextToken();
			
			if (!linea.trim().equalsIgnoreCase("") && !linea.startsWith("%")) {
				String orden = linea.substring(0,linea.indexOf('=')).trim();
				
				if (orden.equalsIgnoreCase("POSX")) {
					P.setPosX(Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim()));
	
				} else if (orden.equalsIgnoreCase("POSY")) {
					P.setPosY(Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim()));
					
				} else if (orden.equalsIgnoreCase("SPEED")) {
					P.setStep(Double.parseDouble(linea.substring(linea.indexOf('=')+1).trim()));
					
				} else if (orden.equalsIgnoreCase("RADIO")) {
					P.setRadio(Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim()));
	
				} else if (orden.equalsIgnoreCase("TARGET")) {
					P.setSource(linea.substring(linea.indexOf('=')+1).trim());


				} else if (orden.equalsIgnoreCase("PAUSETIME")) {
					P.setPauseTime(Integer.parseInt(linea.substring(linea.indexOf('=')+1).trim()));
					
				} else if (orden.equalsIgnoreCase("MOVETIME")) {
					P.setMoveTime(Integer.parseInt((linea.substring(linea.indexOf('=')+1).trim())));
					
				} else if (orden.equalsIgnoreCase("STEP")) {
					P.setStep(Integer.parseInt((linea.substring(linea.indexOf('=')+1).trim())));




				} else { 
					
				}
			}
			
		}
		}
		
		
	}


	
}
