package snowroller.myapplication.hangman.fragments;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import snowroller.myapplication.R;
import snowroller.myapplication.hangman.viewmodels.HangmanViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    HangmanViewModel model;
    ImageView hangmanImageView;
    Bitmap hangman;
    EditText guess;
    TextView maskedText;
    TextView guessedChars;
    TextView guessCount;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gamefragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        model = ViewModelProviders.of(this).get(HangmanViewModel.class);
        getActivity().findViewById(R.id.guess_button).setOnClickListener(this::guessButtonClicked);
        hangman = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.man);
        hangmanImageView = getActivity().findViewById(R.id.imageView);
        guess = getActivity().findViewById(R.id.guessText);
        maskedText = getActivity().findViewById(R.id.maskedText);
        guessedChars = getActivity().findViewById(R.id.guessedChars);
        guessCount = getActivity().findViewById(R.id.triesLeft);
        setHangMan(model.getWrongGuessCount());
        getActivity().getWindow().setBackgroundDrawableResource(R.drawable.background);
        updateView();
    }

    private void guessButtonClicked(View view) {
        //Validate input
        String g = guess.getText().toString();
        if (model.validate(g) && !model.alreadyUsed(g.charAt(0))) {
            model.makeGuess(g.charAt(0));
        }
        updateView();
        if (model.getGuessesLeft() == 0) {
            //Lost
            new AlertDialog.Builder(getContext())
                    .setTitle("You Lost")
                    .setCancelable(false)
                    .setMessage("No more tries left. You didn't find the hidden word, " + model.getSecretWord())
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        model.init();
                        updateView();
                    })
                    .show();

        }
        if (model.allCleared()) {
            //Won
            new AlertDialog.Builder(getContext())
                    .setTitle("You Won")
                    .setCancelable(false)
                    .setMessage("You found the hidden word, " + model.getSecretWord())
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        model.init();
                        updateView();
                    })
                    .show();

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.gamemenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_menu_item:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAbout() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AboutFragment aboutFragment = new AboutFragment();
        fragmentTransaction.replace(R.id.framelayout, aboutFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setHangMan(int frame) {
            int col_width = hangman.getWidth() / 8;

            Drawable subImage = new BitmapDrawable(getActivity().getResources(),
                    Bitmap.createBitmap(hangman, frame * col_width, 0, col_width, hangman.getHeight()));
            hangmanImageView.setImageDrawable(subImage);
    }

    private void updateView() {
        guess.setText("");
        maskedText.setText(model.getMaskedWord());
        guessedChars.setText(model.getGuessedChars());

        int count = model.getGuessesLeft();
        Resources res = getActivity().getResources();
        String guessesLeft = res.getQuantityString(R.plurals.guessesLeft, count, count);
        guessCount.setText(guessesLeft);
        setHangMan(model.getWrongGuessCount());
    }
}
