
package venteEnLigne;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookSqlRepository /*implements*/  {

    private Connection connection;



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

    // public void
    private  ArrayList<Book>  bdWhereRequest(String predicat) throws ClassNotFoundException, SQLException {
        String realRequest="select book.id,book.title,book.price,book.nbpages,book.publisherid,publisher.name from book,publisher,author,listbook" +
                " where "+
                  predicat +
                " and publisher.id=book.publisherid" +
                " and listbook.authorid=author.id" +
                " and listbook.id=book.id";
       // Map authorMap= new HashMap<String,ArrayList<String>>();
        //authorMap.put("name", new ArrayList<>());
        ArrayList<Book> bookList=new ArrayList<>();
        String[] champs={"id","title","price","nbpages","publisher","author"};
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(realRequest);
        // Ecriture st.execute()
        int id=0,nbPage=0,publisherid=0;
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
                    case "publisher":
                        publisherid=rs.getInt("publisherid");
                        publisher=rs.getObject(6).toString();
                        break;
                    default:break;
                }
            }
            Book b=new Book(id,title,price);
            b.setNbPage(nbPage);
            b.setPublisher(new Publisher(publisherid,publisher));
            bookList.add(b);
        }
        addAuthor(bookList);
        return bookList;
    }

    private void addAuthor(ArrayList<Book> bookList )throws SQLException, SQLException {
        //todo rename listbook association by authorbook and change id by bookid
        String request="select author.id,author.name " +
                "from author,listbook,book " +
                "where author.id=listbook.authorid " +
                "and listbook.id=book.id " +
                "and book.id=";
        ResultSet rs;

        for(Book b:bookList){
            String realRequest=request+b.getId();
            Statement st = connection.createStatement();
            if (st != null) {
                rs= st.executeQuery(realRequest);
                if(rs!=null){
                    while(rs.next()) {//todo thought errors cases
                        Author a=new Author(rs.getInt("id"),rs.getString("name"));
                        b.getAuthorList().add(a);
                    }
                }
            }
        }
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


    private boolean hasAllAttributs(Book b){
        if(b.getId()>=0&&
                b.getPublisher()!=null&&b.getPublisher().getName().length()>0 && b.getPublisher().getId()>=0&&
                b.getTitle()!=null&&b.getTitle().length()>0&&
                b.getNbPage()>0
                &&b.getPrice()>0){
            return true;
        }

        return false;
    }
    private boolean isAddable(Book b) throws SQLException, ClassNotFoundException {
        List<Book>bookPublisherList=getByPublisher(b.getPublisher().getName());
        if(bookPublisherList.size()>0){
            for(Book book:bookPublisherList){
                if((book.getNbPage()==b.getNbPage())&&(book.getTitle().equals(b.getTitle()))){
                    return false;
                }
            }
        }

        return true;
    }
    private boolean isTitleIsContained(Book b) throws SQLException, ClassNotFoundException {
        return getByTitle(b.getTitle()).size()>0;
    }

    // @Override
    public void add(Book b) throws SQLException, ClassNotFoundException {
        if(hasAllAttributs(b)){
            if(isAddable(b)){
                //todo
                //remplacer par la requete insert ou par une transaction
                getAll().add(b);
            }
            else{
                //throw mediaException("add in BookRepository: book already exist);
            }
        }
        else{
            //throw mediaException("add in BookRepository: book partially completed);
        }
    }

    //@Override
    public void remove(Book b) throws SQLException, ClassNotFoundException {
        if(hasAllAttributs(b)){

            Book findBook=getById(b.getId());
            if(findBook!=null){
                if(findBook.getTitle().equals(b.getTitle())&&
                        b.getPrice()==findBook.getPrice()&&
                        b.getNbPage()==findBook.getNbPage()&&
                        b.getPublisher().getName().equals(findBook.getPublisher().getName())&&
                        b.getPublisher().getId()==findBook.getPublisher().getId()){
                    //todo
                    //remplacer par la requete delete ou transaction
                    getAll().remove(findBook);
                }
                else{
                    //throw mediaException("remove in BookRepository: book attributes not the same");
                }

            }
            else{
                //throw mediaException("remove in BookRepository: book id unexist");
            }
        }
    }


    //@Override
    public void update(Book b) throws SQLException, ClassNotFoundException {
        if(hasAllAttributs(b)){
            Book findBook=getById(b.getId());
            if(findBook!=null){
                //todo
                //remplacer par la requete update ou trnsaction
                getAll().remove(findBook);
                getAll().add(b);

            }

            else{
                //throw mediaException("update in BookRepository: book unexist for id"+b.getId());
            }
        }
    }

}
