package ch.bzz.produktliste.data;

import ch.bzz.produktliste.model.Hersteller;
import ch.bzz.produktliste.model.Inhalt;
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

    /**
     * privater Konstruktor für die Instanzierung
     */
    private DataHandler() {
        setPublisherList(new ArrayList<>());
        readPublisherJSON();
        setBookList(new ArrayList<>());
        readBookJSON();
    }

    /**
     * sicher ab, dass es nur eine Instanz von diesem Objekt gibt
     * (Singleton-Pattern)
     * @return Instanz des DataHandlers
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * liest alle Hersteller
     * @return Liste der Hersteller
     */
    public List<Hersteller> readAllHersteller() {
        return getHerstellerList();
    }

    /**
     * liest einen Hersteller nach seiner UUID
     * @param herstellerUUID
     * @return den Hersteller (null=not found)
     */
    public Hersteller readBookByUUID(String herstellerUUID) {
        Hersteller hersteller = null;
        for (Hersteller h : getHerstellerList()) {
            if (h.getHerstellerUUID().equals(herstellerUUID)) {
                hersteller = h;
            }
        }
        return hersteller;
    }

    /**
     * liest alle Inhalte
     * @return Liste der Inhalte
     */
    public List<Inhalt> readAllInhalte() {
        return getInhaltListe();
    }

    /**
     * liest einen Inhalt nach seiner UUID
     * @param inhaltUUID
     * @return den Inhalt (null=not found)
     */
    public Inhalt readPublisherByUUID(String inhaltUUID) {
        Inhalt inhalt = null;
        for (Inhalt i : getInhaltListe()) {
            if (i.getInhaltUUID().equals(inhaltUUID)) {
                inhalt = i;
            }
        }
        return inhalt;
    }

    /**
     * liest den Hersteller aus einem JSON-File
     */
    private void readBookJSON() {
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

    /**
     * liest den Inhalt aus einem JSON-File
     */
    private void readPublisherJSON() {
        try {
            String path = Config.getProperty("inhaltJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Inhalt[] inhalte = objectMapper.readValue(jsonData, Inhalt[].class);
            for (Inhalt inhalt : inhalte) {
                getInhaltListe().add(inhalt);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gettet herstellerListe
     *
     * @retun die herstellerListe
     */
    private List<Hersteller> getHerstellerList() {
        return herstellerListe;
    }

    /**
     * settet herstellerListe
     *
     * @param herstellerListe den Wert den man setzen will
     */
    private void setBookList(List<Hersteller> herstellerListe) {
        this.herstellerListe = herstellerListe;
    }

    /**
     * gettet inhaltListe
     *
     * @return Wert der inhaltListet
     */
    private List<Inhalt> getInhaltListe() {
        return inhaltListe;
    }

    /**
     * settet inhaltListe
     *
     * @param inhaltListe der Wert den man setzen will
     */
    private void setPublisherList(List<Inhalt> inhaltListe) {
        this.inhaltListe = inhaltListe;
    }
}
