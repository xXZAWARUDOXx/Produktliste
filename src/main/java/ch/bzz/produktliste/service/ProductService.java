package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Content;
import ch.bzz.produktliste.model.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    public Response readProduct(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String productUUID) {
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
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduct(
                                  @Valid
                                  @BeanParam
                                  Product product,
                                  @FormParam("productUUID")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String productUUID,
                                  @FormParam("producer")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String producerUUID) {
        product.setProductUUID(productUUID);
        product.setProducerUUID(producerUUID);
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
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProduct(
                                  @Valid
                                  @BeanParam
                                  Product product,
                                  @FormParam("productUUID")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String productUUID,
                                  @FormParam("producer")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String producerUUID) {
        int httpStatus = 200;
        Product oldProduct = DataHandler.readProductByUUID(productUUID);
        if (oldProduct != null) {
            setAttributes(oldProduct,
                    productUUID,
                    product.getName(),
                    product.getPrice(),
                    product.getDate(),
                    product.getContents(),
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
    public Response deleteProduct(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String productUUID) {
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
            List<Content> contents,
            String producer
    ) {
        product.setProductUUID(productUUID);
        product.setName(name);
        product.setPrice(price);
        product.setDate(date);
        product.setContents(contents);
        product.setProducerUUID(producer);
    }
}
