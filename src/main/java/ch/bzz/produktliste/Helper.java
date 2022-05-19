package ch.bzz.produktliste;

import ch.bzz.produktliste.model.Hersteller;
import ch.bzz.produktliste.model.Inhalt;
import ch.bzz.produktliste.model.Produkt;
import ch.bzz.produktliste.model.User;

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
     * erstellt eine UUID für den Hersteller
     *
     * @return Wert der herstellerUUID
     */
    public static String createHerstellerUUID(Hersteller hersteller) {
        return UUID.randomUUID().toString();
    }

    /*
     * erstellt eine UUID für den User
     *
     * @return Wert der userUUID
     */
    public static String createUserUUID(User user) {
        return UUID.randomUUID().toString();
    }

    /*
     * erstellt eine UUID für den Produkt
     *
     * @return Wert der produktUUID
     */
    public static String createProduktUUID(Produkt produkt) {
        return UUID.randomUUID().toString();
    }

    /*
     * erstellt eine UUID für den Inhalt
     *
     * @return Wert der inhaltUUID
     */
    public static String createInhaltUUID(Inhalt inhalt) {
        return UUID.randomUUID().toString();
    }
}
