package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Produkt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
        return Response
                .status(200)
                .entity(produkt)
                .build();
    }
}
