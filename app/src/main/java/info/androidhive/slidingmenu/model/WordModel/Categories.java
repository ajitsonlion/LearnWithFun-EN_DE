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


    public ArrayList<FlashCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<FlashCard> cards) {
        this.cards = cards;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    private String eng;


    public String getGer() {
        return ger;
    }

    public void setGer(String ger) {
        this.ger = ger;
    }

    private String ger;
    private ArrayList<FlashCard> cards;


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    private short id;
}
