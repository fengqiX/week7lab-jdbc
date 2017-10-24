/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 715583
 */
public class NoteDB {
    
    public int insert(Note note) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "INSERT INTO User (dateCreated,contents) VALUES (?,?)";
            PreparedStatement ps;
        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setDate(1, note.getDate());
            ps.setString(2, note.getContents());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return 0;
    }
    
    public int update(Note note)
    { ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedSQL = "UPDATE Note SET "
                    + "contents = ?;";

            PreparedStatement ps = connection.prepareStatement(preparedSQL);

            ps.setString(1, note.getContents());
            

            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            //Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot update " + user.toString(), ex);
         //   throw new NotesDBException("Error updating user");
        } finally {
            pool.freeConnection(connection);
        }
        return 0;
    }
    public List<Note> getAll()
    {
        ArrayList<Note> notesList = new ArrayList<Note>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
          try {
            ps = connection.prepareStatement("SELECT * FROM note;");
            rs = ps.executeQuery();
      
            while (rs.next()) {
                notesList.add(new Note(rs.getInt("noteid"),rs.getDate("dateCreated"),rs.getString("contents")));
            }
            pool.freeConnection(connection);
            
        } catch (SQLException ex) {
           // Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
           
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
        return notesList;
    }
    
    public Note getNote(int noteId)
    {
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Note note =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
          try {
            ps = connection.prepareStatement("SELECT * FROM note where noteId=?;");
            ps.setInt(1, noteId);
            rs = ps.executeQuery();
      
            while (rs.next()) {
                note = new Note(rs.getInt("noteid"),rs.getDate("dateCreated"),rs.getString("contents"));
            }
            pool.freeConnection(connection);
            
        } catch (SQLException ex) {
           // Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
           
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
        return note;
    }
    
    public int delete(Note note)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "DELETE FROM note WHERE noteId = ?";
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, note.getNoteId());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
        //    Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot delete " + user.toString(), ex);
       //     throw new NotesDBException("Error deleting User");
        } finally {
            pool.freeConnection(connection);
        }
        return 0;
    }
    
}
