package snowroller.myapplication.hangman.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HangmanViewModel extends ViewModel {

    private String secretWord;
    private int wrongGuessCount;
    private List<Character> guesses = new ArrayList<>();

    public HangmanViewModel() {
        secretWord = "apple";
    }


    public String getWrongGuessCountAsString() {
        return Integer.toString(wrongGuessCount);
    }

    public int getWrongGuessCount() {
        return wrongGuessCount;
    }

    //Returns true if guess is a character within the word
    public boolean makeGuess(char c) {
        return false;
    }

    public String getMaskedWord() {
        StringBuilder builder = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            if (guesses.contains(c))
                builder.append(c);
            else
                builder.append(" _ ");
        }
        return builder.toString();
    }

}
