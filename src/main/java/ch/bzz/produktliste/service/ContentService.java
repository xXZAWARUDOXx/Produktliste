package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Content;
import ch.bzz.produktliste.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * Services for creating, reading, updating and deleting of contents
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
import ch.bzz.produktliste.model.Producer;
 * */
@Path("content")
public class ContentService {
    /*
    * reads a list of contents
    * @return contents as json
    * */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listContents() {
        List<Content> contentListe = DataHandler.readAllContents();
        return Response
                .status(200)
                .entity(contentListe)
                .build();
    }

    /*
    * reads a content
    * @return content as json
    * */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readContent(@QueryParam("uuid") String inhaltUUID) {
        Content content = DataHandler.readContentByUUID(inhaltUUID);
        if (content != null) {
            return Response
                    .status(200)
                    .entity(content)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }

    /*
     * creates a content
     * @param contentUUID
     * @param name
     * @param allergycode
     * @param product
     * @return Response
     * */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduct(@FormParam("contentUUID") String contentUUID,
                                  @FormParam("name") String name,
                                  @FormParam("allergycode") String allergycode,
                                  @FormParam("product") String product) {
        Content content = new Content();
        setAttributes(content,
                contentUUID,
                name,
                allergycode,
                product);

        DataHandler.insertContent(content);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /*
     * updates a content
     * @param contentUUID
     * @param name
     * @param allergycode
     * @param product
     * @return Response
     * */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProduct(@FormParam("contentUUID") String contentUUID,
                                  @FormParam("name") String name,
                                  @FormParam("allergycode") String allergycode,
                                  @FormParam("product") String product) {
        int httpStatus = 200;
        Content content = DataHandler.readContentByUUID(contentUUID);
        if (content != null) {
            setAttributes(content,
                    contentUUID,
                    name,
                    allergycode,
                    product);
            DataHandler.updateContent();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /*
     * deletes a content, searches by contentUUID
     * @param contentUUID
     * @return Response
     * */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteContent(@QueryParam("uuid") String contentUUID) {
        int status = 200;
        if (!DataHandler.deleteContent(contentUUID)) {
            status = 410;
        }
        return Response.status(status)
                .entity("")
                .build();
    }

    /*
     * sets the attributes for the content-object
     * @param content the content-object
     * @param name the name
     * @param contentUUID the contentUUID
     * @param allergycode the allergycode
     * @param product the product
     */
    private void setAttributes(
            Content content,
            String contentUUID,
            String name,
            String allergycode,
            String product
    ) {
        content.setContentUUID(contentUUID);
        content.setName(name);
        content.setAllergycode(allergycode);
        content.setProduct(product);
    }
}
