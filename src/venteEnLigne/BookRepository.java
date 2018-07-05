package venteEnLigne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BookRepository /* implements IBookRepository */{
    private List<Book> bookList=new ArrayList<>();
   // @Override
    public void load(String uri) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(uri));
        reader.readLine();
        String line = reader.readLine();
        int i=0;
        while(line != null) {
            StringTokenizer st = new StringTokenizer(line,";");
            int id = Integer.parseInt(st.nextToken());
            String title = st.nextToken();
            Double price = Double.parseDouble(st.nextToken());
            int nbPages = Integer.parseInt(st.nextToken());
            String publisher=st.nextToken();
            Book book=new Book(id,title,price);
            book.setNbPage(nbPages);
            book.setPublisher(new Publisher(i,publisher));
            getAll().add(book);
            i++;
            line = reader.readLine();
        }
        reader.close();
    }


    //@Override
    public List<Book> getAll() {
        return bookList;
    }

    //@Override
    public Book getById(int id) {

        for(Book curBook : getAll()){
            if(curBook.getId()==id){
                return curBook;
            }
        }

        //bookException
       // throw mediaException("getById:access none allowed, the index "+id+" unexist");
        return null;
    }
    private Boolean isContained(String container,String containted){
        String containerWithout=container.replaceAll("\\s", "").toLowerCase();
        String containtedWithout=containted.replaceAll("\\s", "").toLowerCase();
        return containerWithout.contains(containtedWithout);

    }
    //@Override
    public List<Book> getByTitle(String title) {
        List<Book> matchTitleBookList=new ArrayList<>();
        for(Book curBook : getAll()){
            if(isContained(curBook.getTitle(),title)){
                matchTitleBookList.add(curBook);
            }
        }
        return matchTitleBookList;
    }

    //@Override
    public List<Book> getByPrice(double price) {

        List<Book> matchPriceBookList=new ArrayList<>();
        if(price>0){//livre gratuir
                for(Book curBook : getAll()){
                if(curBook.getNetPrice()<=price){
                    matchPriceBookList.add(curBook);
                }
            }
        }
        //bookException
        // throw mediaException("getByPrice:access negtive value not allowed, price "+price+" unexist");
        return matchPriceBookList;
    }

    //@Override
    public List<Book> getByPublisher(String publisherName) {
        List<Book> matchPublisherBookList=new ArrayList<>();
        for(Book curBook : getAll()){
            if(isContained(curBook.getPublisher().getName(),publisherName)){
                matchPublisherBookList.add(curBook);
            }
        }
        return matchPublisherBookList;
    }
    private boolean hasAllAttributs(Book b){
        if(b.getId()>0&&
                b.getPublisher()!=null&&b.getPublisher().getName().length()>0 &&
                b.getTitle()!=null&&b.getTitle().length()>0&&
                b.getNbPage()>0
                &&b.getPrice()>0){
            return true;
        }

        return false;
    }
    private boolean isAddable(Book b){
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
    private boolean isTitleIsContained(Book b){
        return getByTitle(b.getTitle()).size()>0;
    }

   // @Override
    public void add(Book b) {
        if(hasAllAttributs(b)){
            if(isAddable(b)){
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
    public void remove(Book b) {
        if(hasAllAttributs(b)){

            Book findBook=getById(b.getId());
            if(findBook!=null){
                if(findBook.getTitle().equals(b.getTitle())&&
                        b.getPrice()==findBook.getPrice()&&
                        b.getNbPage()==findBook.getNbPage()&&
                        b.getPublisher().getName().equals(findBook.getPublisher().getName())){
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
    public void update(Book b) {

    }

}
