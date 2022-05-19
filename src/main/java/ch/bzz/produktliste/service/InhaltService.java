package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Inhalt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services fürs Lesen, Erstellen, Ändern und Löschen von Inhalten
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
 * */
@Path("inhalt")
public class InhaltService {
    /*
    * liest eine Liste von Inhalten
    * @return liefert Inhalt als JSON
    * */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listInhalte() {
        List<Inhalt> inhaltListe = DataHandler.getInstance().readAllInhalte();
        return Response
                .status(200)
                .entity(inhaltListe)
                .build();
    }

    /*
    * liest einen Inhalt
    * @return liefert Inhalt als JSON
    * */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readInhalt(@QueryParam("uuid") String inhaltUUID) {
        Inhalt inhalt = DataHandler.getInstance().readInhaltByUUID(inhaltUUID);
        return Response
                .status(200)
                .entity(inhalt)
                .build();
    }
}
