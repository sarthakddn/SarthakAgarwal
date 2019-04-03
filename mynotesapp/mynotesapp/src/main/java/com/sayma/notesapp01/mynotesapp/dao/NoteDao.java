package com.sayma.notesapp01.mynotesapp.dao;

import java.util.List;

import com.sayma.notesapp01.mynotesapp.entity.Note;




public interface NoteDao {
	
	public List<Note> getNotes(String user);

	public Note getNote(int theId);
	
	public void saveNote(Note theNote);
	
	public void updateNote(Note theNote);
	
	public void deleteNote(int theId);
	
	public List<Note> searchNote(String theStringName,String user);	
}