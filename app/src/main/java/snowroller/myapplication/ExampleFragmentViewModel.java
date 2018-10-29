package snowroller.myapplication;

import android.arch.lifecycle.ViewModel;

public class ExampleFragmentViewModel extends ViewModel {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
