
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;



public class FuchsMuster {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		
		Spielfeld Muster = new Spielfeld();
		for (int i = 0; i < 304; i++) {
			Feld inBearbeitung = Muster.gefaerbt.get(0);
			Muster.gefaerbt.remove(0);
			ArrayList<Feld> naechsten = new ArrayList<>();
			for (Feld feld : inBearbeitung.Nachbarn) {
				if (feld.farbe == null) {
					naechsten.add(feld);
				}
			}
			if (naechsten.isEmpty()) {
				for (Feld feld : inBearbeitung.Nachbarn) {
					for (Feld feld2 : feld.Nachbarn) {
						if (feld2.farbe == null) {
							naechsten.add(feld2);
						}
					}
				}
			}
			if (naechsten.isEmpty()) {
				for (Feld feld : inBearbeitung.Nachbarn) {
					for (Feld feld2 : feld.Nachbarn) {
						for (Feld feld3 : feld2.Nachbarn) {
							if (feld3.farbe == null) {
								naechsten.add(feld3);
							}
						}
					}
				}
			}

			for (Feld feld : naechsten) {
				feld.score = Muster.Reihe(inBearbeitung, feld);
			}
			Collections.sort(naechsten);
			System.out.println("Liste:");
			for (Feld feld : naechsten) {
				System.out.println(feld.zuString() + feld.score);
			}
			//naechsten.removeAll();
			if (naechsten.size() > 1) {
				
			}
			Feld naechstes = naechsten.get(0);
			naechstes.iD = i;
			naechstes.farbe = inBearbeitung.farbe;
			Muster.gefaerbt.add(naechstes);
		}
		
		Muster.TabellenAusgabe();
		Muster.exportChartAsSVG();
		
	}

}
