
import java.util.Arrays;
import java.util.Comparator;

public class Test {

	public static void main(String[] args) {
		Kugel k = new Kugel(5) {
			public double getVolumen() {

				if (!erledigt ) {
					erledigt = true;
					ergebnis = 4. / 3 * Math.PI * Math.pow(radius, 3);
				}

				return ergebnis;
			}

		};
		
		System.out.println(k.erledigt);
		System.out.println("Meine Kugel " + (int) k.getVolumen());
		System.out.println(k.erledigt);

		Quader q = new Quader(5, 4, 10) {
			public double getVolumen() {
				return seiteA * seiteB * seiteC;
			}

			public String toString() {
				return "Ich bin ein Quader";
			}
		};
		System.out.println("Mein Quader " + (int) q.getVolumen());

		Kegel w = new Kegel(5, 7) {
			public double getVolumen() {
				return 1 / 3. * Math.PI * Math.pow(radius, 2) * hoehe;
			}

			public String toString() {
				return "Ich bin ein Kegel";
			}
		};
		System.out.println("Mein Kegel " + (int) w.getVolumen());

		GeometrischeKoerper[] alle = { k, q, w };
		System.out.println(Arrays.toString(alle));

		Arrays.sort(alle, new Comparator<GeometrischeKoerper>() {
			public int compare(GeometrischeKoerper a, GeometrischeKoerper b) {
				double volA = a.getVolumen();
				double volB = b.getVolumen();
				return (int) (volA - volB);
			}
		});
		System.out.println(Arrays.toString(alle));
		

	}

}
