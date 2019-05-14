
import java.sql.*;
import java.lang.*;
public class Lasku {
	
	// attribuutit, vastaavat tietokantataulun sarakkeita
    private int lasku_id;
	private int varaus_id;
	private int asiakas_id;
	private String nimi;
	private String lahiosoite;
	private String postitoimipaikka;
	private String postinro;
    private double summa;
    private double alv;

    public Lasku(){
 
    }
	// getterit ja setterit
	public int getLaskuId()
	{
		return lasku_id;
	}
	public int getVarausId() {
		return varaus_id;
	}
	public int getAsiakasId() {
		return asiakas_id;
	}
	public String getNimi() {
		return nimi;
	}
	public String getLahiosoite() {
		return lahiosoite;
	}
	public String getPostitoimipaikka() {
		return postitoimipaikka;
	}
	public String getPostinro() {
		return postinro;
	}
	public double getSumma() {
		return summa;
	}
	public double getAlv() {
		return alv;
	}
	public void setLaskuId (int laid){
		lasku_id = laid;
	}
	public void setVarausId (int vaid) {
		varaus_id = vaid;
	}
	public void setAsiakasId (int asid) {
		asiakas_id= asid;
	}
	public void setNimi (String nm) {
		nimi = nm;
	}
	public void setLahiosoite (String lah) {
		lahiosoite = lah;
	}
	public void setPostitoimipaikka (String post) {
		postitoimipaikka = post;
	}
	public void setPostinro (String postnro) {
		postinro = postnro;
	}
	public void setSumma (double sum) {
		summa = sum;
	}
	public void setAlv(double al) {
		alv = al;
	}
    @Override
    public String toString(){
        return (m_asiakas_id + " " + m_etunimi + " " + m_sukunimi);
    }
	/*
	Haetaan asiakkaan tiedot ja palautetaan laskuolion kutsujalle.
	Staattinen metodi, ei vaadi fyysisen olion olemassaoloa.
	*/
	public static Lasku haeLasku (Connection connection, int id) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = id 
		String sql = "SELECT lasku_id, varaus_id, asiakas_id, nimi, lahiosoite, postitoimipaikka, postinro, summa, alv " 
					+ " FROM Lasku WHERE lasku_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, id); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko == null) {
				throw new Exception("Laskua ei loydy");
			}
		} catch (SQLException se) {
            // SQL virheet
                        throw se;
                } catch (Exception e) {
            // JDBC virheet
                        throw e;
		}
		// käsitellään resultset - laitetaan tiedot asiakasoliolle
		Lasku laskuOlio = new Lasku ();
		
		try {
			if (tulosjoukko.next () == true){
				//lasku_id, varaus_id, asiakas_id, nimi, lahiosoite, postitoimipaikka, postinro, summa, al
				laskuOlio.setLaskuId  (tulosjoukko.getInt("lasku_id"));
				laskuOlio.setVarausId (tulosjoukko.getInt("varaus_id"));
				laskuOlio.setAsiakasId (tulosjoukko.getInt("asiakas_id"));
				laskuOlio.setNimi (tulosjoukko.getString("nimi"));
				laskuOlio.setLahiosoite (tulosjoukko.getString("lahisoite"));
				laskuOlio.setPostitoimipaikka (tulosjoukko.getString("postitoimipaikka"));
				laskuOlio.setPostinro (tulosjoukko.getString("postinro"));
				laskuOlio.setSumma (tulosjoukko.getDouble("summa"));
                laskuOlio.setAlv (tulosjoukko.getDouble("alv"));
            }
			
		}catch (SQLException e) {
			throw e;
		}
		// palautetaan laskuolio
		
		return laskuOlio;
	}
	/*
	Lisätään laskun tiedot tietokantaan.
	Metodissa "laskuolio kirjoittaa tietonsa tietokantaan".
	*/
     public int lisaaLasku (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka lasku_id = olion id -> ei voi lisätä, jos on jo kannassa
		String sql = "SELECT lasku_id" 
					+ " FROM Lasku WHERE lasku_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null; 
		
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getLaskuId()); // asetetaan where ehtoon (?) arvo, olion asiakasid
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == true) { // asiakas loytyi
				throw new Exception("Lasku on jo olemassa");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan INSERT
		sql = "INSERT INTO Lasku "
		+ "(lasku_id, varaus_id, asiakas_id, nimi, lahiosoite, postitoimipaikka, postinro, summa, alv) "
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// System.out.println("Lisataan " + sql);
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot INSERTtiin
			lause.setInt( 1, getLaskuId());
			lause.setInt(2, getVarausId()); 
			lause.setInt(3, getAsiakasId()); 
			lause.setString(4, getNimi());
			lause.setString(5, getLahiosoite ());
			lause.setString(6, getPostitoimipaikka ());
			lause.setString(7, getPostinro());
            lause.setDouble(8, getSumma ());
            lause.setDouble(9, getAlv ());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
		//	System.out.println("lkm " + lkm);
			if (lkm == 0) {
				throw new Exception("Laskun lisaaminen ei onnistu");
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
	Muutetaan laskun tiedot tietokantaan id-tietoa (avain) lukuunottamatta. 
	Metodissa "laskuolio muuttaa tietonsa tietokantaan".
	*/
    public int muutaLasku (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = olion id, virhe, jos ei löydy
		String sql = "SELECT lasku_id" 
					+ " FROM Lasku WHERE lasku_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getLaskuId()); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == false) { // laskua ei löytynyt
				throw new Exception("Laskua ei loydy tietokannasta");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan Update, päiviteään tiedot lukuunottamatta avainta
		sql = "UPDATE  Lasku "
		+ "SET lasku_id = ?, varaus_id = ?, asiakas_id = ?, nimi = ?, lahiosoite = ?, postitoimipaikka = ?, postinro = ?, summa = ?, alv = ? "
		+ " WHERE lasku_id = ?";
		
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			
			// laitetaan olion attribuuttien arvot UPDATEen
			
            lause.setInt( 1, getLaskuId());
			lause.setInt(2, getVarausId()); 
			lause.setInt(3, getAsiakasId()); 
			lause.setString(4, getNimi());
			lause.setString(5, getLahiosoite ());
			lause.setString(6, getPostitoimipaikka ());
			lause.setString(7, getPostinro());
            lause.setDouble(8, getSumma ());
            lause.setDouble(9, getAlv ());
			// where-ehdon arvo
            lause.setInt( 8, getAsiakasId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Laskun muuttaminen ei onnistu");
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
	Poistetaan laskun tiedot tietokannasta. 
	Metodissa "laskusolio poistaa tietonsa tietokannasta".
	*/
	public int poistaLasku (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		
		// parsitaan DELETE
		String sql = "DELETE FROM  Lasku WHERE lasku_id = ?";
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot DELETEn WHERE-ehtoon
			lause.setInt( 1, getLaskuId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Laskun poistaminen ei onnistu");
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
