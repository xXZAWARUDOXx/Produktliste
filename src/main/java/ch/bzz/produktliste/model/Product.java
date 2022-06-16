package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;
import ch.bzz.produktliste.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * a product in the productList
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
     * default constructor
     */
    public Product() {
    }

    /*
     * constructor for instantiation
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
     * gets productUUID
     * @return value of the productUUID
     */
    public String getProductUUID() {
        return productUUID;
    }

    /*
     * gets name
     * @return value of the name
     */
    public String getName() {
        return name;
    }

    /*
     * sets name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * gets price
     * @return value of the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /*
     * sets price
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /*
     * gets date
     * @return value of the date
     */
    public Date getDate() {
        return date;
    }

    /*
     * sets date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /*
     * gets contentList
     * @return value of the contentList
     */
    public List getContents() {
        return contents;
    }

    /*
     * sets contentList
     * @param contentList
     */
    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    /*
     * gets producerList
     * @return value of the producerList
     */
    public Producer getProducer() {
        return producer;
    }

    /*
     * sets producerList
     * @param producerList
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /*
     * sets producer
     * @param producer
     */
    @JsonProperty("producer")
    public void setProducerUUID(String producerUUID) {
        setProducer(DataHandler.readProducerByUUID(producerUUID));
    }

    /*
     * sets contents
     * @param contents
     */
    @JsonProperty("content")
    public void setContentsUUID(List<String> contents) {
        setContents(new ArrayList<>());
        for (String s : contents) {
            this.contents.add(DataHandler.readContentByUUID(s));
        }
    }

    /*
     * printing the products
     * @return product as string
     * */
    @Override
    public String toString() {
        return "product: " + '\n' +
                "\t\t" + productUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + price + '\n' +
                "\t\t" + date + '\n' +
                "\t\t" + contents + '\n' +
                "\t\t" + producer;
    }
}
