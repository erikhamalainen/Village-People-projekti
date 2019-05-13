import java.sql.*;
import java.lang.*;
public class Toimipiste {
	
	// attribuutit, vastaavat tietokantataulun sarakkeita
    private int m_toimipiste_id;
	private String m_nimi;
	private String m_lahiosoite;
	private String m_postitoimipaikka;
	private String m_postinro;
	private String m_email;
	private String m_puhelinnro;

    public Toimipiste(){
 
    }
	// getterit ja setterit
	public int getToimipisteId()
	{
		return m_toimipiste_id;
	}
	public String getNimi() {
		return m_nimi;
	}
	public String getTLahiosoite() {
		return m_lahiosoite;
	}
	public String getTPostitoimipaikka() {
		return m_postitoimipaikka;
	}
	public String getTPostinro() {
		return m_postinro;
	}
	public String getTEmail() {
		return m_email;
	}
	public String getTPuhelinnro() {
		return m_puhelinnro;
	}
	public void setToimipisteId (int id)
	{
		m_toimipiste_id = id;
	}
	public void setNimi (String ni) {
		m_nimi = ni;
	}
	public void setTLahiosoite (String os) {
		m_lahiosoite = os;
	}
	public void setTPostitoimipaikka (String ptp) {
		m_postitoimipaikka = ptp;
	}
	public void setTPostinro (String pno) {
		m_postinro = pno;
	}
	public void setTEmail (String mail) {
		m_email = mail;
	}
	public void setTPuhelinnro (String puhveli) {
		m_puhelinnro = puhveli;
	}
    @Override
    public String toString(){
        return (m_toimipiste_id + " " + m_nimi);
    }
	/*
	Haetaan asiakkaan tiedot ja palautetaan asiakasolio kutsujalle.
	Staattinen metodi, ei vaadi fyysisen olion olemassaoloa.
	*/
	public static Toimipiste haeToimipiste (Connection connection, int id) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = id 
		String sql = "SELECT toimipiste_id, nimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro " 
					+ " FROM Toimipiste WHERE toimipiste_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, id); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko == null) {
				throw new Exception("Toimipistetta ei loydy");
			}
		} catch (SQLException se) {
            // SQL virheet
                        throw se;
                } catch (Exception e) {
            // JDBC virheet
                        throw e;
		}
		// käsitellään resultset - laitetaan tiedot asiakasoliolle
		Toimipiste toimipisteOlio = new Toimipiste ();
		
		try {
			if (tulosjoukko.next () == true){
				//asiakas_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro
				toimipisteOlio.setToimipisteId (tulosjoukko.getInt("toimipiste_id"));
				toimipisteOlio.setNimi (tulosjoukko.getString("nimi"));
				toimipisteOlio.setTLahiosoite (tulosjoukko.getString("lahiosoite"));
				toimipisteOlio.setTPostitoimipaikka (tulosjoukko.getString("postitoimipaikka"));
				toimipisteOlio.setTPostinro (tulosjoukko.getString("postinro"));
				toimipisteOlio.setTEmail (tulosjoukko.getString("email"));
				toimipisteOlio.setTPuhelinnro (tulosjoukko.getString("puhelinnro"));
			}
			
		}catch (SQLException e) {
			throw e;
		}
		// palautetaan asiakasolio
		
		return toimipisteOlio;
	}
	/*
	Lisätään asiakkaan tiedot tietokantaan.
	Metodissa "asiakasolio kirjoittaa tietonsa tietokantaan".
	*/
     public int lisaaToimipiste (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = olion id -> ei voi lisätä, jos on jo kannassa
		String sql = "SELECT toimpiste_id" 
					+ " FROM Toimipiste WHERE toimipiste_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null; 
		
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getToimipisteId()); // asetetaan where ehtoon (?) arvo, olion asiakasid
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == true) { // asiakas loytyi
				throw new Exception("Toimipiste on jo olemassa");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan INSERT
		sql = "INSERT INTO toimipiste "
		+ "(toimipiste_id, nimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro) "
		+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		// System.out.println("Lisataan " + sql);
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot INSERTtiin
			lause.setInt( 1, getToimipisteId());
			lause.setString(2, getNimi()); 
			lause.setString(3, getTLahiosoite());
			lause.setString(4, getTPostitoimipaikka ());
			lause.setString(5, getTPostinro ());
			lause.setString(6, getTEmail ());
			lause.setString(7, getTPuhelinnro ());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
		//	System.out.println("lkm " + lkm);
			if (lkm == 0) {
				throw new Exception("AToimipisteen lisaaminen ei onnistu");
			}
		} catch (SQLException se) {
            // SQL virheet
            throw se;
        } catch (Exception e) {
            // JDBC ym. virheet
            throw e;
		}
		return 0;
	}
	/*
	Muutetaan asiakkaan tiedot tietokantaan id-tietoa (avain) lukuunottamatta. 
	Metodissa "asiakasolio muuttaa tietonsa tietokantaan".
	*/
    public int muutaToimipiste (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = olion id, virhe, jos ei löydy
		String sql = "SELECT toimipiste_id" 
					+ " FROM Toimipiste WHERE toimipiste_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getToimipisteId()); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == false) { // asiakasta ei löytynyt
				throw new Exception("Toimipistetta ei loydy tietokannasta");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan Update, päiviteään tiedot lukuunottamatta avainta
		sql = "UPDATE  Toimipiste "
		+ "SET nimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, email = ?, puhelinnro = ? "
		+ " WHERE toimipiste_id = ?";
		
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			
			// laitetaan olion attribuuttien arvot UPDATEen
			
			lause.setString(1, getNimi()); 
			lause.setString(2, getTLahiosoite());
			lause.setString(3, getTPostitoimipaikka ());
			lause.setString(4, getTPostinro ());
			lause.setString(5, getTEmail ());
			lause.setString(6, getTPuhelinnro ());
			// where-ehdon arvo
            lause.setInt( 7, getToimipisteId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Toimipisteen muuttaminen ei onnistu");
			}
		} catch (SQLException se) {
            // SQL virheet
                throw se;
        } catch (Exception e) {
            // JDBC ym. virheet
                throw e;
		}
		return 0; // toiminto ok
	}
	/*
	Poistetaan asiakkaan tiedot tietokannasta. 
	Metodissa "asiakasolio poistaa tietonsa tietokannasta".
	*/
	public int poistaToimipiste (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		
		// parsitaan DELETE
		String sql = "DELETE FROM  Toimipiste WHERE toimipiste_id = ?";
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot DELETEn WHERE-ehtoon
			lause.setInt( 1, getToimipisteId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Toimipisteen poistaminen ei onnistu");
			}
			} catch (SQLException se) {
            // SQL virheet
                throw se;
             } catch (Exception e) {
            // JDBC virheet
                throw e;
		}
		return 0; // toiminto ok
	}
}