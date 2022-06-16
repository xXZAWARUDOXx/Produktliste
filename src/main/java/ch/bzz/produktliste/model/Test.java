package ch.bzz.produktliste.model;

public class Test {
    public static void main(String[] args) {
        new Test().run();
    }

    public void run() {
        Producer producer = new Producer();
        System.out.println(producer);
        User user = new User();
        System.out.println(user);
        Product product = new Product();
        System.out.println(product);
        Content content = new Content();
        System.out.println(content);
    }
}
