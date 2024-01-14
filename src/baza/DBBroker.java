/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Nastavnik;
import model.Zvanje;
import java.sql.Date;
import java.sql.*;

/**
 *
 * @author necam
 */
public class DBBroker {

    public List<Nastavnik> vratiListuNastavnika() {
        List<Nastavnik> lista = new ArrayList<>();
        String upit = "SELECT * FROM nastavnik n JOIN zvanje z ON n.zvanje_id = z.id ORDER BY z.naziv ASC";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()) {
                int idZvanja = rs.getInt("z.id");
                String nazivZvanja = rs.getString("z.naziv");
                
                Zvanje z = new Zvanje(idZvanja, nazivZvanja);
                
                int id = rs.getInt("n.id");
                String ime = rs.getString("n.ime");
                String prezime = rs.getString("n.prezime");
                Date datumOd = rs.getDate("n.datumOd");
                Date datumDo = rs.getDate("n.datumDo");
                
                java.util.Date datumOdUtil = new java.util.Date(datumOd.getTime());
                java.util.Date datumDoUtil;
                // posto je datumDo opcioni parametar
                if(datumDo == null) {
                    datumDoUtil = null;
                } else {
                    datumDoUtil =new java.util.Date(datumDo.getTime());
                }
                
                Nastavnik n = new Nastavnik(id, ime, prezime, datumOdUtil,datumDoUtil, z);
                lista.add(n);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }

    public List<Zvanje> vratiZvanja() {
        List<Zvanje> lista = new ArrayList<>();
        String upit = "SELECT * FROM zvanje";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                
                Zvanje z = new Zvanje(id, naziv);
                lista.add(z);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Nastavnik> vratiListuNastavnikaPoZvanju(Zvanje selektovanoZvanje) {
        List<Nastavnik> lista = new ArrayList<>();
        String upit = "SELECT * FROM nastavnik n JOIN zvanje z ON n.zvanje_id = z.id WHERE z.naziv = ? ORDER BY n.datumOd ASC";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, selektovanoZvanje.getNaziv());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int idZvanja = rs.getInt("z.id");
                String nazivZvanja = rs.getString("z.naziv");
                
                Zvanje z = new Zvanje(idZvanja, nazivZvanja);
                
                int id = rs.getInt("n.id");
                String ime = rs.getString("n.ime");
                String prezime = rs.getString("n.prezime");
                Date datumOd = rs.getDate("n.datumOd");
                Date datumDo = rs.getDate("n.datumDo");
                
                java.util.Date datumOdUtil = new java.util.Date(datumOd.getTime());
                java.util.Date datumDoUtil;
                // posto je datumDo opcioni parametar
                if(datumDo == null) {
                    datumDoUtil = null;
                } else {
                    datumDoUtil =new java.util.Date(datumDo.getTime());
                }
                
                Nastavnik n = new Nastavnik(id, ime, prezime, datumOdUtil,datumDoUtil, z);
                lista.add(n);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean azurirajNastavnika(int id, java.util.Date datumOd, java.util.Date datumDo) throws SQLException {
        try {
            String upit = "UPDATE nastavnik SET datumOd = ?, datumDo = ? WHERE id = ?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            java.sql.Date datumOdSQL = new java.sql.Date(datumOd.getTime());
            java.sql.Date datumDoSQL = new java.sql.Date(datumDo.getTime());
            ps.setDate(1, datumOdSQL);
            ps.setDate(2, datumDoSQL);
            ps.setInt(3, id);
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            return true;
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
    }
    
}
