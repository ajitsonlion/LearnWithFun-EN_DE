package info.androidhive.slidingmenu.model.WordModel;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ajit on 05.12.14.
 */
public class Categories   {

    public String getCatEng() {
        return catEng;
    }

    public void setCatEng(String catEng) {
        this.catEng = catEng;
    }

    public ArrayList<FlashCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<FlashCard> cards) {
        this.cards = cards;
    }

    private String catEng;

    public String getCatGer() {
        return catGer;
    }

    public void setCatGer(String catGer) {
        this.catGer = catGer;
    }

    private String catGer;
    private ArrayList<FlashCard> cards;


    public short getCatID() {
        return catID;
    }

    public void setCatID(short catID) {
        this.catID = catID;
    }

    private short catID;
}
