import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GUI extends JFrame {
    private Connection conn;

    private Asiakas m_asiakas = new Asiakas();
    private Toimipiste m_toimipiste = new Toimipiste();
    private Palvelu m_palvelu = new Palvelu();
	private Varaus m_varaus = new Varaus();
	private Lasku m_lasku = new Lasku();
	private VarauksenPalvelut m_vpalvelu = new VarauksenPalvelut();
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
	
	//GUI varauksen_palvelut
	private JLabel lblVarausID6;
	private JLabel lblPalveluID6;
	private JLabel lblLkm6;

	private JTextField txtVarausID6;
	private JTextField txtPalveluID6;
	private JTextField txtLkm6;

	private JButton btnLisaa6;
	private JButton btnPoista6;
	private JButton btnMuuta6;
	private JButton btnHae6;

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
		
		//varauksen_palvelut
		lblVarausID6 = new JLabel("Varaus id");
		lblPalveluID6 = new JLabel("Palvelu id");
		lblLkm6 = new JLabel("Lukumaara");

		txtVarausID6 = new JTextField(25);
		txtPalveluID6 = new JTextField(25);
		txtLkm6 = new JTextField(25);

		btnLisaa6 = new JButton("Lisaa");
		btnPoista6 = new JButton("Poista");
		btnMuuta6 = new JButton("Muuta");
		btnHae6 = new JButton("Hae");

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
		pnl3.add(btnPoista3);
        pnl3.add(lblTyyppi3);
        pnl3.add(txtTyyppi3);
        pnl3.add(btnHae3);
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
        pnl4.add(btnPoista4);
        pnl4.add(lblVarauspvm4);
        pnl4.add(txtVarauspvm4);
		pnl4.add(btnHae4);
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
		pnl5.add(btnPoista5);
		pnl5.add(btnHae5);
        pnl5.add(lblNimi5);
		pnl5.add(txtNimi5);
		pnl5.add(btnHae5);
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
		
		pnl6.setLayout(new GridLayout(8, 3));
		pnl6.add(lblVarausID6);
		pnl6.add(txtVarausID6);
		pnl6.add(btnLisaa6);
		pnl6.add(lblPalveluID6);
		pnl6.add(txtPalveluID6);
		pnl6.add(btnPoista6);
		pnl6.add(lblLkm6);
		pnl6.add(txtLkm6);
		pnl6.add(btnMuuta6);
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(btnHae6);
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));
		pnl6.add(Box.createRigidArea(new Dimension(100,10)));

        pnl8.add(btnExit);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setTitle("Varaus- ja asiakashallintajarjestelma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    
        // establishing a connection to the db, "driver:databasesystem://ip:port/database","user","password"
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/vp","root","salasana123");
    
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
                muuta_tiedot();
            }
        });

        btnHae1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hae_tiedot();
            }
        });

        btnPoista1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                poista_tiedot();
            }
        });

        btnLisaa2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_toimipiste();;
            }
        });

        btnMuuta2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                muuta_toimipiste();
            }
        });

        btnHae2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hae_toimipiste();
            }
        });

        btnPoista2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                poista_toimipiste();
            }
        });

        btnLisaa3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_palvelu();
            }
        });

        btnMuuta3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                muuta_palvelu();
            }
        });

        btnHae3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hae_palvelu();
            }
        });

        btnPoista3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                poista_palvelu();
            }
        });

        btnLisaa4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_varaus();
            }
        });

        btnMuuta4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                muuta_varaus();
            }
        });

        btnHae4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hae_varaus();
            }
        });

        btnPoista4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                poista_varaus();
            }
        });

        btnLisaa5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_lasku();
            }
        });

        btnMuuta5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                muuta_lasku();
            }
        });

        btnHae5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hae_lasku();
            }
        });

        btnPoista5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                poista_lasku();
            }
		});

		btnHae6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hae_vpalvelu();
            }
        });
		
		btnLisaa6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lisaa_vpalvelu();
            }
		});

		btnMuuta6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				muuta_vpalvelu();
			}
		});
		
		btnPoista6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                poista_vpalvelu();
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
			JOptionPane.showMessageDialog(null, "Tietokantavirhe." + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			asiakas_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe." + e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
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

    public  void hae_tiedot() {
		// haetaan tietokannasta asiakasta, jonka asiakas_id = txtAsiakasID 
		m_asiakas = null;
		
		try {
			m_asiakas = Asiakas.haeAsiakas (conn, Integer.parseInt(txtAsiakasID1.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy." + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy." + e, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_asiakas.getEtunimi() == null) {
		// muut virheet
			txtEtunimi1.setText("");
			txtSukunimi1.setText("");
			txtLahiosoite1.setText("");
			txtPostinro1.setText("");
			txtPostitoimipaikka1.setText("");
			txtEmail1.setText("");
			txtPuhelinnro1.setText("");
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// naytetaan tiedot
			txtEtunimi1.setText(m_asiakas.getEtunimi());
			txtSukunimi1.setText(m_asiakas.getSukunimi());
			txtLahiosoite1.setText(m_asiakas.getLahiosoite());
			txtPostinro1.setText(m_asiakas.getPostinro());
			txtPostitoimipaikka1.setText(m_asiakas.getPostitoimipaikka());
			txtEmail1.setText(m_asiakas.getEmail());
			txtPuhelinnro1.setText(m_asiakas.getPuhelinnro());
		}
		
    }
    
    public  void muuta_tiedot() {
		//System.out.println("Muutetaan...");
			boolean asiakas_muutettu = true;
		// asetetaan tiedot oliolle
			m_asiakas.setEtunimi(txtEtunimi1.getText());
			m_asiakas.setSukunimi(txtSukunimi1.getText());
			m_asiakas.setLahiosoite(txtLahiosoite1.getText());
			m_asiakas.setPostinro(txtPostinro1.getText());
			m_asiakas.setPostitoimipaikka(txtPostitoimipaikka1.getText());
			m_asiakas.setEmail(txtEmail1.getText());
			m_asiakas.setPuhelinnro(txtPuhelinnro1.getText());
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_asiakas.muutaAsiakas (conn);
			} catch (SQLException se) {
			// SQL virheet
				asiakas_muutettu = false;
				JOptionPane.showMessageDialog(null, "Asiakkaan tietojen muuttaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				 //se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				asiakas_muutettu = false;
				JOptionPane.showMessageDialog(null, "Asiakkaan tietojen muuttaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (asiakas_muutettu == true)
					JOptionPane.showMessageDialog(null, "Asiakkaan tiedot muutettu.");
			}
		
    }
    
    public  void poista_tiedot() {
		// haetaan tietokannasta asiakasta, jonka asiakas_id = txtAsiakasID 
		m_asiakas = null;
		boolean asiakas_poistettu = false;
		
		try {
			m_asiakas = Asiakas.haeAsiakas (conn, Integer.parseInt(txtAsiakasID1.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_asiakas.getEtunimi() == null) {
		// poistettavaa asiakasta ei löydy tietokannasta, tyhjennetään tiedot näytöltä
			txtEtunimi1.setText("");
			txtSukunimi1.setText("");
			txtLahiosoite1.setText("");
			txtPostinro1.setText("");
			txtPostitoimipaikka1.setText("");
			txtEmail1.setText("");
			txtPuhelinnro1.setText("");
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan asiakkaan tiedot
			txtEtunimi1.setText(m_asiakas.getEtunimi());
			txtSukunimi1.setText(m_asiakas.getSukunimi());
			txtLahiosoite1.setText(m_asiakas.getLahiosoite());
			txtPostinro1.setText(m_asiakas.getPostinro());
			txtPostitoimipaikka1.setText(m_asiakas.getPostitoimipaikka());
			txtEmail1.setText(m_asiakas.getEmail());
			txtPuhelinnro1.setText(m_asiakas.getPuhelinnro());
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa asiakkaan?")==0) {// vahvistus ikkunassa
				m_asiakas.poistaAsiakas (conn);
				asiakas_poistettu = true;
			}
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Asiakkaan tietojen poistaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				// se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Asiakkaan tietojen poistaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (asiakas_poistettu == true) { // ainoastaan, jos vahvistettiin ja poisto onnistui
					txtAsiakasID1.setText("");
					txtEtunimi1.setText("");
					txtSukunimi1.setText("");
					txtLahiosoite1.setText("");
					txtPostinro1.setText("");
					txtPostitoimipaikka1.setText("");
					txtEmail1.setText("");
					txtPuhelinnro1.setText("");
					JOptionPane.showMessageDialog(null, "Asiakkaan tiedot poistettu tietokannasta.");
					m_asiakas = null;
				}
			}
			
		
	}
    

   
   
   
   
   

    //toimipisteosio
    public  void hae_toimipiste() {
		// haetaan tietokannasta pavlelua, jonka palvelu_id = txtPalveluID3
		m_toimipiste = null;
		
		try {
			m_toimipiste = Toimipiste.haeToimipiste(conn, Integer.parseInt(txtToimipisteID2.getText()));

		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Toimipistett ei loydy. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Toimipistetta ei loydy. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_toimipiste.getToimipisteId() == 0) {
        // muut virheet
            txtToimipisteID2.setText("");
            txtNimi2.setText("");
            txtLahiosoite2.setText(m_toimipiste.getTLahiosoite());
			txtPostinro2.setText(m_toimipiste.getTPostinro());
			txtPostitoimipaikka2.setText(m_toimipiste.getTPostitoimipaikka());
			txtEmail2.setText(m_toimipiste.getTEmail());
			txtPuhelinnro2.setText(m_toimipiste.getTPuhelinnro());
			JOptionPane.showMessageDialog(null, "Toimipistetta ei loydy. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
            // naytetaan tiedot
            
			txtToimipisteID2.setText(m_toimipiste.getToimipisteId() + "");
			txtNimi2.setText(m_toimipiste.getNimi());
			txtLahiosoite2.setText(m_toimipiste.getTLahiosoite());
			txtPostinro2.setText(m_toimipiste.getTPostinro());
			txtPostitoimipaikka2.setText(m_toimipiste.getTPostitoimipaikka());
			txtEmail2.setText(m_toimipiste.getTEmail());
			txtPuhelinnro2.setText(m_toimipiste.getTPuhelinnro());
		}
		
	}
   
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
				JOptionPane.showMessageDialog(null, "Toimipisteen lisaaminen ei onnistu" + se,  "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				toimipiste_lisatty = false;
				JOptionPane.showMessageDialog(null, "Toimipisteen lisaaminen ei onnistu." + e, "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (toimipiste_lisatty == true)
					JOptionPane.showMessageDialog(null, "Toimipisteen tiedot lisatty tietokantaan.");
			}
		
		}
		
    }

    public  void muuta_toimipiste() {
		//System.out.println("Muutetaan...");
			boolean toimipiste_muutettu = false;
		// asetetaan tiedot oliolle
		m_toimipiste.setNimi(txtNimi2.getText());
		m_toimipiste.setTLahiosoite(txtLahiosoite2.getText());
		m_toimipiste.setTPostinro(txtPostinro2.getText());
		m_toimipiste.setTPostitoimipaikka(txtPostitoimipaikka2.getText());
		m_toimipiste.setTEmail(txtEmail2.getText());
		m_toimipiste.setTPuhelinnro(txtPuhelinnro2.getText());
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_toimipiste.muutaToimipiste(conn);
				toimipiste_muutettu = true;
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Toimipisteen tietojen muuttaminen ei onnistu. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				 //se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Toimipisteen tietojen muuttaminen ei onnistu. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (toimipiste_muutettu == true)
					JOptionPane.showMessageDialog(null, "Toimipisteen tiedot muutettu.");
			}
		
    }
    
    public  void poista_toimipiste() {
		// haetaan tietokannasta palvelua, jonka palvelu_id = txtPalveluID3 
        m_toimipiste = null;
		boolean toimipiste_poistettu = false;
		
		try {
			m_toimipiste = Toimipiste.haeToimipiste(conn, Integer.parseInt(txtToimipisteID2.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Toimipistetta ei loydy. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Toimipistetta ei loydy. GUI " + e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_toimipiste.getToimipisteId() == 0) {
        // poistettavaa palvelua ei löydy tietokannasta, tyhjennetään tiedot näytöltä
            txtToimipisteID2.setText("");
            txtNimi2.setText("");
            txtLahiosoite2.setText("");
            txtPostinro2.setText("");
            txtPostitoimipaikka2.setText("");
            txtEmail2.setText("");
            txtPuhelinnro2.setText("");
			JOptionPane.showMessageDialog(null, "Toimipistetta ei loydy. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan opintosuorituksen tiedot
			m_toimipiste.setToimipisteId(Integer.parseInt(txtToimipisteID2.getText()));
            txtToimipisteID2.setText( "" + m_toimipiste.getToimipisteId());
            txtNimi2.setText(m_toimipiste.getNimi());
			txtLahiosoite2.setText(m_toimipiste.getTLahiosoite());
			txtPostinro2.setText(m_toimipiste.getTPostinro());
			txtPostitoimipaikka2.setText(m_toimipiste.getTPostitoimipaikka());
			txtEmail2.setText(m_toimipiste.getTEmail());
			txtPuhelinnro2.setText(m_toimipiste.getTPuhelinnro());
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa toimipisteen?")==0) {// vahvistus ikkunassa
				m_toimipiste.poistaToimipiste(conn);
				toimipiste_poistettu = true;
			}
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Toimipisteen tietojen poistaminen ei onnistu. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				// se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Toimipisteen tietojen poistaminen ei onnistu. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (toimipiste_poistettu == true) { // ainoastaan, jos vahvistettiin ja poisto onnistui
                    txtToimipisteID2.setText("");
                    txtNimi2.setText("");
                    txtLahiosoite2.setText("");
                    txtPostinro2.setText("");
                    txtPostitoimipaikka2.setText("");
                    txtEmail2.setText("");
                    txtPuhelinnro2.setText("");
					JOptionPane.showMessageDialog(null, "Toimipiste tiedot poistettu tietokannasta.");
				}
			}
			
		
	}





    	/*
	Haetaan tietokannasta palvelu palvelu_id:n perusteella
	*/
	public  void hae_palvelu() {
		// haetaan tietokannasta pavlelua, jonka palvelu_id = txtPalveluID3
		m_palvelu = null;
		
		try {
			m_palvelu = Palvelu.haePalvelu(conn, Integer.parseInt(txtPalveluID3.getText()));

		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Palvelua ei loydy. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Palvelua ei loydy. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_palvelu.getPalvelu_id() == 0) {
        // muut virheet
            txtPalveluID3.setText("");
			txtToimipisteID3.setText("");
			txtNimi3.setText("");
			txtTyyppi3.setText("");
			txtKuvaus3.setText("");
            txtHinta3.setText("");
            txtAlv3.setText("");
			JOptionPane.showMessageDialog(null, "Palvelua ei loydy. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
            // naytetaan tiedot
            
			txtPalveluID3.setText(m_palvelu.getPalvelu_id() + "");
			txtToimipisteID3.setText(m_palvelu.getToimipiste_id() + "");
			txtNimi3.setText(m_palvelu.getNimi());
			txtTyyppi3.setText(m_palvelu.getTyyppi() + "");
			txtKuvaus3.setText(m_palvelu.getKuvaus());
            txtHinta3.setText(m_palvelu.getHinta() + "");
            txtAlv3.setText(m_palvelu.getAlv() + "");
		}
		
	}



    public  void lisaa_palvelu() {
		// lisätään tietokantaan palvelu
		boolean palvelu_lisatty = true;
		m_palvelu = null;
		try {
			m_palvelu = Palvelu.haePalvelu (conn, Integer.parseInt(txtPalveluID3.getText()));
		} catch (SQLException se) {
		// SQL virheet
			palvelu_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe. " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
            palvelu_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe. "+ e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_palvelu.getPalvelu_id() != 0) {
		// palvelu jo olemassa, näytetään tiedot
			palvelu_lisatty = false;
			txtPalveluID3.setText(m_palvelu.getPalvelu_id() + "");
			txtToimipisteID3.setText(m_palvelu.getToimipiste_id() + "");
			txtNimi3.setText(m_palvelu.getNimi());
			txtTyyppi3.setText(m_palvelu.getTyyppi() + "");
			txtKuvaus3.setText(m_palvelu.getKuvaus());
            txtHinta3.setText(m_palvelu.getHinta() + "");
            txtAlv3.setText(m_palvelu.getAlv() + "");
			JOptionPane.showMessageDialog(null, "Toimipiste on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_palvelu.setPalvelu_id(Integer.parseInt(txtPalveluID3.getText()));
			m_palvelu.setToimipiste_id(Integer.parseInt(txtToimipisteID3.getText()));
			m_palvelu.setNimi(txtNimi3.getText());
			m_palvelu.setTyyppi(Integer.parseInt(txtTyyppi3.getText()));
			m_palvelu.setKuvaus(txtKuvaus3.getText());
			m_palvelu.setHinta(Double.parseDouble(txtHinta3.getText()));
			m_palvelu.setAlv(Double.parseDouble(txtAlv3.getText()));
			try {
				// yritetään kirjoittaa kantaan
				m_palvelu.lisaaPalvelu (conn);
			} catch (SQLException se) {
			// SQL virheet
				palvelu_lisatty = false;
				JOptionPane.showMessageDialog(null, "Palvelun lisaaminen ei onnistu " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
                palvelu_lisatty = false;
				JOptionPane.showMessageDialog(null, "Palvelun lisaaminen ei onnistu. " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (palvelu_lisatty == true)
					JOptionPane.showMessageDialog(null, "Palvelun tiedot lisatty tietokantaan.");
			}
		
		}
		
    }
    

    public  void muuta_palvelu() {
		//System.out.println("Muutetaan...");
			boolean palvelu_muutettu = false;
		// asetetaan tiedot oliolle
		m_palvelu.setNimi(txtNimi3.getText());
		m_palvelu.setTyyppi(Integer.parseInt(txtTyyppi3.getText()));
		m_palvelu.setHinta(Double.parseDouble(txtHinta3.getText()));
		m_palvelu.setAlv(Double.parseDouble(txtAlv3.getText()));
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_palvelu.muutaPalvelu(conn);
				palvelu_muutettu = true;
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Palvelun tietojen muuttaminen ei onnistu. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				 //se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Palvelun tietojen muuttaminen ei onnistu. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (palvelu_muutettu == true)
					JOptionPane.showMessageDialog(null, "Palvelun tiedot muutettu.");
			}
		
    }
    
    public  void poista_palvelu() {
		// haetaan tietokannasta palvelua, jonka palvelu_id = txtPalveluID3 
		m_palvelu = null;
		boolean palvelu_poistettu = false;
		
		try {
			m_palvelu = Palvelu.haePalvelu(conn, Integer.parseInt(txtPalveluID3.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Palvelua ei loydy. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Palvelua ei loydy. GUI " + e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_palvelu.getPalvelu_id() == 0) {
        // poistettavaa palvelua ei löydy tietokannasta, tyhjennetään tiedot näytöltä
            txtPalveluID3.setText("");
            txtToimipisteID3.setText("");
            txtNimi3.setText("");
            txtTyyppi3.setText("");
            txtKuvaus3.setText("");
            txtHinta3.setText("");
            txtAlv3.setText("");
			JOptionPane.showMessageDialog(null, "Palvelua ei loydy. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan opintosuorituksen tiedot
			m_palvelu.setPalvelu_id(Integer.parseInt(txtPalveluID3.getText()));
            txtToimipisteID3.setText( "" + m_palvelu.getToimipiste_id());
            txtNimi3.setText(m_palvelu.getNimi());
            txtTyyppi3.setText( "" + m_palvelu.getTyyppi());
            txtKuvaus3.setText(m_palvelu.getKuvaus());
            txtHinta3.setText( "" + m_palvelu.getHinta());
            txtAlv3.setText( "" + m_palvelu.getAlv());
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa palvelun?")==0) {// vahvistus ikkunassa
				m_palvelu.poistaPalvelu(conn);
				palvelu_poistettu = true;
			}
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Palvelun tietojen poistaminen ei onnistu. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				// se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Palvelun tietojen poistaminen ei onnistu. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (palvelu_poistettu == true) { // ainoastaan, jos vahvistettiin ja poisto onnistui
                    txtPalveluID3.setText("");
                    txtToimipisteID3.setText("");
                    txtNimi3.setText("");
                    txtTyyppi3.setText("");
                    txtKuvaus3.setText("");
                    txtHinta3.setText("");
                    txtAlv3.setText("");
					JOptionPane.showMessageDialog(null, "Palvelun tiedot poistettu tietokannasta.");
				}
			}
			
		
	}


    //varaus osio

    public  void lisaa_varaus() {
		// lisätään tietokantaan varaus
		//System.out.println("Lisataan...");
		boolean varaus_lisatty = true;
		m_varaus = null;
		try {
			m_varaus = Varaus.haeVaraus (conn, Integer.parseInt(txtVarausID4.getText()));
		} catch (SQLException se) {
		// SQL virheet
			varaus_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe." + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			varaus_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe." + e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_varaus.getVarattuPvm() != null) {
		// asiakas jo olemassa, näytetään tiedot
			varaus_lisatty = false;
			txtVahvistuspvm4.setText(m_varaus.getVahvistusPvm());
			txtAlkupvm4.setText(m_varaus.getVarattuAlkuPvm());
			txtLoppupvm4.setText(m_varaus.getVarattuLoppuPvm());
			JOptionPane.showMessageDialog(null, "Varaus on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_varaus.setVarausId(Integer.parseInt(txtVarausID4.getText()));
			m_varaus.setAsiakasId(Integer.parseInt(txtAsiakasID4.getText()));
			m_varaus.setToimipisteId(Integer.parseInt(txtToimipisteID4.getText()));
			m_varaus.setVarattuPvm(txtVarauspvm4.getText());
			m_varaus.setVahvistusPvm(txtVahvistuspvm4.getText());
			m_varaus.setVarattuAlkuPvm(txtAlkupvm4.getText());
			m_varaus.setVarattuLoppuPvm(txtLoppupvm4.getText());
			try {
				// yritetään kirjoittaa kantaan
				m_varaus.lisaaVaraus (conn);
			} catch (SQLException se) {
			// SQL virheet
				varaus_lisatty = false;
				JOptionPane.showMessageDialog(null, "Varauksen lisaaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				varaus_lisatty = false;
				JOptionPane.showMessageDialog(null, "Varauksen lisaaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (varaus_lisatty == true)
					JOptionPane.showMessageDialog(null, "Varauksen tiedot lisatty tietokantaan.");
			}
		
		}
		
    }

    public  void hae_varaus() {
		// haetaan tietokannasta varausta, jonka varaus_id = txtVarausID4
		m_varaus = null;
		
		try {
			m_varaus = Varaus.haeVaraus (conn, Integer.parseInt(txtVarausID4.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Varausta ei loydy." + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Varausta ei loydy." + e, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_varaus.getVarattuPvm() == null) {
		// muut virheet
            txtAsiakasID4.setText("");
            txtToimipisteID4.setText("");
            txtVahvistuspvm4.setText("");
			txtAlkupvm4.setText("");
			txtLoppupvm4.setText("");
			JOptionPane.showMessageDialog(null, "Varausta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// naytetaan tiedot
			txtAsiakasID4.setText(m_varaus.getAsiakasId() + "");
			txtToimipisteID4.setText(m_varaus.getToimipisteId() + "");
			txtVarauspvm4.setText(m_varaus.getVarattuPvm());
			txtVahvistuspvm4.setText(m_varaus.getVahvistusPvm());
			txtAlkupvm4.setText(m_varaus.getVarattuAlkuPvm());
			txtLoppupvm4.setText(m_varaus.getVarattuLoppuPvm());
		}
		
    }
    
    public  void muuta_varaus() {
		//System.out.println("Muutetaan...");
			boolean varaus_muutettu = true;
		// asetetaan tiedot oliolle
			m_varaus.setAsiakasId(Integer.parseInt(txtAsiakasID4.getText()));
			m_varaus.setToimipisteId(Integer.parseInt(txtToimipisteID4.getText()));
			m_varaus.setVarattuPvm(txtVarauspvm4.getText());
			m_varaus.setVahvistusPvm(txtVahvistuspvm4.getText());
			m_varaus.setVarattuAlkuPvm(txtAlkupvm4.getText());
			m_varaus.setVarattuLoppuPvm(txtLoppupvm4.getText());
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_varaus.muutaVaraus (conn);
			} catch (SQLException se) {
			// SQL virheet
				varaus_muutettu = false;
				JOptionPane.showMessageDialog(null, "Varauksen tietojen muuttaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				 //se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				varaus_muutettu = false;
				JOptionPane.showMessageDialog(null, "Varauksen tietojen muuttaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (varaus_muutettu == true)
					JOptionPane.showMessageDialog(null, "Varauksen tiedot muutettu.");
			}
		
    }
    
    public  void poista_varaus() {
		// haetaan tietokannasta asiakasta, jonka asiakas_id = txtAsiakasID 
		m_varaus = null;
        boolean varaus_poistettu = false;
		
		try {
			m_varaus = Varaus.haeVaraus (conn, Integer.parseInt(txtVarausID4.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Varausta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Varausta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_varaus.getVarattuPvm() == null) {
		// poistettavaa asiakasta ei löydy tietokannasta, tyhjennetään tiedot näytöltä
			txtAsiakasID4.setText("");
			txtToimipisteID4.setText("");
			txtVarauspvm4.setText("");
			txtVahvistuspvm4.setText("");
			txtAlkupvm4.setText("");
			txtLoppupvm4.setText("");
			JOptionPane.showMessageDialog(null, "Varausta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan asiakkaan tiedot
			txtAsiakasID4.setText(m_varaus.getAsiakasId() + "");
			txtToimipisteID4.setText(m_varaus.getToimipisteId() + "");
			txtVarauspvm4.setText(m_varaus.getVarattuPvm());
			txtVahvistuspvm4.setText(m_varaus.getVahvistusPvm());
			txtAlkupvm4.setText(m_varaus.getVarattuAlkuPvm());
			txtLoppupvm4.setText(m_varaus.getVarattuLoppuPvm());
			
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa asiakkaan?")==0) {// vahvistus ikkunassa
				m_varaus.poistaVaraus (conn);
				varaus_poistettu = true;
			}
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Varaus tietojen poistaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				// se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Varaus tietojen poistaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (varaus_poistettu == true) { // ainoastaan, jos vahvistettiin ja poisto onnistui
					txtAsiakasID4.setText("");
					txtToimipisteID4.setText("");
					txtVarauspvm4.setText("");
					txtVahvistuspvm4.setText("");
					txtAlkupvm4.setText("");
					txtLoppupvm4.setText("");
					JOptionPane.showMessageDialog(null, "Varauksen tiedot poistettu tietokannasta.");
					m_varaus = null;
				}
			}
			
		
	}

	public void lisaa_lasku() {
		// lisätään tietokantaan lasku
		//System.out.println("Lisataan...");
		boolean lasku_lisatty = true;
		m_lasku = null;
		try {
			m_lasku = Lasku.haeLasku (conn, Integer.parseInt(txtLaskuID5.getText()));
		} catch (SQLException se) {
		// SQL virheet
			lasku_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe." + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			lasku_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe." + e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_lasku.getVarausId() != 0) {
		// asiakas jo olemassa, näytetään tiedot
			lasku_lisatty = false;
			txtLaskuID5.setText(m_lasku.getLaskuId() + "");
			txtVarausID5.setText(m_lasku.getVarausId() + "");
			txtAsiakasID5.setText(m_lasku.getAsiakasId() + "");
			txtNimi5.setText(m_lasku.getNimi());
			txtLahiosoite5.setText(m_lasku.getLahiosoite());
			txtPostitoimipaikka5.setText(m_lasku.getPostitoimipaikka());
			txtPostinro5.setText(m_lasku.getPostinro());
			txtSumma5.setText(m_lasku.getSumma() + "");
			txtAlv5.setText(m_lasku.getAlv() + "");
			JOptionPane.showMessageDialog(null, "Varaus on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_lasku.setLaskuId(Integer.parseInt(txtLaskuID5.getText()));
			m_lasku.setVarausId(Integer.parseInt(txtVarausID5.getText()));
			m_lasku.setAsiakasId(Integer.parseInt(txtAsiakasID5.getText()));
			m_lasku.setNimi(txtNimi5.getText());
			m_lasku.setLahiosoite(txtLahiosoite5.getText());
			m_lasku.setPostitoimipaikka(txtPostitoimipaikka5.getText());
			m_lasku.setPostinro(txtPostinro5.getText());
			m_lasku.setSumma(Double.parseDouble(txtSumma5.getText()));
			m_lasku.setAlv(Double.parseDouble(txtAlv5.getText()));
			try {
				// yritetään kirjoittaa kantaan
				m_lasku.lisaaLasku (conn);
                lasku_lisatty = true;
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Laskun lisaaminen ei onnistu. GUI" + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Laskun lisaaminen ei onnistu. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (lasku_lisatty == true)
					JOptionPane.showMessageDialog(null, "Laskun tiedot lisatty tietokantaan.");
			}		
		}		
    }

    public void hae_lasku() {
		// haetaan tietokannasta laskua, jonka lasku_id = txtLaskuID5
		m_lasku = null;
		
		try {
			m_lasku = Lasku.haeLasku (conn, Integer.parseInt(txtLaskuID5.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Laskua ei loydy." + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Laskua ei loydy." + e, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_lasku.getLaskuId() == 0) {
		// muut virheet
		txtLaskuID5.setText("");
		txtVarausID5.setText("");
		txtAsiakasID5.setText("");
		txtNimi5.setText("");
		txtLahiosoite5.setText("");
		txtPostitoimipaikka5.setText("");
		txtPostinro5.setText("");
		txtSumma5.setText("");
		txtAlv5.setText("");
			JOptionPane.showMessageDialog(null, "Laskua ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// naytetaan tiedot
			txtLaskuID5.setText(m_lasku.getLaskuId() + "");
			txtVarausID5.setText(m_lasku.getVarausId() + "");
			txtAsiakasID5.setText(m_lasku.getAsiakasId() + "");
			txtNimi5.setText(m_lasku.getNimi());
			txtLahiosoite5.setText(m_lasku.getLahiosoite());
			txtPostitoimipaikka5.setText(m_lasku.getPostitoimipaikka());
			txtPostinro5.setText(m_lasku.getPostinro());
			txtSumma5.setText(m_lasku.getSumma() + "");
			txtAlv5.setText(m_lasku.getAlv() + "");
		}
		
    }
    
    public void muuta_lasku() {
		//System.out.println("Muutetaan...");
			boolean lasku_muutettu = true;
		// asetetaan tiedot oliolle
		m_lasku.setLaskuId(Integer.parseInt(txtLaskuID5.getText()));
		m_lasku.setVarausId(Integer.parseInt(txtVarausID5.getText()));
		m_lasku.setAsiakasId(Integer.parseInt(txtAsiakasID5.getText()));
		m_lasku.setNimi(txtNimi5.getText());
		m_lasku.setLahiosoite(txtLahiosoite5.getText());
		m_lasku.setPostitoimipaikka(txtPostitoimipaikka5.getText());
		m_lasku.setPostinro(txtPostinro5.getText());
		m_lasku.setSumma(Double.parseDouble(txtSumma5.getText()));
		m_lasku.setAlv(Double.parseDouble(txtAlv5.getText()));
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_lasku.muutaLasku (conn);
			} catch (SQLException se) {
			// SQL virheet
				lasku_muutettu = false;
				JOptionPane.showMessageDialog(null, "Laskun tietojen muuttaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				 //se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				lasku_muutettu = false;
				JOptionPane.showMessageDialog(null, "Laskun tietojen muuttaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (lasku_muutettu == true)
					JOptionPane.showMessageDialog(null, "Laskun tiedot muutettu.");
			}
		
	}
    
    public void poista_lasku() {
		// haetaan tietokannasta laskua, jonka lasku_id = txtLaskuID5 
		m_varaus = null; 
        boolean lasku_poistettu = false;
		
		try {
			m_lasku = Lasku.haeLasku (conn, Integer.parseInt(txtLaskuID5.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Laskua ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Laskua ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_lasku.getLaskuId() == 0) {
		// poistettavaa laskua ei löydy tietokannasta, tyhjennetään tiedot näytöltä
			txtLaskuID5.setText("");
			txtVarausID5.setText("");
			txtAsiakasID5.setText("");
			txtNimi5.setText("");
			txtLahiosoite5.setText("");
			txtPostitoimipaikka5.setText("");
			txtPostinro5.setText("");
			txtSumma5.setText("");
			txtAlv5.setText("");
			JOptionPane.showMessageDialog(null, "Laskua ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan asiakkaan tiedot
			txtLaskuID5.setText(m_lasku.getLaskuId() + "");
			txtVarausID5.setText(m_lasku.getVarausId() + "");
			txtAsiakasID5.setText(m_lasku.getAsiakasId() + "");
			txtNimi5.setText(m_lasku.getNimi());
			txtLahiosoite5.setText(m_lasku.getLahiosoite());
			txtPostitoimipaikka5.setText(m_lasku.getPostitoimipaikka());
			txtPostinro5.setText(m_lasku.getPostinro());
			txtSumma5.setText(m_lasku.getSumma() + "");
			txtAlv5.setText(m_lasku.getAlv() + "");
			
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa laskun?")==0) {// vahvistus ikkunassa
				m_lasku.poistaLasku (conn);
				lasku_poistettu = true;
			}
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Laskun tietojen poistaminen ei onnistu.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				// se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Laskun tietojen poistaminen ei onnistu.", "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (lasku_poistettu == true) { // ainoastaan, jos vahvistettiin ja poisto onnistui
					txtLaskuID5.setText("");
					txtVarausID5.setText("");
					txtAsiakasID5.setText("");
					txtNimi5.setText("");
					txtLahiosoite5.setText("");
					txtPostitoimipaikka5.setText("");
					txtPostinro5.setText("");
					txtSumma5.setText("");
					txtAlv5.setText("");
					JOptionPane.showMessageDialog(null, "Laskun tiedot poistettu tietokannasta.");
					m_lasku = null;
				}
			}
			
		
	}


	//varauksen palvelut
	
	public  void hae_vpalvelu() {
		// haetaan tietokannasta pavlelua, jonka palvelu_id = txtPalveluID3
		m_vpalvelu = null;
		
		try {
			m_vpalvelu = VarauksenPalvelut.haeVarauksenPalvelu(conn, Integer.parseInt(txtVarausID6.getText()), Integer.parseInt(txtPalveluID6.getText()));

		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Varauksen palvelua ei loydy. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Varauksen palvelua ei loydy. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_vpalvelu.getVarausId() == 0) {
		// muut virheet
			txtVarausID6.setText("");
            txtPalveluID6.setText("");
			txtLkm6.setText("");
			JOptionPane.showMessageDialog(null, "Varauksen palvelua ei loydy. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
            // naytetaan tiedot
            
			txtVarausID6.setText(m_vpalvelu.getVarausId() + "");
			txtPalveluID6.setText(m_vpalvelu.getPalveluId() + "");
            txtLkm6.setText(m_vpalvelu.getLukumaara() + "");
		}
		
	}


// pate is gay
	public  void lisaa_vpalvelu() {
		// lisätään tietokantaan palvelu
		boolean vpalvelu_lisatty = true;
		m_vpalvelu = null;
		try {
			m_vpalvelu = VarauksenPalvelut.haeVarauksenPalvelu (conn, Integer.parseInt(txtVarausID6.getText()), Integer.parseInt(txtPalveluID6.getText()));
		} catch (SQLException se) {
		// SQL virheet
			vpalvelu_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe. " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
            vpalvelu_lisatty = false;
			JOptionPane.showMessageDialog(null, "Tietokantavirhe. "+ e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_vpalvelu.getVarausId() != 0 && m_vpalvelu.getPalveluId() != 0) {
		// palvelu jo olemassa, näytetään tiedot
			vpalvelu_lisatty = false;
			txtVarausID6.setText(m_vpalvelu.getVarausId() + "");
			txtPalveluID6.setText(m_vpalvelu.getPalveluId() + "");
			txtLkm6.setText(m_vpalvelu.getLukumaara() + "");
			JOptionPane.showMessageDialog(null, "Varauksen palvelut on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_vpalvelu.setVarausId(Integer.parseInt(txtVarausID6.getText()));
			m_vpalvelu.setPalveluId(Integer.parseInt(txtPalveluID6.getText()));
			m_vpalvelu.setLukumaara(Integer.parseInt(txtLkm6.getText()));
			try {
				// yritetään kirjoittaa kantaan
				m_vpalvelu.lisaaVarauksenPalvelu (conn);
			} catch (SQLException se) {
			// SQL virheet
				vpalvelu_lisatty = false;
				JOptionPane.showMessageDialog(null, "Varauksen palvelun lisaaminen ei onnistu " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
			//	 se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
                vpalvelu_lisatty = false;
				JOptionPane.showMessageDialog(null, "Varauksen palvelun lisaaminen ei onnistu. " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
			//	 e.printStackTrace();
			}finally {
				if (vpalvelu_lisatty == true)
					JOptionPane.showMessageDialog(null, "Varauksen palvelun tiedot lisatty tietokantaan.");
			}
		
		}
		
	}
	
	public  void poista_vpalvelu() {
		// haetaan tietokannasta palvelua, jonka palvelu_id = txtPalveluID3 
		m_vpalvelu = null;
		boolean vpalvelu_poistettu = false;
		
		try {
			m_vpalvelu = VarauksenPalvelut.haeVarauksenPalvelu(conn, Integer.parseInt(txtVarausID6.getText()), Integer.parseInt(txtPalveluID6.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Varauksen palvelua ei loydy. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Varauksen palvelua ei loydy. GUI " + e, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_vpalvelu.getVarausId() == 0 && m_vpalvelu.getPalveluId() == 0) {
        // poistettavaa palvelua ei löydy tietokannasta, tyhjennetään tiedot näytöltä
			txtVarausID6.setText("");
			txtPalveluID6.setText("");
            txtLkm6.setText("");
			JOptionPane.showMessageDialog(null, "Varauksen palvelua ei loydy. GUI", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan opintosuorituksen tiedot
			m_vpalvelu.setVarausId(Integer.parseInt(txtVarausID6.getText()));
			m_vpalvelu.setPalveluId(Integer.parseInt(txtPalveluID6.getText()));
            m_vpalvelu.setLukumaara(Integer.parseInt(txtLkm6.getText()));
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa varauksen palvelun?")==0) {// vahvistus ikkunassa
				m_vpalvelu.poistaVarauksenPalvelu(conn);
				vpalvelu_poistettu = true;
			}
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Varauksen palvelun tietojen poistaminen ei onnistu. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				// se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Varauksen palvelun tietojen poistaminen ei onnistu. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (vpalvelu_poistettu == true) { // ainoastaan, jos vahvistettiin ja poisto onnistui
					txtVarausID6.setText("");
					txtPalveluID6.setText("");
                    txtLkm6.setText("");
					JOptionPane.showMessageDialog(null, "Varauksen palvelun tiedot poistettu tietokannasta.");
				}
			}
			
		
	}

	public  void muuta_vpalvelu() {
		//System.out.println("Muutetaan...");
			boolean vpalvelu_muutettu = false;
		// asetetaan tiedot oliolle
		m_vpalvelu.setLukumaara(Integer.parseInt(txtLkm6.getText()));
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_vpalvelu.muutaVarauksenPalvelu(conn);
				vpalvelu_muutettu = true;
			} catch (SQLException se) {
			// SQL virheet
				JOptionPane.showMessageDialog(null, "Varauksen palvelun tietojen muuttaminen ei onnistu. GUI " + se, "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
				 //se.printStackTrace();
			} catch (Exception e) {
			// muut virheet
				JOptionPane.showMessageDialog(null, "Varauksen palvelun tietojen muuttaminen ei onnistu. GUI " + e, "Virhe", JOptionPane.ERROR_MESSAGE);
				// e.printStackTrace();
			} finally {
				if (vpalvelu_muutettu == true)
					JOptionPane.showMessageDialog(null, "Varauksen palvelun tiedot muutettu.");
			}
		
    }



    public static void main (String[] args){
        GUI instance = new GUI();
        System.out.println("Juoksee");

    }
}