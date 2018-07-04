package venteEnLigne;

import java.io.IOException;
import java.util.List;

public class MediaMain {

    public static void main(String[] args) throws IOException {
       /*media and classe dérived test
        Media m2 = new Book(0,"harry potter",3);
        Media m = new Book(1,"Java",10);
        Media b = new Book(1,"Java",10);
        Media c = new Cd(2,"A que Johnny",20);
        m = new Dvd(3, "Rambo 12", 30);
        System.out.println(c.getNetPrice());
        m.setPublisher(new Publisher(4,"Gaumont"));
        m.getAuthorList().add(new Author(5,"Silvester","Stallone"));
        int zone = ((Dvd) m).getZone();

        /*Cart test
        Cart baseket=new Cart();

        baseket.addMediaToCart(m2);
        baseket.addMediaToCart(m2);
        baseket.addMediaToCart(m);
        baseket.addMediaToCart(b);
        baseket.addMediaToCart(c);
        System.out.println("avant suppression\n"+baseket);
        baseket.removeMediaFromCart(b);
        System.out.println("\naprès suppression de "+b.getTitle()+"\n"+baseket);*/
       /*Repository test*/
        BookRepository bookRepository=new BookRepository();
        bookRepository.load("bookRepository.csv");
        List<Book> bookList=bookRepository.getAll();
        /*getAll() test
         System.out.println(book.getId()+" "
                    +book.getTitle() +" "
                    +book.getNetPrice()+" "
                    +book.getNbPage()+" "
                    +book.getPublisher().getName());

        }/*/
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
        *
        * */
        Book b=new Book(1,"Livre 2",10);
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
    }
}
