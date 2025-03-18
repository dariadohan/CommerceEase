package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame{
    private JPanel mainP;
    private JButton COMMANDButton;
    private JButton BILLButton;
    private JButton PRODUCTButton;
    private JButton ClientButton;

    private static int WIDTH=460;
    private static int HEIGHT=320;

    public MainGui() {
        super();
        this.setContentPane(mainP);
        this.setSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        ClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ClientPage cp=new ClientPage();
            }
        });
        PRODUCTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductPage pb =new ProductPage();
            }
        });
        COMMANDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                OrderPage cp= new OrderPage();
            }
        });
        BILLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BillPage bp= new BillPage();
            }
        });
    }

}
