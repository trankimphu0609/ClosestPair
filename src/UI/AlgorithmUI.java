/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import Algorithm.BruteForce;
import Algorithm.DivideAndConquer;
import Entity.Pair;
import Entity.Point;
import Algorithm.Randomized;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.BorderFactory.createLineBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author trankimphu0609
 */
public class AlgorithmUI extends JPanel {

    private static final List<Point> t = new ArrayList<>();

    private final Locale localeEN = new Locale("en", "EN");
    private final NumberFormat en = NumberFormat.getInstance(localeEN);

    private JTextField txtInput;

    private JPanel txtOutput;

    private JTextArea txtOutputGenerated;

    private JButton btnGenerate, btnRun, btnClear, btnBruteForce, btnDivideAndConquer, btnRandomized;

    private JTextField txtResult1, txtResult2, txtResult3;
    private JTextField txtDistance1, txtDistance2, txtDistance3;
    private JTextField txtTime1, txtTime2, txtTime3;

    public AlgorithmUI() {
        init();
    }

    public final void init() {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(0, 0, 1000, 700));

        Font font0 = new Font("Segoe UI", Font.PLAIN, 15);
        Font font1 = new Font("Segoe UI", Font.BOLD, 15);
        Font font2 = new Font("Tahoma", Font.PLAIN, 16);

