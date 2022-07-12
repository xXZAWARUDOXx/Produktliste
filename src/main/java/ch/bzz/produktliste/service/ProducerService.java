package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.DataHandler;
import ch.bzz.produktliste.model.Producer;
import ch.bzz.produktliste.util.AES256;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Services for creating, reading, updating and deleting of producers
 *
 * @author bzz: Vergili Nahro
 * @version 1.0
 * @date 2022-05-19
 */
@Path("producer")
public class ProducerService {
    /*
     * reads a list of producers
     * @return producers as json
     * */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listProducers(
            @CookieParam("userRole") String userRole) {
        List<Producer> producerListe = null;
        int httpStatus = 200;
        if (userRole == null || AES256.decrypt(userRole).equals("guest")) {
            httpStatus = 403;
        } else {
            producerListe = DataHandler.readAllProducer();
        }
        return Response
                .status(httpStatus)
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
    public Response readProducer(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid")
                    String herstellerUUID,
            @CookieParam("userRole") String userRole) {
        int httpStatus = 200;
        Producer producer = DataHandler.readProducerByUUID(herstellerUUID);
        if (userRole == null || AES256.decrypt(userRole).equals("guest")) {
            httpStatus = 403;
            producer = null;
        } else if (producer == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(producer)
                .build();
    }

    /*
     * creates a product
     * @param productUUID
     * @param name
     * @param price
     * @param date
     * @return Response
     * */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProduct(
            @Valid
            @BeanParam
                    Producer producer,
            @FormParam("producerUUID")
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                    String producerUUID,
            @FormParam("product")
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                    String product,
            @CookieParam("userRole") String userRole) {
        int httpStatus = 200;
        if (userRole == null || !AES256.decrypt(userRole).equals("admin")) {
            httpStatus = 403;
        } else {
            producer.setProducerUUID(producerUUID);
            producer.setProduct(product);
            DataHandler.insertProducer(producer);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /*
     * creates a producer
     * @param productUUID
     * @param name
     * @param price
     * @param date
     * @return Response
     * */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProducer(
            @Valid
            @BeanParam
                    Producer producer,
            @FormParam("producerUUID")
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                    String producerUUID,
            @FormParam("product")
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                    String product,
            @CookieParam("userRole") String userRole) {
        int httpStatus = 200;
        Producer oldProducer = DataHandler.readProducerByUUID(producerUUID);
        if (userRole == null || !AES256.decrypt(userRole).equals("admin")) {
            httpStatus = 403;
        } else if (oldProducer != null) {
            setAttributes(oldProducer,
                    producerUUID,
                    producer.getName(),
                    producer.getNumOfFactories(),
                    producer.getNumOfProduceableBottlesPerYear(),
                    product);
            DataHandler.updateProducer();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
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
    public Response deleteProducer(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String producerUUID,
            @CookieParam("userRole") String userRole) {
        int httpStatus = 200;
        if (userRole == null || !AES256.decrypt(userRole).equals("admin")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteProducer(producerUUID)) {
                httpStatus = 410;
            }
        }
        return Response.status(httpStatus)
                .entity("")
                .build();
    }

    /*
     * sets the attributes for the producer-object
     * @param producer the producer-object
     * @param name the name
     * @param numOfFactories  the numOfFactories
     * @param numOfProduceableBottlesPerYear the numOfProduceableBottlesPerYear
     * @param producerUUID the producerUUID
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
