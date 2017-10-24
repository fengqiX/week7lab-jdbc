/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 715583
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String action = req.getParameter("action");
       NoteService ns = new NoteService();
       String url = "/WEB-INF/notes.jsp";
       
       if(action!=null && action.equals("delete"))
       {
           String noteId = req.getParameter("selectedNoteId");
           if(noteId==null) {}
           else
           {
               ns.delete(Integer.parseInt(noteId));
           }
       }
       if(action!=null && action.equals("edit"))
       {
           String noteId =req.getParameter("id");
           String contents =req.getParameter("contents");
           
           if(contents==null) {}
           else
           {
               ns.update(Integer.parseInt(noteId), contents);
           }
       }
       ArrayList<Note> notesList= (ArrayList<Note>) ns.getAll();
       if(notesList==null) notesList=new ArrayList<Note>();
       
       getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");
       NoteService ns = new NoteService();
       ArrayList<Note> notesList= (ArrayList<Note>) ns.getAll();
       if(notesList==null) notesList=new ArrayList<Note>();
       String url = "/WEB-INF/notes.jsp";
       
       req.setAttribute("notesList", notesList);
       
       if(action!=null && action.equals("view"))
       {
           int noteId = Integer.parseInt(req.getParameter("selectedNoteId"));
           Note note = ns.getNote(noteId);
           req.setAttribute("note", note);
       }
       getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
