package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ein Hersteller in der Produktliste
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Producer {
    private final String producerUUID = Helper.createUUID();
    private String name;
    private int numOfFactories;
    private int numOfProduceableBottlesPerYear;
    @JsonIgnore
    private Product product;

    /*
    * default constructor
     *
    * */
    public Producer() {
    }

    /*
     * Konstruktor zur Instanzierung
     *
     * */
    public Producer(String name,
                    int numOfFactories,
                    int numOfProduceableBottlesPerYear,
                    Product product) {
        this.name = name;
        this.numOfFactories = numOfFactories;
        this.numOfProduceableBottlesPerYear = numOfProduceableBottlesPerYear;
        this.product = product;
    }

    /*
     * gettet herstellerUUID
     *
     * @return Wert der herstellerUUID
     * */
    public String getProducerUUID() {
        return producerUUID;
    }

    /*
     * gettet name
     *
     * @return Wert des namens
     * */
    public String getName() {
        return name;
    }

    /*
     * settet name
     *
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }


    /*
     * gettet anzFabriken
     *
     * @return Wert der anzFabriken
     * */
    public int getNumOfFactories() {
        return numOfFactories;
    }

    /*
     * settet anzFabriken
     *
     * @param anzFabriken
     * */
    public void setNumOfFactories(int numOfFactories) {
        this.numOfFactories = numOfFactories;
    }

    /*
     * gettet anzProduzierbareFlaschenProJahr
     *
     * @return Wert der anzProduzierbareFlaschenProJahr
     * */
    public int getNumOfProduceableBottlesPerYear() {
        return numOfProduceableBottlesPerYear;
    }

    /*
     * settet anzProduzierbareFlaschenProJahr
     *
     * @param anzProduzierbareFlaschenProJahr
     * */
    public void setNumOfProduceableBottlesPerYear(int numOfProduceableBottlesPerYear) {
        this.numOfProduceableBottlesPerYear = numOfProduceableBottlesPerYear;
    }

    /*
     * gettet Produkt
     *
     * @return Wert der Produkt
     * */
    public Product getProdukt() {
        return product;
    }

    /*
     * settet Produkt
     *
     * @param Produkt
     * */
    public void setProdukt(Product product) {
        this.product = product;
    }

    /*
     * erstellt einen Hersteller
     *
     * @return void
     * */
    public void create() {

    }

    /*
     * liest einen Hersteller
     *
     * @return void
     * */
    public void read() {

    }

    /*
     * liest alle Hersteller
     *
     * @return void
     * */
    public void list() {

    }

    /*
     * bearbeitet einen Hersteller
     *
     * @return void
     * */
    public void update() {

    }

    /*
     * löscht einen Hersteller
     *
     * @return void
     * */
    public void delete() {

    }

    /*
     * formatiert die Ausgabe eines Herstellers
     *
     * @return hersteller als String
     * */
    @Override
    public String toString() {
        return "Hersteller: " + '\n' +
                "\t\t" + producerUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + numOfFactories + '\n' +
                "\t\t" + numOfProduceableBottlesPerYear;
    }
}
