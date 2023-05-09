/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import UI.model.header;
import UI.model.navItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author trankimphu0609
 */
public class UI extends JFrame implements MouseListener {

    private final boolean flag = true;
    private JPanel header, nav, main;
    private final int DEFAULT_WIDTH = 1300;
    private final int DEFAULT_HEIGHT = 740;
    private ArrayList<String> navItem = new ArrayList<>();  // chứa thông tin có button cho menu
    private final ArrayList<navItem> navObj = new ArrayList<>();  // chứa button trên thanh menu

    public UI() throws FileNotFoundException, Exception {
        view();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UI app = new UI();
                app.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void view() throws FileNotFoundException {
        setLayout(new BorderLayout());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        
        // header
        header = new JPanel(null);
        header.setBackground(new Color(25, 25, 34));
        header.setPreferredSize(new Dimension(DEFAULT_WIDTH, 40));

        header hmain = new header(DEFAULT_WIDTH, 40);

        // tạo btn EXIT & MINIMIZE
        navItem exit = new navItem("", new Rectangle(DEFAULT_WIDTH - 50, -8, 50, 50), "exit_25px.png", "exit_25px.png", "exit_hover_25px.png", new Color(240, 71, 74));
        navItem mini = new navItem("", new Rectangle(DEFAULT_WIDTH - 100, -8, 50, 50), "minimize_25px.png", "minimize_25px.png", "minimize_hover_25px.png", new Color(80, 80, 80));

        hmain.add(exit.isButton());
        hmain.add(mini.isButton());

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //xoá dữ liêụ
                FileWriter fileWriter;
                try {
                    fileWriter = new FileWriter("./src/UI/data.txt");

                    // Ghi nội dung trống vào file để xóa dữ liệu cũ
                    fileWriter.write("");
                    fileWriter.close();

                } catch (IOException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });

        mini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setState(Frame.ICONIFIED);
            }
        });

        header.add(hmain);

        // nav
        nav = new JPanel(null);
        nav.setBackground(new Color(55, 63, 81));
        nav.setPreferredSize(new Dimension(220, DEFAULT_HEIGHT));

        JScrollPane scroll = new JScrollPane(nav);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(1, 100));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm ( Tên btn : icon : icon hover )
        navItem.add("ALGORITHM:icons8_flow_chart_30px_1.png:icons8_flow_chart_30px_1.png");
        navItem.add("TABLE & CHART:icons8-combo-chart-30.png:icons8-combo-chart-30.png");

        outNav();

        // phần main (hiển thị)
        main = new JPanel(null);
        navObj.get(0).doActive();
        changeMainInfo(0);

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);

        setVisible(true);

    }

    private void outNav() {
        navObj.clear();
        for (int i = 0; i < navItem.size(); i++) {
            String s = navItem.get(i).split(":")[0];
            String icon = navItem.get(i).split(":")[1];
            String iconActive = navItem.get(i).split(":")[2];
            navObj.add(new navItem(s, new Rectangle(0, 200 + 50 * i, 220, 50), icon, iconActive));
            navObj.get(i).addMouseListener(this);
        }
        if (!flag && navObj.size() > 8) {
            navObj.get(4).setColorNormal(new Color(86, 94, 127));
            navObj.get(5).setColorNormal(new Color(86, 94, 127));
        }

        // xuất ra Naigation
        nav.removeAll();
        JLabel profile = new JLabel(new ImageIcon("./src/img/business-icon.png"));
        profile.setBounds(0, 0, 210, 210);
        nav.add(profile);
        for (navItem n : navObj) {
            nav.add(n);
        }
        repaint();
        revalidate();
    }

    private void changeMainInfo(int i) throws FileNotFoundException {
        // đổi phần hiển thị khi bấm btn trên menu
        switch (i) {
            case 0 -> {
                // ALGORITHM
                main.removeAll();
                main.add(new AlgorithmUI());
                main.repaint();
                main.revalidate();
            }
            case 1 -> {
                // TABLE
                main.removeAll();
                main.add(new TableChartUI());
                main.repaint();
                main.revalidate();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < navObj.size(); i++) {
            navItem item = navObj.get(i); // lấy vị trí item trong menu
            if (e.getSource() == item) {
                item.doActive(); // Active NavItem đc chọn
                try {
                    changeMainInfo(i); // Hiển thị ra phần main
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                item.noActive();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
