/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import dao.PacienteDAO;
import entidades.Paciente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class BuscarPaciente2 extends javax.swing.JDialog {

    private String nombres;
    private String id;
    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form BuscarPaciente2
     */
    public BuscarPaciente2(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();

        listar();
    }

    private void listar() {

        PacienteDAO pDAO = new PacienteDAO();

        try {
            ArrayList<Paciente> pacientes = pDAO.listarPacientes();

            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            modelo.addColumn("ID");
            modelo.addColumn("NOMBRES");
            modelo.addColumn("APELLIDOS");
            modelo.addColumn("EDAD");

            for (Paciente paciente : pacientes) {
                String[] fila = new String[4];
                fila[0] = String.valueOf(paciente.getId());
                fila[1] = paciente.getNombres();
                fila[2] = paciente.getApellidos();
                fila[3] = String.valueOf(paciente.getEdad());

                modelo.addRow(fila);
            }

            tblPacientes.setModel(modelo);

            tblPacientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPacientes.getColumnModel().getColumn(0).setMinWidth(0);
            tblPacientes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tblPacientes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

            tblPacientes.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(modelo);
            tblPacientes.setRowSorter(sorter);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombres = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        btSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNombres.setText("Nombres / Apellidos");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
        });

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPacientes);

        btSeleccionar.setText("Seleccionar");
        btSeleccionar.setFocusable(false);
        btSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNombres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btSeleccionar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btSeleccionar)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombres)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_txtNombresKeyReleased

    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacientesMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            String nombre = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 1));
            String apellido = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 2));
            nombres = nombre + " " + apellido;
            id = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0));
            dispose();
        }

    }//GEN-LAST:event_tblPacientesMouseClicked

    private void btSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSeleccionarActionPerformed
        // TODO add your handling code here:
        
        if (tblPacientes.getSelectedRow() >= 0) {
            String nombre = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 1));
            String apellido = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 2));
            nombres = nombre + " " + apellido;
            id = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0));
            dispose();
        }
        
    }//GEN-LAST:event_btSeleccionarActionPerformed

    private void filtrar() {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombres.getText()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BuscarPaciente2 dialog = new BuscarPaciente2(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
    public String[] obtenerNombres() {

        setVisible(true);
        String[] valores = new String[2];
        valores[0] = nombres;
        valores[1] = id;
        return valores;
    }

    public int obtenerIdParaHC() {

        setVisible(true);
        int idPaciente = 0;
        if (id != null) {
            idPaciente = Integer.parseInt(id);
        }

        return idPaciente;
    }
}
