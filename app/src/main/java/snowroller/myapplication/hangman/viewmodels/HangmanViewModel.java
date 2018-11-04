package snowroller.myapplication.hangman.viewmodels;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HangmanViewModel extends ViewModel {

    public String getSecretWord() {
        return secretWord;
    }

    private String secretWord;
    private int wrongGuessCount;
    private List<Character> guesses = new ArrayList<>();

    public HangmanViewModel() {
        init();
    }

    public String getWrongGuessCountAsString() {
        return Integer.toString(wrongGuessCount);
    }

    public int getWrongGuessCount() {
        return wrongGuessCount;
    }

    //Returns true if guess is a character within the word
    public boolean makeGuess(char c) {
        if (secretWord.indexOf(c) == -1) {
            wrongGuessCount++;
            guesses.add(c);
            return false;
        } else {
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

    public boolean validate(String g) {
        if (g.matches("^[a-zåäö]$"))
            return true;
        return false;
    }

    public boolean alreadyUsed(char c) {
        for (char ch : guesses) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public String getGuessedChars() {
        StringBuilder builder = new StringBuilder();
        for (char c : guesses) {
            builder.append(c);
            builder.append(",");
        }
        if (builder.length() > 1)
            builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public int getGuessesLeft() {
        return 7 - getWrongGuessCount();
    }

    public boolean allCleared() {
        if (getMaskedWord().indexOf('_') == -1)
            return true;
        return false;
    }

    public void init() {
        secretWord = "apple";
        guesses.clear();
        wrongGuessCount = 0;
    }
}
