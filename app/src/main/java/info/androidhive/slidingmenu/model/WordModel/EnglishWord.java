package info.androidhive.slidingmenu.model.WordModel;

/**
 * Created by ajit on 05.12.14.
 */
public class EnglishWord {
    private String word;
    private short englishWordID;

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

    public short getEnglishWordID() {
        return englishWordID;
    }

    public void setEnglishWordID(short englishWordID) {
        this.englishWordID = englishWordID;
    }

    private String gender;

}
