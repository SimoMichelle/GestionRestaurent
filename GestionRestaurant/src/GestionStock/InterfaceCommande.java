package GestionStock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceCommande extends JFrame {

	private JPanel contentPane;
	private JLabel titre,label;
	private Font font;
	
	private String pass,log;

	public InterfaceCommande(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
      
		titre=new  JLabel("CHOISIR UNE OPTION");
		titre.setBounds(200, 16, 300, 20);
		titre.setForeground(Color.cyan);
		font = new Font("Arial",Font.ITALIC,25);
		titre.setFont(font);
		contentPane.add(titre);
		
		JCheckBox chckb = new JCheckBox("RESERVER  UNE  TABLE");
		chckb.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		chckb.setFont(font);
		chckb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InterfaceReservTable frame = new InterfaceReservTable(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				dispose();
			}
		});
		chckb.setBounds(76, 84, 242, 23);
		contentPane.add(chckb);

		JCheckBox chckb1 = new JCheckBox("PASSER  UNE  COMMANDE");
		chckb1.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		chckb1.setFont(font);
		chckb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InterfaceFact frame = new InterfaceFact(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				dispose();
			}
		});
		chckb1.setBounds(76, 128, 230, 23);
		contentPane.add(chckb1);

		JButton btn = new JButton("RETOUR A LA PAGE D'AUTHENTIFICATION");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Authentification frame = new Authentification();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						dispose();
					}
				});
			}
		});
		btn.setBounds(306, 218, 300, 29);
		contentPane.add(btn);

		JButton btn1 = new JButton("RETOUR A LA PAGE D'ACCEUIL");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HomeInterface frame = new HomeInterface(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						dispose();
					}
				});
			}
		});
		btn1.setBounds(374, 127, 232, 29);
		contentPane.add(btn1);

		JCheckBox chckb3 = new JCheckBox("MENUS DES PLATS");
		chckb3.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		chckb3.setFont(font);
		chckb3.setBounds(76, 170, 225, 23);
		contentPane.add(chckb3);
		chckb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MenuPlat frame = new MenuPlat(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
				dispose();
			}
		});

		JCheckBox chckb4 = new JCheckBox("MENUS BOISSONS");
		chckb4.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		chckb4.setFont(font);
		chckb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MenuBoisson frame = new MenuBoisson(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				dispose();
			}
		});
		chckb4.setBounds(76, 219, 242, 23);
		contentPane.add(chckb4);

	    label=new JLabel();
		label.setBackground(new Color(255, 204, 255));
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/java/image1.jpg"));
		contentPane.add(label);
		label.setBounds(1, 1, 700, 403);
	}
}
