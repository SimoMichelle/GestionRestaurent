package GestionStock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionCommande.GestionSTock;

import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeInterface extends JFrame {

	private JPanel contentPane;
	private JLabel titre;
	private Font font;
	private JLabel label;
	private String pass,log;

	public HomeInterface(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titre=new JLabel("CHOISIR   UNE   OPTION");
		titre.setBounds(335,6 ,244 ,50 );
		titre.setForeground(Color.blue);
		font = new Font("Arial",Font.ITALIC,20);
		titre.setFont(font);
		contentPane.add(titre);
		JCheckBox chckb = new JCheckBox("GERER LES COMMANDES");
		chckb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InterfaceCommande frame = new InterfaceCommande(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				dispose();
			}
		});
		chckb.setBounds(325, 68, 184, 41);
		contentPane.add(chckb);
		
		JCheckBox chckb1 = new JCheckBox("GERER LE STOCK");
		chckb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GestionSTock frame = new GestionSTock(log,pass);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		chckb1.setBounds(325, 121, 164, 41);
		contentPane.add(chckb1);
		
		JButton btn = new JButton("Retour à la Page d'Authentification");
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
		btn.setBounds(325, 208, 250, 29);
		contentPane.add(btn);
			
		
		label=new JLabel();
		label.setBackground(new Color(255, 204, 255));
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/java/image.jpg"));
		contentPane.add(label);
		label.setBounds(6, 6, 355, 266);
		
	}
	
	public void commande() {
		
	}
}
