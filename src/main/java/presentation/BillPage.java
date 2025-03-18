package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillPage extends JFrame{
    private JButton CAUTAButton;
    private JButton backButton;
    private JPanel billPage;
    private JTable table1;

    private static int WIDTH=460;
    private static int HEIGHT=320;

    public BillPage() {
        super();
        this.setContentPane(billPage);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGui mpp=new MainGui();
            }
        });
    }
}
