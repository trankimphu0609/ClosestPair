/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author trankimphu0609
 */
public class TableChartUI extends JPanel {

    private JButton btnClearAll;
    private JPanel itemView1, itemView2;

    private JTable tbl;

    private DefaultCategoryDataset dataset;

    public TableChartUI() {
        init();
        drawChart();

    }

    private void init() {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1300, 740));

        // Đọc dữ liệu từ file vào danh sách các dòng
        List<String[]> data = readDataFromFile();

        // Tạo một DefaultTableModel từ danh sách các dòng
        String[] columnNames = {"Algorithm", "Size", "Time (µs)"};
        DefaultTableModel model = new DefaultTableModel(data.toArray(String[][]::new), columnNames);

        // Tạo một JTable với DefaultTableModel và đặt nó trong một JScrollPane
        tbl = new JTable(model);

        itemView1 = new JPanel(null);
        itemView1.setBounds(new Rectangle(0, 0, 350, 740));
        itemView1.setBackground(null);

        itemView2 = new JPanel(null);
        itemView2.setBounds(new Rectangle(355, 0, 715, 695));
        itemView2.setBackground(Color.WHITE);

        Font font1 = new Font("Tahoma", Font.PLAIN, 15);

        btnClearAll = new JButton("Clear All");
        btnClearAll.setFont(font1);
        btnClearAll.setForeground(Color.black);
        btnClearAll.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));
        btnClearAll.setBounds(new Rectangle(100, 640, 150, 60));
        btnClearAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClearAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileWriter fileWriter;
                try {
                    model.setRowCount(0);

                    fileWriter = new FileWriter("./src/UI/data.txt");

                    // Ghi nội dung trống vào file để xóa dữ liệu cũ
                    fileWriter.write("");

                    fileWriter.close();

                } catch (IOException ex) {
                    Logger.getLogger(TableChartUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                dataset.clear();
            }
        });
        
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(50);

        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0, 0));
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(35);
        tbl.setShowVerticalLines(false);
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(134, 64, 0));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(52, 152, 219));

        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(5, 5, 350, 620));
        scroll.setBackground(null);

        itemView1.add(scroll);
        itemView1.add(btnClearAll);
        add(itemView1);
        add(itemView2);

    }

    private static List<String[]> readDataFromFile() {
        List<String[]> data = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./src/UI/data.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] row = line.split("-");
                data.add(row);
            }

            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(TableChartUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }

    private void drawChart() {
        // Create the dataset
        dataset = new DefaultCategoryDataset();

        for (int i = 0; i < tbl.getRowCount(); i++) {

            String algorithm = tbl.getValueAt(i, 0).toString();
            String size = tbl.getValueAt(i, 1).toString();
            String timeStr = tbl.getValueAt(i, 2).toString();

            double time = Double.parseDouble(
                    timeStr.replaceAll("[^\\d.]+", "").replaceFirst("\\.", "@")
                            .replaceAll("\\.", "").replace("@", "."));

            dataset.addValue(time, algorithm, Integer.valueOf(size));

        }

        // Create the chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Algorithm", // Chart title
                "Size", // X-axis label
                "Time", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true, // Legend
                true, // Tooltips
                false // URLs
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Customize the chart
        chart.setBackgroundPaint(Color.white);

        // Tùy chỉnh màu sắc và ký hiệu của từng đường trong biểu đồ
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(1, Color.BLACK);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesPaint(2, Color.RED);
        renderer.setSeriesShapesVisible(2, true);
        plot.setRenderer(renderer);

        // Create the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(2000, 460));

        // chữ nghiêng trên trục Ox
        CategoryAxis categoryAxis = plot.getDomainAxis();
        CategoryLabelPositions newPosition = CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0);
        categoryAxis.setCategoryLabelPositions(newPosition);

        // Add the chart panel to the UI
        itemView2.removeAll();
        itemView2.setLayout(new BorderLayout());
        itemView2.add(chartPanel, BorderLayout.CENTER);
        itemView2.revalidate();
        itemView2.repaint();

        add(itemView2);
    }
}
