package ch.bzz.produktliste.data;

import ch.bzz.produktliste.model.Hersteller;
import ch.bzz.produktliste.model.Inhalt;
import ch.bzz.produktliste.model.Produkt;
import ch.bzz.produktliste.service.Config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
 */
public class DataHandler {
    private static List<Hersteller> herstellerListe;
    private static List<Inhalt> inhaltListe;
    private static List<Produkt> produktList;

    /*
     * privater Konstruktor für die Instanzierung
     */
    private DataHandler() {
        setInhaltList(new ArrayList<>());
        readInhaltJSON();
        setHerstellerList(new ArrayList<>());
        readHerstellerJSON();
        setProduktList(new ArrayList<>());
        readProduktJSON();
    }

    /*
     * liest alle Hersteller
     * @return Liste der Hersteller
     */
    public static List<Hersteller> readAllHersteller() {
        return getHerstellerList();
    }

    /*
     * liest einen Hersteller nach seiner UUID
     * @param herstellerUUID
     * @return den Hersteller (null=not found)
     */
    public static Hersteller readHerstellerByUUID(String herstellerUUID) {
        Hersteller hersteller = null;
        for (Hersteller h : getHerstellerList()) {
            if (h.getHerstellerUUID().equals(herstellerUUID)) {
                hersteller = h;
            }
        }
        return hersteller;
    }

    /*
     * liest alle Inhalte
     * @return Liste der Inhalte
     */
    public static List<Inhalt> readAllInhalte() {
        return getInhaltList();
    }

    /*
     * liest einen Inhalt nach seiner UUID
     * @param inhaltUUID
     * @return den Inhalt (null=not found)
     */
    public static Inhalt readInhaltByUUID(String inhaltUUID) {
        Inhalt inhalt = null;
        for (Inhalt i : getInhaltList()) {
            if (i.getInhaltUUID().equals(inhaltUUID)) {
                inhalt = i;
            }
        }
        return inhalt;
    }

    /*
     * liest alle Produkte
     * @return Liste der Inhalte
     */
    public static List<Produkt> readAllProdukte() {
        return getProduktList();
    }

    /*
     * liest ein Produkt nach seiner UUID
     * @param produktUUID
     * @return das Produkt (null=not found)
     */
    public static Produkt readProduktByUUID(String produktUUID) {
        Produkt produkt = null;
        for (Produkt p : getProduktList()) {
            if (p.getProduktUUID().equals(produktUUID)) {
                produkt = p;
            }
        }
        return produkt;
    }

    /*
     * liest den Hersteller aus einem JSON-File
     */
    private static void readHerstellerJSON() {
        try {
            String path = Config.getProperty("herstellerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Hersteller[] hersteller = objectMapper.readValue(jsonData, Hersteller[].class);
            for (Hersteller h : hersteller) {
                getHerstellerList().add(h);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * liest den Inhalt aus einem JSON-File
     */
    private static void readInhaltJSON() {
        try {
            String path = Config.getProperty("inhaltJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Inhalt[] inhalte = objectMapper.readValue(jsonData, Inhalt[].class);
            for (Inhalt inhalt : inhalte) {
                getInhaltList().add(inhalt);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * liest die Produkte aus einem JSON-File
     */
    private static void readProduktJSON() {
        try {
            String path = Config.getProperty("produktJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Produkt[] produkte = objectMapper.readValue(jsonData, Produkt[].class);
            for (Produkt produkt : produkte) {
                getProduktList().add(produkt);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * schreibt eine Produktliste ins JSON-File
     */
    private static void schreibProduktJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String produktPath = Config.getProperty("produktJSON");
        try {
            fileOutputStream = new FileOutputStream(produktPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getProduktList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
    * löscht ein Produkt was über die UUID identifiziert wird
    * @param produktUUID
    * @return erfolg=true/false
    * */
    public static boolean produktLoeschen(String produktUUID) {
        Produkt produkt = new Produkt();
        if (produkt != null) {
            getProduktList().remove(produkt);
            schreibProduktJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * gettet herstellerListe
     *
     * @retun die herstellerListe
     */
    private static List<Hersteller> getHerstellerList() {
        return herstellerListe;
    }

    /*
     * settet herstellerListe
     *
     * @param herstellerListe den Wert den man setzen will
     */
    private static void setHerstellerList(List<Hersteller> herstellerListe) {
        DataHandler.herstellerListe = herstellerListe;
    }

    /*
     * gettet inhaltListe
     *
     * @return Wert der inhaltListet
     */
    private static List<Inhalt> getInhaltList() {
        return inhaltListe;
    }

    /*
     * settet inhaltListe
     *
     * @param inhaltListe der Wert den man setzen will
     */
    private static void setInhaltList(List<Inhalt> inhaltListe) {
        DataHandler.inhaltListe = inhaltListe;
    }

    /*
     * gettet produktListe
     *
     * @return Wert der produktListe
     */
    public static List<Produkt> getProduktList() {
        if (produktList == null) {
            setProduktList(new ArrayList<>());
            readProduktJSON();
        }

        return produktList;
    }

    /*
     * settet produktListe
     *
     * @param produktListe der Wert den man setzen will
     */
    public static void setProduktList(List<Produkt> produktList) {
        DataHandler.produktList = produktList;
    }
}
