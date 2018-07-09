package venteEnLigne;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MediaMain {

    public static void main(String[] args) {
      try{
        BookSqlRepository bookSqlRepository=new BookSqlRepository();
        bookSqlRepository.load("localhost");
        List<Book> bookList=bookSqlRepository.getAll();
        /*getAll() test*/
        for(Book book:bookList){
            System.out.println(book.getId()+" "
                    +book.getTitle() +" "
                    +book.getNetPrice()+" "
                    +book.getNbPage()+" "
                    +book.getPublisher().getName());
        }
      } catch (SQLException e) {
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
       /*/
        /* getById test
        System.out.println(bookRepository.getById(2).getTitle());
        //erreur d'index
        System.out.println(bookRepository.getById(2).getTitle());
        /*/
        /*getbyTitle
        for(Book book : bookRepository.getByTitle("livre 1")){
            System.out.println(book.getTitle());
        }

        for(Book book : bookRepository.getByTitle("Livre   1")){
            System.out.println(book.getTitle());
        }
         for(Book book : bookRepository.getByTitle("Li vre")){
            System.out.println(book.getTitle());
        }
        */
        /*price test 0 accepted if is ebook
        * for(Book book : bookRepository.getByPrice(-1)){
            System.out.println(book.getTitle()+" "+book.getPrice());
        }
        for(Book book : bookRepository.getByPrice(50)){
            System.out.println(book.getTitle()+" "+book.getPrice());
        }
         for(Book book : bookRepository.getByPrice(50)){
            System.out.println(book.getTitle()+" "+book.getPrice());
        }
        */
        /*getByPublisher() test
        *  for(Book book : bookRepository.getByPublisher("paspublisher")){
            System.out.println(book.getTitle()+" "+book.getPrice());
        }
        //perhaps allowed but not in this exemple
         for(Book book : bookRepository.getByPublisher("pas publisher")){
            System.out.println(book.getTitle()+" "+book.getPrice());
        }
        for(Book book : bookRepository.getByPublisher("p u b l i s h e r 1")){
            System.out.println(book.getTitle()+" "+book.getPrice());
        }
        */
        /*add test
        *   Book b=new Book(0,"",100)
        *   Book b=new Book(0,"",100);
        *   b.setNbPage(100);
        *   b.setPublisher(new Publisher(0,"mon publisher"));
        *   Book b=new Book(1,"Livre 2",10);
            b.setNbPage(100);
            b.setPublisher(new Publisher(0,"Publisher 1"));

            bookRepository.add(b);
            for(Book book: bookList){
            System.out.println(book.getId()+" "
                    +book.getTitle() +" "
                    +book.getNetPrice()+" "
                    +book.getNbPage()+" "
                    +book.getPublisher().getName());

        }
        * */
        /*remove test*/

       /* Book b=new Book(1,"Livre 2",20);
        b.setNbPage(90);
        b.setPublisher(new Publisher(1,"Publisher"));
        bookRepository.update(b);

        for(Book book: bookList){
            System.out.println(book.getId()+" "
                    +book.getTitle() +" "
                    +book.getNetPrice()+" "
                    +book.getNbPage()+" "
                    +book.getPublisher().getName());

        }
        */

    }
}
