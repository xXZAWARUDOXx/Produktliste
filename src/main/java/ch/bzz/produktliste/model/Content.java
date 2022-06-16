package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * a Content as the part of the Productlist
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Content {
    private final String contentUUID = Helper.createUUID();
    private String name;
    private String allergycode;
    @JsonIgnore
    private Product product;

    /*
     * default constructor
     */
    public Content() {
    }

    /*
     * constructor for instances
     */
    public Content(String name,
                   String allergycode,
                   Product product) {
        this.name = name;
        this.allergycode = allergycode;
        this.product = product;
    }

    /*
     * gets the contentUUID
     * @return value of the contentUUID
     */
    public String getContentUUID() {
        return contentUUID;
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
     * gets allergycode
     * @return value of the allergycode
     */
    public String getAllergycode() {
        return allergycode;
    }

    /*
     * sets allergycode
     * @param allergycode
     */
    public void setAllergycode(String allergycode) {
        this.allergycode = allergycode;
    }

    /*
     * gets product
     * @return value of the product
     */
    public Product getProduct() {
        return product;
    }

    /*
     * sets product
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /*
     * printing content
     * @return content as string
     * */
    @Override
    public String toString() {
        return "content: " + '\n' +
                "\t\t" + contentUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + allergycode + '\n' +
                "\t\t" + product;
    }
}
