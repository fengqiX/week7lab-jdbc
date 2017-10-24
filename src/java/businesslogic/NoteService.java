/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author 715583
 */
public class NoteService {
    
    private NoteDB ndb;

    public NoteService() {
        this.ndb = new NoteDB();
    }
    
    public Note getNote(int noteId)
    {
        return ndb.getNote(noteId);
    }
    
    public List<Note> getAll()
    {
        return ndb.getAll();
    }

    public int update(int noteId,String contents)
    {
        Note note = new Note(noteId,contents);
        return ndb.update(note);
    }
    public int delete(int noteId)
    {
        Note note = new Note();
        note.setNoteId(noteId);
        return ndb.delete(note);
    }
    public int insert(String contents) //insert new
    {
        Date today = (Date) new java.util.Date();
        
        Note note = new Note(0,today,contents);
         
         return ndb.insert(note);
    }
}
