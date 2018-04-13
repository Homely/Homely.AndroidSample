package au.com.homely.sampleapp;

import java.util.ArrayList;

public class FavouritesManager {

    private ArrayList<Integer> favourites;

    public FavouritesManager(ArrayList<Integer> favourites) {
        this.favourites = favourites;
    }

    public void add(Integer id) {
        if (this.isFavourite(id)) { return; }
        this.favourites.add(id);
    }

    public void remove(Integer id) {
        if (!this.isFavourite(id)) { return; }
        this.favourites.remove(id);
    }

    public Boolean isFavourite(Integer id) {
        return this.favourites.contains(id);
    }

}
