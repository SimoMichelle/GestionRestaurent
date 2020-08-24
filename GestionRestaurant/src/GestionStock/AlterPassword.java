package GestionStock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AlterPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textLogin;
	private JTextField textAcien;
	private JTextField textNouveau;
	private JLabel login,newPassword,oldPassword,titre,label;
	
	private ClassConnexion conn;
	private Statement stat;
	private ResultSet result;
	private Font font;

	public AlterPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("PAGE DE MODIFICATION DU MOT DE PASSE");
		contentPane.setLayout(null);
		
		login=new JLabel("LOGIN");
		login.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		login.setFont(font);
		login.setBounds(10, 43, 100, 37);
		contentPane.add(login);
		
		textLogin = new JTextField();
		textLogin.setBounds(206, 43, 235, 37);
		contentPane.add(textLogin);
		textLogin.setColumns(10);
		
		oldPassword=new JLabel("ANCIEN MOT DE PASSE");
		oldPassword.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		oldPassword.setFont(font);
		oldPassword.setBounds(10, 116, 200, 37);
		contentPane.add(oldPassword);
		
		textAcien = new JTextField();
		textAcien.setBounds(206, 116, 235, 37);
		contentPane.add(textAcien);
		textAcien.setColumns(10);
		
		newPassword=new JLabel("NOUVEAU MOT DE PASSE");
		newPassword.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		newPassword.setFont(font);
		newPassword.setBounds(10, 179, 200, 37);
		contentPane.add(newPassword);
		
		textNouveau = new JTextField();
		textNouveau.setBounds(206, 179, 235, 37);
		contentPane.add(textNouveau);
		textNouveau.setColumns(10);
		
		JButton btn = new JButton("VALIDER");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn=new ClassConnexion();
				String s="";
				String requette="select password from Employe where login='"+textLogin.getText()+"'";
				String request="update Employe set password='"+textNouveau.getText()+"' where login='"+textLogin.getText()+"'";
				try {
					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(requette);
					if(result.next())
						s=result.getString("password");

					if(!s.equals("") && s.contentEquals(textAcien.getText())) {
						stat.executeUpdate(request);
					}else {
						JOptionPane.showConfirmDialog(null,"LOGIN OU MOT DE PASSE INCORRECT");
					}
					result.close();
					stat.close();
					conn.getConnexion().close();
					JOptionPane.showConfirmDialog(null, "MOT DE PASSE MODIFIER");
						
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btn.setBounds(116, 238, 117, 29);
		contentPane.add(btn);
		
		JButton btnAnnuler = new JButton("ANNULER");
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textLogin.setText("");
				textAcien.setText("");
				textNouveau.setText("");
				
			}
		});
		btnAnnuler.setBounds(287, 238, 117, 29);
		contentPane.add(btnAnnuler);
		
		JButton btnRetour = new JButton("RETOUR A LA PAGE D'AUTHENTIFICATION");
		btnRetour.addActionListener(new ActionListener() {
			
			@Override
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
		btnRetour.setBounds(116, 286, 291, 29);
		contentPane.add(btnRetour);
		
		label=new JLabel();
		label.setBackground(new Color(255, 204, 255));
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/java/image1.jpg"));
		contentPane.add(label);
		label.setBounds(1, 1, 541, 343);
	}
}
