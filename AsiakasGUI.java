import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class AsiakasGUI extends JFrame {
//
    private Asiakas m_asiakas = new Asiakas (); // asiakasolio, jota tässä pääsääntöisesti käsitellään
	private Connection m_conn; // tietokantayhteys
// käyttöliittymän otsikkokentät
	private JLabel lblAsiakasID;
	private JLabel lblEtunimi;
    private JLabel lblSukunimi;
	private JLabel lblLahiosoite;
	private JLabel lblPostinro;
    private JLabel lblPostitoimipaikka;
	private JLabel lblEmail;
    private JLabel lblPuhelinnro;
//käyttöliittymän tekstikentät
	private JTextField txtAsiakasID;
	private JTextField txtEtunimi;
    private JTextField txtSukunimi;
	private JTextField txtLahiosoite;
	private JTextField txtPostinro;
    private JTextField txtPostitoimipaikka;
	private JTextField txtEmail;
    private JTextField txtPuhelinnro;
// käyttöliittymän painikkeet	
    private JButton btnLisaa;
    private JButton btnMuuta;
	private JButton btnHae;
	private JButton btnPoista;
	private JButton btnPaluu;
	
    public AsiakasGUI() {

		lblAsiakasID = new JLabel("Asiakas id");
		lblEtunimi = new JLabel("Etunimi");
		lblSukunimi = new JLabel("Sukunimi");
		lblLahiosoite = new JLabel("Lahiosoite");
		lblPostinro = new JLabel("Postinro");
		lblPostitoimipaikka = new JLabel("Postitoimipaikka");
		lblEmail = new JLabel("Email");
		lblPuhelinnro = new JLabel("Puhelin");
		
		txtAsiakasID = new JTextField (6);
		txtEtunimi = new JTextField (12);
		txtSukunimi = new JTextField (26);
		txtLahiosoite = new JTextField (36);
		txtPostinro = new JTextField (5);
		txtPostitoimipaikka = new JTextField (26);
		txtEmail = new JTextField (36);
		txtPuhelinnro = new JTextField (26);

        btnHae = new JButton("Hae");
		btnMuuta = new JButton("Muuta");
		btnLisaa = new JButton("Lisaa");
		btnPoista = new JButton("Poista");
		btnPaluu = new JButton("Lopeta");

		// lisätään hakupainikkeelle tapahtumakuuntelija
		btnHae.addActionListener(   // toteutetaan  käyttämällä Javan ns. nimettömiä sisäluokkia
			new ActionListener () {// parametrina luotavan "rajapintaluokan ilmentymä": new ActionListener()
				public void actionPerformed(ActionEvent actEvent) {	
						hae_tiedot ();
					
				}
			}
		);
		// lisätään lisayspainikkeelle tapahtumakuuntelija
		btnLisaa.addActionListener(   // toteutetaan  käyttämällä Javan ns. nimettömiä sisäluokkia
			new ActionListener () {// parametrina luotavan "rajapintaluokan ilmentymä": new ActionListener()
				public void actionPerformed(ActionEvent actEvent) {	
						lisaa_tiedot ();
					
				}
			}
		);
		// lisätään muuta-painikkeelle tapahtumakuuntelija
		btnMuuta.addActionListener(   // toteutetaan  käyttämällä Javan ns. nimettömiä sisäluokkia
			new ActionListener () {// parametrina luotavan "rajapintaluokan ilmentymä": new ActionListener()
				public void actionPerformed(ActionEvent actEvent) {	
						muuta_tiedot ();
					
				}
			}
		);
		// lisätään muuta-painikkeelle tapahtumakuuntelija
		btnPoista.addActionListener(   // toteutetaan  käyttämällä Javan ns. nimettömiä sisäluokkia
			new ActionListener () {// parametrina luotavan "rajapintaluokan ilmentymä": new ActionListener()
				public void actionPerformed(ActionEvent actEvent) {	
						poista_tiedot ();
					
				}
			}
		);
		// lisätään lopetuspainikkeelle tapahtumakuuntelija
		btnPaluu.addActionListener(   
			new ActionListener () {
				public void actionPerformed(ActionEvent actEvent) {
					try {
						sulje_kanta ();
					} catch (SQLException se) {
					// SQL virheet
						JOptionPane.showMessageDialog(null, "Tapahtui tietokantavirhe tietokantaa suljettaessa.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
					} catch (Exception e) {
					// muut virheet
						JOptionPane.showMessageDialog(null, "Tapahtui virhe tietokantaa suljettaessa.", "Virhe", JOptionPane.ERROR_MESSAGE);
					} finally {
						System.exit(0);
					}
						
        }
      }
    );
		// näytön paneli, jossa vasen ja oikea puoli
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 2));
		// vasemman reunan paneli, jossa kenttien otsikot ja tietokentät
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(8, 2));
		
        p2.add(lblAsiakasID);
        p2.add(txtAsiakasID);

        p2.add(lblEtunimi);
		p2.add(txtEtunimi);
		
		p2.add(lblSukunimi);
		p2.add(txtSukunimi);
		
		p2.add(lblLahiosoite);
		p2.add(txtLahiosoite);
		
		p2.add(lblPostinro);
		p2.add(txtPostinro);
		
		p2.add(lblPostitoimipaikka);
		p2.add(txtPostitoimipaikka);
		
		p2.add(lblEmail);
		p2.add(txtEmail);
		
		p2.add(lblPuhelinnro);
		p2.add(txtPuhelinnro);
