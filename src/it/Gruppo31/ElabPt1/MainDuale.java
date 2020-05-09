package it.Gruppo31.ElabPt1;

import gurobi.GRB;
import gurobi.GRBConstr;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;
import gurobi.GRB.DoubleAttr;
import gurobi.GRB.IntAttr;
import gurobi.GRB.IntParam;
import gurobi.GRB.StringAttr;

public class MainDuale {

	public static void main(String[] args) {
		
		try {
		GRBEnv env=new GRBEnv("progettoGurobi.log");
		env.set(IntParam.Presolve,0);
		env.set(IntParam.Method,0); //uso simplesso per risolvere
	
	//creo modello	
	GRBModel model=new GRBModel(env);
		
	//aggiungo var al modello
	GRBVar x1=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x1");
	GRBVar x2=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x2");
	GRBVar x3=model.addVar(-GRB.INFINITY,0,0,GRB.CONTINUOUS,"x3");
	GRBVar x4=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x4");
	GRBVar x5=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x5");
	GRBVar x6=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x6");
	GRBVar x7=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x7");
	//creo f.o. e gli aggiungo parametri
	//la f.o. e' max 4x1+4x2+6x3+10x4+7x5+4x6-x7+7x8
	GRBLinExpr expr=new GRBLinExpr(); 
	expr.addTerm(10.0,x1);
	expr.addTerm(1.0,x2);
	expr.addTerm(-8.0,x3);
	expr.addTerm(5.0,x4);
	expr.addTerm(8.0,x5);
	expr.addTerm(5.0,x6);
	expr.addTerm(5.0,x7);
	
	model.setObjective(expr,GRB.MINIMIZE); 
	
	//creo vincoli
	//primo vincolo e' 4x1-3x6<=10
	expr=new GRBLinExpr(); 
	expr.addTerm(4.0,x1);
	expr.addTerm(-6.0,x5);
	expr.addTerm(-7.0,x6);
	
	GRBConstr c0=model.addConstr(expr,GRB.GREATER_EQUAL,4.0,"c0");
	
	//secondo vincolo e' 3x5+5x6+4x8<=1
	expr=new GRBLinExpr();
	expr.addTerm(-2.0,x3);
	expr.addTerm(7.0,x5);
	GRBConstr c1=model.addConstr(expr,GRB.GREATER_EQUAL,4.0,"c1");
	
	//terzo vincolo e' -2x2-3x3+7x4-x5+4x7-7x8>=-8
	expr=new GRBLinExpr();
	expr.addTerm(-3.0,x3);
	expr.addTerm(-2.0,x7);
	
	GRBConstr c2=model.addConstr(expr,GRB.GREATER_EQUAL,6.0,"c2");

	expr=new GRBLinExpr();
	expr.addTerm(7.0,x3);
	expr.addTerm(5.0,x6);
	
	GRBConstr c3=model.addConstr(expr,GRB.GREATER_EQUAL,10.0,"c3");
	
	expr=new GRBLinExpr();
	expr.addTerm(3.0,x2);
	expr.addTerm(-1.0,x3);
	
	GRBConstr c4=model.addConstr(expr,GRB.GREATER_EQUAL,7.0,"c4");
	
	
	expr=new GRBLinExpr();
	expr.addTerm(-3.0,x1);
	expr.addTerm(5.0,x2);
	
	GRBConstr c5=model.addConstr(expr,GRB.GREATER_EQUAL,4.0,"c5");
	
	
	expr=new GRBLinExpr();
	expr.addTerm(4.0,x3);
	expr.addTerm(6.0,x6);
	expr.addTerm(7, x7);
	
	
	GRBConstr c6=model.addConstr(expr,GRB.GREATER_EQUAL,-1.0,"c6");
	
	expr=new GRBLinExpr();
	expr.addTerm(4.0,x2);
	expr.addTerm(-7.0,x3);
	expr.addTerm(-3.0,x4);
	expr.addTerm(-3.0,x5);
	
	GRBConstr c7 =model.addConstr(expr,GRB.GREATER_EQUAL,7.0,"c7");
	
	//ora risolvo
	model.optimize();
	
	//vedo stato
	int status=model.get(IntAttr.Status);
	System.out.println("status: "+status);
	
	
	
	//voglio vedere valori var 
	for(GRBVar var : model.getVars()) {
		
		System.out.println("Variabile: "+var.get(StringAttr.VarName)
		+" valore: "+var.get(DoubleAttr.X)+" CRR: "+var.get(DoubleAttr.RC));
	}
	//voglio vedere valori vincoli
	for(GRBConstr constr : model.getConstrs()) {
		System.out.println("Vincolo: "+constr.get(StringAttr.ConstrName)
		+" slack: "+constr.get(DoubleAttr.Slack)
				 );
	}
	
	System.out.println("low "+c6.get(GRB. DoubleAttr . SARHSLow)+ "  up"+ c6.get (GRB. DoubleAttr . SARHSUp));
	//voglio valore f.o.
	double funzioneObiettivo=model.get(DoubleAttr.ObjVal);
	System.out.println("Valore f.o: "+funzioneObiettivo);
	
	
	
	
	
	
	} catch (GRBException e) {
		
		e.printStackTrace();
	}

	}

}
