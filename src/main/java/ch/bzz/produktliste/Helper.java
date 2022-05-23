package ch.bzz.produktliste;

import java.util.UUID;

/**
 * Hilfklasse zur Erstellung von UUIDs
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Helper {
    /*
     * erstellt eine UUID für den Hersteller, Produkt, User und Inhalt
     *
     * @return Wert der UUID
     */
    public static String createUUID() {
        return UUID.randomUUID().toString();
    }
}
