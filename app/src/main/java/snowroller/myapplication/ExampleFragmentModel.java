package snowroller.myapplication;

public class ExampleFragmentModel {

    private final static ExampleFragmentModel instance = new ExampleFragmentModel();

    public static ExampleFragmentModel getInstance() {
        return instance;
    }

    private ExampleFragmentModel(){
        text = "Default";
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
