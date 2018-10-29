package snowroller.myapplication;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class ExampleFragment extends Fragment {


  //  ExampleFragmentModel model;
    ExampleFragmentViewModel model;

    TextView textView;

    public ExampleFragment() {
        // Required empty public constructor
        //model = ExampleFragmentModel.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment'
        model = ViewModelProviders.of(this).get(ExampleFragmentViewModel.class);

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_example, container, false);
        textView =  v.findViewById(R.id.textView3);
        textView.setText(model.getText());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.button2).setOnClickListener(this::buttonClicked);
    }

    public void buttonClicked(View v) {

        model.setText("Button clicked in fragment");

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Example2Fragment example2Fragment = new Example2Fragment();

        fragmentTransaction.replace(R.id.framelayout, example2Fragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.frag1menu, menu);
    }
}
