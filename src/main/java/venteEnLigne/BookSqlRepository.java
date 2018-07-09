
package venteEnLigne;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookSqlRepository /*implements*/  {

    private String uri;
   // public void
    private  ArrayList<Book>  sousFonction(String request){
        String result="";
        ArrayList<Book> bookList=new ArrayList<>();
        String[] champs={"id","title","price","nbpages","publisher","author"};
        String driverName = "org.postgresql.Driver";
        String url = "jdbc:postgresql://"+uri+":5432/postgres";
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url,"postgres","Admin");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(request);
            // Ecriture st.execute()
            int id,nbPage,publisherid;
            String title;
            double price;
            String publisher;
            while(rs.next()) {
                id=0;
                title="";
                price=0.0;
                nbPage=0;
                publisherid=0;
                publisher=null;
                for(String elem:champs){
                    switch(elem){
                        case "id":
                             id=rs.getInt("id");
                            break;
                        case "title":
                                    title=rs.getString("title");
                            break;
                        case "price":
                                    price=rs.getDouble("price");
                            break;
                        case "nbpages":
                                    nbPage=rs.getInt("nbpages");
                            break;
                        case "author":
                            //price=rs.getString("author");
                            break;
                        case "publisher":
                            publisherid=rs.getInt("publisherid");
                            publisher=rs.getString("name");
                            break;
                        default:break;
                    }


                }
                Book b=new Book(id,title,price);
                b.setPublisher(new Publisher(publisherid,publisher));
                bookList.add(b);
            }
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
    public void load(String uri) throws IOException {
            this.uri=uri;

    }


    public List<Book> getAll() {

        return sousFonction("select * from book,publisher " +
                                     "where publisher.id=book.publisherid");
    }


    public Book getById(int id) {
        return sousFonction("select * from book,publisher" +
                                    "where publisher.id=book.publisherid and id="+id ).get(0);

    }


    public List<Book> getByTitle(String title){
        return sousFonction("select * from book,publisher" +
                                     "where publisher.id=book.publisherid and title like %"+title+"%");
    }


    public List<Book> getByPrice(double price) {

        return sousFonction("select * from book,publisher " +
                                    "where publisher.id=book.publisherid and price<="+price);

    }


    public List<Book> getByPublisher(String publisherName) {

        return sousFonction("select book.id,book.title,book.nbpages,book.publisherid,publisher.name from book,publisher " +
                                                            "where book.publisherid=publisher.id " +
                                                            "and publisher.name="+publisherName );
    }


    public void add(Book b) {

    }


    public void remove(Book b) {

    }

    public void update(Book b) {

    }
}
