import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GUI extends JFrame {
    private Connection conn;

    private Asiakas m_asiakas = new Asiakas();
    private Toimipiste m_toimipiste = new Toimipiste();
    //GUI container
    private JPanel pnlContainer;

    //GUI main panel, secondary panels
    private JPanel pnlMain;
    private JPanel pnl1;
    private JPanel pnl2;
    private JPanel pnl3;
    private JPanel pnl4;
    private JPanel pnl5;
    private JPanel pnl6;
    private JPanel pnl7;
    private JPanel pnl8;
    private JButton btnExit;


    //GUI labels pnl1, number correlates with panel number
	private JLabel lblAsiakasID1;
	private JLabel lblEtunimi1;
    private JLabel lblSukunimi1;
	private JLabel lblLahiosoite1;
	private JLabel lblPostinro1;
    private JLabel lblPostitoimipaikka1;
	private JLabel lblEmail1;
    private JLabel lblPuhelinnro1;

    //GUI textfields pnl1, number correlates with panel number
	private JTextField txtAsiakasID1;
	private JTextField txtEtunimi1;
    private JTextField txtSukunimi1;
	private JTextField txtLahiosoite1;
	private JTextField txtPostinro1;
    private JTextField txtPostitoimipaikka1;
	private JTextField txtEmail1;
    private JTextField txtPuhelinnro1;
    
    // GUI buttons pnl1, number correlates with panel number
    private JButton btnLisaa1;
    private JButton btnMuuta1;
	private JButton btnHae1;
	private JButton btnPoista1;

    //GUI labels pnl2, number correlates with panel number
    private JLabel lblToimipisteID2;
    private JLabel lblNimi2;
    private JLabel lblLahiosoite2;
    private JLabel lblPostitoimipaikka2;
    private JLabel lblPostinro2;
    private JLabel lblEmail2;
    private JLabel lblPuhelinnro2;

    //GUI textfields pnl2, number correlates with panel number
    private JTextField txtToimipisteID2;
    private JTextField txtNimi2;
    private JTextField txtLahiosoite2;
    private JTextField txtPostitoimipaikka2;
    private JTextField txtPostinro2;
    private JTextField txtEmail2;
    private JTextField txtPuhelinnro2;

    //GUI buttons pnl2, number correlates with panel number
    private JButton btnLisaa2;
    private JButton btnMuuta2;
    private JButton btnHae2;
    private JButton btnPoista2;

    //GUI labels pnl3, number correlates with panel number
    private JLabel lblPalveluID3;
    private JLabel lblToimipisteID3;
    private JLabel lblNimi3;
    private JLabel lblTyyppi3;
    private JLabel lblKuvaus3;
    private JLabel lblHinta3;
    private JLabel lblAlv3;

    //GUI textfields pnl3, number correlates with panel number
    private JTextField txtPalveluID3;
    private JTextField txtToimipisteID3;
    private JTextField txtNimi3;
    private JTextField txtTyyppi3;
    private JTextField txtKuvaus3;
    private JTextField txtHinta3;
    private JTextField txtAlv3;

    //GUI buttons pnl3, number correlates with panel number
    private JButton btnLisaa3;
    private JButton btnMuuta3;
    private JButton btnHae3;
    private JButton btnPoista3;

    //GUI labels pnl4, number correlates with panel number
    private JLabel lblVarausID4;
    private JLabel lblAsiakasID4;
    private JLabel lblToimipisteID4;
    private JLabel lblVarauspvm4;
    private JLabel lblVahvistuspvm4;
    private JLabel lblAlkupvm4;
    private JLabel lblLoppupvm4;

    //GUI textfields pnl4, number correlates with panel number
    private JTextField txtVarausID4;
    private JTextField txtAsiakasID4;
    private JTextField txtToimipisteID4;
    private JTextField txtVarauspvm4;
    private JTextField txtVahvistuspvm4;
    private JTextField txtAlkupvm4;
    private JTextField txtLoppupvm4;

    //GUI buttons pnl4, number correlates with panel number
    private JButton btnLisaa4;
    private JButton btnMuuta4;
    private JButton btnHae4;
    private JButton btnPoista4;

    //GUI labels pnl5, number correlates with panel number
    private JLabel lblLaskuID5;
    private JLabel lblVarausID5;
    private JLabel lblAsiakasID5;
    private JLabel lblNimi5;
    private JLabel lblLahiosoite5;
    private JLabel lblPostitoimipaikka5;
    private JLabel lblPostinro5;
    private JLabel lblSumma5;
    private JLabel lblAlv5;
    
    //GUI textfields pnl5, number correlates with panel number
    private JTextField txtLaskuID5;
    private JTextField txtVarausID5;
    private JTextField txtAsiakasID5;
    private JTextField txtNimi5;
    private JTextField txtLahiosoite5;
    private JTextField txtPostitoimipaikka5;
    private JTextField txtPostinro5;
    private JTextField txtSumma5;
    private JTextField txtAlv5;

    //GUI buttons pnl5, number correlates with panel number
    private JButton btnLisaa5;
    private JButton btnMuuta5;
    private JButton btnHae5;
    private JButton btnPoista5;              

    public GUI(){
        JFrame frame = new JFrame();

        //main panel with an exit button
        pnlMain = new JPanel();
        btnExit = new JButton("Lopeta sovellus");

        //secondary panels
        pnl1 = new JPanel();
        pnl2 = new JPanel();
        pnl3 = new JPanel();
        pnl4 = new JPanel();
        pnl5 = new JPanel();
        pnl6 = new JPanel();
        pnl7 = new JPanel();
        pnl8 = new JPanel();

        //labels for asiakas
        lblAsiakasID1 = new JLabel("Asiakas id");
		lblEtunimi1 = new JLabel("Etunimi");
		lblSukunimi1 = new JLabel("Sukunimi");
		lblLahiosoite1 = new JLabel("Lahiosoite");
		lblPostinro1 = new JLabel("Postinro");
		lblPostitoimipaikka1 = new JLabel("Postitoimipaikka");
		lblEmail1 = new JLabel("Email");
		lblPuhelinnro1 = new JLabel("Puhelin");
         
        //textfields for asiakas
		txtAsiakasID1 = new JTextField (6);
		txtEtunimi1 = new JTextField (12);
		txtSukunimi1 = new JTextField (26);
		txtLahiosoite1 = new JTextField (36);
		txtPostinro1 = new JTextField (5);
		txtPostitoimipaikka1 = new JTextField (26);
		txtEmail1 = new JTextField (36);
		txtPuhelinnro1 = new JTextField (26);

        //buttons for asiakas
        btnHae1 = new JButton("Hae");
		btnMuuta1 = new JButton("Muuta");
		btnLisaa1 = new JButton("Lisaa");
        btnPoista1 = new JButton("Poista");
        
        //labels for toimipiste
        lblToimipisteID2 = new JLabel("Toimipiste id");
        lblNimi2 = new JLabel("Toimipisteen nimi");
        lblLahiosoite2 = new JLabel("Lahiosoite");
        lblPostitoimipaikka2 = new JLabel("Postitoimipaikka");
        lblPostinro2 = new JLabel("Postinumero");
        lblEmail2 = new JLabel("Email");
        lblPuhelinnro2 = new JLabel("Puhelinnumero");

        //textfields for toimipiste
        txtToimipisteID2 = new JTextField(25);
        txtNimi2 = new JTextField(25);
        txtLahiosoite2 = new JTextField(25);
        txtPostitoimipaikka2 = new JTextField(25);
        txtPostinro2 = new JTextField(25);
        txtEmail2 = new JTextField(25);
        txtPuhelinnro2 = new JTextField(25);

        //buttons for toimipiste
        btnLisaa2 = new JButton("Lisaa");
        btnMuuta2 = new JButton("Muuta");
        btnHae2 = new JButton("Hae");
        btnPoista2 = new JButton("Poista");
        
        //labels for palvelu
        lblPalveluID3 = new JLabel("Palvelut id");
        lblToimipisteID3 = new JLabel("Toimipiste id");
        lblNimi3 = new JLabel("Palvelun nimi");
        lblTyyppi3 = new JLabel("Tyyppi");
        lblKuvaus3 = new JLabel("Kuvaus");
        lblHinta3 = new JLabel("Hinta");
        lblAlv3 = new JLabel("ALV");

        //textfields for palvelu
        txtPalveluID3 = new JTextField(25);
        txtToimipisteID3 = new JTextField(25);
        txtNimi3 = new JTextField(25);
        txtTyyppi3 = new JTextField(25);
        txtKuvaus3 = new JTextField(25);
        txtHinta3 = new JTextField(25);
        txtAlv3 = new JTextField(25);

        //buttons for palvelu
        btnLisaa3 = new JButton("Lisaa");
        btnMuuta3 = new JButton("Muuta");
        btnHae3 = new JButton("Hae");
        btnPoista3 = new JButton("Poista");

        //labels for varaus
        lblVarausID4 = new JLabel("Varaus id");
        lblAsiakasID4 = new JLabel("Asiakas id");
        lblToimipisteID4 = new JLabel("Toimipiste id");
        lblVarauspvm4 = new JLabel("Varauspaivamaara");
        lblVahvistuspvm4 = new JLabel("Vahvistuspaivamaara");
        lblAlkupvm4 = new JLabel("Varauksen alkupaivamaara");
        lblLoppupvm4 = new JLabel("Varauksen loppupaivamaara");

        //textfields for varaus
        txtVarausID4 = new JTextField(25);
        txtAsiakasID4 = new JTextField(25);
        txtToimipisteID4 = new JTextField(25);
        txtVarauspvm4 = new JTextField(25);
        txtVahvistuspvm4 = new JTextField(25);
        txtAlkupvm4 = new JTextField(25);
        txtLoppupvm4 = new JTextField(25);

        //buttons for varaus
        btnLisaa4 = new JButton("Lisaa");
        btnMuuta4 = new JButton("Muuta");
        btnHae4 = new JButton("Hae");
        btnPoista4 = new JButton("Poista");

        //labels for lasku
        lblLaskuID5 = new JLabel("Lasku id");
        lblVarausID5 = new JLabel("Varaus id");
        lblAsiakasID5 = new JLabel("Asiakas id");
        lblNimi5 = new JLabel("Nimi");
        lblLahiosoite5 = new JLabel("Lahiosoite");
        lblPostitoimipaikka5 = new JLabel("Postitoimipaikka");
        lblPostinro5 = new JLabel("Postinumero");
        lblSumma5 = new JLabel("Summa");
        lblAlv5 = new JLabel("Alv");

        //textfields for lasku
        txtLaskuID5 = new JTextField(25);
        txtVarausID5 = new JTextField(25);
        txtAsiakasID5 = new JTextField(25);
        txtNimi5 = new JTextField(25);
        txtLahiosoite5 = new JTextField(25);
        txtPostitoimipaikka5 = new JTextField(25);
        txtPostinro5 = new JTextField(25);
        txtSumma5 = new JTextField(25);
        txtAlv5 = new JTextField(25);

        //buttons for lasku
        btnLisaa5 = new JButton("Lisaa");
        btnMuuta5 = new JButton("Muuta");
        btnHae5 = new JButton("Hae");
        btnPoista5 = new JButton("Poista");


        frame.add(pnlMain);

        //setting main panel layout and adding secondary panels to it
        pnlMain.setLayout(new GridLayout(2, 4));
        pnlMain.add(pnl1);
        pnlMain.add(pnl2);
        pnlMain.add(pnl3);
        pnlMain.add(pnl4);
        pnlMain.add(pnl5);
        pnlMain.add(pnl6);
        pnlMain.add(pnl7);
        pnlMain.add(pnl8);

        pnl1.setLayout(new GridLayout(8, 2));
        pnl1.add(lblAsiakasID1);
        pnl1.add(txtAsiakasID1);
        pnl1.add(btnLisaa1);
        pnl1.add(lblEtunimi1);
        pnl1.add(txtEtunimi1);
        pnl1.add(btnMuuta1);
        pnl1.add(lblSukunimi1);
        pnl1.add(txtSukunimi1);
        pnl1.add(btnPoista1);
        pnl1.add(lblLahiosoite1);
        pnl1.add(txtLahiosoite1);
        pnl1.add(btnHae1);
        pnl1.add(lblPostinro1);
        pnl1.add(txtPostinro1);
        pnl1.add(Box.createRigidArea(new Dimension(100,10)));
        pnl1.add(lblPostitoimipaikka1);
        pnl1.add(txtPostitoimipaikka1);
        pnl1.add(Box.createRigidArea(new Dimension(100,10)));
        pnl1.add(lblEmail1);
        pnl1.add(txtEmail1);
        pnl1.add(Box.createRigidArea(new Dimension(100,10)));
        pnl1.add(lblPuhelinnro1);
        pnl1.add(txtPuhelinnro1);

        pnl2.setLayout(new GridLayout(8, 2));
        pnl2.add(lblToimipisteID2);
        pnl2.add(txtToimipisteID2);
        pnl2.add(btnLisaa2);
        pnl2.add(lblNimi2);
        pnl2.add(txtNimi2);
        pnl2.add(btnMuuta2);
        pnl2.add(lblLahiosoite2);
        pnl2.add(txtLahiosoite2);
        pnl2.add(btnPoista2);
        pnl2.add(lblPostinro2);
        pnl2.add(txtPostinro2);
        pnl2.add(btnHae2);
        pnl2.add(lblPostitoimipaikka2);
        pnl2.add(txtPostitoimipaikka2);
        pnl2.add(Box.createRigidArea(new Dimension(100,10)));
        pnl2.add(lblEmail2);
        pnl2.add(txtEmail2);
        pnl2.add(Box.createRigidArea(new Dimension(100,10)));
        pnl2.add(lblPuhelinnro2);
        pnl2.add(txtPuhelinnro2);

        pnl3.setLayout(new GridLayout(8, 2));
        pnl3.add(lblPalveluID3);
        pnl3.add(txtPalveluID3);
        pnl3.add(btnLisaa3);
        pnl3.add(lblToimipisteID3);
        pnl3.add(txtToimipisteID3);
        pnl3.add(btnMuuta3);
        pnl3.add(lblNimi3);
        pnl3.add(txtNimi3);
        pnl3.add(btnHae3);
        pnl3.add(lblTyyppi3);
        pnl3.add(txtTyyppi3);
        pnl3.add(btnPoista3);
        pnl3.add(lblKuvaus3);
        pnl3.add(txtKuvaus3);
        pnl3.add(Box.createRigidArea(new Dimension(100,10)));
        pnl3.add(lblHinta3);
        pnl3.add(txtHinta3);
        pnl3.add(Box.createRigidArea(new Dimension(100,10)));
        pnl3.add(lblAlv3);
        pnl3.add(txtAlv3);

        pnl4.setLayout(new GridLayout(8, 2));
        pnl4.add(lblVarausID4);
        pnl4.add(txtVarausID4);
        pnl4.add(btnLisaa4);
        pnl4.add(lblAsiakasID4);
        pnl4.add(txtAsiakasID4);
        pnl4.add(btnMuuta4);
        pnl4.add(lblToimipisteID4);
        pnl4.add(txtToimipisteID4);
        pnl4.add(btnHae4);
        pnl4.add(lblVarauspvm4);
        pnl4.add(txtVarauspvm4);
        pnl4.add(btnPoista4);
        pnl4.add(lblVahvistuspvm4);
        pnl4.add(txtVahvistuspvm4);
        pnl4.add(Box.createRigidArea(new Dimension(100,10)));
        pnl4.add(lblAlkupvm4);
        pnl4.add(txtAlkupvm4);
        pnl4.add(Box.createRigidArea(new Dimension(100,10)));
        pnl4.add(lblLoppupvm4);
        pnl4.add(txtLoppupvm4);

        pnl5.setLayout(new GridLayout(9, 2));
        pnl5.add(lblLaskuID5);
        pnl5.add(txtLaskuID5);
        pnl5.add(btnLisaa5);
        pnl5.add(lblVarausID5);
        pnl5.add(txtVarausID5);
        pnl5.add(btnMuuta5);
        pnl5.add(lblAsiakasID5);
        pnl5.add(txtAsiakasID5);
        pnl5.add(btnHae5);
        pnl5.add(lblNimi5);
        pnl5.add(txtNimi5);
        pnl5.add(btnPoista5);
        pnl5.add(lblLahiosoite5);
        pnl5.add(txtLahiosoite5);
        pnl5.add(Box.createRigidArea(new Dimension(100,10)));
        pnl5.add(lblPostitoimipaikka5);
        pnl5.add(txtPostitoimipaikka5);
        pnl5.add(Box.createRigidArea(new Dimension(100,10)));
        pnl5.add(lblPostinro5);
        pnl5.add(txtPostinro5);
        pnl5.add(Box.createRigidArea(new Dimension(100,10)));
        pnl5.add(lblSumma5);
        pnl5.add(txtSumma5);
        pnl5.add(Box.createRigidArea(new Dimension(100,10)));
        pnl5.add(lblAlv5);
        pnl5.add(txtAlv5);

        pnl7.add(btnExit);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setTitle("Varaus- ja asiakashallintajarjestelma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    
        // establishing a connection to the db, "driver:databasesystem://ip:port/database","user","password"
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/vp","root","!Tyyny12345!");
    
        }catch (Exception e) {
            System.out.println(e);
        }finally {
            System.out.println("Yhteys luotu!");
        }

        //action listeners
        btnLisaa1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_tiedot();
            }
        });

        btnMuuta1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnHae1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnPoista1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnLisaa2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_toimipiste();
            }
        });

        btnMuuta2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnHae2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnPoista2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnLisaa3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnMuuta3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnHae3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnPoista3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnLisaa4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnMuuta4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnHae4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnPoista4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnLisaa5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnMuuta5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnHae5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnPoista5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnExit.addActionListener(new ActionListener(){
    
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public  void lisaa_tiedot() {
		// lisätään tietokantaan asiakas
		//System.out.println("Lisataan...");
		boolean asiakas_lisatty = true;
		m_asiakas = null;
		try {
			m_asiakas = Asiakas.haeAsiakas (conn, Integer.parseInt(txtAsiakasID1.getText()));
		} catch (SQLException se) {
		// SQL virheet
			asiakas_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			asiakas_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_asiakas.getEtunimi() != null) {
		// asiakas jo olemassa, näytetään tiedot
			asiakas_lisatty = false;
			txtEtunimi1.setText(m_asiakas.getEtunimi());
			txtSukunimi1.setText(m_asiakas.getSukunimi());
			txtLahiosoite1.setText(m_asiakas.getLahiosoite());
			txtPostinro1.setText(m_asiakas.getPostinro());
			txtPostitoimipaikka1.setText(m_asiakas.getPostitoimipaikka());
			txtEmail1.setText(m_asiakas.getEmail());
			txtPuhelinnro1.setText(m_asiakas.getPuhelinnro());
			JOptionPane.showMessageDialog(null, "Asiakas on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_asiakas.setAsiakasId(Integer.parseInt(txtAsiakasID1.getText()));
			m_asiakas.setEtunimi(txtEtunimi1.getText());
			m_asiakas.setSukunimi(txtSukunimi1.getText());
			m_asiakas.setLahiosoite(txtLahiosoite1.getText());
			m_asiakas.setPostinro(txtPostinro1.getText());
			m_asiakas.setPostitoimipaikka(txtPostitoimipaikka1.getText());
			m_asiakas.setEmail(txtEmail1.getText());
			m_asiakas.setPuhelinnro(txtPuhelinnro1.getText());
			try {
				// yritetään kirjoittaa kantaan
				m_asiakas.lisaaAsiakas (conn);
			} catch (SQLException se) {
			// SQL virheet
				asiakas_lisatty = false;
				JOptionPane.showMessageDialog(null, "Asiakkaan lisaaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				asiakas_lisatty = false;
				JOptionPane.showMessageDialog(null, "Asiakkaan lisaaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (asiakas_lisatty == true)
					JOptionPane.showMessageDialog(null, "Asiakkaan tiedot lisatty tietokantaan.");
			}
		
		}
		
    }
    

   
   
   
   
   

    //toimipisteosio
   
   
    public  void lisaa_toimipiste() {
		// lisätään tietokantaan asiakas
		//System.out.println("Lisataan...");
		boolean toimipiste_lisatty = true;
		m_toimipiste = null;
		try {
			m_toimipiste = Toimipiste.haeToimipiste (conn, Integer.parseInt(txtToimipisteID2.getText()));
		} catch (SQLException se) {
		// SQL virheet
			toimipiste_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			toimipiste_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_toimipiste.getNimi() != null) {
		// asiakas jo olemassa, näytetään tiedot
			toimipiste_lisatty = false;
			txtNimi2.setText(m_toimipiste.getNimi());
			txtLahiosoite2.setText(m_toimipiste.getTLahiosoite());
			txtPostinro2.setText(m_toimipiste.getTPostinro());
			txtPostitoimipaikka2.setText(m_toimipiste.getTPostitoimipaikka());
			txtEmail2.setText(m_toimipiste.getTEmail());
			txtPuhelinnro2.setText(m_toimipiste.getTPuhelinnro());
			JOptionPane.showMessageDialog(null, "Toimipiste on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_toimipiste.setToimipisteId(Integer.parseInt(txtToimipisteID2.getText()));
			m_toimipiste.setNimi(txtNimi2.getText());
			m_toimipiste.setTLahiosoite(txtLahiosoite2.getText());
			m_toimipiste.setTPostinro(txtPostinro2.getText());
			m_toimipiste.setTPostitoimipaikka(txtPostitoimipaikka2.getText());
			m_toimipiste.setTEmail(txtEmail2.getText());
			m_toimipiste.setTPuhelinnro(txtPuhelinnro2.getText());
			try {
				// yritetään kirjoittaa kantaan
				m_toimipiste.lisaaToimipiste (conn);
			} catch (SQLException se) {
			// SQL virheet
				toimipiste_lisatty = false;
				JOptionPane.showMessageDialog(null, "Toimipisteen lisaaminen ei onnistu", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				toimipiste_lisatty = false;
				JOptionPane.showMessageDialog(null, "Toimipisteen lisaaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (toimipiste_lisatty == true)
					JOptionPane.showMessageDialog(null, "Toimipisteen tiedot lisatty tietokantaan.");
			}
		
		}
		
	}


    public static void main (String[] args){
        GUI instance = new GUI();
        System.out.println("Juoksee");

    }
}