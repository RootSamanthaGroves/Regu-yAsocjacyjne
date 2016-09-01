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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import associativerules.WekaApriori;
import associativerules.Data;
import static associativerules.Data.loadData;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Dominika
 */
public class windowGenerating extends javax.swing.JFrame {

    private static final Instances OLD_DATA = windowWEKA.getData();
    private static int numOfNewInstance;
    private static String[][] dataInTable;
    private static Instances newData;
    private static JTable jTabData;
    private static final List<String> LIST_OF_HEADER = windowWEKA.getListOfH();
    private static String[] rulesWichSupportObject;
    private static String[] tabHeaders;

    public static void infoObj()
            throws Exception {
        Instances data = loadData("./src/date/osmolski.arff");

        for (int i = 0; i < data.numInstances(); i++) { //Przegladanie obiektow
            System.out.println("Wiersz numer " + i + ":");
            Instance instance = data.instance(i); //Pobranie obiektu (wiersza danych) o podanym numerze

            for (int j = 0; j < instance.numAttributes(); j++) { //Przegladanie atrybutow w obiekcie
                String textValue = instance.toString(j); //Pobranie wartosci atrybutu o podanym numerze (tzn. pobranie tekstowej reprezentacji wartosci)
                System.out.print(textValue + ", ");
            }
            System.out.println();
        }
    }

