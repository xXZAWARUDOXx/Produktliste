package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * ein Produkt in der Produktliste
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Produkt {
    private final String produktUUID = Helper.createProduktUUID(this);
    private String name;
    private BigDecimal preis;
    private LocalDate datum;
    private List<Inhalt> inhaltListe;
    private List<Hersteller> herstellerListe;

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
                   LocalDate datum,
                   List<Inhalt> inhaltListe,
                   List<Hersteller> herstellerListe) {
        this.name = name;
        this.preis = preis;
        this.datum = datum;
        this.inhaltListe = inhaltListe;
        this.herstellerListe = herstellerListe;
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
    public LocalDate getDatum() {
        return datum;
    }

    /*
     * settet datum
     *
     * @param datum
     */
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /*
     * gettet inhaltListe
     *
     * @return die inhaltListe
     */
    public List<Inhalt> getInhaltListe() {
        return inhaltListe;
    }

    /*
     * settet inhaltListe
     *
     * @param inhaltListe
     */
    public void setInhaltListe(List<Inhalt> inhaltListe) {
        this.inhaltListe = inhaltListe;
    }

    /*
     * gettet herstellerListe
     *
     * @return die herstellerListe
     */
    public List<Hersteller> getHerstellerListe() {
        return herstellerListe;
    }

    /*
     * settet herstellerListe
     *
     * @param herstellerListe
     */
    public void setHerstellerListe(List<Hersteller> herstellerListe) {
        this.herstellerListe = herstellerListe;
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
                "\t\t" + inhaltListe + '\n' +
                "\t\t" + herstellerListe;
    }
}
