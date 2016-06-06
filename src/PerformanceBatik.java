/* 
 * PerformanceBatik.java
 * ---------------------
 * Copyright (c) 2014, Object Refinery Limited.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   - Neither the name of the Object Refinery Limited nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package org.jfree.graphics2d.svg;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.axis.StandardCategoryAxis3D;
import com.orsoncharts.data.DefaultKeyedValues;
import com.orsoncharts.data.category.CategoryDataset3D;
import com.orsoncharts.data.category.StandardCategoryDataset3D;
import com.orsoncharts.interaction.StandardKeyedValues3DItemSelection;
import com.orsoncharts.label.StandardCategoryItemLabelGenerator;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.marker.CategoryMarker;
import com.orsoncharts.plot.CategoryPlot3D;
import com.orsoncharts.renderer.category.BarRenderer3D;
import com.orsoncharts.renderer.category.StandardCategoryColorSource;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 * Performance measurement for Orson Charts to SVG via Batik.
 */
public class PerformanceBatik {
    
    Chart3D chart;
    
    DOMImplementation domImpl;
    
    public PerformanceBatik() {
        this.chart = createChart();
        this.domImpl = GenericDOMImplementation.getDOMImplementation();
    }

    public static Chart3D createChart() {
        CategoryDataset3D dataset = createDataset();
        Chart3D chart = Chart3DFactory.createBarChart("Quarterly Revenues", 
                "For some large IT companies", dataset, null, "Quarter", 
                "$billion Revenues");
        chart.setChartBoxColor(new Color(255, 255, 255, 127));
        chart.setLegendAnchor(LegendAnchor.BOTTOM_RIGHT);
        CategoryPlot3D plot = (CategoryPlot3D) chart.getPlot();
        plot.setGridlinePaintForValues(Color.BLACK);
        StandardCategoryAxis3D rowAxis 
                = (StandardCategoryAxis3D) plot.getRowAxis();
        rowAxis.setMarker("RM1", new CategoryMarker("Apple"));
        StandardCategoryAxis3D columnAxis 
                = (StandardCategoryAxis3D) plot.getColumnAxis();
        columnAxis.setMarker("CM1", new CategoryMarker("Q4/12"));
        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setColorSource(new StandardCategoryColorSource() {
            @Override
            public Color getColor(int series, int row, int column) {
                if (series == 3 && row == 3 && column == 6) {
                    return Color.RED;
                }
                return super.getColor(series, row, column);
            }
        });
        StandardCategoryItemLabelGenerator itemLabelGenerator = 
                new StandardCategoryItemLabelGenerator(
                StandardCategoryItemLabelGenerator.VALUE_TEMPLATE);
        StandardKeyedValues3DItemSelection itemSelection 
                = new StandardKeyedValues3DItemSelection();
        itemLabelGenerator.setItemSelection(itemSelection);
        renderer.setItemLabelGenerator(itemLabelGenerator);

        chart.getViewPoint().panLeftRight(-Math.PI / 16);
        return chart;
    }
  
    private static CategoryDataset3D createDataset() {    
        StandardCategoryDataset3D dataset = new StandardCategoryDataset3D();

        DefaultKeyedValues<Double> s1 = new DefaultKeyedValues<Double>();
        s1.put("Q2/11", 8.181);
        s1.put("Q3/11", 8.792);
        s1.put("Q4/11", 9.039);
        s1.put("Q1/12", 10.916);
        s1.put("Q2/12", 8.181);
        s1.put("Q3/12", 9.094);
        s1.put("Q4/12", 8.958);
        s1.put("Q1/13", 10.947);
        s1.put("Q2/13", 8.372);
        s1.put("Q3/13", 9.275);
        dataset.addSeriesAsRow("Oracle", s1);

        DefaultKeyedValues<Double> s2 = new DefaultKeyedValues<Double>();
        s2.put("Q2/11", 9.03);
        s2.put("Q3/11", 9.72);
        s2.put("Q4/11", 10.58);
        s2.put("Q1/12", 10.65);
        s2.put("Q2/12", 12.214);
        s2.put("Q3/12", 14.101);
        s2.put("Q4/12", 14.419);
        s2.put("Q1/13", 13.969);
        s2.put("Q2/13", 14.105);
        s2.put("Q3/13", 14.893);
        s2.put("Q4/13", 16.858);
        dataset.addSeriesAsRow("Google", s2);
        
        DefaultKeyedValues<Double> s3 = new DefaultKeyedValues<Double>();
        s3.put("Q2/11", 17.37);
        s3.put("Q3/11", 17.37);
        s3.put("Q4/11", 20.89);
        s3.put("Q1/12", 17.41);
        s3.put("Q2/12", 18.06);
        s3.put("Q3/12", 16.008);
        s3.put("Q4/12", 21.456);
        s3.put("Q1/13", 20.489);
        s3.put("Q2/13", 19.896);
        s3.put("Q3/13", 18.529);
        s3.put("Q4/13", 24.519);
        dataset.addSeriesAsRow("Microsoft", s3);
        
        DefaultKeyedValues<Double> s4 = new DefaultKeyedValues<Double>();
        s4.put("Q2/11", 28.57);
        s4.put("Q3/11", 28.27);
        s4.put("Q4/11", 46.33);
        s4.put("Q1/12", 39.20);
        s4.put("Q2/12", 35.00);
        s4.put("Q3/12", 36.00);
        s4.put("Q4/12", 54.5);
        s4.put("Q1/13", 43.6);
        s4.put("Q2/13", 35.323);
        s4.put("Q3/13", 37.5);
        s4.put("Q4/13", 57.594);
        dataset.addSeriesAsRow("Apple", s4);
        
        return dataset;
    }

    public String generateSVG() throws UnsupportedEncodingException, SVGGraphics2DIOException {
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        org.apache.batik.svggen.SVGGraphics2D g2 = new org.apache.batik.svggen.SVGGraphics2D(document);
        this.chart.draw(g2, new Rectangle(600, 400));
        boolean useCSS = true; // we want to use CSS style attributes
        ByteArrayOutputStream baos = new ByteArrayOutputStream(200000);
        Writer out = new OutputStreamWriter(baos, "UTF-8");
        g2.stream(out, useCSS);
        this.chart.draw(g2, new Rectangle(600, 400));
        return baos.toString("UTF-8");
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, SVGGraphics2DIOException {
        PerformanceBatik app = new PerformanceBatik();
        String svg = "";
        // warmup
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            svg = app.generateSVG();
        }
        long end = System.currentTimeMillis();
        System.out.println("Warmup ran for " + (end - start) + " milliseconds.");
        // test
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            svg = app.generateSVG();
        }
        end = System.currentTimeMillis();
        System.out.println("Test ran for " + (end - start) + " milliseconds.");
        System.out.println(svg);
    }

}