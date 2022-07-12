package ch.bzz.produktliste.util;

import java.util.UUID;

/**
 * Helper-Class for creating valid UUIDs
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Helper {
    /*
     * creates a valid UUID for the Producer, Product and Content
     * @return value of the UUID
     */
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }
}
