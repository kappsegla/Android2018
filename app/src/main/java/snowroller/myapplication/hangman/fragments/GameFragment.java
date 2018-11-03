package snowroller.myapplication.hangman.fragments;


import android.arch.lifecycle.ViewModelProviders;
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


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
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
        setHangMan(model.getWrongGuessCount());
    }

    private void guessButtonClicked(View view) {
        //Validate input
        String g = guess.getText().toString();
        //Try the char
        if (g.length() == 1) {
            model.makeGuess(g.charAt(0));
        }
        //Update view
        guess.setText("");
        maskedText.setText(model.getMaskedWord());
        setHangMan(model.getWrongGuessCount());
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
        int col_width = hangman.getWidth() / 7;

        Drawable subImage = new BitmapDrawable(getActivity().getResources(),
                Bitmap.createBitmap(hangman, frame * col_width, 0, col_width, hangman.getHeight()));
        hangmanImageView.setImageDrawable(subImage);
    }
}
