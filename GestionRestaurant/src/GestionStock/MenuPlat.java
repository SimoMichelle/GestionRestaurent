package GestionStock;

import java.awt.BorderLayout;
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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class MenuPlat extends JFrame {

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textDescrip;
	private JTextField textPrix;
	private JButton btn4;
	private JLabel titre,nom,descrip,prix,cath,id;
	private Font font;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox comboBox;

	ClassConnexion conn;
	int idcath;
	private JButton btnNew;
	private JTextField textField;
	private String log,pass;



	public MenuPlat(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 691);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		titre=new JLabel("INFORMATIONS SUR LES PLATS");
		titre.setBounds(60,15 ,450 ,38 );
		titre.setForeground(Color.cyan);
		font = new Font("Arial",Font.ITALIC,20);
		titre.setFont(font);
		contentPane.add(titre);

		nom=new JLabel("Nom");
		nom.setBounds(10,58 ,50 ,30 );
		contentPane.add(nom);

		textNom = new JTextField();
		textNom.setBounds(109, 58, 300, 38);
		contentPane.add(textNom);
		textNom.setColumns(10);

		descrip=new JLabel("Description");
		descrip.setBounds(10, 108, 100, 30);
		contentPane.add(descrip);

		textDescrip = new JTextField();
		textDescrip.setBounds(109, 108, 300, 40);
		contentPane.add(textDescrip);
		textDescrip.setColumns(10);

		prix=new JLabel("Prix Unitaire");
		prix.setBounds(10, 160, 200, 30);
		contentPane.add(prix);

		textPrix = new JTextField();
		textPrix.setBounds(109, 160, 300, 38);
		contentPane.add(textPrix);
		textPrix.setColumns(10);

		JButton btn = new JButton("Ajouter");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conn =new ClassConnexion();
				idcath();
				String nom1= textNom.getText();
				String description1=textDescrip.getText();
				int prix1=Integer.parseInt(textPrix.getText());
				String request=" insert into PlatBoisson(idcathegorie,nomPB,prix,description) values("+idcath+",'"+nom1+"',"+prix1+",'"+description1+"')";

				try {
					Statement stat=conn.getConnexion().createStatement();
					stat.executeUpdate(request);
				}catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btn.setBounds(10, 269, 117, 29);
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
		btn1.setBounds(179, 247, 117, 29);
		contentPane.add(btn1);

		JButton btn2 = new JButton("Supprimer");
		btn2.setBounds(343, 257, 117, 29);
		contentPane.add(btn2);

		btn4 = new JButton("Afficher");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conn=new ClassConnexion();
				String requette="select * from PlatBoisson where idcathegorie="+1+"";
				PreparedStatement stat;
				ResultSet result;

				try {
					stat=conn.getConnexion().prepareStatement(requette);
					result=stat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(result));
				}catch (SQLException e1) {
					e1.printStackTrace();}

			}
		});
		btn4.setBounds(179, 288, 117, 29);
		contentPane.add(btn4);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 329, 473, 301);
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
		comboBox.setBounds(109, 202, 147, 40);
		contentPane.add(comboBox);

		cath=new JLabel("CATHEGORIE");
		cath.setBounds(10,202,100,30);
		contentPane.add(cath);

		btnNew = new JButton("RETOUR A LA PAGE D'ACCEUIL");
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
		btnNew.setBounds(125, 634, 234, 29);
		contentPane.add(btnNew);
		
		textField = new JTextField();
		textField.setBounds(353, 210, 130, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		id=new JLabel("ID");
		id.setBounds(340,210,50,30);
		contentPane.add(id);
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
