package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;
import ch.bzz.produktliste.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ein Produkt in der Produktliste
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Product {
    private final String productUUID = Helper.createUUID();
    private String name;
    private BigDecimal price;
    private Date date;
    private List<Content> contents;
    private Producer producer;

    /*
     * default Konstruktor
     *
     */
    public Product() {
    }

    /*
     * Konstruktor zur Instanzierung
     *
     */
    public Product(String name,
                   BigDecimal price,
                   Date date,
                   List contents,
                   Producer producer) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.contents = contents;
        this.producer = producer;
    }

    /*
     * gettet produktUUID
     *
     * @return Wert der produktUUID
     */
    public String getProductUUID() {
        return productUUID;
    }

    /*
     * gettet name
     *
     * @return Wert des namens
     */
    public String getName() {
        return name;
    }

    /*
     * settet name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * gettet preis
     *
     * @return Wert des preises
     */
    public BigDecimal getPrice() {
        return price;
    }

    /*
     * settet preis
     *
     * @param preis
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /*
     * gettet datum
     *
     * @return Wert des datums
     */
    public Date getDate() {
        return date;
    }

    /*
     * settet datum
     *
     * @param datum
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /*
     * gettet inhaltListe
     *
     * @return die inhaltListe
     */
    public List getContents() {
        return contents;
    }

    /*
     * settet inhaltListe
     *
     * @param inhaltListe
     */
    public void setContents(List contents) {
        this.contents = contents;
    }

    /*
     * gettet herstellerListe
     *
     * @return die herstellerListe
     */
    public Producer getHersteller() {
        return producer;
    }

    /*
     * settet herstellerListe
     *
     * @param herstellerListe
     */
    public void setHersteller(Producer producer) {
        this.producer = producer;
    }

    // in Bearbeitung
    /*@JsonProperty("hersteller")
    public String getHerstellerUUID() {
        if (hersteller != null) {
            return  hersteller.getHerstellerUUID();
        } else {
            return null;
        }
    }*/

    /*
     * sets genre
     *
     * @param herstellerUUID Wert der UUID
     */
    @JsonProperty("hersteller")
    public void setHerstellerUUID(String herstellerUUID) {
        setHersteller(DataHandler.readHerstellerByUUID(herstellerUUID));
    }

    /*
     * sets genre
     *
     * @param inhalteUUID Wert der UUID
     */
    @JsonProperty("content")
    public void setInhalteByUUID(List<String> inhalteUUID) {
        setContents(new ArrayList<>());
        for (String s : inhalteUUID) {
            this.contents.add(DataHandler.readInhaltByUUID(s));
        }
    }

    /*
     * erstellt ein Produkt
     *
     * @return void
     * */
    public void create() {

    }

    /*
     * liest ein Produkt
     *
     * @return void
     * */
    public void read() {

    }

    /*
     * liest alle Produkte
     *
     * @return void
     * */
    public void list() {

    }

    /*
     * bearbeitet ein Produkt
     *
     * @return void
     * */
    public void update() {

    }

    /*
     * löscht ein Produkt
     *
     * @return void
     * */
    public void delete() {

    }

    /*
     * formatiert die Ausgabe eines Produktes
     *
     * @return produkt als String
     * */
    @Override
    public String toString() {
        return "Produkt: " + '\n' +
                "\t\t" + productUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + price + '\n' +
                "\t\t" + date + '\n' +
                "\t\t" + contents + '\n' +
                "\t\t" + producer;
    }
}
