package ch.bzz.produktliste.data;

import ch.bzz.produktliste.model.Content;
import ch.bzz.produktliste.model.Producer;
import ch.bzz.produktliste.model.Product;
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
public final class DataHandler {
    private static List<Producer> producerListe;
    private static List<Content> contentListe;
    private static List<Product> productList;

    /*
     * private constructor for instances
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
    * initializes the lists
    * */
    public static void initListen() {
        DataHandler.setHerstellerList(null);
        DataHandler.setInhaltList(null);
        DataHandler.setProduktList(null);
    }

    /*
     * reads all Producer
     * @return list of the producers
     */
    public static List<Producer> readAllHersteller() {
        return getHerstellerList();
    }

    /*
     * reads a producer, searches by producerUUID
     * @param herstellerUUID
     * @return den Hersteller (null=not found)
     */
    public static Producer readHerstellerByUUID(String herstellerUUID) {
        Producer producer = null;
        for (Producer h : getHerstellerList()) {
            if (h.getProducerUUID().equals(herstellerUUID)) {
                producer = h;
            }
        }
        return producer;
    }

    /*
     * erstellt einen Inhalt
     *
     */
    public static void insertInhalt(Content content) {
        getInhaltList().add(content);
        schreibInhaltJSON();
    }

    /*
     * erstellt einen Hersteller
     *
     */
    public static void insertHersteller(Producer producer) {
        getHerstellerList().add(producer);
        schreibHerstellerJSON();
    }

    /*
     * erstellt ein Produkt
     *
     */
    public static void insertProdukt(Product product) {
        getProduktList().add(product);
        schreibInhaltJSON();
    }

    /*
     * liest alle Inhalte
     * @return Liste der Inhalte
     */
    public static List<Content> readAllInhalte() {
        return getInhaltList();
    }

    /*
     * liest einen Inhalt nach seiner UUID
     * @param inhaltUUID
     * @return den Inhalt (null=not found)
     */
    public static Content readInhaltByUUID(String inhaltUUID) {
        Content content = null;
        for (Content i : getInhaltList()) {
            if (i.getContentUUID().equals(inhaltUUID)) {
                content = i;
            }
        }
        return content;
    }

    /*
     * liest alle Produkte
     * @return Liste der Inhalte
     */
    public static List<Product> readAllProdukte() {
        return getProduktList();
    }

    /*
     * liest ein Produkt nach seiner UUID
     * @param produktUUID
     * @return das Produkt (null=not found)
     */
    public static Product readProduktByUUID(String produktUUID) {
        Product product = null;
        for (Product p : getProduktList()) {
            if (p.getProductUUID().equals(produktUUID)) {
                product = p;
            }
        }
        return product;
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
            Producer[] producer = objectMapper.readValue(jsonData, Producer[].class);
            for (Producer h : producer) {
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
            Content[] inhalte = objectMapper.readValue(jsonData, Content[].class);
            for (Content content : inhalte) {
                getInhaltList().add(content);
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
            Product[] produkte = objectMapper.readValue(jsonData, Product[].class);
            for (Product product : produkte) {
                getProduktList().add(product);
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
     * schreibt eine Produktliste ins JSON-File
     */
    private static void schreibInhaltJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String inhaltPath = Config.getProperty("inhaltJSON");
        try {
            fileOutputStream = new FileOutputStream(inhaltPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInhaltList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * schreibt einen Inhalt ins JSON-File
     */
    private static void schreibHerstellerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String herstellerPath = Config.getProperty("herstellerJSON");
        try {
            fileOutputStream = new FileOutputStream(herstellerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getHerstellerList());
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
        Product product = new Product();
        if (product != null) {
            getProduktList().remove(product);
            schreibProduktJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * löscht einen Inhalt welcher über die UUID identifiziert wird
     * @param herstellerUUID
     * @return erfolg=true/false
     * */
    public static boolean inhaltLoeschen(String inhaltUUID) {
        Content content = new Content();
        if (content != null) {
            getProduktList().remove(content);
            schreibInhaltJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * löscht einen Hersteller welcher über die UUID identifiziert wird
     * @param herstellerUUID
     * @return erfolg=true/false
     * */
    public static boolean herstellerLoeschen(String herstellerUUID) {
        Producer producer = new Producer();
        if (producer != null) {
            getProduktList().remove(producer);
            schreibHerstellerJSON();
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
    private static List<Producer> getHerstellerList() {
        if (producerListe == null) {
            setHerstellerList(new ArrayList<>());
            readHerstellerJSON();
        }
        return producerListe;
    }

    /*
     * settet herstellerListe
     *
     * @param herstellerListe den Wert den man setzen will
     */
    private static void setHerstellerList(List<Producer> producerListe) {
        DataHandler.producerListe = producerListe;
    }

    /*
     * gettet inhaltListe
     *
     * @return Wert der inhaltListet
     */
    private static List<Content> getInhaltList() {
        if (contentListe == null) {
            setInhaltList(new ArrayList<>());
            readInhaltJSON();
        }
        return contentListe;
    }

    /*
     * settet inhaltListe
     *
     * @param inhaltListe der Wert den man setzen will
     */
    private static void setInhaltList(List<Content> contentListe) {
        DataHandler.contentListe = contentListe;
    }

    /*
     * gettet produktListe
     *
     * @return Wert der produktListe
     */
    public static List<Product> getProduktList() {
        if (productList == null) {
            setProduktList(new ArrayList<>());
            readProduktJSON();
        }
        return productList;
    }

    /*
     * settet produktListe
     *
     * @param produktListe der Wert den man setzen will
     */
    public static void setProduktList(List<Product> productList) {
        DataHandler.productList = productList;
    }
}
