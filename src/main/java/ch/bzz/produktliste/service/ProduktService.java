package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Produkt;

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
@Path("produkt")
public class ProduktService {
    /*
    * liest eine Liste von Produkten
    * @return liefert Produkte als JSON
    * */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listProdukte() {
        List<Produkt> produktListe = DataHandler.getInstance().readAllProdukte();
        return Response
                .status(200)
                .entity(produktListe)
                .build();
    }

    /*
    * liest ein Produkt
    * @return liefert Produkt als JSON
    * */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readProdukte(@QueryParam("uuid") String produktUUID) {
        Produkt produkt = DataHandler.getInstance().readProduktByUUID(produktUUID);
        if (produkt != null) {
            return Response
                    .status(200)
                    .entity(produkt)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }

    /*
    * loescht ein Produkt identifiziert durch eine UUID
    * @param produktUUID
    * @return Response
    * */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProdukt(@QueryParam("uuid") String produktUUID) {
        int status = 200;
        if (!DataHandler.getInstance().produktLoeschen(produktUUID)) {
            status = 410;
        }
        return Response.status(status)
                       .entity("")
                       .build();
    }
}
