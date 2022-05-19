package ch.bzz.produktliste.model;

import ch.bzz.produktliste.Helper;

/**
 * ein Hersteller in der Produktliste
 *
 * @author bzz: Vergili Nahro
 * @date 2022-05-19
 * @verison 1.0
 */
public class Hersteller {
    private final String herstellerUUID = Helper.createHerstellerUUID(this);
    private String name;
    private int anzFabriken;
    private int anzProduzierbareFlaschenProJahr;

    /**
    * default Konstruktor
     *
    * */
    public Hersteller() {
    }

    /**
     * Konstruktor zur Instanzierung
     *
     * */
    public Hersteller(String name,
                      int anzFabriken,
                      int anzProduzierbareFlaschenProJahr) {
        this.name = name;
        this.anzFabriken = anzFabriken;
        this.anzProduzierbareFlaschenProJahr = anzProduzierbareFlaschenProJahr;
    }

    /**
     * gettet herstellerUUID
     *
     * @return Wert der herstellerUUID
     * */
    public String getHerstellerUUID() {
        return herstellerUUID;
    }

    /**
     * gettet name
     *
     * @return Wert des namens
     * */
    public String getName() {
        return name;
    }

    /**
     * settet name
     *
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * gettet anzFabriken
     *
     * @return Wert der anzFabriken
     * */
    public int getAnzFabriken() {
        return anzFabriken;
    }

    /**
     * settet anzFabriken
     *
     * @param anzFabriken
     * */
    public void setAnzFabriken(int anzFabriken) {
        this.anzFabriken = anzFabriken;
    }

    /**
     * gettet anzProduzierbareFlaschenProJahr
     *
     * @return Wert der anzProduzierbareFlaschenProJahr
     * */
    public int getAnzProduzierbareFlaschenProJahr() {
        return anzProduzierbareFlaschenProJahr;
    }

    /**
     * settet anzProduzierbareFlaschenProJahr
     *
     * @param anzProduzierbareFlaschenProJahr
     * */
    public void setAnzProduzierbareFlaschenProJahr(int anzProduzierbareFlaschenProJahr) {
        this.anzProduzierbareFlaschenProJahr = anzProduzierbareFlaschenProJahr;
    }

    /**
     * erstellt einen Hersteller
     *
     * @return void
     * */
    public void create() {

    }

    /**
     * liest einen Hersteller
     *
     * @return void
     * */
    public void read() {

    }

    /**
     * liest alle Hersteller
     *
     * @return void
     * */
    public void list() {

    }

    /**
     * bearbeitet einen Hersteller
     *
     * @return void
     * */
    public void update() {

    }

    /**
     * löscht einen Hersteller
     *
     * @return void
     * */
    public void delete() {

    }

    /**
     * formatiert die Ausgabe eines Herstellers
     *
     * @return hersteller als String
     * */
    @Override
    public String toString() {
        return "Hersteller: " + '\n' +
                "\t\t" + herstellerUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + anzFabriken + '\n' +
                "\t\t" + anzProduzierbareFlaschenProJahr;
    }
}
