package presentation;

import dataaccess.ClientDAO;
import dataaccess.FacturaDAO;
import dataaccess.OrderDAO;
import dataaccess.ProductDAO;
import model.Client;
import model.Comanda;
import model.Factura;
import model.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class OrderPage extends JFrame{
    private JPanel panel1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton VIEWButton;
    private JButton MODIFYButton;
    private JButton ERASEButton;
    private JButton ADDButton;
    private JButton backButton;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private static int WIDTH=460;
    private static int HEIGHT=320;

    public OrderPage() {
        super();
        this.setContentPane(panel1);
        this.setSize(new Dimension(500, 500));
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
        VIEWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderDAO cd=new OrderDAO();
                List<Comanda> orderList=cd.printAll();
                cd.writeTable(table1,orderList);
            }
        });
        MODIFYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.valueOf(textField4.getText());;
                String adress;
                adress=textField6.getText();
                int idClient=Integer.parseInt(textField1.getText());
                int idProdus=Integer.parseInt(textField2.getText());
                Comanda ah=new Comanda(id, adress,idClient,idProdus);
                OrderDAO cd=new OrderDAO();
                cd.modifyF(ah,id);
                cd.printAll();
            }
        });
        ERASEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.valueOf(textField4.getText());
                OrderDAO cd=new OrderDAO();
                cd.deleteF(id);
                cd.printAll();
            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random= new Random();
                int id=Integer.valueOf(textField4.getText());;
                String adress=textField6.getText();
                int idClient=Integer.parseInt(textField1.getText());
                int idProdus=Integer.parseInt(textField2.getText());
                Comanda tinca=new Comanda(id, adress,idClient,idProdus);
                OrderDAO cd=new OrderDAO();
                int nrProd=cd.numProducts(idProdus);
                int nrProdReq=Integer.parseInt(textField3.getText());

                Factura factura=new Factura(id,idClient,adress);
                FacturaDAO facturaDAO=new FacturaDAO();
                if(cd.UnderS(idProdus,nrProdReq,panel1)==1)
                    return;
                ProductDAO productDAO = new ProductDAO();
//                if((nrProd-nrProdReq)>0)
                    productDAO.decrementProducts(idProdus,nrProd-nrProdReq);
                facturaDAO.insertF();
                cd.insertF(tinca);
                cd.printAll();
            }
        });
    }
}
