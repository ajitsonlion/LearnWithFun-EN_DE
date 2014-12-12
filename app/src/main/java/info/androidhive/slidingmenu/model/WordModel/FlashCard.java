package info.androidhive.slidingmenu.model.WordModel;

import com.orm.SugarRecord;

/**
 * Created by ajit on 05.12.14.
 */
public class FlashCard extends SugarRecord<FlashCard> {

    public GermanWord getGer() {
        return ger;
    }

    public void setGer(GermanWord ger) {
        this.ger = ger;
    }

    public EnglishWord getEng() {
        return eng;
    }

    public void setEng(EnglishWord eng) {
        this.eng = eng;
    }

    private GermanWord ger;
    private EnglishWord eng;

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    private long categoryID;

    public boolean isBookMarked() {
        return isBookMarked;
    }

    public void setBookMarked(boolean bookMarked) {
        this.isBookMarked = bookMarked;
    }

    private boolean isBookMarked;


}
