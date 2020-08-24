package GestionStock;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

//import GestionCommande.ClassConnexion;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JScrollPane;

public class InterfaceFact extends JFrame {

	private JPanel contentPane;
	private JTextField textNom,textid;
	private JLabel nomC,nomCl,cath,produit,quantite,idlabel,label;
	private JButton btn;
	private JButton btn1,btn2,btn3,btn4;
	private JPanel panel_1;
	private JComboBox comboBox,comboBox_1,comboBox_2;
	private JTextField textQuantite;
	private JButton btnP;
	private JButton btnP1;
	private JTable table;
	private DefaultTableModel model;
	private Font font;

	private ClassConnexion conn;
	private String requete,request,requet,requette,nomclient,pass,log;
	private Statement stat;
	private int prixUnitaire,prixTotal,idEmpl,idClient;

	public InterfaceFact(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 713);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 457, 165);
		contentPane.add(panel);
		panel.setBorder ( new TitledBorder("Enregistrer Un Client"));
		panel.setLayout(null);

		nomC=new JLabel("Nom Client");
		nomC.setBounds(17,41 ,100 ,30 );
		panel.add(nomC);

		textNom = new JTextField();
		textNom.setBounds(129, 36, 275, 41);
		panel.add(textNom);
		textNom.setColumns(10);

		btn = new JButton("ENREGISTRER LE CLIENT");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn=new ClassConnexion();
				requete ="insert into Client(nomclient) value('"+textNom.getText()+"') ";

				try {

					stat=conn.getConnexion().createStatement();
					stat.executeUpdate(requete);
					JOptionPane.showMessageDialog(null, "Client Enregitré");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btn.setBounds(17, 118, 180, 29);
		panel.add(btn);

		btn1 = new JButton("ANNULER");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNom.setText(" ");
			}
		});
		btn1.setBounds(250, 80, 117, 29);
		panel.add(btn1);

		JButton btnNew = new JButton("RETOUR A LA PAGE D'ACCEUIL");
		btnNew.addActionListener(new ActionListener() {

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
		btnNew.setBounds(215, 118, 220, 29);
		panel.add(btnNew);

		panel_1 = new JPanel();
		panel_1.setBounds(6, 183, 457, 251);
		contentPane.add(panel_1);
		panel_1.setBorder ( new TitledBorder("Entrer Une Commande"));
		panel_1.setLayout(null);

		nomCl=new JLabel("Nom Client");
		nomCl.setBounds(10,20 ,100,30);
		panel_1.add(nomCl);

		comboBox = new JComboBox();
		comboBox.setBounds(155, 20, 237, 37);

		conn=new ClassConnexion();
		request="select * from Client";

		try {
			stat=conn.getConnexion().createStatement();
			ResultSet result=stat.executeQuery(request);
			comboBox.addItem("");
			while(result.next()) {
				comboBox.addItem(result.getString("nomclient"));

			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}

		panel_1.add(comboBox);

		cath=new JLabel("Cathegorie");
		cath.setBounds(10,69,100,30);
		panel_1.add(cath);

		comboBox_1 = new JComboBox();

		requet="select * from Cathegorie";

		try {
			Statement stat=conn.getConnexion().createStatement();
			ResultSet result=stat.executeQuery(requet);
			comboBox_1.addItem("");
			while(result.next()) {
				comboBox_1.addItem(result.getString("nom"));

			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}

		comboBox_1.setBounds(155, 69, 237, 37);
		panel_1.add(comboBox_1);

		comboBox_1.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String r="select PlatBoisson.nomPB from PlatBoisson,Cathegorie where PlatBoisson.idcathegorie = Cathegorie.idcathegorie and Cathegorie.nom = '"+comboBox_1.getSelectedItem().toString().trim()+"' ";

				try {
					comboBox_2.removeAllItems();
					stat=conn.getConnexion().createStatement();
					ResultSet resul=stat.executeQuery(r);
					comboBox_2.addItem("");
					while(resul.next()) {
						comboBox_2.addItem(resul.getString("nomPB"));
					}
					comboBox_2.repaint();
					comboBox_2.revalidate();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});

		produit= new JLabel("Produit");
		produit.setBounds(10,121,100,30);
		panel_1.add(produit);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(155, 121, 237, 37);
		panel_1.add(comboBox_2);

		quantite= new JLabel("Quantité");
		quantite.setBounds(10, 170, 100, 30);
		panel_1.add(quantite);

		textQuantite = new JTextField();
		textQuantite.setBounds(155, 170, 237, 26);
		panel_1.add(textQuantite);
		textQuantite.setColumns(10);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 446, 457, 200);
		model=new DefaultTableModel();
		Object[] column= {"Nom Du Produit","Quantité","Prix Unitaire","Prix Total"};
		final Object[] row=new Object[4];
		model.setColumnIdentifiers(column);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);

		btn2=new JButton("RETIRER DE LA LISTE");
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				model.removeRow(i);}
		});
		btn2.setBounds(16,660 ,170 ,30 );
		contentPane.add(btn2);

		btn3=new JButton("ENREGISTRER");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conn=new ClassConnexion();
				String req="update Facture set type="+1+" where idfacture="+Integer.parseInt(textid.getText())+"";
				try {
					stat=conn.getConnexion().createStatement();
					stat.executeUpdate(req);
				}catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btn3.setBounds(200,660 ,120 ,30 );
		contentPane.add(btn3);

		btnP = new JButton("AJOUTER A LA LISTE");
		btnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn=new ClassConnexion();
				String req="select idclient from Client where nomclient='"+comboBox.getSelectedItem().toString()+"'";

				try {
					Statement stat=conn.getConnexion().createStatement();
					ResultSet result=stat.executeQuery(req);

					if(result.next()) {
						idClient=Integer.parseInt(result.getString("idclient"));
					}
				}catch(SQLException e1) {

				}

				if(comboBox.getSelectedItem().toString().equals("")||comboBox_1.getSelectedItem().toString().equals("")||comboBox_2.getSelectedItem().toString().equals("")||textQuantite.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "UNE DES ZONES DE TEXTE A REMPLIR EST VIDE");
				}else {
					prixunitaire();
					row[0]=comboBox_2.getSelectedItem().toString();
					row[1]=Integer.parseInt(textQuantite.getText());
					row[2]=prixUnitaire;
					row[3]=prixUnitaire*Integer.parseInt(textQuantite.getText());
					model.addRow(row);


					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					comboBox_2.setSelectedIndex(0);
					textQuantite.setText("");}
			}
		});
		btnP.setBounds(20, 212, 170, 29);
		panel_1.add(btnP);

		btn4=new JButton("VALIDER");
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String s=format.format(date);
				conn=new ClassConnexion();
				Statement statement;
				ResultSet result;
				String req="select idEmploye from Employe where login='"+log+"'";
				Object [] tab=new Object [2];
				ArrayList<Object> list=new ArrayList();
				String st="",requet,requett;
				int k=0,l=0;

				try {

					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(req);

					if(result.next()) {

						idEmpl=Integer.parseInt(result.getString("idEmploye"));
					}

				}catch (SQLException e1) {
					e1.printStackTrace();
				}

				String request="insert into Facture(idclient,idemploye,date,type) values("+idClient+","+idEmpl+",'"+s+"',"+0+")";

				try {

					stat=conn.getConnexion().createStatement();
					stat.executeUpdate(request);

				}catch (SQLException e1) {
					e1.printStackTrace();
				}

				String reques="select max(idFacture) as idFacture from Facture";

				try {

					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(reques);
					if(result.next())
						l=Integer.parseInt(result.getString("idFacture"));

				}catch (SQLException e1) {
					e1.printStackTrace();
				}


				for(int i=0;i<table.getRowCount();i++) {
					
					st =table.getModel().getValueAt(i,0).toString();
					int t =Integer.parseInt(table.getModel().getValueAt(i,1).toString());
					requett="select idPB from PlatBoisson where nomPB='"+st+"'";
					
					try {
						stat=conn.getConnexion().createStatement();
						result=stat.executeQuery(requett);
						if(result.next())
							k=Integer.parseInt(result.getString("idPB"));
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					requet="insert into FacturePB values("+l+","+k+","+t+")";
					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(requet);
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn4.setBounds(350,660,100,30);
		contentPane.add(btn4);

		btnP1 = new JButton("VIDER LA TABLE");
		btnP1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { 
				while(model.getRowCount()>0) {
					for(int i=0;i<model.getRowCount();i++) {
						model.removeRow(0);
					}

				}

			}
		});
		btnP1.setBounds(226, 212, 200, 29);
		panel_1.add(btnP1);

		idlabel=new JLabel("IDFACTURE");
		idlabel.setBounds(10,80,80,30);
		panel.add(idlabel);

		textid=new JTextField();
		textid.setBounds(90, 80, 117, 29);
		panel.add(textid);
		
		label=new JLabel();
		label.setBackground(new Color(255, 204, 255));
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/java/image1.jpg"));
		contentPane.add(label);
		label.setBounds(1,1,469, 713);
	}

	public int prixunitaire() {
		String st="select prix from PlatBoisson where nomPB='"+comboBox_2.getSelectedItem().toString()+"'";
		try {
			stat=conn.getConnexion().createStatement();
			ResultSet result=stat.executeQuery(st);

			if(result.next())
				prixUnitaire=Integer.parseInt(result.getString("prix"));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return prixUnitaire;
	}

}
