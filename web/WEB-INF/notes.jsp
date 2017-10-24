<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <link rel="stylesheet" href="<c:url value='styles/notes.css' />" />
    </head>
    
    <body>
        <h1>Manage Notes</h1>

        <table>
            <tr>
                <th>NoteId</th>
                <th>dateCreated</th>
                <th>Contents</th>
            </tr>
            <c:forEach var="note" items="${notesList}">
                <tr>
                    <td>${note.id}</td>
                    <td>${note.date}</td>
                    <td>${note.contents}</td>
                    <td>
                        <form action="notes" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedNoteId" value="${note.id}">
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedNoteId" value="${note.id}}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

            <h3>Edit User</h3>
            <form action="notes?action=edit" method="POST">
                Note ID: <input type="text" name="id" value="${note.id} disbaled"><br>
                Date Created: <input type="text" name="date" value="${note.date} disbaled"><br>
                Contents:<input type="text" name="contents" value="${note.contents}"><br>
                <input type="submit" value="Save">
            </form>
 
    </body>
</html>
