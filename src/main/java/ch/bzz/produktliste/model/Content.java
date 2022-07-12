package ch.bzz.produktliste.model;

import ch.bzz.produktliste.util.Helper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * a Content as the part of the Productlist
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Content {
    private String contentUUID = Helper.createUUID();
    @FormParam("name")
    @NotEmpty
    @Size(min = 4, max = 40)
    private String name;
    @FormParam("allergycode")
    @NotEmpty
    @Size(min = 1, max = 1)
    private String allergycode;
    @FormParam("product")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String product;

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
                   String product) {
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
     * sets contentUUID
     * @param contentUUID
     */
    public void setContentUUID(String contentUUID) {
        this.contentUUID = contentUUID;
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
    public String getProduct() {
        return product;
    }

    /*
     * sets product
     * @param product
     */
    public void setProduct(String product) {
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
