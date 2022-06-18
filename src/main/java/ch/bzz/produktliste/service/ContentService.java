package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Content;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response readContent(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String contentUUID) {
        Content content = DataHandler.readContentByUUID(contentUUID);
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
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduct(
                                  @Valid
                                  @BeanParam
                                  Content content,
                                  @FormParam("contentUUID")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String contentUUID,
                                  @FormParam("product")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String product) {
        content.setContentUUID(contentUUID);
        content.setProduct(product);
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
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProduct(
                                  @Valid
                                  @BeanParam
                                  Content content,
                                  @FormParam("contentUUID")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String contentUUID,
                                  @FormParam("product")
                                  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                  String product) {
        int httpStatus = 200;
        Content oldContent = DataHandler.readContentByUUID(contentUUID);
        if (oldContent != null) {
            setAttributes(oldContent,
                    contentUUID,
                    content.getName(),
                    content.getAllergycode(),
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
    public Response deleteContent(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String contentUUID) {
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
