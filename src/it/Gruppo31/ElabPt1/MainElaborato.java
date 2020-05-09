package it.Gruppo31.ElabPt1;
import gurobi.*;
import gurobi.GRB.DoubleAttr;
import gurobi.GRB.IntAttr;
import gurobi.GRB.IntParam;
import gurobi.GRB.StringAttr;

public class MainElaborato {
	

		public static void main(String [] args) {
			
			
			
			PrintData p1= new PrintData(31, "Trovato Eleonora","Ragnoli Michele" );
			
			try {
				GRBEnv env=new GRBEnv("progettoGurobi.log");
				env.set(IntParam.Presolve,0);
				env.set(IntParam.Method,0); //uso simplesso per risolvere
			
			//creo modello	
			GRBModel model=new GRBModel(env);
		
				
			//aggiungo var al modello
			GRBVar x1=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x1");
			GRBVar x2=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x2");
			GRBVar x3=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x3");
			GRBVar x4=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x4");
			GRBVar x5=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x5");
			GRBVar x6=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x6");
			GRBVar x7=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x7");
			GRBVar x8=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"x8");
			
			
			/*
			GRBVar y1=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y1");
			GRBVar y2=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y2");
			GRBVar y3=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y3");
			GRBVar y4=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y4");
			GRBVar y5=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y5");
			GRBVar y6=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y6");
			GRBVar y7=model.addVar(0,GRB.INFINITY,0,GRB.CONTINUOUS,"y7");
			*/
			
			
			//creo f.o. e gli aggiungo parametri
			//la f.o. e' max 4x1+4x2+6x3+10x4+7x5+4x6-x7+7x8
			GRBLinExpr expr=new GRBLinExpr(); 
			expr.addTerm(4.0,x1);
			expr.addTerm(4.0,x2);
			expr.addTerm(6.0,x3);
			expr.addTerm(10.0,x4);
			expr.addTerm(7.0,x5);
			expr.addTerm(4.0,x6);
			expr.addTerm(-1.0,x7);
			expr.addTerm(7.0,x8);
			model.setObjective(expr,GRB.MAXIMIZE); 
			
			//creo vincoli
			//primo vincolo e' 4x1-3x6<=10
			expr=new GRBLinExpr(); 
			expr.addTerm(4.0,x1);
			expr.addTerm(-3.0,x6);
			//expr.addTerm(1.0, y1);
			GRBConstr c0=model.addConstr(expr,GRB.LESS_EQUAL,10.0,"c0");
			
			//secondo vincolo e' 3x5+5x6+4x8<=1
			expr=new GRBLinExpr();
			expr.addTerm(3.0,x5);
			expr.addTerm(5.0,x6);
			expr.addTerm(4.0, x8);
			//expr.addTerm(1.0, y2);
			GRBConstr c1=model.addConstr(expr,GRB.LESS_EQUAL,1.0,"c1");
			
			//terzo vincolo e' -2x2-3x3+7x4-x5+4x7-7x8>=-8
			expr=new GRBLinExpr();
			expr.addTerm(-2.0,x2);
			expr.addTerm(-3.0,x3);
			expr.addTerm(7.0, x4);
			expr.addTerm(-1.0,x5);
			expr.addTerm(4.0,x7);
			expr.addTerm(-7.0, x8);
			//expr.addTerm(-1.0, y3);
			GRBConstr c2=model.addConstr(expr,GRB.GREATER_EQUAL,-8.0,"c2");
			
			//quarto vincolo e' -3x8<=5
			expr=new GRBLinExpr();
			expr.addTerm(-3.0,x8);
			//expr.addTerm(1.0, y4);
			GRBConstr c3=model.addConstr(expr,GRB.LESS_EQUAL,5.0,"c3");
			
			//quinto vincolo e' -6x1+7x2-3x8<=8
			expr=new GRBLinExpr();
			expr.addTerm(-6.0,x1);
			expr.addTerm(7.0,x2);
			expr.addTerm(-3.0,x8);
			//expr.addTerm(1.0, y5);
			GRBConstr c4=model.addConstr(expr,GRB.LESS_EQUAL,8.0,"c4");
			
			//sesto vincolo e' -7x1+5x4+6x7<=5
			expr=new GRBLinExpr();
			expr.addTerm(-7.0,x1);
			expr.addTerm(5.0,x4);
			expr.addTerm(6.0,x7);
			//expr.addTerm(1.0, y6);
			GRBConstr c5=model.addConstr(expr,GRB.LESS_EQUAL,5.0,"c5");
			
			//settimo vincolo e' -2x3+7x7<=5
			expr=new GRBLinExpr();
			expr.addTerm(-2.0,x3);
			expr.addTerm(7.0,x7);
			//expr.addTerm(1.0, y7);
			GRBConstr c6=model.addConstr(expr,GRB.LESS_EQUAL,5.0,"c6");
			
			
			
			//ora risolvo
			model.optimize();
			
			//vedo stato
			int status=model.get(IntAttr.Status);
			System.out.println("status: "+status);
			
			
			
			//voglio vedere valori var 
			for(GRBVar var : model.getVars()) {
				
				System.out.println("Variabile: "+var.get(StringAttr.VarName)
				+" valore: "+var.get(DoubleAttr.X)+" CRR: "+var.get(DoubleAttr.RC) );
				
				p1.setSolBaseOttima(var.get(DoubleAttr.X));
				
				
				if((var.get(DoubleAttr.RC)==0.0)&& (var.get(DoubleAttr.X)!=0.0)) {
					
					p1.setVarInBase(1);
				}
				else if((var.get(DoubleAttr.RC)==0.0)&& (var.get(DoubleAttr.X)==0.0)) p1.setSolOttMult("Si");
				else p1.setVarInBase(0);
				
				
				p1.setCoeffCosto(var.get(DoubleAttr.RC));
				
			}
			
			
			
			
			//voglio vedere valori vincoli
			for(GRBConstr constr : model.getConstrs()) {
				System.out.println("Vincolo: "+constr.get(StringAttr.ConstrName)
				+" slack: "+constr.get(DoubleAttr.Slack)+" prezzo ombra: "+
						constr.get(DoubleAttr.Pi));
				
				p1.setSolBaseOttima(constr.get(DoubleAttr.Slack));
				p1.setSolBaseOttimDual(constr.get(DoubleAttr.Pi));
				
                  if((constr.get(DoubleAttr.Pi)==0.0)&& (constr.get(DoubleAttr.Slack)!=0.0)) {
					
					p1.setVarInBase(1);
				}
                  else if ((constr.get(DoubleAttr.Pi)==0.0)&& (constr.get(DoubleAttr.Slack)==0.0)) p1.setSolOttimaDeg("Si");
				else p1.setVarInBase(0);
                  
                  p1.setCoeffCosto((-1.0)*constr.get(DoubleAttr.Pi));
			}
			
			for(GRBVar var : model.getVars()) {
				
				p1.setSolBaseOttimDual((-1)*var.get(DoubleAttr.RC));
			}
			
			p1.setSolOttimaDeg("NO");
			
			//voglio valore f.o.
			double funzioneObiettivo=model.get(DoubleAttr.ObjVal);
			System.out.println("Valore f.o: "+funzioneObiettivo);
			p1.setFObbiettivo(funzioneObiettivo);
			p1.setFObbDuale(funzioneObiettivo);
			
			
			System.out.println("low "+x7.get(GRB. DoubleAttr . SAObjLow)+ "  up"+ x7.get (GRB. DoubleAttr . SAObjUp));
			
			p1.setLowSens(x7.get(GRB. DoubleAttr . SAObjLow));
			p1.setUpSens(x7.get (GRB. DoubleAttr . SAObjUp));
			
			p1.stampaDati();
			
			
			
			} catch (GRBException e) {
				
				e.printStackTrace();
			}
	}
	}


