import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {
	public static int kostenabweichung(double fw, double ik) {
		return (int) fw - (int) ik;
	}

	public static int planabweichung(double fw, double pk) {
		return (int) fw - (int) pk;
	}

	public static double effizienzFaktor(double fw, double ik) {
		double ef = fw / ik;
		ef = Math.round(ef * 100) / 100.0;
		return ef;
	}

	public static double zeitplanKennzahl(double fw, double pk) {
		return fw / pk;
	}

	public static double erwarteteGesamtkosten(double pgk, double ik, double fw) {
		return pgk * (ik / fw);
	}

	public static int geplanteKostenabweichung(double pgk, double egk) {
		return (int) pgk - (int) egk;
	}

	public static int erwarteteRestkosten(double egk, double ik) {
		return (int) egk - (int) ik;
	}

	public static void main(String[] args) {
		System.out.println("EVA-Rechner");
		System.out.println("-----------");
		System.out.println();

		Scanner PGK = new Scanner(System.in);
		System.out.println("Bitte die Projektgesamtkosten(PGK) eingeben:");
		int pgk = PGK.nextInt();

		Scanner PK = new Scanner(System.in);
		System.out.println("Bitte die Plankosten(PK) eingeben:");
		int pk = PK.nextInt();

		Scanner IK = new Scanner(System.in);
		System.out.println("Bitte die Istkosten(IK) eingeben:");
		double ik = IK.nextDouble();

		Scanner FW = new Scanner(System.in);
		System.out.println("Bitte den Fertigstellungswert(FW) eingeben:");
		double fw = FW.nextDouble();

		System.out.println();
		System.out.println("Analyse:");
		if (kostenabweichung(fw, ik) >= 0) {
			System.out.println("Das Projekt ist " + Math.abs(kostenabweichung(fw, ik)) + "€ unter dem Budget.");
		} else {
			System.out.println("Das Projekt ist " + Math.abs(kostenabweichung(fw, ik)) + "€ ueber dem Budget.");
		}

		if (planabweichung(fw, pk) >= 0) {
			System.out.println("Das Projekt ist vor dem Zeitplan.");
		} else {
			System.out.println("Das Projekt ist hinter dem Zeitplan.");
		}

		System.out.println("Fuer jeden investierten € wurden " + effizienzFaktor(fw, ik) + "€ Wert geschaffen.");

		DecimalFormat prozent = new DecimalFormat(",##%");
		System.out.println("Es sind erst " + prozent.format(zeitplanKennzahl(fw, pk))
				+ " des bis zu diesem Punkt geplanten Umfangs erledigt.");

		System.out.println("Linear angenommen: Wir gehen davon aus, dass das Projekt "
				+ (int) erwarteteGesamtkosten(pgk, ik, fw) + "€ kosten wird.");

		if (geplanteKostenabweichung(pgk, erwarteteGesamtkosten(pgk, ik, fw)) >= 0) {
			System.out.println(
					"Wir erwarten, dass " + Math.abs(geplanteKostenabweichung(pgk, erwarteteGesamtkosten(pgk, ik, fw)))
							+ "€ weniger als geplant bis Projektende ausgegeben werden.");
		} else {
			System.out.println(
					"Wir erwarten, dass " + Math.abs(geplanteKostenabweichung(pgk, erwarteteGesamtkosten(pgk, ik, fw)))
							+ "€ mehr als geplant bis Projektende ausgegeben werden.");
		}

		System.out.println("Es muessen noch " + erwarteteRestkosten(erwarteteGesamtkosten(pgk, ik, fw), ik)
				+ "€ bis Projektende ausgegeben werden.");

		PGK.close();
		PK.close();
		IK.close();
		FW.close();
	}

}
