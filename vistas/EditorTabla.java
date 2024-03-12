/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Usuario
 */
public class EditorTabla extends AbstractCellEditor implements TableCellEditor {

    private StringOnlyDigitField stringOnlyDigitField = new StringOnlyDigitField();
    private TableCellEditor editor;

    public Object getCellEditorValue() {
        return stringOnlyDigitField.getText();
        //return "";
    }

    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        return stringOnlyDigitField;
        
    }

    
    
}