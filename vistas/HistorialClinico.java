/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import bd.Conexion;
import dao.HCDAO;
import entidades.Consulta;
import entidades.Imagen;
import entidades.Paciente;
import entidades.Receta;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class HistorialClinico extends javax.swing.JDialog {

    HCDatosPaciente p1 = new HCDatosPaciente();
    HCDatosPaciente2 p2 = new HCDatosPaciente2();
    HCDatosPaciente3 p3 = new HCDatosPaciente3();
    HCDatosConsultas p4 = new HCDatosConsultas();

    /**
     * Creates new form HistorialClinico
     */
    public HistorialClinico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelScroll.getVerticalScrollBar().setUnitIncrement(16);
        txtIdPaciente.setVisible(false);

        p1.setSize(1000, 860);
        p1.setLocation(0, 0);
        p2.setSize(1000, 860);
        p2.setLocation(0, 0);
        p3.setSize(1000, 860);
        p3.setLocation(0, 0);
        p4.setSize(1000, 860);
        p4.setLocation(0, 0);
        panelElementos.add(p1, BorderLayout.CENTER);
        p1.setVisible(true);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
    }

    public void datosPersonales(int id) {
        HCDAO hc = new HCDAO();

        try {
            Paciente dp = hc.datosPaciente(id);

            txtIdPaciente.setText(String.valueOf(id));
            p1.txtNombres.setText(dp.getNombres());
            p1.txtApellidos.setText(dp.getApellidos());
            String sexoPaciente = "";
            if (String.valueOf(dp.getSexo()).equals("M")) {
                sexoPaciente = "Masculino";
            } else if (String.valueOf(dp.getSexo()).equals("F")) {
                sexoPaciente = "Femenino";
            }
            p1.txtSexo.setText(sexoPaciente);
            p1.txtEdad.setText(String.valueOf(dp.getEdad()));
            p1.txtDomicilio.setText(dp.getDomicilio());
            p1.txtTelefono.setText(dp.getTelefono());
            p1.txtEstadoCivil.setText(dp.getEstadoCivil());
            p1.txtAntecedentes_pp.setText(dp.getAntecedentes_pp());
            p1.txtAntecedentes_pf.setText(dp.getAntecedentes_pf());
            p1.txtAntecedentes_pn.setText(dp.getAntecedentes_prenatales());
            p2.txtAlergias.setText(dp.getAlergias());
            p2.txtCirugias.setText(dp.getCirugias());
            p2.txtTabaco.setText(dp.getTabaco());
            p2.txtTabacoObservacion.setText(dp.getTabaco_observacion());
            p2.txtAlcohol.setText(dp.getAlcohol());
            p2.txtAlcoholObservacion.setText(dp.getAlcohol_observacion());
            p2.txtCantidadHijos.setText(String.valueOf(dp.getCantidad_hijos()));
            p2.txtInspeccionGeneral.setText(dp.getInspeccionGeneral());
            p3.txtAntecedentes_obs.setText(dp.getAntecedentes_obs());
            if (dp.getMenarquia() != null) {
                p3.txtMenarquia.setText(String.valueOf(dp.getMenarquia()));
            }
            if (dp.getFecha_ultima_menstruacion() != null) {
                p3.txtFechaUM.setText(String.valueOf(dp.getFecha_ultima_menstruacion()));
            }

            this.setTitle(dp.getNombres() + " " + dp.getApellidos());

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void datosUltimaConsulta(int id) {
        HCDAO hc = new HCDAO();

        try {
            Consulta uc = hc.datosUltimaConsulta(id);
            int idConsulta = uc.getId();
            
            p4.txtIdConsulta.setText(String.valueOf(idConsulta));
            p4.txtFecha.setText(String.valueOf(uc.getFecha()));
            int talla = (int) uc.getTalla();
            p4.txtTalla.setText(String.valueOf(talla));
            p4.txtPeso.setText(String.valueOf(uc.getPeso()));
            p4.txtIMC.setText(String.valueOf(uc.getImc()));
            p4.txtTemperatura.setText(String.valueOf(uc.getTemperatura()));
            p4.txtPulso.setText(String.valueOf(uc.getPulso()));
            p4.txtPresionA.setText(uc.getPresionArterial());
            p4.txtMotivo.setText(uc.getDescripcion());
            p4.txtCuadroClinico.setText(uc.getCuadroClinico());
            p4.txtExamenesComp.setText(uc.getExamenesComplementarios());
            p4.txtDiagnostico.setText(uc.getDiagnostico());
            p4.txtTratamiento.setText(uc.getTratamiento());
            //p4.txtReceta.setText(uc.getReceta());
            datosReceta(idConsulta);
            mostrarImagenes(idConsulta);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void datosTodasConsultas(int id) {
        HCDAO hc = new HCDAO();

        try {
            ArrayList<Consulta> consultas = hc.datosTodasConsultas(id);

            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            modelo.addColumn("Fecha");
            modelo.addColumn("Talla");
            modelo.addColumn("Peso");
            modelo.addColumn("IMC");
            modelo.addColumn("Temperatura");
            modelo.addColumn("Pulso");
            modelo.addColumn("Presión arterial");
            modelo.addColumn("Motivo");
            modelo.addColumn("Cuadro clínico");
            modelo.addColumn("Exámenes complementarios");
            modelo.addColumn("Diagnostico");
            modelo.addColumn("Tratamiento");
            modelo.addColumn("Id Consulta");

            for (Consulta c : consultas) {
                String[] fila = new String[13];
                fila[0] = String.valueOf(c.getFecha());
                int talla = (int) c.getTalla();
                fila[1] = String.valueOf(talla);
                fila[2] = String.valueOf(c.getPeso());
                fila[3] = String.valueOf(c.getImc());
                fila[4] = String.valueOf(c.getTemperatura());
                fila[5] = String.valueOf(c.getPulso());
                fila[6] = c.getPresionArterial();
                fila[7] = c.getDescripcion();
                fila[8] = c.getCuadroClinico();
                fila[9] = c.getExamenesComplementarios();
                fila[10] = c.getDiagnostico();
                fila[11] = c.getTratamiento();
                fila[12] = String.valueOf(c.getId());

                modelo.addRow(fila);
            }

            p4.tblConsultas.setModel(modelo);

            ocultarColumnas(1);
            ocultarColumnas(2);
            ocultarColumnas(3);
            ocultarColumnas(4);
            ocultarColumnas(5);
            ocultarColumnas(6);
            ocultarColumnas(7);
            ocultarColumnas(8);
            ocultarColumnas(9);
            ocultarColumnas(10);
            ocultarColumnas(11);
            ocultarColumnas(12);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void ocultarColumnas(int col) {
        p4.tblConsultas.getColumnModel().getColumn(col).setMaxWidth(0);
        p4.tblConsultas.getColumnModel().getColumn(col).setMinWidth(0);
        p4.tblConsultas.getTableHeader().getColumnModel().getColumn(col).setMaxWidth(0);
        p4.tblConsultas.getTableHeader().getColumnModel().getColumn(col).setMinWidth(0);
    }

    public void datosReceta(int idConsulta) {
        HCDAO hc = new HCDAO();

        try {
            ArrayList<Receta> receta = hc.datosReceta(idConsulta);

            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            modelo.addColumn("Medicamento");
            modelo.addColumn("Cantidad");

            for (Receta r : receta) {
                String[] fila = new String[2];
                fila[0] = r.getNombreMedicamento();
                fila[1] = String.valueOf(r.getCantidad());

                modelo.addRow(fila);
            }

            p4.tblReceta.setModel(modelo);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void mostrarImagenes(int idConsulta) {
        HCDAO hc = new HCDAO();

        try {
            ArrayList<Imagen> imagenes = hc.imagenesConsulta(idConsulta);

            p4.tblImagenes.setDefaultRenderer(Object.class, new btTablaImagenes());
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Archivo");
            modelo.addColumn("Extensión");

            for (Imagen img : imagenes) {
                Object[] fila = new Object[4];
                fila[0] = img.getId();
                fila[1] = img.getNombre();
                if (img.getArchivo() != null) {
                    fila[2] = new JButton("Ver imagen");
                } else {
                    fila[2] = new JButton("vacio");
                }
                fila[3] = img.getExtension();

                modelo.addRow(fila);
            }

            p4.tblImagenes.setModel(modelo);

            p4.tblImagenes.getColumnModel().getColumn(0).setMaxWidth(0);
            p4.tblImagenes.getColumnModel().getColumn(0).setMinWidth(0);
            p4.tblImagenes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            p4.tblImagenes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            p4.tblImagenes.getColumnModel().getColumn(3).setMaxWidth(0);
            p4.tblImagenes.getColumnModel().getColumn(3).setMinWidth(0);
            p4.tblImagenes.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
            p4.tblImagenes.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);

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

        panelScroll = new javax.swing.JScrollPane();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btImprimir = new javax.swing.JButton();
        txtIdPaciente = new javax.swing.JTextField();
        panelElementos = new javax.swing.JPanel();
        btAtras = new javax.swing.JButton();
        btSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelScroll.setPreferredSize(new java.awt.Dimension(1017, 860));

        panelPrincipal.setPreferredSize(new java.awt.Dimension(1020, 860));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Historial Clínico");

        btImprimir.setText("Imprimir");
        btImprimir.setFocusable(false);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        txtIdPaciente.setEditable(false);

        panelElementos.setPreferredSize(new java.awt.Dimension(1000, 860));

        javax.swing.GroupLayout panelElementosLayout = new javax.swing.GroupLayout(panelElementos);
        panelElementos.setLayout(panelElementosLayout);
        panelElementosLayout.setHorizontalGroup(
            panelElementosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        panelElementosLayout.setVerticalGroup(
            panelElementosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );

        btAtras.setText("Atrás");
        btAtras.setFocusable(false);
        btAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtrasActionPerformed(evt);
            }
        });

        btSiguiente.setText("Siguiente");
        btSiguiente.setFocusable(false);
        btSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(btAtras)
                        .addGap(18, 18, 18)
                        .addComponent(btSiguiente)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(btImprimir))
                    .addComponent(panelElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImprimir)
                    .addComponent(btAtras)
                    .addComponent(btSiguiente))
                .addGap(18, 18, 18)
                .addComponent(panelElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelScroll.setViewportView(panelPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSiguienteActionPerformed
        // TODO add your handling code here:

        if (p1.isVisible() == true) {
            p1.setVisible(false);
            p2.setVisible(true);
            panelElementos.add(p2);
            panelElementos.validate();
            btAtras.setVisible(true);
        } else if (p2.isVisible() == true) {
            p2.setVisible(false);
            if (p1.txtSexo.getText().equals("Femenino")) {
                p3.setVisible(true);
                panelElementos.add(p3);
            } else {
                p4.setVisible(true);
                panelElementos.add(p4);
                btSiguiente.setVisible(false);
            }
            panelElementos.validate();

        } else if (p3.isVisible() == true) {
            p3.setVisible(false);
            p4.setVisible(true);
            panelElementos.add(p4);
            panelElementos.validate();

        }
    }//GEN-LAST:event_btSiguienteActionPerformed

    private void btAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtrasActionPerformed
        // TODO add your handling code here:
        if (p4.isVisible() == true) {

            p4.setVisible(false);
            if (p1.txtSexo.getText().equals("Femenino")) {
                p3.setVisible(true);
                panelElementos.add(p3);
            } else {
                p2.setVisible(true);
                panelElementos.add(p2);
            }
            panelElementos.validate();
            btSiguiente.setVisible(true);
        } else if (p3.isVisible() == true) {
            p2.setVisible(true);
            p3.setVisible(false);
            panelElementos.add(p2);
            panelElementos.validate();

        } else if (p2.isVisible() == true) {
            p1.setVisible(true);
            p2.setVisible(false);
            panelElementos.add(p1);
            panelElementos.validate();

        }
    }//GEN-LAST:event_btAtrasActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        // TODO add your handling code here:

        Conexion cn = new Conexion();
        String path = "C:\\HistorialClinico\\src\\reportes\\historial_clinico.jasper";
        //String path = ".\\HistorialClinico\\src\\reportes\\historial_clinico.jasper";
        JasperReport jr = null;
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idPaciente", txtIdPaciente.getText());
        parametros.put("imagen", this.getClass().getResourceAsStream("/reportes/simbolo_medicina.png"));
        
        
        try {
            //jr = (JasperReport) JRLoader.loadObjectFromFile(path);
            //JasperReport subReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/reportes/historial_clinico_consultas.jrxml"));
            
            parametros.put("subReport", this.getClass().getResourceAsStream("/reportes/historial_clinico_consultas.jasper"));
            jr=(JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/historial_clinico.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cn.conectar());
            JasperViewer jv = new JasperViewer(jp, false);
            this.setVisible(false);
            jv.setVisible(true);
            jv.setTitle("Historial Clínico");

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un evento " + ex.getMessage());
        }
    }//GEN-LAST:event_btImprimirActionPerformed

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
            java.util.logging.Logger.getLogger(HistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistorialClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HistorialClinico dialog = new HistorialClinico(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btAtras;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelElementos;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JTextField txtIdPaciente;
    // End of variables declaration//GEN-END:variables
}
