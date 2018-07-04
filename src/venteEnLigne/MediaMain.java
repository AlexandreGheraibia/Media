package venteEnLigne;

public class MediaMain {

    public static void main(String[] args) {
        try {
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
            baseket.addMediaToCart(m2);
            baseket.addMediaToCart(m2);
            baseket.addMediaToCart(m);
            baseket.addMediaToCart(b);
            baseket.addMediaToCart(c);
            System.out.println("avant suppression\n"+baseket);

            baseket.removeMediaFromCart(m);


            System.out.println("\naprès suppression de "+b.getTitle()+"\n"+baseket);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }


    }
}