        /*
         * PHẦN HIỂN THỊ THÔNG TIN
         */
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 5, 1000, 700));
        itemView.setBackground(null);

        /*
         * TAO CAC LABEL & TEXTFIELD
         */
        JLabel lbInput = new JLabel("Input: ");
        txtInput = new JTextField("");
        lbInput.setBounds(new Rectangle(50, 5, 200, 40));
        lbInput.setFont(font1);
        txtInput.setBounds(new Rectangle(150, 5, 220, 40));
        txtInput.setFont(font0);

        JLabel lbOutput = new JLabel("Output: ");
        txtOutput = new JPanel();
        txtOutputGenerated = new JTextArea();
        lbOutput.setBounds(50, 60, 200, 40);
        lbOutput.setFont(font1);
        txtOutput.setBounds(new Rectangle(150, 60, 900, 180));
        txtOutput.setBackground(Color.WHITE);
        txtOutputGenerated.setLineWrap(true);
        txtOutputGenerated.setWrapStyleWord(true);
        txtOutputGenerated.setEditable(false);
        txtOutputGenerated.setFont(font0);
        JScrollPane scroll = new JScrollPane(txtOutputGenerated);
        scroll.setBounds(new Rectangle(150, 60, 900, 180));
        // -------------------------------------------------------------------------------

        btnBruteForce = new JButton("Brute Force");
        btnBruteForce.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));
        btnBruteForce.setFont(font2);
        btnBruteForce.setForeground(Color.BLACK);
        btnBruteForce.setBounds(new Rectangle(50, 250, 180, 35));
        btnBruteForce.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtResult1 = new JTextField("");
        txtResult1.setBounds(new Rectangle(250, 250, 800, 35));
        txtResult1.setFont(font0);
        txtResult1.setEditable(false);
        txtResult1.setForeground(Color.red);

        JLabel lblDistance1 = new JLabel("Distance: ");
        lblDistance1.setForeground(Color.red);
        lblDistance1.setBounds(new Rectangle(150, 300, 200, 35));
        lblDistance1.setFont(font1);
        txtDistance1 = new JTextField("");
        txtDistance1.setBounds(new Rectangle(250, 300, 800, 35));
        txtDistance1.setFont(font0);
        txtDistance1.setEditable(false);
        txtDistance1.setForeground(Color.red);

        JLabel lbTime1 = new JLabel("Time (µs): ");
        lbTime1.setForeground(Color.red);
        lbTime1.setBounds(new Rectangle(150, 350, 200, 35));
        lbTime1.setFont(font1);
        txtTime1 = new JTextField("");
        txtTime1.setBounds(new Rectangle(250, 350, 800, 35));
        txtTime1.setFont(font0);
        txtTime1.setEditable(false);
        txtTime1.setForeground(Color.red);

        //--------------------------------------------------------------------------------
        btnDivideAndConquer = new JButton("Divide And Conquer");
        btnDivideAndConquer.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));
        btnDivideAndConquer.setFont(font2);
        btnDivideAndConquer.setForeground(Color.BLACK);
        btnDivideAndConquer.setBounds(new Rectangle(50, 400, 180, 35));
        btnDivideAndConquer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtResult2 = new JTextField("");
        txtResult2.setBounds(new Rectangle(250, 400, 800, 35));
        txtResult2.setFont(font0);
        txtResult2.setEditable(false);
        txtResult2.setForeground(Color.BLACK);

        JLabel lblDistance2 = new JLabel("Distance: ");
        lblDistance2.setForeground(Color.BLACK);
        txtDistance2 = new JTextField("");
        lblDistance2.setBounds(new Rectangle(150, 450, 200, 35));
        lblDistance2.setFont(font1);
        txtDistance2.setBounds(new Rectangle(250, 450, 800, 35));
        txtDistance2.setFont(font0);
        txtDistance2.setEditable(false);
        txtDistance2.setForeground(Color.BLACK);

        JLabel lbTime2 = new JLabel("Time (µs): ");
        lbTime2.setForeground(Color.BLACK);
        txtTime2 = new JTextField("");
        lbTime2.setBounds(new Rectangle(150, 500, 200, 35));
        lbTime2.setFont(font1);
        txtTime2.setBounds(new Rectangle(250, 500, 800, 35));
        txtTime2.setFont(font0);
        txtTime2.setEditable(false);
        txtTime2.setForeground(Color.BLACK);

        //--------------------------------------------------------------------------------
        btnRandomized = new JButton("Randomized");
        btnRandomized.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));
        btnRandomized.setFont(font2);
        btnRandomized.setForeground(Color.BLACK);
        btnRandomized.setBounds(new Rectangle(50, 550, 180, 35));
        btnRandomized.setCursor(new Cursor(Cursor.HAND_CURSOR));

        txtResult3 = new JTextField("");
        txtResult3.setBounds(new Rectangle(250, 550, 800, 35));
        txtResult3.setFont(font0);
        txtResult3.setEditable(false);
        txtResult3.setForeground(Color.BLUE);

        JLabel lblDistance3 = new JLabel("Distance: ");
        lblDistance3.setForeground(Color.BLUE);
        txtDistance3 = new JTextField("");
        lblDistance3.setBounds(new Rectangle(150, 600, 200, 35));
        lblDistance3.setFont(font1);
        txtDistance3.setBounds(new Rectangle(250, 600, 800, 35));
        txtDistance3.setFont(font0);
        txtDistance3.setEditable(false);
        txtDistance3.setForeground(Color.BLUE);

        JLabel lbTime3 = new JLabel("Time (µs): ");
        lbTime3.setForeground(Color.BLUE);
        txtTime3 = new JTextField("");
        lbTime3.setBounds(new Rectangle(150, 650, 200, 35));
        lbTime3.setFont(font1);
        txtTime3.setBounds(new Rectangle(250, 650, 800, 35));
        txtTime3.setFont(font0);
        txtTime3.setEditable(false);
        txtTime3.setForeground(Color.BLUE);

        //--------------------------------------------------------------------------------
        itemView.add(lbInput);
        itemView.add(txtInput);
        itemView.add(lbOutput);
        itemView.add(txtOutput);
        itemView.add(btnBruteForce);
        itemView.add(txtResult1);
        itemView.add(lblDistance1);
        itemView.add(txtDistance1);
        itemView.add(lbTime1);
        itemView.add(txtTime1);
        itemView.add(btnDivideAndConquer);
        itemView.add(txtResult2);
        itemView.add(lblDistance2);
        itemView.add(txtDistance2);
        itemView.add(lbTime2);
        itemView.add(txtTime2);
        itemView.add(btnRandomized);
        itemView.add(txtResult3);
        itemView.add(lblDistance3);
        itemView.add(txtDistance3);
        itemView.add(lbTime3);
        itemView.add(txtTime3);

        add(itemView);
        add(scroll);

        btnGenerate = new JButton("Randomly Generate");
        btnRun = new JButton("Run");
        btnClear = new JButton("Clear");

        //font chữ
        btnGenerate.setFont(font2);
        btnGenerate.setForeground(Color.BLACK);
        btnRun.setFont(font2);
        btnRun.setForeground(Color.BLACK);
        btnClear.setFont(font2);
        btnClear.setForeground(Color.BLACK);

        // màu nền
        btnGenerate.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));
        btnRun.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));
        btnClear.setBorder(createLineBorder(new Color(134, 64, 0), 5, true));

        btnGenerate.setBounds(new Rectangle(400, 0, 180, 50));
        btnGenerate.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRun.setBounds(new Rectangle(600, 0, 180, 50));
        btnRun.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnClear.setBounds(new Rectangle(800, 0, 180, 50));
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        itemView.add(btnGenerate);
        itemView.add(btnRun);
        itemView.add(btnClear);

        btnGenerate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.clear();
                txtOutputGenerated.setText("");
                txtResult1.setText("");
                txtResult2.setText("");
                txtResult3.setText("");
                txtDistance1.setText("");
                txtDistance2.setText("");
                txtDistance3.setText("");
                txtTime1.setText("");
                txtTime2.setText("");
                txtTime3.setText("");

                String input = txtInput.getText();
                if (input.matches("[0-9]+")) {
                    int numPoints = Integer.parseInt(input);
                    if (numPoints <= 1) {
                        txtOutputGenerated.setText("Number of points must be greater than one!");
                    } else {
                        Point[] output = renderData(numPoints);
                        txtOutputGenerated.setText(Arrays.toString(output));
                    }

                } else {
                    txtOutputGenerated.setText("Invalid number of points!");
                }
            }
        });

        btnRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                long startTime;
                long endTime1 = 0;
                long endTime2 = 0;
                long endTime3 = 0;

                if (t.isEmpty()) {
                    txtResult1.setText("Not Found");
                    txtResult2.setText("Not Found");
                    txtResult3.setText("Not Found");
                    txtDistance1.setText("Not Found");
                    txtDistance2.setText("Not Found");
                    txtDistance3.setText("Not Found");
                    txtTime1.setText("Not Found");
                    txtTime2.setText("Not Found");
                    txtTime3.setText("Not Found");
                } else {

                    startTime = System.nanoTime();

                    Pair bruteForce = BruteForce.bruteForceClosestPair(t);
                    endTime2 += (System.nanoTime() - startTime) / 1000;

                    txtResult1.setText(bruteForce.p1 + ", " + bruteForce.p2);
                    double distance1 = bruteForce.distance();
                    txtDistance1.setText(String.valueOf(distance1));
                    txtTime1.setText(en.format(endTime2));
                    //---------------------------------------------------------------------

                    startTime = System.nanoTime();

                    Pair closestPair = DivideAndConquer.closestPair(t);
                    endTime1 += (System.nanoTime() - startTime) / 1000;

                    txtResult2.setText(closestPair.p1 + ", " + closestPair.p2);
                    double distance = closestPair.distance();
                    txtDistance2.setText(String.valueOf(distance));
                    txtTime2.setText(en.format(endTime1));
                    //---------------------------------------------------------------------

                }
                Point[] pointsArray = t.toArray(Point[]::new);
                if (pointsArray.length == 0) {
                    txtResult3.setText("Not Found");
                } else {
                    startTime = System.nanoTime();
                    Point[] closestPair = Randomized.closestPair(pointsArray);
                    endTime3 += (System.nanoTime() - startTime) / 1000;
                    txtResult3.setText(closestPair[0] + ", " + closestPair[1]);
                    double distance = distance(closestPair[0], closestPair[1]);
                    txtDistance3.setText(String.valueOf(distance));
                    txtTime3.setText(en.format(endTime3));
                }

                if (!txtInput.getText().equals("") && Integer.parseInt(txtInput.getText()) > 1) {
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("./src/UI/data.txt", true);
                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            String[][] textFieldContents = new String[3][3];
                            textFieldContents[0][0] = "Brute Force";
                            textFieldContents[0][1] = txtInput.getText();
                            textFieldContents[0][2] = txtTime1.getText();
                            textFieldContents[1][0] = "Divide & Conquer";
                            textFieldContents[1][1] = txtInput.getText();
                            textFieldContents[1][2] = txtTime2.getText();
                            textFieldContents[2][0] = "Randomized";
                            textFieldContents[2][1] = txtInput.getText();
                            textFieldContents[2][2] = txtTime3.getText();

                            String[] combinedTexts = new String[3];
                            for (int i = 0; i < 3; i++) {
                                combinedTexts[i] = String.join("-", textFieldContents[i]);
                                bufferedWriter.write(combinedTexts[i] + "\n");
                            }
                        }
                        fileWriter.close();

                    } catch (IOException ex) {
                        Logger.getLogger(AlgorithmUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        });

        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.clear();

                txtInput.setText("");
                txtOutputGenerated.setText("");
                txtResult1.setText("");
                txtResult2.setText("");
                txtResult3.setText("");
                txtDistance1.setText("");
                txtDistance2.setText("");
                txtDistance3.setText("");
                txtTime1.setText("");
                txtTime2.setText("");
                txtTime3.setText("");
