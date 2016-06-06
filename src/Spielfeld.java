import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class Spielfeld {

	int groesse = 20;
	Feld[][] alleFelder;
	ArrayList<Feld> gefaerbt = new ArrayList<>();
	
	void Ausgabe(){
		System.out.println("Ausgabe:");
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				if (alleFelder[i][j].farbe != null) {
					System.out.println(alleFelder[i][j].zuString());
				}	
			}	
		}
	}
	
	void exportChartAsSVG() throws IOException {
        // Get a DOMImplementation and create an XML document
        DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(null, "svg", null);

        // Create an instance of the SVG Generator
        // SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        SVGGraphics2D g2 = new SVGGraphics2D(document);
		 g2.setPaint(Color.BLACK);
		 g2.draw(new Rectangle(0, 0, 30*groesse, 30*groesse));

		 System.out.println("Ausgabe:");
			for (int i = 0; i < groesse; i++) {
				for (int j = 0; j < groesse; j++) {
					if (alleFelder[i][j].farbe != null) {
						if (alleFelder[i][j].farbe.name.contains("r")) {
							 g2.setPaint(Color.RED);}
						if (alleFelder[i][j].farbe.name.contains("b")) {
							 g2.setPaint(Color.BLUE);
						}
						g2.fill(new Rectangle(30*i, 30*j, 30, 30));
						g2.setColor(Color.BLACK);
						g2.drawString(String.valueOf(alleFelder[i][j].iD), 30*i+5, 30*j+20);
					}	
				}	
			}

        // Write svg file
		 File svgFile = new File("Hallo.svg");
        OutputStream outputStream = new FileOutputStream(svgFile);
        Writer out = new OutputStreamWriter(outputStream, "UTF-8");
        g2.stream(out, true /* use css */);						
        outputStream.flush();
        outputStream.close();
	}
	
	void TabellenAusgabe(){
		System.out.println("Ausgabe:");
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				if (alleFelder[i][j].farbe != null) {
					System.out.print(alleFelder[i][j].farbe.name.charAt(0));
				}	else {
					System.out.print(".");
				}
			}	
			System.out.println("");
		}
	}
	
	int Reihe(Feld letztes, Feld naechstes){
		int xShift = naechstes.xWert - letztes.xWert;
		int yShift = naechstes.yWert - letztes.yWert;

		while (naechstes.xWert + xShift < groesse &&
				naechstes.yWert + yShift < groesse &&
				naechstes.xWert + xShift >= 0 &&
				naechstes.yWert + yShift >= 0 &&
				alleFelder[naechstes.xWert+xShift][naechstes.yWert+yShift].farbe == null) {
			if (xShift != 0) {
				xShift = xShift + xShift/Math.abs(xShift);
			} else {
				yShift = yShift + yShift/Math.abs(yShift);
			}
		}

		int wert = Math.abs(xShift + yShift);
		return wert;
	}
	
	Spielfeld(){
		alleFelder = new Feld[groesse][groesse];
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				alleFelder[i][j] = new Feld(0,i,j);
			}	
		}
		alleFelder[2][3].farbe = new Farbe("bla");
		alleFelder[18][18].farbe = new Farbe("rot");
		gefaerbt.add(alleFelder[2][3]);
		gefaerbt.add(alleFelder[18][18]);
		
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				try {
					alleFelder[i][j].Nachbarn.add(alleFelder[i+1][j]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					alleFelder[i][j].Nachbarn.add(alleFelder[i-1][j]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					alleFelder[i][j].Nachbarn.add(alleFelder[i][j+1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					alleFelder[i][j].Nachbarn.add(alleFelder[i][j-1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
					
			}	
		}
	}
	
	
}
