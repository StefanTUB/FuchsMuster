import java.util.ArrayList;
import java.util.Comparator;

public class Feld implements Comparable<Feld>{
	public Feld(int iD2, int i, int j) {
		// TODO Auto-generated constructor stub
		iD = iD2;
		xWert=i;
		yWert=j;
		farbe=null;
	}
	
	public Feld() {
		// TODO Auto-generated constructor stub
		farbe=null;
	}
	
	ArrayList<Feld> Nachbarn = new ArrayList();
	int iD;
	int xWert, yWert;
	Farbe farbe;
	int score;
	
	String zuString(){
		String text = "";
		text += Integer.toString(xWert);
		text += ",";
		text += Integer.toString(yWert);
		text += ",";
		try {
			text += farbe.name;
		} catch (Exception e) {
			// TODO: handle exception
			text += "keine farbe"; 
		}
		return text;
	}
	
	@Override
	public int compareTo(Feld feld) {
		return feld.score-score;
	}
}
