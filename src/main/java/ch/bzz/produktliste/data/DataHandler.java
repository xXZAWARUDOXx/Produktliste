package ch.bzz.produktliste.data;

import ch.bzz.produktliste.model.Content;
import ch.bzz.produktliste.model.Producer;
import ch.bzz.produktliste.model.Product;
import ch.bzz.produktliste.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 *
 * @author bzz: Vergili Nahro
 * @version 1.0
 * @date 2022-05-19
 */
public final class DataHandler {
    private static List<Producer> producerList;
    private static List<Content> contentList;
    private static List<Product> productList;

    /*
     * initializes the lists
     * */
    public static void initListen() {
        DataHandler.setProducerList(null);
        DataHandler.setContentList(null);
        DataHandler.setProductList(null);
    }

    /*
     * reads all Producer
     * @return list of the producers
     */
    public static List<Producer> readAllProducer() {
        return getProducerList();
    }

    /*
     * reads a producer, searches by producerUUID
     * @param producerUUID
     * @return the producer (null=not found)
     */
    public static Producer readProducerByUUID(String producerUUID) {
        for (Producer producer : getProducerList()) {
            if (producer.getProducerUUID().equals(producerUUID)) {
                return producer;
            }
        }
        return null;
    }

    /*
     * creates a content
     */
    public static void insertContent(Content content) {
        getContentList().add(content);
        writeContentJSON();
    }

    /*
     * creates a producer
     */
    public static void insertProducer(Producer producer) {
        getProducerList().add(producer);
        writeProducerJSON();
    }

    /*
     * creates a product
     */
    public static void insertProduct(Product product) {
        getProductList().add(product);
        writeProductJSON();
    }

    /*
     * updates a product
     */
    public static void updateProduct() {
        writeProductJSON();
    }

    /*
     * updates a content
     */
    public static void updateContent() {
        writeContentJSON();
    }

    /*
     * updates a producer
     */
    public static void updateProducer() {
        writeProducerJSON();
    }

    /*
     * reads all contents
     * @return list of all contents
     */
    public static List<Content> readAllContents() {
        return getContentList();
    }

    /*
     * reads a content, searches by contentUUID
     * @param contentUUID
     * @return the content (null=not found)
     */
    public static Content readContentByUUID(String contentUUID) {
        for (Content content : getContentList()) {
            if (content.getContentUUID().equals(contentUUID)) {
                return content;
            }
        }
        return null;
    }

    /*
     * reads all products
     * @return list of the contents
     */
    public static List<Product> readAllProducts() {
        return getProductList();
    }

    /*
     * reads a product, searches by productUUID
     * @param productUUID
     * @return the product (null=not found)
     */
    public static Product readProductByUUID(String productUUID) {
        Product product = null;
        for (Product p : getProductList()) {
            if (p.getProductUUID().equals(productUUID)) {
                product = p;
            }
        }
        return product;
    }

    /*
     * reads the producer out of the JSON-File
     */
    private static void readProducerJSON() {
        try {
            String path = Config.getProperty("producerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Producer[] producer = objectMapper.readValue(jsonData, Producer[].class);
            for (Producer h : producer) {
                getProducerList().add(h);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * reads the content out of the JSON-File
     */
    private static void readContentJSON() {
        try {
            String path = Config.getProperty("contentJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Content[] contents = objectMapper.readValue(jsonData, Content[].class);
            for (Content content : contents) {
                getContentList().add(content);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * reads the product out of the JSON-File
     */
    private static void readProductJSON() {
        try {
            String path = Config.getProperty("productJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Product[] products = objectMapper.readValue(jsonData, Product[].class);
            for (Product product : products) {
                getProductList().add(product);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * writes a productList into the JSON-File
     */
    private static void writeProductJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String productJSON = Config.getProperty("productJSON");
        try {
            fileOutputStream = new FileOutputStream(productJSON);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getProductList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * writes a contentList into the JSON-File
     */
    private static void writeContentJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String contentJSON = Config.getProperty("contentJSON");
        try {
            fileOutputStream = new FileOutputStream(contentJSON);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getContentList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * writes a producerList into the JSON-File
     */
    private static void writeProducerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String producerJSON = Config.getProperty("producerJSON");
        try {
            fileOutputStream = new FileOutputStream(producerJSON);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getProducerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * deletes a product, searches by productUUID
     * @param productUUID
     * @return success=true/false
     * */
    public static boolean deleteProduct(String productUUID) {
        Product product = readProductByUUID(productUUID);
        if (product != null) {
            getProductList().remove(product);
            writeProductJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * deletes a content, searches by contentUUID
     * @param contentUUID
     * @return success=true/false
     * */
    public static boolean deleteContent(String contentUUID) {
        Content content = readContentByUUID(contentUUID);
        if (content != null) {
            getContentList().remove(content);
            writeContentJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * deletes a producer, searches by producerUUID
     * @param producerUUID
     * @return success=true/false
     * */
    public static boolean deleteProducer(String producerUUID) {
        Producer producer = readProducerByUUID(producerUUID);
        if (producer != null) {
            getProducerList().remove(producer);
            writeProducerJSON();
            return true;
        } else {
            return false;
        }
    }

    /*
     * gets producerList
     * @return value of the producerList
     */
    private static List<Producer> getProducerList() {
        if (producerList == null) {
            setProducerList(new ArrayList<>());
            readProducerJSON();
        }
        return producerList;
    }

    /*
     * sets producerList
     * @param producerList
     */
    private static void setProducerList(List<Producer> producerList) {
        DataHandler.producerList = producerList;
    }

    /*
     * gets contentList
     * @return value of the contentList
     */
    private static List<Content> getContentList() {
        if (contentList == null) {
            setContentList(new ArrayList<>());
            readContentJSON();
        }
        return contentList;
    }

    /*
     * sets contentList
     * @param contentList
     */
    private static void setContentList(List<Content> contentList) {
        DataHandler.contentList = contentList;
    }

    /*
     * gets productList
     * @return value of the productList
     */
    public static List<Product> getProductList() {
        if (productList == null) {
            setProductList(new ArrayList<>());
            readProductJSON();
        }
        return productList;
    }

    /*
     * sets productList
     * @param productList
     */
    public static void setProductList(List<Product> productList) {
        DataHandler.productList = productList;
    }
}
