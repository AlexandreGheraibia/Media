package venteEnLigne;

public class CartRow {
    private int quantity=1; //une ligne a un media donc il a toujours un media a la creation
    private IMedia media; //on passe au type qui applique la spec un Media est un IMedia

    public CartRow(IMedia media){//On construit une ligne par media donc le constructeur a un parametre media
        this.media=media;
    }

    public void increment(){
        quantity = getQuantity() + 1;
    }

    public void decrement(){
        if(getQuantity() >1){
            quantity = getQuantity() - 1;
        }
        else{
            //todo erreur
        }

    }

    public IMedia getMedia() {
        return media;
    }

    public int getQuantity() {
        return quantity;
    }
}
