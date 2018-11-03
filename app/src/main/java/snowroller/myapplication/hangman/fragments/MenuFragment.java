package snowroller.myapplication.hangman.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import snowroller.myapplication.hangman.viewmodels.HangmanViewModel;
import snowroller.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


  //  ExampleFragmentModel model;
    HangmanViewModel model;

    TextView textView;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment'
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        model = ViewModelProviders.of(this).get(HangmanViewModel.class);
        getActivity().findViewById(R.id.button2).setOnClickListener(this::buttonClicked);
        textView = getActivity().findViewById(R.id.textView3);
        textView.setText(model.getGuessCount());
    }

    public void buttonClicked(View v) {

        model.setText("Button clicked in fragment");

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        GameFragment gameFragment = new GameFragment();

        fragmentTransaction.replace(R.id.framelayout, gameFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.startmenu, menu);
    }
}
