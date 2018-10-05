package snowroller.myapplication.model.presenters;

import snowroller.myapplication.model.Note;

public class FullNotePresenter implements Presenter {

    @Override
    public String present(Note note) {
        return "Title: " + note.getTitle()
                + "\n================\n" + note.getBody();
    }
}
