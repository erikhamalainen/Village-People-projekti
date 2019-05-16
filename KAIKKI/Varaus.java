
import java.sql.*;
import java.lang.*;

	
	// attribuutit, vastaavat tietokantataulun sarakkeita
    public class Varaus { //Varaus

        private int varaus_id; //varaus_id
        private int asiakas_id; // asiakas_id
        private int toimipiste_id; // toimipiste_id
        private String varattu_pvm;// varattu_pvm
        private String vahvistus_pvm;//vahvistus_pvm
        private String varattu_alkupvm;//varattu_alkupvm
        private String varattu_loppupvm;//varattu_loppupvm

        public Varaus(){ //Varaus
 
        }
        public int getVarausId() {
            return varaus_id;
        }
        public int getAsiakasId() {
            return asiakas_id;
        }
        public int getToimipisteId() {
            return toimipiste_id;
        }
        public String getVarattuPvm() {
            return varattu_pvm;
        }
        public String getVahvistusPvm() {
            return vahvistus_pvm;
        }
        public String getVarattuAlkuPvm() {
            return varattu_alkupvm;
        }
        public String getVarattuLoppuPvm() {
            return varattu_loppupvm;
		}
		
        public void setVarausId (int vid) {
            varaus_id = vid;
        }
        public void setAsiakasId(int aid) {
            asiakas_id = aid;
        }
        public void setToimipisteId(int tid) {
            toimipiste_id = tid;
        }
        public void setVarattuPvm (String  vpm) {
            varattu_pvm = vpm;
        }
        public void setVahvistusPvm (String  vapm) {
            vahvistus_pvm = vapm;
        }
        public void setVarattuAlkuPvm(String varapm) {
            varattu_alkupvm = varapm;
        }
        public void setVarattuLoppuPvm (String  varlpm) {
            varattu_loppupvm = varlpm;
        }
        
    
     @Override
    public String toString(){
        return (asiakas_id + " " + varaus_id + " " + toimipiste_id);
    }
	public static Varaus haeVaraus (Connection connection, int vid) throws Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = id 
		String sql = "SELECT varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm" 
					+ " FROM Varaus WHERE varaus_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, vid); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
            if (tulosjoukko == null) {
				throw new Exception("Varausta ei loydy");
			}
		} catch (SQLException se) {
            // SQL virheet
                        throw se;
                } catch (Exception e) {
            // JDBC virheet
                        throw e;
		}
		// käsitellään resultset - laitetaan tiedot varausoliolle
		Varaus varausOlio = new Varaus();

		try {
			if (tulosjoukko.next () == true){
				//varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm,
				varausOlio.setVarausId (tulosjoukko.getInt("varaus_id"));
				varausOlio.setAsiakasId (tulosjoukko.getInt("asiakas_id"));
				varausOlio.setToimipisteId(tulosjoukko.getInt("toimipiste_id"));
				varausOlio.setVarattuPvm (tulosjoukko.getString ("varattu_pvm"));
				varausOlio.setVahvistusPvm (tulosjoukko.getString ("vahvistus_pvm"));
				varausOlio.setVarattuAlkuPvm (tulosjoukko.getString ("varattu_alkupvm"));
				varausOlio.setVarattuLoppuPvm (tulosjoukko.getString ("varattu_loppupvm"));
				
			}
		}catch (SQLException e) {
			throw e;
		}
		//palautetaan varaussolio
		
		return varausOlio;
	}



	
	/*
	Lisätään asiakkaan tiedot tietokantaan.
	Metodissa "varaussolio kirjoittaa tietonsa tietokantaan".
	*/
     public int lisaaVaraus (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka varaus_id = olion id -> ei voi lisätä, jos on jo kannassa
		String sql = "SELECT varaus_id" 
					+ " FROM Varaus WHERE varaus_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null; 
		
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt(1, getVarausId()); // asetetaan where ehtoon (?) arvo, olion varausid
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == true) { // varausloytyi
				throw new Exception("Varaus on jo olemassa");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan INSERT
		sql = "INSERT INTO Varaus "
		+ "(varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm) "
		+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		// System.out.println("Lisataan " + sql);
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot INSERTtiin
			lause.setInt(1, getVarausId());
			lause.setInt(2, getAsiakasId()); 
			lause.setInt(3, getToimipisteId()); 
			lause.setString(4, getVarattuPvm());
			lause.setString(5, getVahvistusPvm());
			lause.setString(6, getVarattuAlkuPvm());
			lause.setString(7, getVarattuLoppuPvm());
			
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			//System.out.println("lkm " + lkm);
			if (lkm == 0) {
				throw new Exception("Varauksen lisaaminen ei onnistu");
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
	Muutetaan varauksen tiedot tietokantaan id-tietoa (avain) lukuunottamatta. 
	Metodissa "varaussolio muuttaa tietonsa tietokantaan".
	*/

	
    public int muutaVaraus (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta varausta, jonka varaus_id = olion id, virhe, jos ei löydy
		String sql = "SELECT varaus_id" 
					+ " FROM Varaus WHERE varaus_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, getVarausId()); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko.next () == false) { // varausta ei löytynyt
				throw new Exception("Varausta ei loydy tietokannasta");
			}
		} catch (SQLException se) {
            // SQL virheet
                    throw se;
                } catch (Exception e) {
            // JDBC virheet
                    throw e;
		}
		// parsitaan Update, päiviteään tiedot lukuunottamatta avainta
		sql = "UPDATE  Varaus "
		+ "SET varaus_id = ?, asiakas_id = ?, toimipiste_id = ?, varattu_pvm = ?, vahvistus_pvm= ?, varattu_alkupvm = ?, varattu_loppupvm = ? "
		+ " WHERE varaus_id = ?";
		
		lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			
			// laitetaan olion attribuuttien arvot UPDATEen
			
            lause.setInt( 1, getVarausId());
            lause.setInt(2, getAsiakasId()); 
            lause.setInt(3, getToimipisteId()); 
            lause.setString(4, getVarattuPvm());
            lause.setString(5, getVahvistusPvm ());
            lause.setString(6, getVarattuAlkuPvm());
            lause.setString(7, getVarattuLoppuPvm());
			// where-ehdon arvo
            lause.setInt( 8, getAsiakasId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Varauksen muuttaminen ei onnistu");
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
	Poistetaan varauksen tiedot tietokannasta. 
	Metodissa "varausolio poistaa tietonsa tietokannasta".
	*/
	public int poistaVaraus(Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
		
		// parsitaan DELETE
		String sql = "DELETE FROM  Varaus WHERE varaus_id = ?";
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot DELETEn WHERE-ehtoon
			lause.setInt( 1, getVarausId());
			// suorita sql-lause
			int lkm = lause.executeUpdate();	
			if (lkm == 0) {
				throw new Exception("Varuksen poistaminen ei onnistu");
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
