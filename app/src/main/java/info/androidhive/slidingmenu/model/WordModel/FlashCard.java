package info.androidhive.slidingmenu.model.WordModel;

import com.orm.SugarRecord;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by ajit on 05.12.14.
 */
public class FlashCard     {


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

    public long getCrdID() {
        return crdID;
    }

    public void setCrdID(long crdID) {
        this.crdID = crdID;
    }

    private long crdID;

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    private boolean isBookmarked;


}
