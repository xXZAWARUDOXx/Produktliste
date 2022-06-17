package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * Services for creating, reading, updating and deleting a product
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
 * */
@Path("product")
public class ProductService {
    /*
    * reads a list of products
    * @return products as json
    * */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listProducts() {
        List<Product> productListe = DataHandler.readAllProducts();
        return Response
                .status(200)
                .entity(productListe)
                .build();
    }

    /*
    * reads a product
    * @return product as json
    * */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readProduct(@QueryParam("uuid") String productUUID) {
        Product product = DataHandler.readProductByUUID(productUUID);
        if (product != null) {
            return Response
                    .status(200)
                    .entity(product)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }

    /*
     * creates a product
     * @param productUUID
     * @param name
     * @param price
     * @param date
     * @return Response
     * */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduct(@FormParam("productUUID") String productUUID,
                                  @FormParam("name") String name,
                                  @FormParam("price") BigDecimal price,
                                  @FormParam("date") String date,
                                  @FormParam("contents") List<String> contents,
                                  @FormParam("producer") String producerUUID) {
        Product product = new Product();
        setAttributes(product,
                      productUUID,
                      name,
                      price,
                      date,
                      contents,
                      producerUUID);

        DataHandler.insertProduct(product);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /*
     * updates a product
     * @param productUUID
     * @param name
     * @param price
     * @param date
     * @return Response
     * */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProduct(@FormParam("productUUID") String productUUID,
                                  @FormParam("name") String name,
                                  @FormParam("price") BigDecimal price,
                                  @FormParam("date") String date,
                                  @FormParam("contents") List<String> contents,
                                  @FormParam("producer") String producerUUID) {
        int httpStatus = 200;
        Product product = DataHandler.readProductByUUID(productUUID);
        if (product != null) {
            setAttributes(product,
                    productUUID,
                    name,
                    price,
                    date,
                    contents,
                    producerUUID);
            DataHandler.updateProduct();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /*
    * deletes a product, searches by productUUID
    * @param productUUID
    * @return Response
    * */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduct(@QueryParam("uuid") String productUUID) {
        int status = 200;
        if (!DataHandler.deleteProduct(productUUID)) {
            status = 410;
        }
        return Response.status(status)
                       .entity("")
                       .build();
    }

    /*
     * sets the attributes for the product-object
     * @param product  the product-object
     * @param name  the name
     * @param price  the price
     * @param date  the uuid of the publisher
     * @param price  the price
     * @param producer the producer
     */
    private void setAttributes(
            Product product,
            String productUUID,
            String name,
            BigDecimal price,
            String date,
            List<String> contents,
            String producer
    ) {
        product.setProductUUID(productUUID);
        product.setName(name);
        product.setPrice(price);
        product.setDate(date);
        product.setContentsUUID(contents);
        product.setProducerUUID(producer);
    }
}
