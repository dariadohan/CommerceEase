package presentation;

import dataaccess.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class ClientPage extends JFrame{
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField pnTF;
    private JButton searchC;
    private JLabel nameC;
    private JLabel emailC;
    private JButton modifyC;
    private JButton addC;
    private JButton deleteC;
    private JLabel phoneC;
    private JButton backButton;
    private JPanel clientPage;
    private JTable table1;
    private JTextField textField1;
    private static int WIDTH=460;
    private static int HEIGHT=320;

    public ClientPage() {
        super();
        this.setContentPane(clientPage);
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGui mpp=new MainGui();
            }
        });
        this.setVisible(true);
        searchC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientDAO cd=new ClientDAO();
                List <Client> clientList=cd.printAll();
                cd.writeTable(table1,clientList);
            }
        });
        addC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random= new Random();
                String name, email, pn;
                name=nameTF.getText();
                email=emailTF.getText();
                pn=pnTF.getText();
                Client tinca =new Client(random.nextInt(),name,email,pn);
                ClientDAO cd= new ClientDAO();
                cd.insertF(tinca);
                cd.printAll();
            }
        });
        modifyC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                String name, email, pn;
                name=nameTF.getText();
                email=emailTF.getText();
                pn=pnTF.getText();
                id=Integer.valueOf(textField1.getText());
                Client ah=new Client(id,name,email,pn);
                ClientDAO cd=new ClientDAO();
                cd.modifyF(ah,id);
                cd.printAll();
            }
        });
        deleteC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int id=Integer.valueOf(textField1.getText());
               ClientDAO cd=new ClientDAO();
               cd.deleteF(id);
               cd.printAll();
            }
        });
    }
}
