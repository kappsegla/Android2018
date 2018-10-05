package snowroller.myapplication.model.presenters;


import snowroller.myapplication.model.Note;

public class NotePresenter implements Presenter{

    public String present(Note note){
        return "Title: " + note.getTitle() + "\n";
    }
}
