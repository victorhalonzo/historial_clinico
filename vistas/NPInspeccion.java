/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

/**
 *
 * @author Usuario
 */
public class NPInspeccion extends javax.swing.JPanel {

    /**
     * Creates new form NPInspeccion
     */
    public NPInspeccion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgFuma = new javax.swing.ButtonGroup();
        bgAlcohol = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInspeccionGeneral = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rbFumaNo = new javax.swing.JRadioButton();
        rbFumaSi = new javax.swing.JRadioButton();
        rbAlcoholNo = new javax.swing.JRadioButton();
        rbAlcoholSi = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtHijos = new javax.swing.JTextField();
        txtTabacoObservacion = new javax.swing.JTextField();
        txtAlcoholObservacion = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Inspección General");

        txtInspeccionGeneral.setColumns(20);
        txtInspeccionGeneral.setRows(5);
        jScrollPane1.setViewportView(txtInspeccionGeneral);

        jLabel2.setText("Fuma");

        jLabel3.setText("Consume alcohol");

        bgFuma.add(rbFumaNo);
        rbFumaNo.setText("NO");

        bgFuma.add(rbFumaSi);
        rbFumaSi.setText("SI");

        bgAlcohol.add(rbAlcoholNo);
        rbAlcoholNo.setText("NO");

        bgAlcohol.add(rbAlcoholSi);
        rbAlcoholSi.setText("SI");

        jLabel4.setText("Cantidad de hijos");

        txtHijos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHijosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(txtHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbFumaNo)
                                    .addComponent(rbAlcoholNo))))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbFumaSi)
                                .addGap(18, 18, 18)
                                .addComponent(txtTabacoObservacion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbAlcoholSi)
                                .addGap(18, 18, 18)
                                .addComponent(txtAlcoholObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rbFumaNo)
                    .addComponent(rbFumaSi)
                    .addComponent(txtTabacoObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rbAlcoholNo)
                    .addComponent(rbAlcoholSi)
                    .addComponent(txtAlcoholObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHijosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHijosKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtHijosKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.ButtonGroup bgAlcohol;
    public static javax.swing.ButtonGroup bgFuma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JRadioButton rbAlcoholNo;
    public static javax.swing.JRadioButton rbAlcoholSi;
    public static javax.swing.JRadioButton rbFumaNo;
    public static javax.swing.JRadioButton rbFumaSi;
    public static javax.swing.JTextField txtAlcoholObservacion;
    public static javax.swing.JTextField txtHijos;
    public static javax.swing.JTextArea txtInspeccionGeneral;
    public static javax.swing.JTextField txtTabacoObservacion;
    // End of variables declaration//GEN-END:variables
}