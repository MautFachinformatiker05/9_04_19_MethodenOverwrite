
public class Kegel extends GeometrischeKoerper {
	double hoehe;
	double radius;
	public Kegel(double _hoehe, double _radius){
		this.hoehe=_hoehe;
		this.radius=_radius;
	}
	public double getVolumen(){
		return 5;
	}

}
