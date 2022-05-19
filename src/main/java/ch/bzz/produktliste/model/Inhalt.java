package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;

/**
 * ein Inhalt in der Produktliste
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Inhalt {
    private final String inhaltUUID = Helper.createInhaltUUID(this);
    private String name;
    private String allergiecode;

    /*
     * default Konstruktor
     *
     */
    public Inhalt() {
    }

    /*
     * Konstruktor zur Instanzierung
     *
     */
    public Inhalt(String name,
                  String allergiecode) {
        this.name = name;
        this.allergiecode = allergiecode;
    }

    /*
     * gettet inhaltUUID
     *
     * @return Wert der inhaltUUID
     */
    public String getInhaltUUID() {
        return inhaltUUID;
    }

    /*
     * gettet name
     *
     * @return Wert des namens
     */
    public String getName() {
        return name;
    }

    /*
     * settet name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * gettet allerigecode
     *
     * @return Wert des allergiecodes
     */
    public String getAllergiecode() {
        return allergiecode;
    }

    /*
     * settet allerigecode
     *
     * @param allergiecode
     */
    public void setAllergiecode(String allergiecode) {
        this.allergiecode = allergiecode;
    }

    /*
     * erstellt einen Inhalt
     *
     * @return void
     * */
    public void create() {

    }

    /*
     * liest einen Inhalt
     *
     * @return void
     * */
    public void read() {

    }

    /*
     * liest alle Inhalte
     *
     * @return void
     * */
    public void list() {

    }

    /*
     * bearbeitet einen Inhalt
     *
     * @return void
     * */
    public void update() {

    }

    /*
     * löscht einen Inhalt
     *
     * @return void
     * */
    public void delete() {

    }

    /*
     * formatiert einen Inhalt zur Ausgabe
     *
     * @return inhalt als String
     * */
    @Override
    public String toString() {
        return "Inhalt: " + '\n' +
                "\t\t" + inhaltUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + allergiecode;
    }
}
