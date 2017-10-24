/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainmodel;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author 715583
 */
public class Note implements Serializable{
    private int noteId;
    private Date date;
    private String contents;

    public Note() {
    }

    public Note(int noteId, Date date, String contents) {
        this.noteId = noteId;
        this.date = date;
        this.contents = contents;
    }

    public Note(int noteId, String contents) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getNoteId() {
        return noteId;
    }

    public Date getDate() {
        return date;
    }

    public String getContents() {
        return contents;
    }
}
