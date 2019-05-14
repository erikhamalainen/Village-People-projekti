import java.sql.*;
import java.lang.*;

public class Palvelu { // Luodaan palvelu-luokka

    private int palvelu_id;
    private int toimipiste_id;
    private String nimi;
    private int tyyppi;
    private String kuvaus;
    private double hinta;
    private double alv;

    public Palvelu(){ // Luodaan Palvelu-metodi

    }
    public int getPalvelu_id() {
        return palvelu_id;
    }
    public int getToimipiste_id() {
        return toimipiste_id;
    }
    public String getNimi() {
        return nimi;
    }
    public int getTyyppi() {
        return tyyppi;
    }
    public String getKuvaus() {
        return kuvaus;
    }
    public double getHinta() {
        return hinta;
    }
    public double getAlv() {
        return alv;
    }

    public void setPalvelu_id (int pid) {
        palvelu_id = pid;
    }
    public void setToimipiste_id (int tid) {
        toimipiste_id = tid;
    }
    public void setNimi (String n) {
        nimi = n;
    }
    public void setTyyppi (int t) {
        tyyppi = t;
    }
    public void setKuvaus (String kuv) {
        kuvaus = kuv;
    }
    public void setHinta (double h) {
        hinta = h;
    }
    public void setAlv (double a) {
        alv = a;
    }

    /*@Override
    public String toString(){
        return (palvelu_id + " " + nimi);
    }*/

    public static Palvelu haePalvelu (Connection connection, int pid) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta palvelua, jonka palvelu_id = pid 
		String sql = "SELECT palvelu_id, toimipiste_id, nimi, tyyppi, kuvaus, hinta, alv " 
					+ " FROM palvelu WHERE palvelu_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, pid); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko == null) {
				throw new Exception("Palvelua ei loydy");
			}
		} catch (SQLException se) {
            // SQL virheet
                        throw se;
                } catch (Exception e) {
            // JDBC virheet
                        throw e;
		}
		// käsitellään resultset - laitetaan tiedot asiakasoliolle
		Palvelu palveluOlio = new Palvelu ();
		
		try {
			if (tulosjoukko.next () == true){
                //palvelu_id, toimipiste_id, nimi, tyyppi, kuvaus, hinta, alv
                palveluOlio.setPalvelu_id (tulosjoukko.getInt("palvelu_id"));
				palveluOlio.setToimipiste_id (tulosjoukko.getInt("toimipiste_id"));
				palveluOlio.setNimi (tulosjoukko.getString("nimi"));
				palveluOlio.setTyyppi (tulosjoukko.getInt("tyyppi"));
				palveluOlio.setKuvaus (tulosjoukko.getString("kuvaus"));
				palveluOlio.setHinta (tulosjoukko.getDouble("hinta"));
				palveluOlio.setAlv (tulosjoukko.getDouble("alv"));
				
			}
			
		}catch (SQLException e) {
			throw e;
		}
		// palautetaan asiakasolio
		
		return palveluOlio;
	}


    /*
	Lisätään palvelun tiedot tietokantaan.
	*/
    public int lisaaPalvelu (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta palvelua, jonka palvelu_id = olion id -> ei voi lisätä, jos on jo kannassa
		String sql = "SELECT palvelu_id"
					+ " FROM palvelu WHERE palvelu_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null; 
		
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getPalvelu_id()); // asetetaan where ehtoon (?) arvo, olion asiakasid
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == true) { // asiakas loytyi
				throw new Exception("Palvelu on jo olemassa");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan INSERT
		sql = "INSERT INTO palvelu "
		+ "(palvelu_id, toimipiste_id, nimi, tyyppi, kuvaus, hinta, alv) "
		+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		// System.out.println("Lisataan " + sql);
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot INSERTtiin
			lause.setInt( 1, getPalvelu_id());
			lause.setInt(2, getToimipiste_id()); 
			lause.setString(3, getNimi());
			lause.setInt(4, getTyyppi());
			lause.setString(5, getKuvaus());
			lause.setDouble(6, getHinta());
			lause.setDouble(7, getAlv());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
		//	System.out.println("lkm " + lkm);
			if (lkm == 0) {
				throw new Exception("Palvelun lisaaminen ei onnistu.");
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

    public int muutaPalvelu (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta palvleua, jonka palvelu_id = olion id, virhe, jos ei löydy
		String sql = "SELECT palvelu_id" 
					+ " FROM palvelu WHERE palvelu_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getPalvelu_id()); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == false) { // asiakasta ei löytynyt
				throw new Exception("Palvelua ei loydy tietokannasta.");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan Update, päiviteään tiedot lukuunottamatta avainta
		sql = "UPDATE  Palvelu "
		+ "SET nimi = ?, tyyppi = ?, kuvaus = ?, hinta = ?, alv = ? "
		+ " WHERE palvelu_id = ?";
		
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			
			// laitetaan olion attribuuttien arvot UPDATEen
			lause.setString(1, getNimi());
			lause.setInt(2, getTyyppi());
			lause.setString(3, getKuvaus());
			lause.setDouble(4, getHinta());
			lause.setDouble(5, getAlv());
			// where-ehdon arvo
            lause.setInt( 6, getPalvelu_id());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Palvelun muuttaminen ei onnistu.");
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
    
    public int poistaPalvelu (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		
		// parsitaan DELETE
		String sql = "DELETE FROM palvelu WHERE palvelu_id = ?";
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot DELETEn WHERE-ehtoon
			lause.setInt( 1, getPalvelu_id());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Palvelun poistaminen ei onnistu");
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