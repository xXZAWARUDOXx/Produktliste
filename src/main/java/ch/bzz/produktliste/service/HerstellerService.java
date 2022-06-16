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
 * Services for creating, reading, updating and deleting of producers
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
 * */
@Path("hersteller")
public class HerstellerService {
    /*
    * reads a list of producers
    * @return producers as json
    * */
    @GET
    @Path("liste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listProducers() {
        List<Producer> producerListe = DataHandler.readAllProducer();
        return Response
                .status(200)
                .entity(producerListe)
                .build();
    }

    /*
     * reads a producers
     * @return producer as json
     * */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readProducer(@QueryParam("uuid") String herstellerUUID) {
        Producer producer = DataHandler.readProducerByUUID(herstellerUUID);
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
