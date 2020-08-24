package gestionCommande;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import GestionStock.ClassConnexion;
import GestionStock.HomeInterface;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class GestionSTock extends JFrame {

	private DefaultTableModel model;
	private Font font;
	private JPanel contentPane;
	private JTextField textNouveCath;
	private JTextField textNom;
	private JLabel nouvelCath,nom,titre,cathOutput,nomProd,quantOutput,id,quantInput,nom1,cath1,cath,label;
	private JTextField textFieldQuant;
	private JTable table;
	private JButton btnAffiche,btnMod,btnRetour;
	private JPanel panel2;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JTextField textQuantite;
	private JTextField textId;
	private ClassConnexion conn;
	private Statement stat;
	private ResultSet result,resul;
	private JComboBox comboBox_1;
	private JComboBox comboBoxPro;
	private String log,pass;

	public GestionSTock(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(5, 72, 511, 229);
		panel.setBorder ( new TitledBorder("Enregistrer Un NouveauProduit Dans Le Stock"));
		contentPane.add(panel);
		panel.setLayout(null);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(190, 138, 203, 27);
		panel.add(comboBox_1);


		String reqtt="select nomCath from CathegorieProduit";
		conn =new ClassConnexion();
		try {

			stat=conn.getConnexion().createStatement();
			resul=stat.executeQuery(reqtt);
			comboBox_1.addItem("");

			while(resul.next()) {

				comboBox_1.addItem(resul.getString("nomCath"));}

		}catch(SQLException e1) {
			e1.printStackTrace();}

		titre=new JLabel("BIENVENUE  DANS  LE  STOCK  DU  RESTAURANT");

		titre.setBounds(100,5,750,30);
		titre.setForeground(Color.CYAN);
		font = new Font("Arial",Font.ITALIC,30);
		titre.setFont(font);
		contentPane.add(titre);

		nouvelCath=new JLabel("NOUVELLE CATHEGORIE");
		nouvelCath.setBounds(10,37,150,30);
		panel.add(nouvelCath);

		textNouveCath = new JTextField();
		/*textNouveCath.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );*/
		textNouveCath.setBounds(190, 31, 175, 37);
		panel.add(textNouveCath);
		textNouveCath.setColumns(10);

		nom=new JLabel("NOM DU PRODUIT");
		nom.setBounds(10, 91, 150, 30);
		panel.add(nom);

		textNom = new JTextField();
		textNom.setBounds(190, 85, 175, 37);
		panel.add(textNom);
		textNom.setColumns(10);

		JButton btnRegister = new JButton("ENREGISTRER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conn =new ClassConnexion();

				int nbre=0,idc=0;
				String requette="insert into CathegorieProduit(nomCath) values('"+textNouveCath.getText()+"')";

				String request="select max(idCath) as idcath from CathegorieProduit";
				if(!textNouveCath.getText().equals("")) {
					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(requette);
						result=stat.executeQuery(request);
						JOptionPane.showInputDialog(null, "NOUVELLE CATHEGORIE ENREGISTRE");

						if(result.next())
							nbre=Integer.parseInt(result.getString("idCath"));
					}catch(SQLException e1) {
						e1.printStackTrace();}}

				if(!textNom.getText().equals("") && !textNouveCath.getText().equals("")) {

					String req="insert into Produit(idCath,nom) values("+nbre+",'"+textNom.getText()+"')";
					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(req);
						JOptionPane.showInputDialog(null, "NOUVEAU PRODUIT ENREGISTRE");
					}catch (SQLException e1) {
						e1.printStackTrace();
					}}  else{  


						String req="select idCath from CathegorieProduit where nomCath='"+comboBox_1.getSelectedItem().toString()+"'";

						try {

							stat=conn.getConnexion().createStatement();
							result=stat.executeQuery(req);

							if(result.next()) {
								idc=Integer.parseInt(result.getString("idCath"));
							}
						}catch (SQLException e1) {
							e1.printStackTrace();
						}

						String reqt="insert into Produit(idCath,nom) values("+idc+",'"+textNom.getText()+"')";
						try {
							stat=conn.getConnexion().createStatement();
							stat.executeUpdate(reqt);
							JOptionPane.showInputDialog(null, "NOUVEAU PRODUIT ENREGISTRE");
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
		btnRegister.setBounds(10, 175, 117, 37);
		panel.add(btnRegister);

		JButton btnAlter = new JButton("MODIFIER");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="select idCath from CathegorieProduit where nomCath='"+comboBox_1.getSelectedItem().toString()+"'";
				int idc=0;

				try {

					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(req);

					if(result.next()) {
						idc=Integer.parseInt(result.getString("idCath"));
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(!textNouveCath.getText().equals("")) {
					String requette="update CathegorieProduit set nomCath='"+textNouveCath.getText()+"' where idCath="+idc+"";

					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(requette);
						JOptionPane.showConfirmDialog(null, "MODIFIE AVEC SUCCES");
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAlter.setBounds(170, 177, 117, 33);
		panel.add(btnAlter);

		JButton btn1 = new JButton("CLEAN");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNouveCath.setText("");
				textNom.setText("");
				comboBox_1.setSelectedIndex(0);
			}
		});
		btn1.setBounds(330, 177, 117, 33);
		panel.add(btn1);

		cath=new JLabel("CATHEGORIE");
		cath.setBounds(10,138, 97, 27);
		panel.add(cath);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 250, 240));
		panel1.setBorder ( new TitledBorder("RENSEIGNER LE PRODUIT A RETIRER"));
		panel1.setBounds(528, 72, 468, 229);
		contentPane.add(panel1);
		panel1.setLayout(null);

		cathOutput=new JLabel("CATHEGORIE");
		cathOutput.setBounds(10, 21, 120, 30);
		panel1.add(cathOutput);

		JComboBox comboBoxCath = new JComboBox();
		comboBoxCath.setBounds(196, 21, 180, 35);
		panel1.add(comboBoxCath);

		nomProd=new JLabel("NOM PRODUIT");
		nomProd.setBounds(10, 68, 150, 30);
		panel1.add(nomProd);

		comboBoxPro = new JComboBox();
		comboBoxPro.setBounds(196, 68, 180, 35);
		panel1.add(comboBoxPro);

		remplircomboproduit();


		String requet="select nomCath from CathegorieProduit";
		try {

			stat=conn.getConnexion().createStatement();
			resul=stat.executeQuery(requet);
			comboBoxCath.addItem("");

			while(resul.next()) {

				comboBoxCath.addItem(resul.getString("nomCath"));}

		}catch(SQLException e1) {
			e1.printStackTrace();}

		quantOutput=new JLabel("QUANTITE A RETIRER");
		quantOutput.setBounds(10, 120, 150, 30);
		panel1.add(quantOutput);

		textFieldQuant = new JTextField();
		textFieldQuant.setBounds(196, 120, 175, 37);
		panel1.add(textFieldQuant);
		textFieldQuant.setColumns(10);

		JButton btnValider = new JButton("VALIDER");
		btnValider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String req="select quantite from Produit where nom ='"+comboBoxPro.getSelectedItem().toString()+"'";
				int nbr=0;

				try {
					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(req);

					if(result.next()) {
						nbr=Integer.parseInt(result.getString("quantite"));
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println(nbr);
				if(nbr>Integer.parseInt(textFieldQuant.getText())) {
					int nb=nbr- Integer.parseInt(textFieldQuant.getText());
					System.out.println(nb);
					String request="update Produit set quantite="+nb+" where nom ='"+comboBoxPro.getSelectedItem().toString()+"'";

					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(request);
						JOptionPane.showConfirmDialog(null, "QUANTITE MODIFIER");
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else
					JOptionPane.showInputDialog(null, "La quantité contenu dans le stock est insuffisant");



			}
		});
		btnValider.setBounds(85, 180, 117, 35);
		panel1.add(btnValider);

		JButton btn = new JButton("CLEAN");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldQuant.setText("");
				comboBoxCath.setSelectedIndex(0);
				comboBoxPro.setSelectedIndex(0);

			}
		});
		btn.setBounds(259, 180, 117, 35);
		panel1.add(btn);

		JScrollPane scrollPane = new JScrollPane();
		model=new DefaultTableModel();
		Object[] column= {"Identifiant du produit","Identifiant de la cathegorie","Nom du Produit","Quantite en Kg"};
		final Object[] row=new Object[7];
		model.setColumnIdentifiers(column);
		scrollPane.setBounds(6, 320, 507, 258);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnRetour=new JButton("RETOUR A LA PAGE D'ACCEUIL");
		btnRetour.addActionListener(new ActionListener() {
			
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
		btnRetour.setBounds(500, 584, 296, 39);
		contentPane.add(btnRetour);

		btnAffiche = new JButton("INFORMATIONS SUR LES PRODUITS EN STOCK");
		btnAffiche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String requette="select * from Produit";

				try {
					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(requette);
					table.setModel(DbUtils.resultSetToTableModel(result));

				}catch (SQLException e1) {
					e1.printStackTrace();}

			}
		});
		btnAffiche.setBounds(100, 584, 296, 39);
		contentPane.add(btnAffiche);

		panel2 = new JPanel();
		panel2.setBackground(new Color(255, 250, 240));
		panel2.setBorder ( new TitledBorder("RENSEIGNER LE PRODUIT ACHETE"));
		panel2.setBounds(525, 320, 471, 258);
		contentPane.add(panel2);
		panel2.setLayout(null);

		cath1=new JLabel("CATHEGORIE");
		cath1.setBounds(10,49, 185, 27);
		panel2.add(cath1);

		comboBox = new JComboBox();
		comboBox.setBounds(183, 49, 185, 27);
		panel2.add(comboBox);

		remplircomboCath();

		nom1=new JLabel("NOM DU PRODUIT");
		nom1.setBounds(10,98, 185, 27);
		panel2.add(nom1);

		comboBox1 = new JComboBox();
		comboBox1.setBounds(183, 98, 185, 27);
		panel2.add(comboBox1);

		remplircombo();

		quantInput=new JLabel("QUANTITE");
		quantInput.setBounds(10, 144, 100, 30);
		panel2.add(quantInput);

		textQuantite = new JTextField();
		textQuantite.setBounds(183, 144, 175, 37);
		panel2.add(textQuantite);
		textQuantite.setColumns(10);

		id=new JLabel("ID PRODUIT");
		id.setBounds(10, 193, 175, 30);
		panel2.add(id);

		JButton btn5=new JButton("VALIDER");
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String req="select quantite from Produit where nom ='"+comboBox1.getSelectedItem().toString()+"'";
				int nbre=0;

				try {
					stat=conn.getConnexion().createStatement();
					result=stat.executeQuery(req);

					if(result.next()) {
						nbre=Integer.parseInt(result.getString("quantite"));
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}


				if(!textQuantite.getText().equals("")) {

					int nb=Integer.parseInt(textQuantite.getText().toString());
					int addnbre=nbre+ nb;
					String requet ="update Produit set quantite="+addnbre+" where nom ='"+comboBox1.getSelectedItem().toString()+"' ";
					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(requet);
						JOptionPane.showConfirmDialog(null, "QUANTITE MODIFIE AVEC SUCCES");
					}catch (SQLException e1) {
						e1.printStackTrace();
					}}
			}
		});
		btn5.setBounds(10, 225, 150, 30);
		panel2.add(btn5);

		textId = new JTextField();
		textId.setBounds(183, 193, 175, 37);
		panel2.add(textId);
		textId.setColumns(10);

		btnMod=new JButton("MODIFIER");
		btnMod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(!textId.getText().equals("")) {
					String s="update Produit set quantite="+Integer.parseInt(textQuantite.getText())+" where id="+Integer.parseInt(textId.getText())+"";

					try {
						stat=conn.getConnexion().createStatement();
						stat.executeUpdate(s);
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnMod.setBounds(350, 225, 120, 30);
		panel2.add(btnMod);
		

		label=new JLabel();
		label.setBackground(new Color(255, 204, 255));
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/java/image1.jpg"));
		contentPane.add(label);
		label.setBounds(1, 1, 1002, 651);

	}

	public void remplircomboproduit() {

		conn =new ClassConnexion();
		String request="select nom from Produit";

		try {
			stat=conn.getConnexion().createStatement();
			resul=stat.executeQuery(request);
			comboBoxPro.addItem("");

			while(resul.next()) {
				comboBoxPro.addItem(resul.getString("nom"));}

		}catch(SQLException e1) {
			e1.printStackTrace();}

	}

	public void remplircomboCath() {

		String requet="select nomCath from CathegorieProduit";

		try {
			stat=conn.getConnexion().createStatement();
			resul=stat.executeQuery(requet);
			comboBox.addItem("");

			while(resul.next()) {
				comboBox.addItem(resul.getString("nomCath"));}

		}catch(SQLException e1) {
			e1.printStackTrace();}
	}

	public void remplircombo() {

		String request="select nom from Produit";

		try {
			stat=conn.getConnexion().createStatement();
			resul=stat.executeQuery(request);
			comboBox1.addItem("");

			while(resul.next()) {
				comboBox1.addItem(resul.getString("nom"));}

		}catch(SQLException e1) {
			e1.printStackTrace();}}




}
