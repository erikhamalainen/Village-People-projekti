import java.sql.*;
import java.lang.*;

public class VarauksenPalvelut {


private int varaus_id;
private int palvelu_id;
private int lukumaara;



public int getVarausId() {
return varaus_id;
}
public int getPalveluId() {
    return palvelu_id;
}
public int getLukumaara() {
    return lukumaara;
}

public void setVarausId (int vid) {
    varaus_id = vid;
}
public void setPavleluId (int pid) {
    palvelu_id = pid;
}
public void setLukumaara (int lkm) {
    lukumaara = lkm;
}

public static Palvelu haeVarauksenPalvelu (Connection connection, int pid) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
    // haetaan tietokannasta palvelua, jonka palvelu_id = pid 
    String sql = "SELECT varaus_id, palvleu_id, lkm " 
                + " FROM varauksen_palvelut WHERE varaus_id = ? AND palvelu_id"; // ehdon arvo asetetaan jäljempänä
    ResultSet tulosjoukko = null;
    PreparedStatement lause = null;
    try {
        // luo PreparedStatement-olio sql-lauseelle
        lause = connection.prepareStatement(sql);
        lause.setInt( 1, vid); // asetetaan where ehtoon (?) arvo
        // suorita sql-lause
        tulosjoukko = lause.executeQuery();	
        if (tulosjoukko == null) {
            throw new Exception("Varauksen palvelua ei loydy");
        }
    } catch (SQLException se) {
        // SQL virheet
                    throw se;
            } catch (Exception e) {
        // JDBC virheet
                    throw e;
    }
    // käsitellään resultset - laitetaan tiedot palveluoliolle
    VarauksenPalvelut palveluOlio = new VarauksenPalvelu ();
    
    try {
        if (tulosjoukko.next () == true){
            palveluOlio.setVarausId (tulosjoukko.getInt("varaus_id"));
            palveluOlio.setPalvelu_id (tulosjoukko.getInt("palvelu_id"));
            palveluOlio.setLukumaara (tulosjoukko.getInt("lkm"));
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
public int lisaaVarauksenPalvelu (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
    // haetaan tietokannasta palvelua, jonka palvelu_id = olion id -> ei voi lisätä, jos on jo kannassa
    String sql = "SELECT varaus_id"
                + " FROM varauksen_palvelut WHERE varaus_id = ? AND palvelu_id"; // ehdon arvo asetetaan jäljempänä
    ResultSet tulosjoukko = null;
    PreparedStatement lause = null; 
    
    try {
        // luo PreparedStatement-olio sql-lauseelle
        lause = connection.prepareStatement(sql);
        lause.setInt( 1, getVarausId()); // asetetaan where ehtoon (?) arvo, olion varaus_id
        // suorita sql-lause
        tulosjoukko = lause.executeQuery();	
        if (tulosjoukko.next () == true) { // palvelu loytyi
            throw new Exception("Varauksen palvelu on jo olemassa");
        }
    } catch (SQLException se) {
        // SQL virheet
                throw se;
            } catch (Exception e) {
        // JDBC virheet
                throw e;
    }
    // parsitaan INSERT
    sql = "INSERT INTO varauksen_palvelut "
    + "(varaus_id, palvelu_id, lkm) "
    + " VALUES (?, ?, ?)";
    // System.out.println("Lisataan " + sql);
    lause = null;
    try {
        // luo PreparedStatement-olio sql-lauseelle
        lause = connection.prepareStatement(sql);
        // laitetaan arvot INSERTtiin
        lause.setInt(1, getVarausId());
        lause.setInt(2, getPalveluId());
        lause.setInt(3, getLukumaara());
        // suorita sql-lause
        int lkm = lause.executeUpdate();
        //System.out.println("lkm " + lkm);
        if (lkm == 0) {
            throw new Exception("Palvelun varauksen lisaaminen ei onnistu.");
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

public int muutaVarauksenPalvelu (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
    // haetaan tietokannasta palvleua, jonka palvelu_id = olion id, virhe, jos ei löydy
    String sql = "SELECT varaus_id"
                + " FROM varauksen_palvelut WHERE varaus_id = ? AND palvelu_id"; // ehdon arvo asetetaan jäljempänä
    ResultSet tulosjoukko = null;
    PreparedStatement lause = null;
    try {
        // luo PreparedStatement-olio sql-lauseelle
        lause = connection.prepareStatement(sql);
        lause.setInt( 1, getVarausId()); // asetetaan where ehtoon (?) arvo
        // suorita sql-lause
        tulosjoukko = lause.executeQuery();	
        if (tulosjoukko.next () == false) { // varauksen palvelua ei löytynyt
            throw new Exception("Varauksen palvelua ei loydy tietokannasta.");
        }
    } catch (SQLException se) {
        // SQL virheet
                throw se;
            } catch (Exception e) {
        // JDBC virheet
                throw e;
    }
    // parsitaan Update, päiviteään tiedot lukuunottamatta avainta
    sql = "UPDATE varauksen_palvelut "
    + "SET lkm = ? "
    + " WHERE varaus_id = ? AND palvelu_id = ?";

    lause = null;
    try {
        // luo PreparedStatement-olio sql-lauseelle
        lause = connection.prepareStatement(sql);

        // laitetaan olion attribuuttien arvot UPDATEen
        lause.setInt(1, getLukumaara());
        // where-ehdon arvo
        lause.setInt(2, getVarausId());
        lause.setInt(3, getPalvelu_id());
        // suorita sql-lause
        int lkm = lause.executeUpdate();	
        if (lkm == 0) {
            throw new Exception("Varauksen palvelun muuttaminen ei onnistu.");
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

public int poistaVarauksenPalvelu (Connection connection) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
    
    // parsitaan DELETE
    String sql = "DELETE FROM varauksen_palvelut WHERE varaus_id AND palvelu_id = ?";
    PreparedStatement lause = null;
    try {
        // luo PreparedStatement-olio sql-lauseelle
        lause = connection.prepareStatement(sql);
        // laitetaan arvot DELETEn WHERE-ehtoon
        lause.setInt( 1, getPalvelu_id());
        // suorita sql-lause
        int lkm = lause.executeUpdate();	
        if (lkm == 0) {
            throw new Exception("Varauksen palvelun poistaminen ei onnistu");
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