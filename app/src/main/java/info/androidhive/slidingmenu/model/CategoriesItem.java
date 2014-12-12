package info.androidhive.slidingmenu.model;

/**
 * Created by ajit on 07.12.14.
 */
public class CategoriesItem  {

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    private String english;
    private int icon;
    private String german ;
    // boolean to set visiblity of the counter

    public CategoriesItem(){}


    public CategoriesItem(String english, int icon, String german){
        this.english = english;
        this.icon = icon;
        this.german = german;
    }


}
