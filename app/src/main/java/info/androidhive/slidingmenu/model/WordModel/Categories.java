package info.androidhive.slidingmenu.model.WordModel;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by ajit on 05.12.14.
 */
public class Categories extends SugarRecord<Categories> {

    public short getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(short categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryNameInEnglish() {
        return categoryNameInEnglish;
    }

    public void setCategoryNameInEnglish(String categoryNameInEnglish) {
        this.categoryNameInEnglish = categoryNameInEnglish;
    }

    public ArrayList<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(ArrayList<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }

    private short categoryID;
    private String categoryNameInEnglish;

    public String getCategoryNameInGerman() {
        return categoryNameInGerman;
    }

    public void setCategoryNameInGerman(String categoryNameInGerman) {
        this.categoryNameInGerman = categoryNameInGerman;
    }

    private String categoryNameInGerman;
    private ArrayList<FlashCard> flashCards;
}
