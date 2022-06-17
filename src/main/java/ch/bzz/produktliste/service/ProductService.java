package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services fürs Lesen, Erstellen, Ändern und Löschen von Produkten
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
}
