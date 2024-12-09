package ForJava;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class ChartHandler {
    private final DataBase db;
    ChartHandler() {
         db = new DataBase();
    }
    public DefaultPieDataset pieHandler() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        HashMap<String, Integer> hash = db.selectGenreCount();
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            dataset.setValue(key, value);
        }
        return dataset;

    }


    Color[] colors = {new Color(200, 200, 255), new Color(255, 200, 200),
            new Color(200, 255, 200), new Color(200, 255, 200)};

    JFreeChart createPieChart(PieDataset dataset)
    {
        JFreeChart chart = ChartFactory.createPieChart(
                "Жанры",  // chart title
                dataset,             // data
                false,               // no legend
                true,                // tooltips
                false                // no URL generation
        );

        // Определение фона графического изображения
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
                new Color(20, 20, 20),
                new Point(400, 200),
                Color.DARK_GRAY));

        // Определение заголовка
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        // Определение подзаголовка
        TextTitle source = new TextTitle("Жанры",
                new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);

        RadialGradientPaint rgpBlue  ;
        RadialGradientPaint rgpRed   ;
        RadialGradientPaint rgpGreen ;
        RadialGradientPaint rgpYellow;

        rgpBlue   = createGradientPaint(colors[0], Color.BLUE  );
        rgpRed    = createGradientPaint(colors[1], Color.RED   );
        rgpGreen  = createGradientPaint(colors[2], Color.GREEN );
        rgpYellow = createGradientPaint(colors[3], Color.YELLOW);

        // Определение секций круговой диаграммы
        plot.setSectionPaint(1 , rgpBlue  );
        plot.setSectionPaint(2, rgpRed   );
        plot.setSectionPaint(3  , rgpGreen );

        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

        // Настройка меток названий секций
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        return chart;
    }
    private RadialGradientPaint createGradientPaint(Color c1, Color c2)
    {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};
        return new RadialGradientPaint(center, radius, dist,
                new Color[] {c1, c2});
    }

    public JPanel createPiePanel()
    {
        JFreeChart chart = createPieChart(pieHandler());
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setPreferredSize(new Dimension(600, 300));
        JFrame frame = new JFrame();
// add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 370));
/* setLayout( new FlowLayout() );
add(chartPanel); */
        frame.add(chartPanel);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
        frame.setVisible(true);
        return panel;
    }



}
