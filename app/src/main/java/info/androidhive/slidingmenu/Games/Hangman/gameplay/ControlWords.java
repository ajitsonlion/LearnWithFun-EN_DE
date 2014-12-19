package info.androidhive.slidingmenu.Games.Hangman.gameplay;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import info.androidhive.slidingmenu.MainActivity;
import info.androidhive.slidingmenu.model.WordModel.Categories;
import info.androidhive.slidingmenu.model.WordModel.FlashCard;

public class ControlWords {

	public ArrayList<String> populateWords(InputStream stream){
		ArrayList<String> words;
		words = new ArrayList<String>();


        for (Categories c:MainActivity.wordByCategories){

            for (FlashCard card:c.getCards()){

               String word=card.getGer().getWord();
               if (word.matches("/^[A-z]+$/")){


               }
                words.add(word);

            }

        }

		
		return words;
	}
	
	public String getWord(int length, ArrayList<String> words) {
		String randomWord = "";
		while (randomWord.length() != length) {
			randomWord = words.get(new Random().nextInt(words.size() - 1));
		}

		return randomWord;

	}
}
