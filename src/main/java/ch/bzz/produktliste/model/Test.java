package ch.bzz.produktliste.model;

public class Test {
    public static void main(String[] args) {
        new Test().run();
    }

    public void run() {
        Hersteller hersteller = new Hersteller();
        System.out.println(hersteller);
        User user = new User();
        System.out.println(user);
        Produkt produkt = new Produkt();
        System.out.println(produkt);
        Inhalt inhalt = new Inhalt();
        System.out.println(inhalt);
    }
}
