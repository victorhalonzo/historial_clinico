/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import dao.PacienteDAO;
import entidades.Paciente;
import java.awt.Frame;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static vistas.NPDatosPersonales.jdFechaNac;

/**
 *
 * @author Usuario
 */
public class ListadoPacientes2 extends javax.swing.JDialog {

    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form ListadoPacientes2
     */
    public ListadoPacientes2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listar();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        btEditar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombres", "Apellidos", "Sexo", "Lugar de nacimientol", "Domicilio", "Estado civil"
            }
        ));
        jScrollPane1.setViewportView(tblPacientes);

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Listado de pacientes");

        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btEliminar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditar)
                    .addComponent(btEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        // TODO add your handling code here:

        if (tblPacientes.getSelectedRow() >= 0) {
            this.dispose();
            NuevoPaciente2 np = new NuevoPaciente2((Frame) this.getParent(), true);
            np.lblNPTitle.setText("Editar Paciente");
            np.txtIdPaciente.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0)));
            np.p1.txtNombres.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 1)));
            np.p1.txtApellidos.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 2)));
            String sexo = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 3));
            if (sexo.equals("M")) {
                np.p1.rbMasculino.setSelected(true);
            } else if (sexo.equals("F")) {
                np.p1.rbFemenino.setSelected(true);
            }
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha_nacimiento = null;
            Date menarquia = null;
            Date fecha_um = null;
            try {
                fecha_nacimiento = formato.parse(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 4)));
                if (!String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 16)).equals("null")) {
                    menarquia = formato.parse(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 16)));
                }
                if (!String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 17)).equals("null")) {
                    fecha_um = formato.parse(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 17)));
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un evento " + ex.getMessage());

            }
            np.p1.jdFechaNac.setDate(fecha_nacimiento);
            np.p1.txtEdad.setText(calcularEdad(fecha_nacimiento));
            np.p1.txtLugarNac.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 6)));
            np.p1.cbEstadoCivil.setSelectedItem(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 7)));
            np.p1.txtDomicilio.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 8)));
            np.p1.txtTelefono.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 9)));
            np.p2.txtAntecedentesPP.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 10)));
            np.p2.txtAntecedentesPF.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 11)));
            np.p3.txtAlergias.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 12)));
            np.p3.txtCirugias.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 13)));
            np.p3.txtAntecedentesPN.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 14)));
            np.p4.txtAntecedentesObs.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 15)));
            np.p4.jdMenarquia.setDate(menarquia);
            np.p4.jdFechaUM.setDate(fecha_um);
            String tabaco = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 18));
            if (tabaco.equals("SI")) {
                np.p5.rbFumaSi.setSelected(true);
            } else if (tabaco.equals("NO")) {
                np.p5.rbFumaNo.setSelected(true);
            }
            np.p5.txtTabacoObservacion.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 19)));
            String alcohol = String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 20));
            if (alcohol.equals("SI")) {
                np.p5.rbAlcoholSi.setSelected(true);
            } else if (alcohol.equals("NO")) {
                np.p5.rbAlcoholNo.setSelected(true);
            }
            np.p5.txtAlcoholObservacion.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 21)));
            np.p5.txtHijos.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 22)));
            np.p5.txtInspeccionGeneral.setText(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 23)));

            np.setLocationRelativeTo(this);
            np.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente para editar.");
        }

    }//GEN-LAST:event_btEditarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        // TODO add your handling code here:
        if (tblPacientes.getSelectedRow() >= 0) {
            int opcion=JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este paciente?");
            if(opcion==0){
                int idPaciente=Integer.parseInt(String.valueOf(tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0)));
                PacienteDAO pDAO= new PacienteDAO();
                try {
                    if(pDAO.eliminarPaciente(idPaciente)){
                        JOptionPane.showMessageDialog(this, "Paciente eliminado");
                        listar();
                    }else{
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un evento");
                    }
                } catch (SQLException ex) {
                       JOptionPane.showMessageDialog(this, "Ha ocurrido un evento "+ex.getMessage());
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente para eliminar.");
        }
    }//GEN-LAST:event_btEliminarActionPerformed

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
            modelo.addColumn("SEXO");
            modelo.addColumn("FECHA DE NACIMIENTO");
            modelo.addColumn("EDAD");
            modelo.addColumn("LUGAR DE NACIMIENTO");
            modelo.addColumn("ESTADO CIVIL");
            modelo.addColumn("DOMICILIO");
            modelo.addColumn("TELÉFONO");
            modelo.addColumn("ANTECEDENTES PP");
            modelo.addColumn("ANTECEDENTES PF");
            modelo.addColumn("ALERGIAS");
            modelo.addColumn("CIRUGIAS");
            modelo.addColumn("ANTECEDENTES PRENATALES");
            modelo.addColumn("ANTECEDENTES OBS");
            modelo.addColumn("MENARQUIA");
            modelo.addColumn("FECHA ULTIMA MENSTRUACION");
            modelo.addColumn("TABACO");
            modelo.addColumn("TABACO OBSERVACIÓN");
            modelo.addColumn("ALCOHOL");
            modelo.addColumn("ALCOHOL OBSERVACIÓN");
            modelo.addColumn("CANTIDAD HIJOS");
            modelo.addColumn("INSPECCION GENERAL");

            for (Paciente paciente : pacientes) {
                String[] fila = new String[24];
                fila[0] = String.valueOf(paciente.getId());
                fila[1] = paciente.getNombres();
                fila[2] = paciente.getApellidos();
                fila[3] = String.valueOf(paciente.getSexo());
                fila[4] = String.valueOf(paciente.getFechaNacimiento());
                fila[5] = String.valueOf(paciente.getEdad());
                fila[6] = String.valueOf(paciente.getLugarNacimiento());
                fila[7] = paciente.getEstadoCivil();
                fila[8] = paciente.getDomicilio();
                fila[9] = paciente.getTelefono();
                fila[10] = paciente.getAntecedentes_pp();
                fila[11] = paciente.getAntecedentes_pf();
                fila[12] = paciente.getAlergias();
                fila[13] = paciente.getCirugias();
                fila[14] = paciente.getAntecedentes_prenatales();
                fila[15] = paciente.getAntecedentes_obs();
                fila[16] = String.valueOf(paciente.getMenarquia());
                fila[17] = String.valueOf(paciente.getFecha_ultima_menstruacion());
                fila[18] = paciente.getTabaco();
                fila[19] = paciente.getTabaco_observacion();
                fila[20] = paciente.getAlcohol();
                fila[21] = paciente.getAlcohol_observacion();
                fila[22] = String.valueOf(paciente.getCantidad_hijos());
                fila[23] = paciente.getInspeccionGeneral();

                modelo.addRow(fila);
            }

            tblPacientes.setModel(modelo);

            ocultarColumnas(0);
            ocultarColumnas(10);
            ocultarColumnas(11);
            ocultarColumnas(12);
            ocultarColumnas(13);
            ocultarColumnas(14);
            ocultarColumnas(15);
            ocultarColumnas(16);
            ocultarColumnas(17);
            ocultarColumnas(18);
            ocultarColumnas(19);
            ocultarColumnas(20);
            ocultarColumnas(21);
            ocultarColumnas(22);
            ocultarColumnas(23);

            tblPacientes.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(modelo);
            tblPacientes.setRowSorter(sorter);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ocultarColumnas(int col) {
        tblPacientes.getColumnModel().getColumn(col).setMaxWidth(0);
        tblPacientes.getColumnModel().getColumn(col).setMinWidth(0);
        tblPacientes.getTableHeader().getColumnModel().getColumn(col).setMaxWidth(0);
        tblPacientes.getTableHeader().getColumnModel().getColumn(col).setMinWidth(0);
    }

    private String calcularEdad(Date fechaNacimiento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String f2 = format.format(fechaNacimiento);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(f2, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);

        return String.valueOf(periodo.getYears());
    }

    private void filtrar() {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtBusqueda.getText()));
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
            java.util.logging.Logger.getLogger(ListadoPacientes2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoPacientes2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoPacientes2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoPacientes2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoPacientes2 dialog = new ListadoPacientes2(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
