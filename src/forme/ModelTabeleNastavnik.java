/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Nastavnik;
import model.Zvanje;

/**
 *
 * @author necam
 */
public class ModelTabeleNastavnik extends AbstractTableModel {
     private List<Nastavnik> lista = new ArrayList<>();
    private String[] kolone = {"ime", "prezime","datumOd", "datumDo", "zvanje"};

    public ModelTabeleNastavnik() {
    }
    

    public ModelTabeleNastavnik(List<Nastavnik> lista) {
        this.lista = lista;
    }
    
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Nastavnik n = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
                case 0:
                    return n.getIme();
                case 1:
                    return n.getPrezime();
                case 2:
                    return sdf.format(n.getDatumOd());
                case 3:
                    if(n.getDatumDo() == null)
                        return "";
                    return sdf.format(n.getDatumDo());
                case 4:
                    if(n.getZvanje() == null) return null;
                    return n.getZvanje().getNaziv();
                default:
                    return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setLista(List<Nastavnik> lista) {
        this.lista = lista;
    }

    public List<Nastavnik> getLista() {
        return lista;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        Nastavnik n = lista.get(rowIndex);
        if(columnIndex >= 2) 
            return true;
        return false;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Nastavnik n = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 2:
            {
                try {
                    n.setDatumOd(sdf.parse((String) aValue));
                } catch (ParseException ex) {
                    Logger.getLogger(ModelTabeleNastavnik.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case 3:
            {
                try {
                    if(aValue == null  || ((String)aValue).isEmpty()) {
                        n.setDatumDo(null);
                    } else {
                        n.setDatumDo(sdf.parse((String) aValue));
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ModelTabeleNastavnik.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

                //ovo iznad sam koristio za prvu formu IZMENI, sva tri case-a koristim za drugu formu i opciju DODAJ RED
            case 4:
                n.setZvanje((Zvanje) aValue);
                break;
        }
    }
     
    
    
}
