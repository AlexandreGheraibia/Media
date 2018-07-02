package venteEnLigne;

import venteEnLigne.*;

public class MediaMain {

    public static void main(String[] args) {
        Media m2 = new Book(0,"",0);
        Media b = new Book(1,"Java",10);
        Media m = new Cd(2,"A que Johnny",20);
        m = new Dvd(3, "Rambo 12", 30);
        System.out.println(m.getNetPrice());
        m.setPublisher(new Publisher(4,"Gaumont"));
        m.getAuthorList().add(new Author(5,"Silvester","Stallone"));
        int zone = ((Dvd) m).getZone();

    }
}
