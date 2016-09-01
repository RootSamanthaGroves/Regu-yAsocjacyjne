/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package associativerules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.associations.Item;
import weka.core.Instances;
import weka.core.Utils;

/**
 *
 * @author Dominika
 */
public class WekaApriori {

    private String numRules;
    private String minConfidence;
    private static Apriori apriori;

    public WekaApriori() {
    }

    public Apriori getApriori() {
        return apriori;
    }

    public WekaApriori(String numRules, String minConfidence) {
        this.numRules = numRules;
        this.minConfidence = minConfidence;
    }

    public String getMinConfidence() {
        return minConfidence;
    }

    public String getNumRules() {
        return numRules;
    }

    public void setNumRules(String numRules) {
        numRules = this.numRules;
    }

    public void setMinConfidence(String minConfidence) {
        this.minConfidence = minConfidence;
    }

    
    /**
     * Generowanie reguł Asocjacyjnych algorytmem Apriori
     *
     * @param data
     * @param n Liczba regul do policzenia (standardowo: 10)
     * @param c Minmalna ufnosc reguly (standardowo: 0.9).
     * @return
     * @throws Exception
     */
    public String rulesAssociativ(Instances data, String c, String n)
            throws Exception {
        data.setClassIndex(data.numAttributes() - 1);

        String[] options = Utils.splitOptions("-N " + n + " -C " + c);
        apriori = new Apriori();
        apriori.setOptions(options);
        apriori.buildAssociations(data); //Generowanie regul asocjacyjnych

        System.out.println("Liczba regul=" + apriori.getNumRules());

        System.out.println(apriori.toString()); //Wypisanie informacji o regulach
        return apriori.toString();
    }

    /**
     * Metoda Sprawdza czy dana reguła jest wspierana przez obiekty Dzieli
     * regule na premise i consequence i sprawdza czy następniki i poprzedniki
     * reguły mają takie same wartości w sprawdzanym obiekcie
     *
     * @param attribName tablica z nazwami atrybutów
     * @param associationRule reguła do sprawdzenia
     * @param instances zbiór obiektów danych
     * @param numRule numer sprawdzanej reguły
     * @return tablica jednowymiarowa z wartościami typu String przechwującymi
     * numery wspieranych przez obiekt reguł
     */
    public static String[] ruleCheck(String[] attribName, AssociationRule associationRule, String[][] instances, int numRule) {
        String[] tabCompatibility = new String[instances.length];
        for (int j = 0; j < tabCompatibility.length; j++) {
            tabCompatibility[j] = " ";
        }

        Collection<Item> premise = associationRule.getPremise();
        Collection<Item> consequence = associationRule.getConsequence();
        for (int i = 0; i < instances.length; i++) {// przechodzi po calej tablicy danych 
            for (Item item : premise) {
                int index = 0;
                for (int j = 0; j < attribName.length; j++) {
                    if (item.getAttribute().name().equals(attribName[j])) {
                        index = j;
                    }
                }
                String instValueOnAtrrib = instances[i][index];
                if (!instValueOnAtrrib.equals(item.getItemValueAsString())) {// przyrownanie czy czesc z regulu jest spełniona z wartoscia z kolumny
                    tabCompatibility[i] = "niezgodny";
                    break;
                }
            }
            for (Item item : consequence) {
                int index = 0;
                for (int j = 0; j < attribName.length; j++) {
                    if (item.getAttribute().name().equals(attribName[j])) {
                        index = j;
                    }
                }
                String instValueOnAtrrib = instances[i][index];
                if (/*instValueOnAtrrib.equals(item.getItemValueAsString()) &&*/!tabCompatibility[i].equals("niezgodny")) {
                    tabCompatibility[i] = (numRule + 1) + "";
                } else {
                    tabCompatibility[i] = "niezgodny";
                }
            }
        }
        return tabCompatibility;
    }

    /**
     *
     * @param attribName
     * @param associationRule
     * @param instances
     * @param numRule
     * @param acceptRules
     * @return
     */
    public static String[] ruleCheckContradictory(String[] attribName, AssociationRule associationRule, String[][] instances, int numRule, String[] acceptRules) {
        String[] tabCompatibility = new String[instances.length];
        for (int j = 0; j < tabCompatibility.length; j++) {
            tabCompatibility[j] = " ";
        }

        Collection<Item> premise = associationRule.getPremise();
        Collection<Item> consequence = associationRule.getConsequence();
        for (int i = 0; i < instances.length; i++) {// przechodzi po calej tablicy danych 
            for (Item item : premise) {
                int index = 0;
                for (int j = 0; j < attribName.length; j++) {
                    if (item.getAttribute().name().equals(attribName[j])) {
                        index = j;
                    }
                }
                String instValueOnAtrrib = instances[i][index];
                if (!instValueOnAtrrib.equals(item.getItemValueAsString())) {// przyrownanie czy czesc z regulu jest spełniona z wartoscia z kolumny
                    tabCompatibility[i] = "niezgodny";
                }
            }
            for (Item item : consequence) {
                int index = 0;
                for (int j = 0; j < attribName.length; j++) {
                    if (item.getAttribute().name().equals(attribName[j])) {
                        index = j;
                    }
                }
                String instValueOnAtrrib = instances[i][index];
                int newNum = numRule + 1;
                if (instValueOnAtrrib.equals(item.getItemValueAsString()) && !tabCompatibility[i].equals("niezgodny")) {
                    tabCompatibility[i] = "niezgodny";
                } else if (acceptRules[i].contains(String.valueOf(newNum))) {
                    tabCompatibility[i] = String.valueOf(newNum);
                }
            }
        }
        return tabCompatibility;
    }

