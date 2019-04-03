package com.sayma.notesapp01.mynotesapp.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayma.notesapp01.mynotesapp.dao.NoteDao;
import com.sayma.notesapp01.mynotesapp.entity.Note;
import javax.transaction.Transactional;


	@Service
	public class NoteServiceImpl implements NoteService {

		@Autowired
		private NoteDao noteDao;
		
		@Override
		@Transactional
		public List<Note> getNotes(String user) {
			
			return noteDao.getNotes(user);
		}

		@Override
		@Transactional
		public 	Note getNote(int theId) {
			
			return noteDao.getNote(theId);
		}

		@Override
		@Transactional
		public void saveNote(Note theNote) {
			
			LocalDateTime date = LocalDateTime.now();
			if(theNote.getId() == 0) {
				theNote.setCreationDate(date);
				theNote.setUpdationDate(date);	
				
				noteDao.saveNote(theNote);
			}
			else {
				theNote.setUpdationDate(date);
				noteDao.updateNote(theNote);
			}
			
			
		}

		@Override
		@Transactional
		public void deleteNote(int theId) {
			
			noteDao.deleteNote(theId);
			
		}

		@Override
		@Transactional
		public List<Note> searchNote(String theStringName,String user) {
			
			
			System.out.println("first");
			return noteDao.searchNote(theStringName,user);
		}

}
