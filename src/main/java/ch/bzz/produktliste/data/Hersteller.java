package ch.bzz.produktliste.data;

import ch.bzz.produktliste.Helper;

public class Hersteller {
    private final String herstellerUUID = Helper.createHerstellerUUID(this);
    private String name;
    private int anzFabriken;
    private int anzProduzierbareFlaschenProJahr;

    public Hersteller() {
    }

    public Hersteller(String name, int anzFabriken, int anzProduzierbareFlaschenProJahr) {
        this.name = name;
        this.anzFabriken = anzFabriken;
        this.anzProduzierbareFlaschenProJahr = anzProduzierbareFlaschenProJahr;
    }

    public String getHerstellerUUID() {
        return herstellerUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzFabriken() {
        return anzFabriken;
    }

    public void setAnzFabriken(int anzFabriken) {
        this.anzFabriken = anzFabriken;
    }

    public int getAnzProduzierbareFlaschenProJahr() {
        return anzProduzierbareFlaschenProJahr;
    }

    public void setAnzProduzierbareFlaschenProJahr(int anzProduzierbareFlaschenProJahr) {
        this.anzProduzierbareFlaschenProJahr = anzProduzierbareFlaschenProJahr;
    }

    public void create() {

    }

    public void read() {

    }

    public void list() {

    }

    public void update() {

    }

    public void delete() {

    }

    @Override
    public String toString() {
        return "Hersteller: " + '\n' +
                "\t\t" + herstellerUUID + '\n' +
                "\t\t" + name + '\n' +
                "\t\t" + anzFabriken + '\n' +
                "\t\t" + anzProduzierbareFlaschenProJahr;
    }
}
