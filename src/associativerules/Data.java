/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package associativerules;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;


/**
 *
 * @author Dominika
 */
public class Data {

    public Data() {

    }

    public static String swingFileChooser(String title, String fFilter, String extension1) {
        final JFileChooser jfChooser = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(fFilter, extension1);
        jfChooser.setDialogTitle(title);
        jfChooser.setFileFilter(fileFilter);
        jfChooser.setVisible(true);
        int result = jfChooser.showSaveDialog(jfChooser);
        String path = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectFile = jfChooser.getSelectedFile();
            //  setFileName(selectFile.getName());
            path = selectFile.getAbsolutePath();
        }
        return path;
    }

    /**
     * Odczytanie tablicy danych z pliku w formacie ARFF
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Instances loadData(String fileName)
            throws IOException {
        ArffLoader loader = new ArffLoader(); //Utworzenie obiektu czytajacego dane z formatu ARFF
        loader.setFile(new File(fileName)); //Ustawienie pliku do odczytania
        return loader.getDataSet(); //Odczytanie danych z pliku
    }

    /**
     * Zapis danych do pliku w formacie *.arff
     *
     * @param data dane
     * @param fileName nazwa pliku
     * @throws IOException
     */
    public static void saveData(Instances data, String fileName)
            throws IOException {
        ArffSaver saver = new ArffSaver(); //Utworzenie obiektu zapisujacego dane
        saver.setFile(new File(fileName)); //Ustawienie nazwy pliku do zapisu
        saver.setInstances(data);
        saver.writeBatch(); //Zapis do pliku
    }

}
