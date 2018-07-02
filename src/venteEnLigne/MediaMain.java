package venteEnLigne;

public class MediaMain {

    public static void main(String[] args) {
        Media m2 = new Book(0,"harry potter",3);
        Media m = new Book(1,"Java",10);
        Media b = new Book(1,"Java",10);
        Media c = new Cd(2,"A que Johnny",20);
        m = new Dvd(3, "Rambo 12", 30);
        System.out.println(c.getNetPrice());
        m.setPublisher(new Publisher(4,"Gaumont"));
        m.getAuthorList().add(new Author(5,"Silvester","Stallone"));
        int zone = ((Dvd) m).getZone();
        Cart baseket=new Cart();
        baseket.addMediaFromCart(m2);
        baseket.addMediaFromCart(m);
        baseket.addMediaFromCart(b);
        baseket.addMediaFromCart(c);
        System.out.println("avant suppression\n"+baseket);
        baseket.removeMediaFromCart(b);
        System.out.println("\naprès suppression de "+b.getTitle()+"\n"+baseket);
    }
}
