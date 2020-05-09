package it.Gruppo31.ElabPt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



public class PrintData {

	private BufferedWriter bufWriter;
	private BufferedReader reader;
	
	//-----------------------------------------------------------------------
	private int numGruppo;
	private String nome1;
	private String nome2;
	
	//-----------------------------------------------------------------------
	private double valFObbiettivo;
	private String ottimaMultipla;
	private String ottimaDegenere;
	private ArrayList<Double>solBaseOttima=new ArrayList<Double>();
	private ArrayList<Double>varDiBase=new ArrayList<Double>();
	private ArrayList<Double>coeffCostoRidotto= new ArrayList<Double>();
	
	//------------------------------------------------------------------------
	private double valFObbDuale;
	private double sensUp;
	private double sensLow;
	private ArrayList<Double>solBaseOttimaDuale=new ArrayList<Double>();
	
	//------------------------------------------------------------------------
	private String solBaseAssoAmmis;
	private ArrayList<Double>solBaseOttiMult=new ArrayList<Double>();
	private ArrayList<Double>solBaseDualeAsso=new ArrayList<Double>();
	
	
	
	
	PrintData(int numGruppo, String nome1, String nome2){
		
		   this.numGruppo = numGruppo;
		   this.nome1 = nome1;
		   this.nome2 = nome2;
		
		
	}
	
	//------------------------------------------------------------------
	public void setFObbiettivo(double funzioneObiettivo) {
		
		   valFObbiettivo=funzioneObiettivo;
	}
	
	//------------------------------------------------------------------
	public void setSolBaseOttima(double i) {
		
		   solBaseOttima.add(i);
	}
	
	//------------------------------------------------------------------
	public void setVarInBase(double i) {
		
		    varDiBase.add(i);
	}
	
	//------------------------------------------------------------------
	public void setCoeffCosto(double i) {
		
		    coeffCostoRidotto.add(i);
	}
	
	//------------------------------------------------------------------
	public void setSolOttMult(String s) {
		
		    ottimaMultipla=s;
	}
	
	//------------------------------------------------------------------
	public void setSolOttimaDeg(String s) {
		
		    ottimaDegenere=s;
	}
	
	//------------------------------------------------------------------
	public void setFObbDuale(double i) {
		
		    valFObbDuale=i;
	}
	
	//------------------------------------------------------------------
	public void setSolBaseOttimDual(double i) {
		
		    solBaseOttimaDuale.add(i);
		
	}
	
	//------------------------------------------------------------------
	public void setUpSens(double i) {
		
	        sensUp=i;	
	}
	
	//------------------------------------------------------------------
	public void setLowSens(double i) {
		
		    sensLow=i;
	}
	
	//------------------------------------------------------------------
	public void setBaseOttMultip(double i) {
		
		    solBaseOttiMult.add(i);
	}
	
	//------------------------------------------------------------------
	public void setBaseDualAssoc(double i) {
		
		    solBaseDualeAsso.add(i);
	}
	
	//------------------------------------------------------------------
	public void setSolAssocDualAmm(String s) {
		
		    solBaseAssoAmmis.equals(s);
	}
	
	//------------------------------------------------------------------
	
	public void stampaDati() {
		
		try {
		
			reader = new BufferedReader(new FileReader("spiegazione.txt"));
			OutputStreamWriter writer = new OutputStreamWriter(
            new FileOutputStream("risposte_gruppo"+numGruppo+".txt"), "UTF-8");
		
			bufWriter = new BufferedWriter(writer);
		
			bufWriter.write("GRUPPO "+numGruppo);
            bufWriter.newLine();
            bufWriter.write("Componenti: "+ nome1 + ",  " + nome2);
            
            doppioSpazio();
            bufWriter.newLine();
            
            bufWriter.write("QUESITO I:");
            bufWriter.newLine();
            dQuesito1();
            doppioSpazio();
            
            bufWriter.write("QUESITO II:");
            bufWriter.newLine();
            dQuesito2();
            doppioSpazio();
            
            bufWriter.write("QUESITO III:");
            bufWriter.newLine();
            dQuesito3();
 
            bufWriter.close();
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	//------------------------------------------------------------------------------------------------
	
	private void doppioSpazio() {
		
		try {
		    bufWriter.newLine();
		    bufWriter.newLine();
		}   catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------------------------------
	
	private void dQuesito1() {
		int i=0;
		String line="";
		
		try {
			
			bufWriter.write("funzione obbiettivo = " + valFObbiettivo);
			bufWriter.newLine();
			bufWriter.write("soluzione di base ottima = ");
			for(i=0;i<solBaseOttima.size();i++)bufWriter.write(solBaseOttima.get(i)+", ");
			bufWriter.newLine();
			bufWriter.write("variabili in base:");
			for(i=0;i<varDiBase.size();i++)bufWriter.write(varDiBase.get(i)+", ");
			bufWriter.newLine();
			bufWriter.write("coefficenti di costo ridotto:");
			for(i=0;i<coeffCostoRidotto.size();i++)bufWriter.write(coeffCostoRidotto.get(i)+", ");
			bufWriter.newLine();
			bufWriter.write("soluzione ottima multipla: "+ ottimaMultipla);
			bufWriter.newLine();
			bufWriter.write("soluzione ottima degenere: "+ ottimaDegenere);
			bufWriter.newLine();
			bufWriter.write("motivazione: ");
			bufWriter.newLine();
			line = reader.readLine();
			while(line!=null) {
			     bufWriter.write(line);
			     bufWriter.newLine();
			     line = reader.readLine();
			}
				
		}catch(Exception e) {
			
			
		}
		
	}
	
	//--------------------------------------------------------------------------------------------------
	
	private void dQuesito2() {
		int i=0;
		
		try {
		    bufWriter.write("funzione obbiettivo duale = "+ valFObbDuale);
		    bufWriter.newLine();
		    bufWriter.write("soluzione di base ottima duale: ");
		    for(i=0;i<solBaseOttimaDuale.size();i++)bufWriter.write(solBaseOttimaDuale.get(i)+", ");
		    bufWriter.newLine();
		    bufWriter.write("sensitività: "+sensLow+"<= DELTA <="+sensUp);
		
		}	catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//---------------------------------------------------------------------------------------------------
	
	private void dQuesito3() {
		int i=0;
		
		try {
			
			bufWriter.write("soluzione di base ottima multipla :");
			for(i=0;i<solBaseOttiMult.size();i++)bufWriter.write(solBaseOttiMult.get(i)+", ");
			bufWriter.newLine();
			bufWriter.write("soluzione di base duale associata :");
			for(i=0;i<solBaseDualeAsso.size();i++)bufWriter.write(solBaseDualeAsso.get(i)+", ");
			bufWriter.newLine();
			bufWriter.write("soluzione di base duale associata è ammissibile: "+ solBaseAssoAmmis);
			
			
		}   catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
