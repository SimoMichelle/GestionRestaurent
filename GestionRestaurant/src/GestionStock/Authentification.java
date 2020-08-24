package GestionStock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import GestionCommande.ClassConnexion;
//import GestionCommande.HomeRecep;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Authentification extends JFrame {
	
	private JLabel titre,image,login,password;
	private Font font;
	private JPanel contentPane;
	private JTextField textLogin;
	private JTextField textPassword;
	private JCheckBox chck;
	private JButton btn;
	private JButton btnClean;
	String  requete;
	
	ClassConnexion conn;
	
	private  String log,passw;
	private static String repass,relog;


	public Authentification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		image=new JLabel();
		image.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/image.jpg"));
		contentPane.add(image);
		image.setBounds(1,1 ,450 ,262);
		
		titre=new JLabel("BIENVENUE AU RESTAURENT LE REGAL");
		titre.setBounds(466,10,430,70);
		titre.setForeground(Color.cyan);
		font = new Font("Arial",Font.ITALIC,20);
		titre.setFont(font);

		contentPane.add(titre);
		login=new JLabel("Login");
		login.setBounds(466,69 ,50 ,30);
		contentPane.add(login);
		textLogin = new JTextField();
		textLogin.setBounds(536, 66, 251, 37);
		contentPane.add(textLogin);
		textLogin.setColumns(10);
		
		password=new JLabel("Password");
		password.setBounds(466,115,80 ,30);
		contentPane.add(password);
		textPassword = new JTextField();
		textPassword.setText("");
		textPassword.setBounds(536, 115, 251, 36);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		chck = new JCheckBox("Alter Password");
		chck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AlterPassword frame = new AlterPassword();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						dispose();
					}
				});
				
			}
		});
		chck.setBounds(479, 178, 128, 23);
		contentPane.add(chck);
		
		repass=textPassword.getText();
		relog=textLogin.getText();
		
		
		btn = new JButton("SingUp");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn =new ClassConnexion();
				requete ="select * from Employe where login='"+textLogin.getText().trim()+"' and password='"+textPassword.getText().trim()+"'";

				try {
					Statement stat=conn.getConnexion().createStatement();
					ResultSet result=stat.executeQuery(requete);
					System.out.println(repass+","+relog);
					if(result.next()) {
						
						log=result.getString("login");
						passw=result.getString("password");
						System.out.println(log+","+passw);
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									HomeInterface frame = new HomeInterface(textLogin.getText(),textPassword.getText());
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
								dispose();
							}
						});
						
					}else
						JOptionPane.showMessageDialog(null, "Incorrect Login or Password");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btn.setBounds(449, 226, 117, 29);
		contentPane.add(btn);
		
		btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textLogin.setText(" ");
				textPassword.setText(" ");
				
			}
		});
		btnClean.setBounds(601, 226, 117, 29);
		contentPane.add(btnClean);
		}

	public static String getRepass() {
		return repass;
	}

	public static String getRelog() {
		return relog;
	}
	

}
