package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Producer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services fürs Lesen, Erstellen, Ändern und Löschen von Herstellern
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
 * */
@Path("hersteller")
public class HerstellerService {
    /*
    * liest eine Liste von Herstellern
    * @return liefert Hersteller als JSON
    * */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHersteller() {
        List<Producer> producerListe = DataHandler.readAllHersteller();
        return Response
                .status(200)
                .entity(producerListe)
                .build();
    }

    /*
     * liest einen Hersteller
     * @return liefert Hersteller als JSON
     * */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHersteller (@QueryParam("uuid") String herstellerUUID) {
        Producer producer = DataHandler.readHerstellerByUUID(herstellerUUID);
        if (producer != null) {
            return Response
                    .status(200)
                    .entity(producer)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }


}
