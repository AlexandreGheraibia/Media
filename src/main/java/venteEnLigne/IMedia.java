package venteEnLigne;

import java.util.List;
/*extraction(generation) de l'interface IMedia à partir de l'abstract Media
*Effet de bord la classe media reste abstraite puisque si media redefinit la methode
* getNetPrice c'est descendant n'auron pas l'obligation
* d'override leur méthode du même nom.
* l'interface et la specification des classes descendante
*
* */
public interface IMedia {
    double getPrice();

    void setPrice(double price);

    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    Publisher getPublisher();

    void setPublisher(Publisher publisher);

    List<Author> getAuthorList();

    double getNetPrice();

    void setAuthorList(List<Author> authorList);
}
