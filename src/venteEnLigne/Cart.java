package venteEnLigne;

import java.util.ArrayList;

//panier 
public class Cart {
// n'a pas de constructeur pas besoin de
//d'initialisation special

//une relation n avec media
//un panier a des medias
private ArrayList<Media> mediaList=new ArrayList<>();
//elle a un montanttotal net
    public void addMediaFromCart(Media media){
        getMediaList().add(media);
    }
    /*algorithme naif ne prenanpas en compte la multiplicit d'un element*/
   public void removeMediaFromCart(Media media){
        getMediaList().remove(media);
   }

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }
    public double getNetTotalPrice(){
       int sum=0;
       for(Media elem:getMediaList()){
           sum+=elem.getNetPrice();
       }
       return sum;
    }

    @Override
    public String toString() {
       String value="";
       for(Media elem:getMediaList()){
           value+=elem.getTitle()+" "+elem.getNetPrice()+"\n";
       }
       value+="\nmontant\t\t\t"+getNetTotalPrice();
       return value;
    }
}
