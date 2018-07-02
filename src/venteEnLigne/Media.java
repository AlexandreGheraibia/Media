package venteEnLigne;

import java.util.ArrayList;
import java.util.List;

public abstract class Media  {

    private double price;
    private int id;
    private String title;
    private Publisher publisher;
    private List<Author> authorList = new ArrayList<>();

    public Media() {}

    public Media(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Publisher getPublisher() {
        return publisher;
    }


    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


    public List<Author> getAuthorList() {
        return authorList;
    }

    public abstract double getNetPrice();/*1 on définit une méthode abstraite
                                        on impose aux descendants de cette classe l'implementation de
                                        la méthode.
                                        1.1 La méthode sera commune a toutes les filles donc on pourra utiliser
                                        avec confianee le polymorphysme sur cette méthode
                                        1.2 la methode etant abstraite la classe doit etre abstraite
                                        puisque qu'une class méthode abstraite ne peut etre contenue
                                        que dans une classe abstraite.L'inverse n'est pas vrai
                                        1.3 une classe abstraite ne peut être instancier,
                                         par contre on peut declarer une variable Abstraite qui contiendra
                                         une instance d'une classe fille.
                                         est donc exploiter le polymorphisme.
                                         1.4 une interface est une abstract compléte elle ne contient que des définition
                                         de méthode est peut contenir des variables
                                         */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

}
