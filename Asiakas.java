/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MonkkonenE
 */
import java.sql.*;
import java.lang.*;
public class Asiakas {
	
	// attribuutit, vastaavat tietokantataulun sarakkeita
    private int m_asiakas_id;
	private String m_etunimi;
	private String m_sukunimi;
	private String m_lahiosoite;
	private String m_postitoimipaikka;
	private String m_postinro;
	private String m_email;
	private String m_puhelinnro;

    public Asiakas(){
 
    }
	// getterit ja setterit
	public int getAsiakasId()
	{
		return m_asiakas_id;
	}
	public String getEtunimi() {
		return m_etunimi;
	}
	public String getSukunimi() {
		return m_sukunimi;
	}
	public String getLahiosoite() {
		return m_lahiosoite;
	}
	public String getPostitoimipaikka() {
		return m_postitoimipaikka;
	}
	public String getPostinro() {
		return m_postinro;
	}
	public String getEmail() {
		return m_email;
	}
	public String getPuhelinnro() {
		return m_puhelinnro;
	}
	public void setAsiakasId (int id)
	{
		m_asiakas_id = id;
	}
	public void setEtunimi (String ni) {
		m_etunimi = ni;
	}
	public void setSukunimi (String ni) {
		m_sukunimi = ni;
	}
	public void setLahiosoite (String os) {
		m_lahiosoite = os;
	}
	public void setPostitoimipaikka (String ptp) {
		m_postitoimipaikka = ptp;
	}
	public void setPostinro (String pno) {
		m_postinro = pno;
	}
	public void setEmail (String mail) {
		m_email = mail;
	}
	public void setPuhelinnro (String puhveli) {
		m_puhelinnro = puhveli;
	}
    @Override
    public String toString(){
        return (m_asiakas_id + " " + m_etunimi + " " + m_sukunimi);
    }
	/*
	Haetaan asiakkaan tiedot ja palautetaan asiakasolio kutsujalle.
	Staattinen metodi, ei vaadi fyysisen olion olemassaoloa.
	*/
	public static Asiakas haeAsiakas (Connection connection, int id) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = id 
		String sql = "SELECT asiakas_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro " 
					+ " FROM Asiakas WHERE asiakas_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, id); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko == null) {
				throw new Exception("Asiakasta ei loydy");
			}
		} catch (SQLException se) {
            // SQL virheet
                        throw se;
                } catch (Exception e) {
            // JDBC virheet
                        throw e;
		}
		// käsitellään resultset - laitetaan tiedot asiakasoliolle
		Asiakas asiakasOlio = new Asiakas ();
		
		try {
			if (tulosjoukko.next () == true){
				//asiakas_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro
				asiakasOlio.setAsiakasId (tulosjoukko.getInt("asiakas_id"));
				asiakasOlio.setEtunimi (tulosjoukko.getString("etunimi"));
				asiakasOlio.setSukunimi(tulosjoukko.getString("sukunimi"));
				asiakasOlio.setLahiosoite (tulosjoukko.getString("lahiosoite"));
				asiakasOlio.setPostitoimipaikka (tulosjoukko.getString("postitoimipaikka"));
				asiakasOlio.setPostinro (tulosjoukko.getString("postinro"));
				asiakasOlio.setEmail (tulosjoukko.getString("email"));
				asiakasOlio.setPuhelinnro (tulosjoukko.getString("puhelinnro"));
			}
			
		}catch (SQLException e) {
			throw e;
		}
		// palautetaan asiakasolio
		
		return asiakasOlio;
	}
	/*
	Lisätään asiakkaan tiedot tietokantaan.
	Metodissa "asiakasolio kirjoittaa tietonsa tietokantaan".
	*/
     public int lisaaAsiakas (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = olion id -> ei voi lisätä, jos on jo kannassa
		String sql = "SELECT asiakas_id" 
					+ " FROM Asiakas WHERE asiakas_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null; 
		
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getAsiakasId()); // asetetaan where ehtoon (?) arvo, olion asiakasid
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == true) { // asiakas loytyi
				throw new Exception("Asiakas on jo olemassa");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan INSERT
		sql = "INSERT INTO Asiakas "
		+ "(asiakas_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro) "
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		// System.out.println("Lisataan " + sql);
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot INSERTtiin
			lause.setInt( 1, getAsiakasId());
			lause.setString(2, getEtunimi()); 
			lause.setString(3, getSukunimi()); 
			lause.setString(4, getLahiosoite());
			lause.setString(5, getPostitoimipaikka ());
			lause.setString(6, getPostinro ());
			lause.setString(7, getEmail ());
			lause.setString(8, getPuhelinnro ());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
		//	System.out.println("lkm " + lkm);
			if (lkm == 0) {
				throw new Exception("Asiakaan lisaaminen ei onnistu");
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
    public int muutaAsiakas (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = olion id, virhe, jos ei löydy
		String sql = "SELECT asiakas_id" 
					+ " FROM Asiakas WHERE asiakas_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getAsiakasId()); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == false) { // asiakasta ei löytynyt
				throw new Exception("Asiakasta ei loydy tietokannasta");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan Update, päiviteään tiedot lukuunottamatta avainta
		sql = "UPDATE  Asiakas "
		+ "SET etunimi = ?, sukunimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, email = ?, puhelinnro = ? "
		+ " WHERE asiakas_id = ?";
		
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			
			// laitetaan olion attribuuttien arvot UPDATEen
			
			lause.setString(1, getEtunimi()); 
			lause.setString(2, getSukunimi()); 
			lause.setString(3, getLahiosoite());
			lause.setString(4, getPostitoimipaikka ());
			lause.setString(5, getPostinro ());
			lause.setString(6, getEmail ());
			lause.setString(7, getPuhelinnro ());
			// where-ehdon arvo
            lause.setInt( 8, getAsiakasId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Asiakkaan muuttaminen ei onnistu");
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
	public int poistaAsiakas (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		
		// parsitaan DELETE
		String sql = "DELETE FROM  Asiakas WHERE asiakas_id = ?";
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot DELETEn WHERE-ehtoon
			lause.setInt( 1, getAsiakasId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Asiakaan poistaminen ei onnistu");
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
