package GestionStock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import GestionCommande.ClassConnexion;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class InterfaceReservTable extends JFrame {
	
	

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textNombre;
	private JTextField textHeure;
	private JTextField textMinute;
	private JTextField textSeconde;
	private JTextField textAnnee;
	private JTextField textMois;
	private JTextField textJour,textid;
	private JTable table;
	private JLabel label,nom,prenom,numberPlace,heure,minute,seconde,annee,mois,jour,date,heureDebut,heureFin,id;
	

	private ClassConnexion conn;
	private String log,pass;
	private String request;
	private String pattern  = "yyyy-MM-dd";
	private DateFormat formatter;
	private DefaultTableModel model;
	private Font font;
	
	public InterfaceReservTable(String log,String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 749);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setBounds(58,54, 5, 5);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Reserver une Table");
		
		nom=new JLabel("NOM");
		nom.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		nom.setFont(font);
		nom.setBounds(17,23 ,40 ,36 );
		contentPane.add(nom);
		
		textNom = new JTextField();
		textNom.setBounds(131, 23, 333, 36);
		contentPane.add(textNom);
		textNom.setColumns(10);
		
		prenom=new JLabel("PRENOM");
		prenom.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		prenom.setFont(font);
		prenom.setBounds(17, 88,70 ,36 );
		contentPane.add(prenom);
		
		textPrenom = new JTextField();
		textPrenom.setBounds(131, 88, 333, 36);
		contentPane.add(textPrenom);
		textPrenom.setColumns(10);
		
		numberPlace=new JLabel("NOMBRE DE PLACE");
		numberPlace.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,13);
		numberPlace.setFont(font);
		numberPlace.setBounds(6,151 , 150, 36);
		contentPane.add(numberPlace);
		
		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		});
		textNombre.setBounds(131, 151, 333, 36);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 345, 484, 289);
		model=new DefaultTableModel();
		Object[] column= {"idReservation","nomClient","prenom","nombrePlace","date","heuredebut","heurefin","valide"};
		final Object[] row=new Object[7];
		model.setColumnIdentifiers(column);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		date=new JLabel("CHOISIR UNE DATE");
		date.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,15);
		date.setFont(font);
		date.setBounds(2,210,150 ,30);
		contentPane.add(date);
		
		JDateChooser dat = new JDateChooser();
		dat.setBounds(150, 210, 150, 30);
		contentPane.add(dat);
		
		id=new JLabel("IDRESERV");
		id.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		id.setFont(font);
		id.setBounds(330,210,70,30);
		contentPane.add(id);
		
		textid=new JTextField();
		textid.setBounds(400,210,50,35);
		contentPane.add(textid);
		
		
		
		JButton btn = new JButton("AJOUTER");
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				conn =new ClassConnexion();
				String nom=textNom.getText().trim();
				String prenom=textPrenom.getText().trim();
				
				Date d=dat.getDate();
				formatter = new SimpleDateFormat(pattern);
				String s = formatter.format(d);
				
				int nombre=Integer.parseInt(textNombre.getText().trim());
				int annee=Integer.parseInt(textAnnee.getText().trim());
				int mois=Integer.parseInt(textMois.getText().trim());
				int jour=Integer.parseInt(textJour.getText().trim());
				int heure=Integer.parseInt(textHeure.getText().trim());
				int minute=Integer.parseInt(textMinute.getText().trim());
				int seconde=Integer.parseInt(textSeconde.getText().trim());

				if(textAnnee.getText().trim().length()<3 && textMois.getText().trim().length()<3 && textJour.getText().trim().length()<3 && textHeure.getText().trim().length()<3 && textMinute.getText().trim().length()<3 && textSeconde.getText().trim().length()<3 ) {
					request ="insert into Reservation(nomClient,prenomClient,nombrePlace,date,heuredebut,heurefin,valide) "
							+ "values('"+nom+"','"+prenom+"',"+nombre+","
							+ "'"+s+"','"+annee+":"+mois+":"+jour+"',' "+heure+":"+minute+":"+seconde+"','oui') ";
					try {
						Statement stat=conn.getConnexion().createStatement();
						stat.executeUpdate(request);
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else JOptionPane.showMessageDialog(null, "Le format de année,le mois,le jour,l'heure,les minutes ou les secondes est incorect");


			}
		});
		btn.setBounds(2, 677, 117, 29);
		contentPane.add(btn);
		
		JButton btn1 = new JButton("ANNULER");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn =new ClassConnexion();
                   String requette= "update Reservation set valide='non' where idReservation="+Integer.parseInt(textid.getText())+"";
				if(e.getSource()==btn1) {
					try {
						Statement stat=conn.getConnexion().createStatement();
						stat.executeUpdate(requette);
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn1.setBounds(373, 677, 117, 29);
		contentPane.add(btn1);
	
		JButton bt=new JButton("RETOUR A LA PAGE D'ACCUEIL");
		bt.addActionListener(new ActionListener() {
			
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
		bt.setBounds(250, 646, 215, 29);
		contentPane.add(bt);
		
		JButton btn2 = new JButton("AFFICHER LES RESERVATIONS");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conn=new ClassConnexion();
				PreparedStatement stat;
				ResultSet result;
				String request="select * from Reservation";

				try {
					stat=conn.getConnexion().prepareStatement(request);
					result=stat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(result));

				}catch (SQLException e1) {
					e1.printStackTrace();}

			}
		});
		
		btn2.setBounds(131, 677, 207, 29);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("MODIFIER UNE RESERVATION");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date d=dat.getDate();
				formatter = new SimpleDateFormat(pattern);
				String s = formatter.format(d);
				String nom=textNom.getText().trim();
				String prenom=textPrenom.getText().trim();
				int nombre=Integer.parseInt(textNombre.getText().trim());
				int annee=Integer.parseInt(textAnnee.getText().trim());
				int mois=Integer.parseInt(textMois.getText().trim());
				int jour=Integer.parseInt(textJour.getText().trim());
				int heure=Integer.parseInt(textHeure.getText().trim());
				int minute=Integer.parseInt(textMinute.getText().trim());
				int seconde=Integer.parseInt(textSeconde.getText().trim());
				conn=new ClassConnexion();
				String request="update Reservation set nomClient='"+nom+"' , prenomClient='"+prenom+"' , nombrePlace="+nombre+" , date='"+s+"' , heuredebut='"+annee+":"+mois+":"+jour+"' , heurefin='"+heure+":"+minute+":"+seconde+"' where idReservation="+Integer.parseInt(textid.getText())+"";
                
				System.out.println("voici la requete complete "+request);
				
				try {
                	Statement stat=conn.getConnexion().createStatement();
                	stat.executeUpdate(request);
                }catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btn3.setBounds(39, 646, 207, 29);
		contentPane.add(btn3);
		
		heureDebut=new JLabel("HEURE FIN :");
		heureDebut.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		heureDebut.setFont(font);
		heureDebut.setBounds(6,300,100,30);
		contentPane.add(heureDebut);
		
		heure=new JLabel("HEURE");
		heure.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		heure.setFont(font);
		heure.setBounds(100,297 ,50 ,36);
		contentPane.add(heure);
		
		textHeure = new JTextField();
		textHeure.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );
		textHeure.setBounds(150, 297, 70, 36);
		contentPane.add(textHeure);
		textHeure.setColumns(10);
		

		minute=new JLabel("MINUTE");
		minute.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		minute.setFont(font);
		minute.setBounds(225,297 , 50,36 );
		contentPane.add(minute);
		
		textMinute = new JTextField();
		textMinute.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );
		textMinute.setBounds(273, 297, 65, 36);
		contentPane.add(textMinute);
		textMinute.setColumns(10);
		
		seconde=new JLabel("SECONDE");
		seconde.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		seconde.setFont(font);
		seconde.setBounds(350,297 ,70 ,36 );
		contentPane.add(seconde);
		
		textSeconde = new JTextField();
		textSeconde.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );
		textSeconde.setBounds(414, 297, 76, 36);
		contentPane.add(textSeconde);
		textSeconde.setColumns(10);
		
		heureFin=new JLabel("HEURE DEBUT :");
		heureFin.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		heureFin.setFont(font);
		heureFin.setBounds(6,255,100,30);
		contentPane.add(heureFin);
		
		annee=new JLabel("HEURE");
		annee.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		annee.setFont(font);
		annee.setBounds(100,249 ,50 ,36);
		contentPane.add(annee);
		
		textAnnee = new JTextField();
		textAnnee.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );
		textAnnee.setBounds(150, 249, 70, 36);
		contentPane.add(textAnnee);
		textAnnee.setColumns(10);
		
		mois=new JLabel("MINUTE");
		mois.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		mois.setFont(font);
		mois.setBounds(230,249 ,50 ,36);
		contentPane.add(mois);
		
		textMois = new JTextField();
		textMois.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );
		textMois.setBounds(280, 249, 63, 36);
		contentPane.add(textMois);
		textMois.setColumns(10);
		
		jour=new JLabel("SECONDE");
		jour.setForeground(Color.green);
		font = new Font("Arial",Font.ITALIC,10);
		jour.setFont(font);
		jour.setBounds(350,249 ,100 ,36 );
		contentPane.add(jour);
		
		textJour = new JTextField();
		textJour.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letter = e.getKeyChar();
				if(!Character.isDigit(letter))
					e.consume();}
		} );
		if(textJour.getText().length()>3) {
			JOptionPane.showMessageDialog(null, "Your test size is incorrect");
		}
		textJour.setBounds(414, 249, 76, 31);
		contentPane.add(textJour);	
		
		label=new JLabel();
		label.setBackground(new Color(255, 204, 255));
		label.setIcon(new ImageIcon("/Users/macbookpro/Desktop/java/java/image1.jpg"));
		contentPane.add(label);
		label.setBounds(1,1,496, 749);
	}
}



	
