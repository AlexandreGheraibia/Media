package venteEnLigne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Cart {
//on definit un Set pour l'unicité des élements contenus dans
// le card
private HashSet<CartRow> cartRowSet=new HashSet<>();

    public void addMediaToCart(IMedia media){
        CartRow row =isElementOfCart(media);
        if(row!=null){
            row.increment();
        }
        else{
           getCartRowSet().add(new CartRow(media));
        }

    }

    private CartRow isElementOfCart(IMedia media){
        for(CartRow elem:cartRowSet){
            if(elem.getMedia().equals(media)){
                return elem;
            }
        }
        return null;
    }


    public void removeMediaFromCart(IMedia media) throws MediaException,CartRowException {
        CartRow row =isElementOfCart(media);
        if(row!=null){
            if(row.getQuantity()>1){

                row.decrement();
            }
            else{
                getCartRowSet().remove(row);
            }
        }
        else{
            throw new MediaException("media exception: try to delete an not existant element");
            //todo Erreur
        }

    }

    public Set<CartRow> getCartRowSet() {
        return cartRowSet;
    }
    public double getNetTotalPrice(){
       int sum=0;
       for(CartRow elem:getCartRowSet()){
           sum+=elem.getMedia().getNetPrice()*elem.getQuantity();
       }
       return sum;
    }
    public boolean validate() throws CartRowException {
        boolean valide=true;
       if(getNetTotalPrice()<0) {
           valide=false;
           throw new CartRowException("Exception") ;

       }
       return valide;
    }
    @Override
    public String toString() {
       String value="";
       for(CartRow elem:getCartRowSet()){
           value+=elem.getMedia().getTitle()+" "+elem.getMedia().getNetPrice()+" "+elem.getQuantity()+"\n";
       }
       value+="\nmontant\t\t\t"+getNetTotalPrice();
       return value;
    }
}
