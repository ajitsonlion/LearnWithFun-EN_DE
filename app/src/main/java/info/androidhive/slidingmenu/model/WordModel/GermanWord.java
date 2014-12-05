package info.androidhive.slidingmenu.model.WordModel;

/**
 * Created by ajit on 05.12.14.
 */
public class GermanWord {

    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }



    public short getGermanWordID() {
        return germanWordID;
    }

    public void setGermanWordID(short germanWordID) {
        this.germanWordID = germanWordID;
    }

    private String word;
    private short germanWordID;
}
