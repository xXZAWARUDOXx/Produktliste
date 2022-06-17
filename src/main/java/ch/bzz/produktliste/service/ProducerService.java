package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Producer;
import ch.bzz.produktliste.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 * Services for creating, reading, updating and deleting of producers
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @version 1.0
 * */
@Path("producer")
public class ProducerService {
    /*
    * reads a list of producers
    * @return producers as json
    * */
    @GET
    @Path("list")
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

    /*
     * creates a product
     * @param productUUID
     * @param name
     * @param price
     * @param date
     * @return Response
     * */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduct(@FormParam("producerUUID") String producerUUID,
                                  @FormParam("name") String name,
                                  @FormParam("numOfFactories") int numOfFactories,
                                  @FormParam("numOfProduceableBottlesPerYear") int numOfProduceableBottlesPerYear,
                                  @FormParam("product") String product) {
        Producer producer = new Producer();
        setAttributes(producer,
                      producerUUID,
                      name,
                      numOfFactories,
                      numOfProduceableBottlesPerYear,
                      product);

        DataHandler.insertProducer(producer);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /*
     * deletes a producer, searches by producerUUID
     * @param producerUUID
     * @return Response
     * */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProducer(@QueryParam("uuid") String producerUUID) {
        int status = 200;
        if (!DataHandler.deleteProducer(producerUUID)) {
            status = 410;
        }
        return Response.status(status)
                .entity("")
                .build();
    }

    /*
     * sets the attributes for the producer-object
     * @param producer  the producer-object
     * @param name  the name
     * @param price  the price
     * @param date  the uuid of the publisher
     * @param price  the price
     * @param producer the producer
     */
    private void setAttributes(
            Producer producer,
            String producerUUID,
            String name,
            int numOfFactories,
            int numOfProduceableBottlesPerYear,
            String product
    ) {
        producer.setProducerUUID(producerUUID);
        producer.setName(name);
        producer.setNumOfFactories(numOfFactories);
        producer.setNumOfProduceableBottlesPerYear(numOfProduceableBottlesPerYear);
        producer.setProduct(product);
    }
}
