
public class Kugel extends GeometrischeKoerper {
	double radius;
	public boolean erledigt=false;
	double ergebnis;
	 public Kugel(double _radius){
		 this.radius=_radius;
		 
	 }
	 
	 public double getVolumen(){
		 return 0;
	 }
	
	 public String toString(){
		 return "Ich bin eine Kugel";
	 }

}
