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
public class Produkt {
    private final String produktUUID = Helper.createUUID();
    private String name;
    private BigDecimal preis;
    private Date datum;
    private List<Inhalt> inhalt;
    private Hersteller hersteller;

    /*
     * default Konstruktor
     *
     */
    public Produkt() {
    }

    /*
     * Konstruktor zur Instanzierung
     *
     */
    public Produkt(String name,
                   BigDecimal preis,
                   Date datum,
                   List inhalt,
                   Hersteller hersteller) {
        this.name = name;
        this.preis = preis;
        this.datum = datum;
        this.inhalt = inhalt;
        this.hersteller = hersteller;
    }

    /*
     * gettet produktUUID
     *
     * @return Wert der produktUUID
     */
    public String getProduktUUID() {
        return produktUUID;
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
    public BigDecimal getPreis() {
        return preis;
    }

    /*
     * settet preis
     *
     * @param preis
     */
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    /*
     * gettet datum
     *
     * @return Wert des datums
     */
    public Date getDatum() {
        return datum;
    }

    /*
     * settet datum
     *
     * @param datum
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /*
     * gettet inhaltListe
     *
     * @return die inhaltListe
     */
    public List getInhalt() {
        return inhalt;
    }

    /*
     * settet inhaltListe
     *
     * @param inhaltListe
     */
    public void setInhalt(List inhalt) {
        this.inhalt = inhalt;
    }

    /*
     * gettet herstellerListe
     *
     * @return die herstellerListe
     */
    public Hersteller getHersteller() {
        return hersteller;
    }

    /*
     * settet herstellerListe
     *
     * @param herstellerListe
     */
    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
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
        setHersteller(DataHandler.getInstance().readHerstellerByUUID(herstellerUUID));
    }

    /*
     * sets genre
     *
     * @param inhalteUUID Wert der UUID
     */
    @JsonProperty("inhalt")
    public void setInhalteByUUID(List<String> inhalteUUID) {
        setInhalt(new ArrayList<>());
        for (String s : inhalteUUID) {
            this.inhalt.add(DataHandler.getInstance().readInhaltByUUID(s));
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
                "\t\t" + produktUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + preis + '\n' +
                "\t\t" + datum + '\n' +
                "\t\t" + inhalt + '\n' +
                "\t\t" + hersteller;
    }
}
