/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class EditorTablaReceta extends JTextField {
    
    public EditorTablaReceta(){
        
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
               setText("");
            }

            public void focusLost(FocusEvent e) {
                System.out.println("focus lost");
                
            }
        });
    }
}