    /**
     * Creates new form windowWEKA
     */
    public windowGenerating() {
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
        btnSaveResults = new javax.swing.JButton();
        btnShowNewData = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResults = new javax.swing.JTextArea();

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

        btnSaveResults.setText("Save results from table to file");
        btnSaveResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveResultsActionPerformed(evt);
            }
        });

        btnShowNewData.setText("Show new data");
        btnShowNewData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowNewDataActionPerformed(evt);
            }
        });

        taResults.setColumns(20);
        taResults.setRows(5);
        jScrollPane2.setViewportView(taResults);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnShowNewData, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(btnSaveResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnShowNewData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveResults))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(546, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnShowNewDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowNewDataActionPerformed

        dataInTable = dataFromInnstancesToTable(OLD_DATA);
        List<Set<String>> listOfSetValues = setListOfAtribValue(dataInTable);

        Set<List<String>> setOfListValues = tabToSetObj(dataInTable);
        Set<List<String>> permutateOfObjects = getCombinations(listOfSetValues);

        System.out.println("Rozmiar zbioru przed usówaniem " + permutateOfObjects.size());

        Iterator iter = setOfListValues.iterator();
        while (iter.hasNext()) {
            permutateOfObjects.remove((List<String>) iter.next());
        }
        numOfNewInstance = permutateOfObjects.size();

        try {
            newData = setToInstances(listOfSetValues, permutateOfObjects);
            newData.setClassIndex(newData.numAttributes() - 1);
            Data.saveData(newData, "NoweObiekty");
        } catch (Exception ex) {
            Logger.getLogger(windowWEKA.class.getName()).log(Level.SEVERE, null, ex);
        }

        String header[] = new String[newData.numAttributes()];
        for (int i = 0; i < header.length; i++) {
            header[i] = newData.attribute(i).name();
        }

        String[][] newDataInTable = dataFromInnstancesToTable(newData);
        WekaApriori wa = windowWEKA.getwA();
        String[] acceptRules = wa.checkAllRules(header, newDataInTable);

        rulesWichSupportObject = acceptRules;

        String[] contradictoryTheRules = wa.checkContradictoryTheRules(header, newDataInTable, acceptRules);  //tworzenie kolumny w tabele TEŻ ZMIENIC

        taResults.setText(wa.rulesAsString());

        loadDataToTable(newDataInTable, acceptRules, contradictoryTheRules);
    }//GEN-LAST:event_btnShowNewDataActionPerformed

    private void btnSaveResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveResultsActionPerformed
        StringBuilder sb = new StringBuilder();

        for (String tabHeader : tabHeaders) {
            sb.append(tabHeader).append("; ");
        }
        sb.append("\n");
        String[][] newDataToTable = dataFromInnstancesToTable(newData);
        for (int i = 0; i < newDataToTable.length; i++) {
            for (int j = 0; j < newDataToTable[0].length; j++) {
                sb.append(newDataToTable[i][j]).append("; ");
            }
            sb.append(rulesWichSupportObject[i]).append("\n");

        }
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("NewObjWithSupportsRules.txt")));
            pw.write(sb.toString());
            pw.close();
            JOptionPane.showMessageDialog(null, "Saved successfully", "Saved", 1);
        } catch (FileNotFoundException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e, "Saved abord", 0);

        }
    }//GEN-LAST:event_btnSaveResultsActionPerformed

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
            java.util.logging.Logger.getLogger(windowGenerating.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new windowGenerating().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveResults;
    private javax.swing.JButton btnShowNewData;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea taResults;
    // End of variables declaration//GEN-END:variables

    /**
     *
     *
     * @param dataInTable
     * @param acceptRules
     * @param contradictoryTheRules
     */
    public void loadDataToTable(String[][] dataInTable, String[] acceptRules, String[] contradictoryTheRules) {
        try {
            int numberRow = numOfNewInstance;
            int numberColumn = newData.numAttributes() + 2;
            jTabData = new JTable(numberRow, numberColumn);

            DefaultTableModel dtm = new DefaultTableModel(0, 0);
            String header[] = new String[numberColumn];
            for (int i = 0; i < numberColumn - 2; i++) {
                header[i] = newData.attribute(i).name();
                LIST_OF_HEADER.add(header[i]);
            }
            header[numberColumn - 2] = "Supports rules";
            header[numberColumn - 1] = "Contradictory the rules";
            tabHeaders = header;
            dtm.setColumnIdentifiers(header);
            jTabData.setModel(dtm);

            List<String[]> listWithInstance = new ArrayList<>();
            for (int i = 0; i < dataInTable.length; i++) {////poprawić
                Instance instance = newData.instance(i);
                String[] row = new String[instance.numAttributes() + 2];
                String[] row2 = instance.toString().split(Pattern.quote(","));
                System.arraycopy(row2, 0, row, 0, row2.length);
                row[row.length - 1] = contradictoryTheRules[i];

                row[row.length - 2] = acceptRules[i];

                listWithInstance.add(row);
            }
            for (int i = 0; i < numberRow; i++) {
                dtm.addRow(listWithInstance.get(i));
            }
            jTabData.setVisible(true);

            Insets insets = this.getInsets();
            JScrollPane jp = new JScrollPane(jTabData);
            jp.setSize(new Dimension(100, 60));

            jp.setBounds(10 + insets.left, 180 + insets.top, this.getWidth() - insets.right - 30, this.getHeight() - 190);
            jp.setVisible(true);

            try {
                this.add(jp);

            } catch (Exception e) {
                System.out.println("Już nie kwiczy magia JAVY :D");
            }
            this.repaint();
        } catch (Exception ex) {
            Logger.getLogger(windowWEKA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Zapisuje do listy zbiór niepowtarzalnych parametrów
     *
     * @param data
     * @return
     */
    private static List setListOfAtribValue(String[][] data) {
        List<Set<String>> listOfAtribValue = new ArrayList<>();
        Set<String> setKolumn;
        for (int i = 0; i < data[0].length; i++) {
            setKolumn = new HashSet<>();
            for (String[] data1 : data) {
                setKolumn.add(data1[i]);

            }
            listOfAtribValue.add(setKolumn);
            System.out.println(setKolumn);
        }
        return listOfAtribValue;
    }

    /**
     * Metoda zwraca zbiór wszystkich permutacji listy zbiorów wartości
     * atrybutów
     *
     * @param <T>
     * @param lists lista z zbiorami niepowtarzalnych wartości dla
     * poszczególnych atrybutów
     * @return
     */
    public static <T> Set<List<T>> getCombinations(List<Set<T>> lists) {
        Set<List<T>> combinations = new HashSet<>();
        Set<List<T>> newCombinations;
        int index = 0;
        //wyodrębnia każdy element z listy
        // i dodaje każdy obiekt typu <T> jako nową listę   

        for (T i : lists.get(0)) {
            List<T> newList = new ArrayList<>();
            newList.add(i);
            combinations.add(newList);
        }
        index++;
        while (index < lists.size()) {
            Set<T> nextSet = lists.get(index); //tworzenie nowego zbioru i przypisanie do niego poszczególnych elementów listy
            newCombinations = new HashSet<>();
            for (List<T> first : combinations) {
                for (T second : nextSet) {
                    List<T> newList = new ArrayList<>();
                    newList.addAll(first);
                    newList.add(second);
                    newCombinations.add(newList);
                }
            }
            combinations = newCombinations;
            index++;
        }
        return combinations;
    }

    public static Set tabToSetObj(String[][] data) {
        Set<List<String>> setObj = new HashSet<>();
        List<String> row;
        for (String[] data1 : data) {
            row = new ArrayList<>();
            for (int j = 0; j < data[0].length; j++) {
                row.add(data1[j]);
            }
            setObj.add(row);
        }
        return setObj;
    }

    /**
     * Metoda zamienia liste zbiorów na instance. Pierwsza pętla tworzy listę
     * wartości jakie mogą przybierać atrybut.
     *
     * @param atr lista atryburów
     * @param s lista zawierajaca kombinajcie uzupełnionych danych
     * @return
     *
     */
    public static Instances setToInstances(List<Set<String>> atr, Set<List<String>> s) {

        ArrayList<Attribute> lAtrib = new ArrayList<>();

        for (int i = 0; i < atr.size(); i++) {
            FastVector labels = new FastVector(); //Utworzenie obiektu kolekcji wartosci nowego atrybutu symbolicznego
            Set<String> setValuesAtr = atr.get(i);
            Iterator ite = setValuesAtr.iterator();
            while (ite.hasNext()) {
                Object e = ite.next();
                labels.addElement(e);
            }
            Attribute attrib = new Attribute(LIST_OF_HEADER.get(i), labels);
            lAtrib.add(attrib);
        }

        Instances dataNewObj = new Instances("Nowa tablica", lAtrib, 0);

        for (int i = 0; i < numOfNewInstance; i++) {
            Instance n = new DenseInstance(lAtrib.size());
            dataNewObj.add(n);
        }
        System.out.println(dataNewObj.numInstances() + " jest instancji nowo wygenerowanych");
        int iteratorek = 0;
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            Instance instance = dataNewObj.instance(iteratorek); //Pobranie obiektu o podanym numerze
            List<String> str = (List<String>) iter.next();
            for (int j = 0; j < dataNewObj.numAttributes(); j++) {
                instance.setValue(j, str.get(j));
            }
            iteratorek++;
        }
        return dataNewObj;
    }

    /**
     * Przpisuje dane z obiektu instances do tablicy dwuwymiarowej
     *
     * @param data
     * @return
     */
    public String[][] dataFromInnstancesToTable(Instances data) {
        String buf[];
        String[] obj;
        List<String[]> listObj = new ArrayList<>();
        for (int i = 0; i < data.numInstances(); i++) {
            buf = data.instance(i).toString().split(",");
            obj = new String[buf.length];
            for (int k = 0; k < buf.length; k++) {
                obj[k] = String.valueOf(buf[k]);
            }
            listObj.add(obj);
        }
        return listObj.toArray(new String[][]{});
    }

}
