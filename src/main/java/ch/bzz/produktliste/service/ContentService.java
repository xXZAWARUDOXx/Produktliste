package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Content;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services for creating, reading, updating and deleting of contents
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
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
}
