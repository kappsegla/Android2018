package snowroller.myapplication.model;

import java.util.Date;

/**
 * Note represents a single note with title and body.
 */
public class Note {

    private String title;
    private String body;
    private final Date created;

    /**
     * Initiates a new Note with title and body.
     * The current time will be stored as creation time
     * and accessed with method getCreated
     *
     * @param title Title of the note
     * @param body  Text body for the note
     */
    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.created = new Date(); //Creates a Date object with current time
    }

    //region Getters/Setters
    //Only get method for Date. Immutable field. Read-only.
    public Date getCreated() {
        return created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    //endregion

    @Override
    public String toString() {
        return "Title: " + getTitle()
                + "\nCreated: " + getCreated()
                + "\n\n" + getBody();
    }
}