//                t.clear();

            }
        });

        btnBruteForce.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                long startTime;
                long endTime = 0;
                if (t.isEmpty()) {
                    txtResult1.setText("Not Found");
                    txtDistance1.setText("Not Found");
                    txtTime1.setText("Not Found");
                } else {
                    startTime = System.nanoTime();

                    Pair bruteForce = BruteForce.bruteForceClosestPair(t);
                    endTime += (System.nanoTime() - startTime) / 1000;

                    txtResult1.setText(bruteForce.p1 + ", " + bruteForce.p2);
                    double distance1 = bruteForce.distance();
                    txtDistance1.setText(String.valueOf(distance1));
                    txtTime1.setText(en.format(endTime));
                }

                if (!txtInput.getText().equals("") && Integer.parseInt(txtInput.getText()) > 1) {
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("./src/UI/data.txt", true);
                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            String[] textFieldContents = new String[3];
                            textFieldContents[0] = "Brute Force";
                            textFieldContents[1] = txtInput.getText();
                            textFieldContents[2] = txtTime1.getText();

                            String combinedText = String.join("-", textFieldContents);
                            bufferedWriter.write(combinedText + "\n");
                        }
                        fileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AlgorithmUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });

        btnDivideAndConquer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                long startTime;
                long endTime = 0;
                if (t.isEmpty()) {
                    txtResult2.setText("Not Found");
                    txtDistance2.setText("Not Found");
                    txtTime2.setText("Not Found");
                } else {
                    startTime = System.nanoTime();

                    Pair closestPair = DivideAndConquer.closestPair(t);
                    endTime += (System.nanoTime() - startTime) / 1000;

                    txtResult2.setText(closestPair.p1 + ", " + closestPair.p2);
                    double distance = closestPair.distance();
                    txtDistance2.setText(String.valueOf(distance));
                    txtTime2.setText(en.format(endTime));
                }

                if (!txtInput.getText().equals("") && Integer.parseInt(txtInput.getText()) > 1) {
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("./src/UI/data.txt", true);
                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            String[] textFieldContents = new String[3];
                            textFieldContents[0] = "Divide & Conquer";
                            textFieldContents[1] = txtInput.getText();
                            textFieldContents[2] = txtTime2.getText();

                            String combinedText = String.join("-", textFieldContents);
                            bufferedWriter.write(combinedText + "\n");
                        }
                        fileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AlgorithmUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        btnRandomized.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                long startTime;
                long endTime = 0;
                Point[] pointsArray = t.toArray(Point[]::new);
                if (pointsArray.length == 0) {
                    txtResult3.setText("Not Found");
                    txtDistance3.setText("Not Found");
                    txtTime3.setText("Not Found");
                } else {
                    startTime = System.nanoTime();
                    Point[] closestPair = Randomized.closestPair(pointsArray);
                    endTime += (System.nanoTime() - startTime) / 1000;
                    txtResult3.setText(closestPair[0] + ", " + closestPair[1]);
                    double distance = distance(closestPair[0], closestPair[1]);
                    txtDistance3.setText(String.valueOf(distance));
                    txtTime3.setText(en.format(endTime));
                }

                if (!txtInput.getText().equals("") && Integer.parseInt(txtInput.getText()) > 1) {
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("./src/UI/data.txt", true);
                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            String[] textFieldContents = new String[3];
                            textFieldContents[0] = "Randomized";
                            textFieldContents[1] = txtInput.getText();
                            textFieldContents[2] = txtTime3.getText();

                            String combinedText = String.join("-", textFieldContents);
                            bufferedWriter.write(combinedText + "\n");
                        }
                        fileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AlgorithmUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static Point[] renderData(int n) {

        Point[] points = new Point[n];
        Random rand = new Random();
        t.clear();
        for (int i = 0; i < n; i++) {
            Point p;
            p = new Point(rand.nextInt(n * 5), rand.nextInt(n * 10));
            points[i] = p;
            t.add(p);
        }
        return points;
    }
}
