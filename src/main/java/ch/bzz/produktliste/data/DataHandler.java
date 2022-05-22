package ch.bzz.produktliste.data;

import ch.bzz.produktliste.model.Hersteller;
import ch.bzz.produktliste.model.Inhalt;
import ch.bzz.produktliste.model.Produkt;
import ch.bzz.produktliste.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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
    private static DataHandler instance = null;
    private List<Hersteller> herstellerListe;
    private List<Inhalt> inhaltListe;
    private List<Produkt> produktList;

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
     * sichert ab, dass es nur eine Instanz von diesem Objekt gibt
     * (Singleton-Pattern)
     * @return Instanz des DataHandlers
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /*
     * liest alle Hersteller
     * @return Liste der Hersteller
     */
    public List<Hersteller> readAllHersteller() {
        return getHerstellerList();
    }

    /*
     * liest einen Hersteller nach seiner UUID
     * @param herstellerUUID
     * @return den Hersteller (null=not found)
     */
    public Hersteller readHerstellerByUUID(String herstellerUUID) {
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
    public List<Inhalt> readAllInhalte() {
        return getInhaltList();
    }

    /*
     * liest einen Inhalt nach seiner UUID
     * @param inhaltUUID
     * @return den Inhalt (null=not found)
     */
    public Inhalt readInhaltByUUID(String inhaltUUID) {
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
    public List<Produkt> readAllProdukte() {
        return getProduktList();
    }

    /*
     * liest ein Produkt nach seiner UUID
     * @param produktUUID
     * @return das Produkt (null=not found)
     */
    public Produkt readProduktByUUID(String produktUUID) {
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
    private void readHerstellerJSON() {
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
    private void readInhaltJSON() {
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
    private void readProduktJSON() {
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
     * gettet herstellerListe
     *
     * @retun die herstellerListe
     */
    private List<Hersteller> getHerstellerList() {
        return herstellerListe;
    }

    /*
     * settet herstellerListe
     *
     * @param herstellerListe den Wert den man setzen will
     */
    private void setHerstellerList(List<Hersteller> herstellerListe) {
        this.herstellerListe = herstellerListe;
    }

    /*
     * gettet inhaltListe
     *
     * @return Wert der inhaltListet
     */
    private List<Inhalt> getInhaltList() {
        return inhaltListe;
    }

    /*
     * settet inhaltListe
     *
     * @param inhaltListe der Wert den man setzen will
     */
    private void setInhaltList(List<Inhalt> inhaltListe) {
        this.inhaltListe = inhaltListe;
    }

    /*
     * gettet produktListe
     *
     * @return Wert der produktListe
     */
    public List<Produkt> getProduktList() {
        return produktList;
    }

    /*
     * settet produktListe
     *
     * @param produktListe der Wert den man setzen will
     */
    public void setProduktList(List<Produkt> produktList) {
        this.produktList = produktList;
    }
}
