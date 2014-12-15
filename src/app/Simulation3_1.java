package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import config.Config;

import data.Registro;
import app.behavs.CreateAgent;

import jade.core.Agent;

@SuppressWarnings("serial")
public class Simulation3_1 extends Agent {
	
	@Override
	protected void setup() {
		super.setup();
		
		initializeBD();
	
		Config.DIR_EXPERIMENTA = "sim3_1/";
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		Config.DIR_EXPERIMENTA_SALIDA = Config.DIR_EXPERIMENTA + timeStamp+ Config.BARRA; 				
		// Crear el directorio de salida del experimento
		config.Config.CreateDir(Config.DIR_EXPERIMENTA_SALIDA);

		Config.read_params_Simulation();
		
		System.out.println("Starting simulation");
		Object[] args = {Config.DIMX,Config.DIMY,Config.export_Config()};
		this.addBehaviour(new CreateAgent(Config.Environment, "environment.Environment", args));
		this.addBehaviour(new CreateAgent(Config.Gui,   "gui.Gui", args));
		
		args = new String[3];
		args[0] = "Envir";
		args[1] = "Gui";
		for(int i=0;i<Config.NAGENTES;i++) {
	 		this.addBehaviour(new CreateAgent(Config.ROOTNAMES + i, "agents.Bee", args));
		}

		
		/*****************************************************************
		 * PARA DEPURAR LOS MENSAJES QUE SE ENVIAN LOS AGENTES
		 *****************************************************************/
		/*
		args = new String[1];
		args[0] = Config.ROOTNAMES + "*";
//		args[1] = "Gui";
		this.addBehaviour(new CreateAgent("pepito", "jade.tools.sniffer.Sniffer", args));
		*/
		
		
		
		
		
		
	}

	private void initializeBD() {
//		Disco.createSTORE();
//		Disco.createENV();
	}

	@Override
	protected void takeDown() {
		super.takeDown();
		
		
		//2. Copiar el config.txt
		try {
			Files.copy(Paths.get(Config.DIR_EXPERIMENTA,"config.txt"), Paths.get(Config.DIR_EXPERIMENTA_SALIDA,"config.txt"),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		// 3 GRABAR DISCO
//		Disco.saveResults(Disco.ALMACEN,"system.csv");
//		Disco.saveResults(Disco.ENVIRONMENT,"environment.csv");
		
		// 4. Grabar la Mac de cada uno de los agentes.
		// Esto se graba en cada uno de los agentes.
		
		
		
		//System.out.println(sb.toString());
		
	}

	
}
