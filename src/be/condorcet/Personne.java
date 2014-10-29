package be.condorcet;
import java.util.*;
public class Personne implements Comparable{
  private String nom="";
  private Date dtd=new Date();
  private Date dta=null;
  
   public Personne(String nom){
	   this.nom=nom;
   }
   
   public void setArrivee(Date dta){
	   this.dta=dta;
   }

   public String toString(){
	   String infos;
	   if(dta==null) infos=dtd.toString();
	   else infos=temps()+"sec";
	   return nom +" "+infos;
   }
   
   public long temps(){
	   if (dta==null) return 1000000;
	   return (dta.getTime()-dtd.getTime())/1000;
   }
   
   public int compareTo(Object o){
	   Personne p=(Personne)o;
	   return (int)(this.temps()-p.temps());
   }
   
   
}
