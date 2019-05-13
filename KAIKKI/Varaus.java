import java.sql.*;
import java.lang.*;

public class Varaus { //Varaus

	private int varaus_id; //varaus_id
	private int asiakas_id; // asiakas_id
	private int toimipiste_id; // toimipiste_id
	private int varattu_pvm;// varattu_pvm
	private int vahvistus_pvm;//vahvistus_pvm
    private int varattu_alkupvm;//varattu_alkupvm
	private int varattu_loppupvm;//varattu_loppupvm
	

    public Varaus(){ //Varaus
 
    }
	public int getVarausId() //VarausId
	{
		return varaus_id;
	}
	public int getAsiakasId() {
		return asiakas_id;
	}
	public int getToimipisteId() {
		return toimipiste_id;
	}
	public int getVarattuPvm() {
		return varattu_pvm;
	}
	public int getVahvistusPvm() {
		return vahvistus_pvm;
	}
	public int getVarattuAlkuPvm() {
		return varattu_alkupvm;
	}
	public int getVarattuLoppuPvm() {
		return varattu_loppupvm;
	}
	public void setVarausId (int vid)
	{
		varaus_id = vid;
	}
	public void setAsiakasId(int aid) {
		asiakas_id = aid;
	}
	public void setToimipiste_id(int tid) {
		toimipiste_id = tid;
	}
	public void setVarattuPvm (int vpm) {
		varattu_pvm = vpm;
	}
	public void setVahvistusPvm (int vapm) {
		vahvistus_pvm = vapm;
	}
	public void setVarattuAlkuPvm(int varapm) {
		varattu_alkupvm = varapm;
	}
	public void VarattuLoppuPvm (int varlpm) {
		varattu_loppupvm = varlpm;
	}
	
    @Override
    public String toString(){
        return (m_asiakas_id + " " + m_etunimi + " " + m_sukunimi);
    }
	public static Varaus haeVaraus (Connection connection, int id) { // tietokantayhteys välitetään parametrina
		// haetaan tietokannasta asiakasta, jonka asiakas_id = id 
		String sql = "SELECT varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm, " 
					+ " FROM Varaus WHERE varaus_id = ?"; // ehdon arvo asetetaan jäljempänä
		ResultSet tulosjoukko = null;
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			lause.setInt( 1, 12001); // asetetaan where ehtoon (?) arvo
			// suorita sql-lause
			tulosjoukko = lause.executeQuery();	
			if (tulosjoukko == null) { // tulosjoukkoon odotetaan ainoastaan max. yhtä riviä
				System.out.println("Ei loydy asiakasta 12001...");
			}
		} catch (SQLException se) {
            // SQL virheet
            se.printStackTrace();
        } catch (Exception e) {
            // JDBC virheet
            e.printStackTrace();
		}
		// käsitellään resultset - laitetaan tiedot asiakasoliolle
		Varaus varausOlio = new Varaus ();
		
		try {
			if (tulosjoukko.next () == true){
				//asiakas_id, etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro
				varausOlio.setVarausId (tulosjoukko.getInt("varaus_id"));
				varausOlio.setAsiakasId (tulosjoukko.getInt("asiakas_id"));
				varausOlio.setToimipisteId(tulosjoukko.getInt("toimipiste_id"));
				varausOlio.setVarattuPvm (tulosjoukko.getInt("varattu_pvm"));
				varausOlio.setVahvistusPvm (tulosjoukko.getInt("vahvistus_pvm"));
				varausOlio.setVarattuAlkuPvm (tulosjoukko.getInt("vahvistus_alkupvm"));
				varausOlio.VarattuLoppuPvm (tulosjoukko.getInt("vahvistus_loppupvm"));
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		// palautetaan asiakasolio
		
		return varausOlio;
	}
	public static void main(String[] args) {
		System.out.println("Yhdistetaan VillagePeople...");
		Connection connection = null;
		int lkm = 0;
		try {
			// ota yhteys kantaan (paikallisella koneella portissa 3306, mikä kerrottu asennettaessa Mariadb
			// kannan nimi=vp, kayttaja = root, salasana = root
			connection=DriverManager.getConnection("jdbc:mariadb://localhost:3306/vp","root","root");
		}
		catch (SQLException e) { // tietokantaan ei saada yhteyttä
			connection = null;
			System.out.println("Ei yhteytta tietokantaan?????.");
		}
		catch (Exception e ) { // JDBC ajuria ei löydy
			System.out.println("JDBC?????.");
		}
		finally {
				System.out.println("Yhteys avattu...");
		}
		
		/* kommentoi tästä eteenpäin, niin voit helposti testata ainakin tietokannan avaamisen ja sulkemisen 
			kun muutat tietokannan nimen ym. relevanteiksi, jos sinulla joku MariaDB-kanta vain on*/
		String sql = "INSERT INTO Varaus "
		+ "(varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement lause = null;
		try {
			// luo PreparedStatement-olio sql-lauseelle
			lause = connection.prepareStatement(sql);
			// laitetaan arvot INSERTtiin
			lause.setInt( 1, 1000);
			lause.setInt(2, 1); 
			lause.setInt(3, 10); 
			lause.setInt(4, 10.1);
			lause.setInt(5, 11.1);
			lause.setInt(6, 20.1);
			lause.setInt(7, 27.1);
			
			// suorita sql-lause
			lkm = lause.executeUpdate();	
			if (lkm == 0) {
				System.out.println("Ei onnistu...");
			}
		} catch (SQLException se) {
            // SQL virheet
            se.printStackTrace();
        } catch (Exception e) {
            // JDBC virheet
            e.printStackTrace();
		}
	
		// Haetaan asiakkaasn tiedot
		
		Varaus uusiVaraus = haeVaraus (connection, 1000); // välitetään tietokantayhteys ja haettava asid
		System.out.println("Varaus " + uusiVaraus.toString ());
		/* tähän kommentit kiinni, jos testaat omalla, olemassa olevalla kannallasi */
		
		// suljetaan
		try {
				System.out.println("Suljetaan yhteys...");
				connection.close();
		} catch (SQLException se) {
				//
				se.printStackTrace();
		} catch (Exception e) {
				//
				e.printStackTrace();
		}
		
	}
}
