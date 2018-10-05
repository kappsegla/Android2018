package snowroller.myapplication.model.validators;

import java.util.ArrayList;
import java.util.List;

import snowroller.myapplication.model.Note;

public class NoteValidator {

    private static List<Validator<Note>> validators = new ArrayList<>();

    public static boolean validate(Note note) {
        for (Validator<Note> val : validators) {
            if (!val.validate(note))
                return false;
        }
        return true;
    }

    public static void addValidator(Validator<Note> validator){
        validators.add(validator);
    }
}
