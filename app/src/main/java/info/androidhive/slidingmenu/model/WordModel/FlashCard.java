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


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    private short id;


}
