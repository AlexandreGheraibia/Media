
package venteEnLigne;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookSqlRepository /*implements*/  {

    private Connection connection;
   // public void
    private  ArrayList<Book>  bdWhereRequest(String predicat) throws ClassNotFoundException, SQLException {
        String realRequest="select book.id,book.title,book.price,book.nbpages,book.publisherid,publisher.name from book,publisher where "+ predicat
                +" and publisher.id=book.publisherid";
        ArrayList<Book> bookList=new ArrayList<>();
        String[] champs={"id","title","price","nbpages","publisher","author"};
         Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(realRequest);
            // Ecriture st.execute()
            int id,nbPage,publisherid;
            String title,publisher;
            double price;
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
                b.setNbPage(nbPage);
                b.setPublisher(new Publisher(publisherid,publisher));
                bookList.add(b);
            }
        return bookList;
    }

    public void connect( String uri) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://"+uri+":5432/postgres";
        String driverName = "org.postgresql.Driver";
        Class.forName(driverName);
        connection = DriverManager.getConnection(url,"postgres","Admin");

    }
    public void close() throws SQLException {
        connection.close();

    }



    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        return bdWhereRequest("publisher.id=book.publisherid");
    }


    public Book getById(int id) throws SQLException, ClassNotFoundException {
        return bdWhereRequest("id="+id ).get(0);
    }


    public List<Book> getByTitle(String title) throws SQLException, ClassNotFoundException {
        return bdWhereRequest("title like %"+title+"%");
    }


    public List<Book> getByPrice(double price) throws SQLException, ClassNotFoundException {
        return bdWhereRequest("price<="+price);
    }


    public List<Book> getByPublisher(String publisherName) throws SQLException, ClassNotFoundException {
        return bdWhereRequest("publisher.name="+publisherName );
    }


    public void add(Book b) {

    }


    public void remove(Book b) {

    }

    public void update(Book b) {

    }
}
