package info.androidhive.slidingmenu.model.WordModel;

/**
 * Created by ajit on 05.12.14.
 */
public class FlashCard {

    private short flashCardID;

    public short getFlashCardID() {
        return flashCardID;
    }

    public void setFlashCardID(short flashCardID) {
        this.flashCardID = flashCardID;
    }

    public GermanWord getGermanFaceOfFlashCard() {
        return germanFaceOfFlashCard;
    }

    public void setGermanFaceOfFlashCard(GermanWord germanFaceOfFlashCard) {
        this.germanFaceOfFlashCard = germanFaceOfFlashCard;
    }

    public EnglishWord getEnglishFaceOfFlashCard() {
        return englishFaceOfFlashCard;
    }

    public void setEnglishFaceOfFlashCard(EnglishWord englishFaceOfFlashCard) {
        this.englishFaceOfFlashCard = englishFaceOfFlashCard;
    }

    private GermanWord germanFaceOfFlashCard;
    private EnglishWord englishFaceOfFlashCard;

}