// oikean reunan paneli, jossa painikkeet
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(5, 1));
		p3.add(btnHae);
		p3.add(btnMuuta);
		p3.add(btnLisaa);
		p3.add(btnPoista); 
		p3.add(btnPaluu); 

        p1.add(p2);
        
        p1.add(p3);
		
        add(p1);
		
		setLocation(100, 100); // Ikkunan paikka 
		setSize(800, 400);     // Ikkunan koko leveys, korkeus
		setTitle("Asiakas");  // yläpalkkiin otsikko
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // osaa loppua
		setVisible(true); // lomake näkyviin
	
		// avataan tietokanta
		try {
			yhdista ();
		 } catch (SQLException se) {
            // SQL virheet
			JOptionPane.showMessageDialog(null, "Tapahtui virhe tietokantaa avattaessa.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // JDBC virheet
            JOptionPane.showMessageDialog(null, "Tapahtui JDBCvirhe tietokantaa avattaessa.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		
    }
	
	public static void main(String args[]) {
    AsiakasGUI frmAsiakas = new AsiakasGUI();  // luodaan lomakeluokan olio
	  
	
	}
	/*
	Avataan tietokantayhteys
	*/
	public  void yhdista() throws SQLException, Exception {
		m_conn = null;
		String url = "jdbc:mariadb://localhost:3306/vp"; // palvelin = localhost, :portti annettu asennettaessa, tietokannan nimi
		try {
			// ota yhteys kantaan, kayttaja = root, salasana = root
			m_conn=DriverManager.getConnection(url,"root","!Tyyny12345!");
		}
		catch (SQLException e) { // tietokantaan ei saada yhteyttä
			m_conn = null;
			throw e;
		}
		catch (Exception e ) { // JDBC ajuria ei löydy
			throw e;
		}
		
	}
	/*
	Suljetaan tietokantayhteys
	*/
	public  void sulje_kanta() throws SQLException, Exception {
		// suljetaan		
		try {
			// sulje yhteys kantaan
			m_conn.close ();
		}
		catch (SQLException e) { // tietokantavirhe
			throw e;
		}
		catch (Exception e ) { // muu virhe tapahtui
			throw e;
		}
		
	}
	/*
	Haetaan tietokannasta asiakkaan tiedot näytöllä olebvan asiakasid:n perusteella ja näytetään tiedot lomakkeella
	*/
	public  void hae_tiedot() {
		// haetaan tietokannasta asiakasta, jonka asiakas_id = txtAsiakasID 
		m_asiakas = null;
		
		try {
			m_asiakas = Asiakas.haeAsiakas (m_conn, Integer.parseInt(txtAsiakasID.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_asiakas.getEtunimi() == null) {
		// muut virheet
			txtEtunimi.setText("");
			txtSukunimi.setText("");
			txtLahiosoite.setText("");
			txtPostinro.setText("");
			txtPostitoimipaikka.setText("");
			txtEmail.setText("");
			txtPuhelinnro.setText("");
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// naytetaan tiedot
			txtEtunimi.setText(m_asiakas.getEtunimi());
			txtSukunimi.setText(m_asiakas.getSukunimi());
			txtLahiosoite.setText(m_asiakas.getLahiosoite());
			txtPostinro.setText(m_asiakas.getPostinro());
			txtPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
			txtEmail.setText(m_asiakas.getEmail());
			txtPuhelinnro.setText(m_asiakas.getPuhelinnro());
		}
		
	}
	/*
	Viedään näytöllä olevat tiedot asiakasoliolle ja kirjoitetaan ne tietokantaan
	*/
	public  void lisaa_tiedot() {
		// lisätään tietokantaan asiakas
		//System.out.println("Lisataan...");
		boolean asiakas_lisatty = true;
		m_asiakas = null;
		try {
			m_asiakas = Asiakas.haeAsiakas (m_conn, Integer.parseInt(txtAsiakasID.getText()));
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
			txtEtunimi.setText(m_asiakas.getEtunimi());
			txtSukunimi.setText(m_asiakas.getSukunimi());
			txtLahiosoite.setText(m_asiakas.getLahiosoite());
			txtPostinro.setText(m_asiakas.getPostinro());
			txtPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
			txtEmail.setText(m_asiakas.getEmail());
			txtPuhelinnro.setText(m_asiakas.getPuhelinnro());
			JOptionPane.showMessageDialog(null, "Asiakas on jo olemassa.", "Virhe", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			// asetetaan tiedot oliolle
			m_asiakas.setAsiakasId(Integer.parseInt(txtAsiakasID.getText()));
			m_asiakas.setEtunimi(txtEtunimi.getText());
			m_asiakas.setSukunimi(txtSukunimi.getText());
			m_asiakas.setLahiosoite(txtLahiosoite.getText());
			m_asiakas.setPostinro(txtPostinro.getText());
			m_asiakas.setPostitoimipaikka(txtPostitoimipaikka.getText());
			m_asiakas.setEmail(txtEmail.getText());
			m_asiakas.setPuhelinnro(txtPuhelinnro.getText());
			try {
				// yritetään kirjoittaa kantaan
				m_asiakas.lisaaAsiakas (m_conn);
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
	/*
	Viedään näytöllä olevat tiedot asiakasoliolle ja muutetaan ne tietokantaan
	*/
	public  void muuta_tiedot() {
		//System.out.println("Muutetaan...");
			boolean asiakas_muutettu = true;
		// asetetaan tiedot oliolle
			m_asiakas.setEtunimi(txtEtunimi.getText());
			m_asiakas.setSukunimi(txtSukunimi.getText());
			m_asiakas.setLahiosoite(txtLahiosoite.getText());
			m_asiakas.setPostinro(txtPostinro.getText());
			m_asiakas.setPostitoimipaikka(txtPostitoimipaikka.getText());
			m_asiakas.setEmail(txtEmail.getText());
			m_asiakas.setPuhelinnro(txtPuhelinnro.getText());
			
			try {
				// yritetään muuttaa (UPDATE) tiedot kantaan
				m_asiakas.muutaAsiakas (m_conn);
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
			m_asiakas = Asiakas.haeAsiakas (m_conn, Integer.parseInt(txtAsiakasID.getText()));
		} catch (SQLException se) {
		// SQL virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		// muut virheet
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Tietokantavirhe", JOptionPane.ERROR_MESSAGE);
		}
		if (m_asiakas.getEtunimi() == null) {
		// poistettavaa asiakasta ei löydy tietokannasta, tyhjennetään tiedot näytöltä
			txtEtunimi.setText("");
			txtSukunimi.setText("");
			txtLahiosoite.setText("");
			txtPostinro.setText("");
			txtPostitoimipaikka.setText("");
			txtEmail.setText("");
			txtPuhelinnro.setText("");
			JOptionPane.showMessageDialog(null, "Asiakasta ei loydy.", "Virhe", JOptionPane.ERROR_MESSAGE);
			return; // poistutaan
		}
		else
		{
			// naytetaan poistettavan asiakkaan tiedot
			txtEtunimi.setText(m_asiakas.getEtunimi());
			txtSukunimi.setText(m_asiakas.getSukunimi());
			txtLahiosoite.setText(m_asiakas.getLahiosoite());
			txtPostinro.setText(m_asiakas.getPostinro());
			txtPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
			txtEmail.setText(m_asiakas.getEmail());
			txtPuhelinnro.setText(m_asiakas.getPuhelinnro());
		}
		try {
			if (JOptionPane.showConfirmDialog(null, "Haluatko todella poistaa asiakkaan?")==0) {// vahvistus ikkunassa
				m_asiakas.poistaAsiakas (m_conn);
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
					txtAsiakasID.setText("");
					txtEtunimi.setText("");
					txtSukunimi.setText("");
					txtLahiosoite.setText("");
					txtPostinro.setText("");
					txtPostitoimipaikka.setText("");
					txtEmail.setText("");
					txtPuhelinnro.setText("");
					JOptionPane.showMessageDialog(null, "Asiakkaan tiedot poistettu tietokannasta.");
					m_asiakas = null;
				}
			}
			
		
	}

}
