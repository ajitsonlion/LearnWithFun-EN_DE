package info.androidhive.slidingmenu.model.WordModel;

import com.orm.SugarRecord;


/**
 * Created by ajit on 05.12.14.
 */
public class GermanWord extends SugarRecord<Categories> {

    private String gen;

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
    private String word;
}
