package venteEnLigne;

import java.util.ArrayList;
import java.util.List;

public  class Media  {

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

    public double getNetPrice(){
        return this.getPrice()*1.2;
    }
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

}