    /**
     *
     * @param atrribName tablica z nazwami atrybutów
     * @param instances obiekty danych zapisane jako dwuwymiarowa tablica
     * stringów
     * @return
     */
    public String[] checkAllRules(String[] atrribName, String[][] instances) {
        AssociationRules associationRules = apriori.getAssociationRules();
        List<AssociationRule> rules = associationRules.getRules();
        ArrayList<String[]> listOfObjCheck = new ArrayList<>();
        String[] rulesSuportForObj = new String[instances.length];
        for (int i = 0; i < rulesSuportForObj.length; i++) {
            rulesSuportForObj[i] = " ";
        }
        for (int i = 0; i < rules.size(); i++) {
            listOfObjCheck.add(ruleCheck(atrribName, rules.get(i), instances, i));
        }

        for (int k = 0; k < listOfObjCheck.get(0).length; k++) {//6462
            for (int i = 0; i < listOfObjCheck.size(); i++) {//10
                if (!listOfObjCheck.get(i)[k].isEmpty()) {
                    if (!listOfObjCheck.get(i)[k].contains("niezgodny")) {
                        rulesSuportForObj[k] += listOfObjCheck.get(i)[k] + ", ";
                    }
                }
            }
        }
        for (int i = 0; i < rulesSuportForObj.length; i++) {
            if (rulesSuportForObj[i].length() > 1) {
                rulesSuportForObj[i] = rulesSuportForObj[i].substring(0, rulesSuportForObj[i].length() - 2);
            }
        }
        return rulesSuportForObj;
    }

    /**
     * Metoda sprawdzajaca sprzeczność reguł z obiektami Krtan cN1 pN1
     *
     *
     * PRZY_ZG=0 => WYNIKI=Brak_wznowy
     *
     * PRZY_ZG=0, ale WYNIKI=Inny rak, i PRZY_ZG=0, to ma być WYNIKI=Brak_wznowy
     *
     * @param atrribName
     * @param instances
     * @param acceptRules
     * @return
     */
    public String[] checkContradictoryTheRules(String[] atrribName, String[][] instances, String[] acceptRules) {
        AssociationRules associationRules = apriori.getAssociationRules();
        List<AssociationRule> rules = associationRules.getRules();
        ArrayList<String[]> listOfObjCheck = new ArrayList<>();
        String[] rulesContradictoryForObj = new String[instances.length];
        for (int i = 0; i < rulesContradictoryForObj.length; i++) {
            rulesContradictoryForObj[i] = " ";
        }
        for (int i = 0; i < rules.size(); i++) {
            listOfObjCheck.add(ruleCheckContradictory(atrribName, rules.get(i), instances, i, acceptRules));
        }

        for (int k = 0; k < listOfObjCheck.get(0).length; k++) {//6462
            for (int i = 0; i < listOfObjCheck.size(); i++) {//10
                if (!listOfObjCheck.get(i)[k].isEmpty()) {
                    if (!listOfObjCheck.get(i)[k].contains("niezgodny")) {
                        rulesContradictoryForObj[k] += listOfObjCheck.get(i)[k] + ", ";
                    }
                }
            }
        }
        for (int i = 0; i < rulesContradictoryForObj.length; i++) {
            if (rulesContradictoryForObj[i].length() > 1) {
                rulesContradictoryForObj[i] = rulesContradictoryForObj[i].substring(0, rulesContradictoryForObj[i].length() - 2);
            }
        }
        return rulesContradictoryForObj;
    }

    public String rulesAsString() {
        StringBuilder rulesAsString = new StringBuilder("");
        List<AssociationRule> rules = apriori.getAssociationRules().getRules();
        int numRule = 1;
        for (AssociationRule rule : rules) {
            Collection<Item> premise = rule.getPremise();
            Collection<Item> consequence = rule.getConsequence();
            rulesAsString.append(numRule);
            rulesAsString.append("\t");
            premise.stream().map((item) -> {
                rulesAsString.append(item.getAttribute().name());
                return item;
            }).map((item) -> {
                rulesAsString.append(" = ");
                rulesAsString.append(item.getItemValueAsString());
                return item;
            }).forEach((_item) -> {
                rulesAsString.append(" ");
            });
            rulesAsString.append("\t->\t");
            consequence.stream().map((item) -> {
                rulesAsString.append(item.getAttribute().name());
                return item;
            }).map((item) -> {
                rulesAsString.append(" = ");
                rulesAsString.append(item.getItemValueAsString());
                return item;
            }).forEach((_item) -> {
                rulesAsString.append(" ");
            });
            rulesAsString.append("\n");
            numRule++;
        }
        return rulesAsString.toString();
    }
}
