package presentation;

import dataaccess.ProductDAO;
import model.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class ProductPage extends JFrame{
    private JPanel panel1;
    private JTextField nameTFP;
    private JTextField priceTFP;
    private JTextField piecesNrTFP;
    private JButton searchP;
    private JLabel piecesNrP;
    private JLabel emailP;
    private JLabel nameP;
    private JButton modifyP;
    private JButton addP;
    private JButton deleteP;
    private JPanel PRODUCTPAGE;
    private JButton BACKButton;
    private JTable table1;
    private JTextField textField1P;

    private static int WIDTH=460;
    private static int HEIGHT=320;

    public ProductPage() {
        super();
        this.setContentPane(panel1);
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGui mpp=new MainGui();
            }
        });
        searchP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductDAO cd=new ProductDAO();
                List<Produs> products=cd.printAll();
                cd.writeTable(table1,products);
            }
        });
        modifyP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id, nr_buc;
                String denumire;
                double pret;
                denumire=nameTFP.getText();
                id=Integer.valueOf(textField1P.getText());
                pret=Double.valueOf(priceTFP.getText());
                nr_buc=Integer.valueOf(piecesNrTFP.getText());
                Produs bh=new Produs(id,denumire,pret,nr_buc);
                ProductDAO cd=new ProductDAO();
                cd.modifyF(bh,id);
                cd.printAll();
            }
        });
        addP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random= new Random();
                int id, nr_buc;
                String denumire;
                double pret;
                denumire=nameTFP.getText();
                id=Integer.valueOf(textField1P.getText());
                pret=Double.valueOf(priceTFP.getText());
                nr_buc=Integer.valueOf(piecesNrTFP.getText());
                Produs ch=new Produs(id,denumire,pret,nr_buc);
                ProductDAO cd=new ProductDAO();
                cd.insertF(ch);
                cd.printAll();
            }
        });
        deleteP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.valueOf(textField1P.getText());
                ProductDAO cd=new ProductDAO();
                cd.deleteF(id);
                cd.printAll();
            }
        });
    }
}
