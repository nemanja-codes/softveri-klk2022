/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Nastavnik;
import model.Zvanje;

/**
 *
 * @author necam
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;
    private Nastavnik selektovaniNastavnik;
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    private Controller() {
        dbb = new DBBroker();
    }

    public List<Nastavnik> vratiListuNastavnika() {
        return dbb.vratiListuNastavnika();
    }

    public List<Zvanje> vratiZvanja() {
        return dbb.vratiZvanja();
    }

    public List<Nastavnik> vratiListuNastavnikaPoZvanju(Zvanje selektovanoZvanje) {
        return dbb.vratiListuNastavnikaPoZvanju(selektovanoZvanje);
    }

    public Nastavnik getSelektovaniNastavnik() {
        return selektovaniNastavnik;
    }

    public void setSelektovaniNastavnik(Nastavnik selektovaniNastavnik) {
        this.selektovaniNastavnik = selektovaniNastavnik;
    }

    public boolean azurirajNastavnika(int id, Date datumOd, Date datumDo) throws SQLException {
        return dbb.azurirajNastavnika(id, datumOd, datumDo);
    }

    
    
    
    
}
