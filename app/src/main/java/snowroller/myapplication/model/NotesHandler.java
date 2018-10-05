package snowroller.myapplication.model;


import java.util.ArrayList;
import java.util.List;

import snowroller.myapplication.model.validators.NoteValidator;
import snowroller.myapplication.model.validators.Validator;

public class NotesHandler {

    List<Note> notes = new ArrayList<>();

    public NotesHandler(){
        //Configure NoteValidator
        //Title must be at least 3 characters long
        NoteValidator.addValidator(new Validator<Note>() {
            @Override
            public boolean validate(Note obj) {
                return obj.getTitle().length() > 2;
            }
        });
        //Title can't be Hej
        NoteValidator.addValidator(obj -> !obj.getTitle().equals("Hej"));
        //Check for line separator in body text. More than one row.
        String newline = System.getProperty("line.separator");
        NoteValidator.addValidator(obj -> obj.getBody().contains( newline ));
    }

    //CRUD operations, Create, Read, Update, Delete
    /**
     * Creates a new Note instance and stores it internally for later use
     *
     * @param title Title to set for new Note
     * @param body  Body of new Note
     * @return Returns the newly created Note instance
     */
    public Note createNote(String title, String body) {
        Note note = new Note(title, body);

        notes.add(note);
        return note;
    }

    /**
     * Search for the first note with matching title
     *
     * @param title title to search for with exact match
     * @return Note with matching title, if none found returns a new Note object
     */
    public Note getNote(String title) {
        //Search for note with title
        for (Note note : notes) {
            if (note.getTitle().equals(title))
                return note;
        }
        return new Note("", "");
    }

    public Note getNote(int index){
        return notes.get(index);
    }

    //    editNote? Just do a getNote and then change info with setTitle, setBody.

    public void deleteNote(Note note){
        notes.remove(note);
    }

    public int count(){
        return notes.size();
    }

    public List<Note> getNotes(){
        return notes;
    }
}
