package GestionStock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import GestionCommande.ClassConnexion;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class MenuBoisson extends JFrame {

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrix;
	private JLabel titre,nom,prix,cath,idpb;
	private JTable table;
	private JScrollPane scrollPane;
	private Font font;
	private JComboBox comboBox ;

	ClassConnexion conn;
	int idcath;
	private JTextField textField;
	private String log,pass;


	public MenuBoisson(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		titre=new JLabel("INFORMATIONS SUR LES BOISSONS");
		titre.setBounds(60,15 ,450 ,38 );
		titre.setForeground(Color.cyan);
		font = new Font("Arial",Font.ITALIC,20);
		titre.setFont(font);
		contentPane.add(titre);

		nom=new JLabel("NOM");
		nom.setBounds(20, 72, 50, 30);
		contentPane.add(nom);

		textNom = new JTextField();
		textNom.setBounds(156, 72, 245, 35);
		contentPane.add(textNom);
		textNom.setColumns(10);

		prix=new JLabel("Prix Unitaire");
		prix.setBounds(20,133 ,100,30);
		contentPane.add(prix);

		textPrix = new JTextField();
		textPrix.setBounds(156, 133, 245, 35);
		contentPane.add(textPrix);
		textPrix.setColumns(10);

		JButton btn = new JButton("Ajouter une Boisson");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				idcath();
				conn =new ClassConnexion();
				String nom=textNom.getText();
				int prix=Integer.parseInt(textPrix.getText());
				String request="insert into PlatBoisson(idcathegorie,nomPB,prix) values("+idcath+",'"+nom+"',"+prix+")";
				try {
					Statement stat=conn.getConnexion().createStatement();
					stat.executeUpdate(request);
				}catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btn.setBounds(6, 249, 161, 29);
		contentPane.add(btn);

		JButton btn1 = new JButton("Modifier");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn=new ClassConnexion();
				Statement stat;
				ResultSet result;
				String requette="update PlatBoisson set nomPB='"+textNom.getText()+"',prix="+Integer.parseInt(textPrix.getText())+" where idPB="+Integer.parseInt(textField.getText())+"";
                try {
                	stat=conn.getConnexion().createStatement();
                	stat.executeUpdate(requette);
                }catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn1.setBounds(166, 221, 117, 29);
		contentPane.add(btn1);

		JButton btn2 = new JButton("Supprimer");
		btn2.setBounds(176, 255, 117, 29);
		contentPane.add(btn2);

		JButton btn3 = new JButton("Afficher la Liste de Boisson");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conn=new ClassConnexion();
				PreparedStatement stat;
				ResultSet result;
				String request="select * from PlatBoisson where idcathegorie="+2+"";

				try {
					stat=conn.getConnexion().prepareStatement(request);
					result=stat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(result));

				}catch (SQLException e1) {
					e1.printStackTrace();}
			}
		});
		btn3.setBounds(310, 249, 215, 29);
		contentPane.add(btn3);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 337, 482, 263);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		comboBox = new JComboBox();
		conn =new ClassConnexion();
		String requet="select * from Cathegorie";
		try {
			Statement stat=conn.getConnexion().createStatement();
			ResultSet result=stat.executeQuery(requet);
			comboBox.addItem("");
			while(result.next()) {
				comboBox.addItem(result.getString("nom"));}

		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBox.setBounds(156, 180, 127, 30);
		contentPane.add(comboBox);

		cath=new JLabel("CATHEGORIE");
		cath.setBounds(20,180,100,30);
		contentPane.add(cath);

		JButton btnNew = new JButton("RETOUR A LA PAGE D'ACCEUIL");
		btnNew .addActionListener(new ActionListener() {

			@Override
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
		btnNew.setBounds(113, 296, 221, 29);
		contentPane.add(btnNew);
		
		textField = new JTextField();
		textField.setBounds(395, 193, 130, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		idpb=new JLabel("ID");
		idpb.setBounds(380,193,50,30);
		contentPane.add(idpb);
	}

	public int idcath() {

		conn=new ClassConnexion();
		String requette="select idcathegorie from Cathegorie where nom='"+comboBox.getSelectedItem().toString()+"'";
		try {
			Statement stat=conn.getConnexion().createStatement();
			ResultSet result=stat.executeQuery(requette);

			if(result.next()) {
				idcath=Integer.parseInt(result.getString("idcathegorie"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return idcath;
	}
}
