/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Usuario
 */
public class EditorNumero extends JTextField implements TableCellEditor {

    private ArrayList<CellEditorListener> suscriptores = new ArrayList<CellEditorListener>();

    /**
     * ***********************************CONSTRUCTOR POR
     * DEFECTO*****************************
     */
    public EditorNumero() {

//Nos apuntamos a la pérdida de foco, que quiere decir que se ha
// dejado de editar la celda.
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
               setText("");
            }

            public void focusLost(FocusEvent e) {
                System.out.println("focus lost");
                editado(true);
            }
        });
//Nos apuntamos a un escuchador de tecla, para detectar si lo
//digitado es correcto. En nuestro caso es correcto si es número.
        this.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent arg0) {
                char caracter = arg0.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    arg0.consume(); // ignorar el evento de teclado
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return Integer.parseInt(this.getText());
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        suscriptores.add(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        suscriptores.remove(l);
    }

    protected void editado(boolean cambiado) {
        ChangeEvent evento = new ChangeEvent(this);
        int i;
        for (i = 0; i < suscriptores.size(); i++) {
            CellEditorListener aux = (CellEditorListener) suscriptores.get(i);
            if ((cambiado == true) && (this.getText().isEmpty() == false)) {
                
                aux.editingStopped(evento);
            } else {
                aux.editingCanceled(evento);
            }
        }
    }
}
