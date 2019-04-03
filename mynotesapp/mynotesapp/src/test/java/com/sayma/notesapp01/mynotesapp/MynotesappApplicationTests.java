package com.sayma.notesapp01.mynotesapp;


import org.junit.Test;

import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sayma.notesapp01.mynotesapp.dao.NoteDao;
import com.sayma.notesapp01.mynotesapp.entity.Note;
import com.sayma.notesapp01.mynotesapp.service.NoteService;
import com.sayma.notesapp01.mynotesapp.service.NoteServiceImpl;




public class MynotesappApplicationTests {
    
	@InjectMocks
    NoteServiceImpl noteService;
     
    @Mock
    NoteDao dao;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void getAllNotesTest()
    {
        LocalDateTime date =  LocalDateTime.now();
    	
        String user = "Sayma";
        
    	List<Note> list = new ArrayList<Note>();
        Note empOne = new Note(0, "John", "John123", date,date,"Sayma");
        Note empTwo = new Note(0, "Sayma", "Sayma123",date,date,"Sayma");
        Note empThree = new Note(0, "Sayma", "Waugh",date,date,"Sayma");
         
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
         
        when(dao.getNotes(user)).thenReturn(list);
         
        //test
        List<Note> empList = noteService.getNotes(user);
         
        assertEquals(3, empList.size());
        verify(dao, times(1)).getNotes(user);
    }   
    
    @Test
    public void getEmployeeByIdTest()
    {
    	LocalDateTime date =  LocalDateTime.now();
    	when(dao.getNote(1)).thenReturn(new Note(1, "John", "John123", date,date,"Sayma"));
         
    	Note note = noteService.getNote(1);
         
        assertEquals("John", note.getTitle());
        assertEquals("John123", note.getDesc());
        assertEquals(date, note.getCreationDate());
        assertEquals(date, note.getUpdationDate());
    }
    @Test
    public void createEmployeeTest()
    {
    	LocalDateTime date =  LocalDateTime.now();
    	Note theNote = new Note(0, "John", "John123", date,date,"Sayma");
         
    	noteService.saveNote(theNote);
         
        verify(dao, times(1)).saveNote(theNote);
    }
}