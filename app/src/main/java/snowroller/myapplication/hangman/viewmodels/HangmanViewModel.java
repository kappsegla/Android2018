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
        if( secretWord.indexOf(c) == -1)
        {
            //Ej en bokstav i ordet, kolla så vi inte gissat på den innan.
            for (char ch : guesses) {
                if( ch == c)
                {
                    //Old stuff
                    return false;
                }
            }
            wrongGuessCount++;
            guesses.add(c);
            return false;
        }
        else {
            guesses.add(c);
            return true;
        }
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
