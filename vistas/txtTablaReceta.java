/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Usuario
 */
public class txtTablaReceta extends DefaultTableCellRenderer{
    public txtTablaReceta() { super(); }

    @Override
    public void setValue(Object value) {
        
        boolean resultado;
        try {
            Integer.parseInt(String.valueOf(value));
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        
        setText((resultado == true) ? String.valueOf(value): "0");
    }
    
}
