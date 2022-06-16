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
    * */
    public Producer() {
    }

    /*
     * constructor for instantiation
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
     * gets producerUUID
     * @return value of the producerUUID
     * */
    public String getProducerUUID() {
        return producerUUID;
    }

    /*
     * gets name
     * @return value of the name
     * */
    public String getName() {
        return name;
    }

    /*
     * sets name
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }


    /*
     * gets numOfFactories
     * @return value of the numOfFactories
     * */
    public int getNumOfFactories() {
        return numOfFactories;
    }

    /*
     * sets numOfFactories
     * @param numOfFactories
     * */
    public void setNumOfFactories(int numOfFactories) {
        this.numOfFactories = numOfFactories;
    }

    /*
     * gets numOfProduceableBottlesPerYear
     * @return value of the numOfProduceableBottlesPerYear
     * */
    public int getNumOfProduceableBottlesPerYear() {
        return numOfProduceableBottlesPerYear;
    }

    /*
     * sets numOfProduceableBottlesPerYear
     * @param numOfProduceableBottlesPerYear
     * */
    public void setNumOfProduceableBottlesPerYear(int numOfProduceableBottlesPerYear) {
        this.numOfProduceableBottlesPerYear = numOfProduceableBottlesPerYear;
    }

    /*
     * gets product
     * @return value of the product
     * */
    public Product getProdukt() {
        return product;
    }

    /*
     * sets product
     * @param product
     * */
    public void setProdukt(Product product) {
        this.product = product;
    }

    /*
     * prints the producer
     * @return producer as string
     * */
    @Override
    public String toString() {
        return "producer: " + '\n' +
                "\t\t" + producerUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + numOfFactories + '\n' +
                "\t\t" + numOfProduceableBottlesPerYear;
    }
}
