package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * Modified testing program to create a bar chart.
 */
public class ChartTest4 {

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Smart Phones Manufactured / Q3 2011",  // chart title
                "Manufacturer",  // domain axis label
                "Percentage",    // range axis label
                dataset            // data
        );

        // set a custom background for the chart
        chart.setBackgroundPaint(new GradientPaint(0, 0, new Color(20, 20, 20),
                400, 200, Color.DARK_GRAY));

        // customise the title position and font
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(null);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis appearance
        plot.getRangeAxis().setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.getRangeAxis().setTickLabelFont(new Font("Courier New", Font.BOLD, 15));

        // customise the bar appearance
        plot.getRenderer().setSeriesPaint(0, Color.blue);

        // add a subtitle giving the data source
        TextTitle source = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523", new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        return chart;
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(27.8, "Percentage", "Samsung");
        dataset.addValue(55.3, "Percentage", "Others");
        dataset.addValue(16.8, "Percentage", "Nokia");
        dataset.addValue(17.1, "Percentage", "Apple");
        return dataset;
    }

    /**
     * Entry point.
     *
     * @param args  command line arguments (ignored).
     *
     * @throws IOException if there is an input/output problem.
     */
    public static void main(String[] args) throws IOException {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        // create an image of the required size for the chart
        int w = (int) Math.rint(800);
        int h = (int) Math.rint(600);
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        chart.draw(g2, new Rectangle2D.Double(0, 0, w, h));
        g2.dispose();

        // save it to a PNG image
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("output4.png")));
        ChartUtils.writeBufferedImageAsPNG(out, img);
        out.close();
        System.out.println("output4.png created");
    }
}