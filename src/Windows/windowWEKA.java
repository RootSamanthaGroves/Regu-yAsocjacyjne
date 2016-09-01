/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import associativerules.WekaApriori;
import associativerules.Data;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Dominika
 */
public class windowWEKA extends javax.swing.JFrame {

    private static int liczbaWierszy;
    private static int liczbaKolumn;
    private static JTable jTabData;
    private static String word;
    private static String path;// = "C:\\Users\\Dominika\\Documents\\NetBeansProjects\\RegulyAsocjacyjne\\src\\data\\irysy.arff";
    private static Instances data;
    private static WekaApriori wA;
    private static List<String> listOfH;

    public static WekaApriori getwA() {
        return wA;
    }

    public static List<String> getListOfH() {
        return listOfH;
    }

    public static Instances getData() {
        return data;
    }

    public static void infoObj()
            throws Exception {
        for (int i = 0; i < data.numInstances(); i++) { //Przegladanie obiektow
            System.out.println("Wiersz numer " + i + ":");

            Instance instance = data.instance(i); //Pobranie obiektu (wiersza danych) o podanym numerze

            for (int j = 0; j < instance.numAttributes(); j++) //Przegladanie atrybutow w obiekcie
            {
                String textValue = instance.toString(j); //Pobranie wartosci atrybutu o podanym numerze (tzn. pobranie tekstowej reprezentacji wartosci)
                System.out.print(textValue + ", ");
            }
            System.out.println();
        }

    }

    /**
     * Creates new form windowWEKA
     */
    public windowWEKA() {
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

        jColorChooser1 = new javax.swing.JColorChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnShowResults = new javax.swing.JButton();
        btnLoadDate = new javax.swing.JButton();
        btnSaveResults = new javax.swing.JButton();
        btnShowData = new javax.swing.JButton();
        tfPath = new javax.swing.JTextField();
        tfMinCon = new javax.swing.JTextField();
        tfNumberRules = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResults = new javax.swing.JTextArea();
        btnRulesForNObjects = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnShowResults.setText("Show results");
        btnShowResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowResultsActionPerformed(evt);
            }
        });

        btnLoadDate.setText("Load data");
        btnLoadDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadDateActionPerformed(evt);
            }
        });

        btnSaveResults.setText("Save results to file");
        btnSaveResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveResultsActionPerformed(evt);
            }
        });

        btnShowData.setText("Show data");
        btnShowData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDataActionPerformed(evt);
            }
        });

        tfPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPathActionPerformed(evt);
            }
        });

        tfMinCon.setText("10");
        tfMinCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMinConActionPerformed(evt);
            }
        });

        tfNumberRules.setText("0.9");
        tfNumberRules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNumberRulesActionPerformed(evt);
            }
        });

        jLabel1.setText("The minimum confidence of a rule.");

        jLabel2.setText("The required number of rules");

        taResults.setColumns(20);
        taResults.setRows(5);
        jScrollPane2.setViewportView(taResults);

        btnRulesForNObjects.setText("Check rules for new objects");
        btnRulesForNObjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRulesForNObjectsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfPath, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoadDate, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnShowData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNumberRules)
                                    .addComponent(tfMinCon)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnShowResults, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSaveResults))
                            .addComponent(btnRulesForNObjects, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnShowData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfMinCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfNumberRules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnShowResults)
                            .addComponent(btnSaveResults))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRulesForNObjects))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(460, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowResultsActionPerformed

        try {
            wA = new WekaApriori();
            word = wA.rulesAssociativ(data, tfNumberRules.getText(), tfMinCon.getText());
            taResults.setText(word);
        } catch (Exception ex) {
            Logger.getLogger(windowWEKA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnShowResultsActionPerformed

    private void btnLoadDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadDateActionPerformed
        path = Data.swingFileChooser("Wybierz plik", "Pliki danych(*.arff)", "arff");
        tfPath.setText(path);
        try {
            data = Data.loadData(path);
        } catch (IOException ex) {
            Logger.getLogger(windowWEKA.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnLoadDateActionPerformed

    private void btnShowDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDataActionPerformed

        liczbaWierszy = data.numInstances();
        liczbaKolumn = data.numAttributes();

        System.out.println(path);

        loadDataToTable();
    }//GEN-LAST:event_btnShowDataActionPerformed

    private void btnSaveResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveResultsActionPerformed
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("reguly-asocjacyjne.txt")));
            pw.write(word);
            pw.close();
            JOptionPane.showMessageDialog(null, "Saved successfully", "Saved", 1);
        } catch (FileNotFoundException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e, "Saved abord", 0);
        }


    }//GEN-LAST:event_btnSaveResultsActionPerformed

    private void tfPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPathActionPerformed

    private void tfMinConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMinConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMinConActionPerformed

    private void tfNumberRulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNumberRulesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNumberRulesActionPerformed

    private void btnRulesForNObjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRulesForNObjectsActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new Windows.windowGenerating().setVisible(true);
        });
    }//GEN-LAST:event_btnRulesForNObjectsActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(windowWEKA.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new windowWEKA().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadDate;
    private javax.swing.JButton btnRulesForNObjects;
    private javax.swing.JButton btnSaveResults;
    private javax.swing.JButton btnShowData;
    private javax.swing.JButton btnShowResults;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea taResults;
    private javax.swing.JTextField tfMinCon;
    private javax.swing.JTextField tfNumberRules;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables

    /**
     * Metoda powinna uzupełniać tabelkę danymi wczytanymi z pliku *.arff
     *
     */
    public void loadDataToTable() {
        try {
            jTabData = new JTable(liczbaWierszy, liczbaKolumn);

            DefaultTableModel dtm = new DefaultTableModel(0, 0);
            String header[] = new String[liczbaKolumn];
            listOfH = new ArrayList<>();
            for (int i = 0; i < liczbaKolumn; i++) {
                header[i] = data.attribute(i).name();
                listOfH.add(header[i]);
            }
            dtm.setColumnIdentifiers(header);
            jTabData.setModel(dtm);
            List<String[]> listWithInstance = new ArrayList<>();
            for (int i = 0; i < data.numInstances(); i++) {
                Instance instance = data.instance(i);
                listWithInstance.add(instance.toString().split(Pattern.quote(",")));
            }
            for (int i = 0; i < liczbaWierszy; i++) {
                dtm.addRow(listWithInstance.get(i));
            }
            jTabData.setVisible(true);

            Insets insets = this.getInsets();
            JScrollPane jp = new JScrollPane(jTabData);
            jp.setSize(new Dimension(100, 80));

            jp.setBounds(10 + insets.left, 190 + insets.top, this.getWidth() - insets.right - 30, this.getHeight() - 200);
            jp.setVisible(true);

            try {
                this.add(jp);

            } catch (Exception e) {
                System.out.println("Już nie kwiczy magia JAVY :D");
            }
            this.repaint();

            System.out.println("Wierszy: " + liczbaWierszy);
            System.out.println("Kolumn " + +liczbaKolumn);

        } catch (Exception ex) {
            Logger.getLogger(windowGenerating.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
