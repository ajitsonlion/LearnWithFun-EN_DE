package info.androidhive.slidingmenu.model.WordModel;

import com.orm.SugarRecord;


/**
 * Created by ajit on 05.12.14.
 */
public class EnglishWord   {
    private String word;

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    private short id;


    private String gen;

}
