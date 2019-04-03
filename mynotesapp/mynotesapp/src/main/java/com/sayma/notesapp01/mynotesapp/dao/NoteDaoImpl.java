package com.sayma.notesapp01.mynotesapp.dao;
import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sayma.notesapp01.mynotesapp.entity.Note;


@Repository
public class NoteDaoImpl implements NoteDao{
	// define field for entitymanager
		private EntityManager entityManager;
		
		// set up constructor injection
		@Autowired
		public NoteDaoImpl(EntityManager theEntityManager) {

			entityManager = theEntityManager;
		}
		
		@Override
		public List<Note> getNotes(String user) {
			// get the current hibernate session
			Session session = entityManager.unwrap(Session.class);
			// create a query
			
			Query <Note> query = session.createQuery("from Note where userid = :theName",Note.class);
			query.setParameter("theName", user);
			// execute query and get and return result
			List <Note> Notes = query.getResultList();
			
			return Notes;
		}
		
		@Override
		public Note getNote(int theId) {
			
			Session session = entityManager.unwrap(Session.class);
			
			Note theNote = session.get(Note.class, theId);
			
			return theNote;
		}

		@Override
		public void saveNote(Note theNote) {
		
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(theNote);
			
		}
		
		@Override
		public void updateNote(Note theNote) {
		
			Session session = entityManager.unwrap(Session.class);
			
			Note theNote1 = session.get(Note.class, theNote.getId());
			
			theNote1.setUpdationDate(theNote.getUpdationDate());
			theNote1.setTitle(theNote.getTitle());
			theNote1.setDesc(theNote.getDesc());
			
			session.saveOrUpdate(theNote1);
			
		}	

		@Override
		public void deleteNote(int theId) {
			
			Session session = entityManager.unwrap(Session.class);
			
			Query theQuery = session.createQuery("delete from Note where id=:NoteId");
			
			theQuery.setParameter("NoteId", theId);
			
			theQuery.executeUpdate();		
			
		}

		@Override
		public List<Note> searchNote(String theStringName,String user) {
			
			System.out.println("hghgh");
			Session session = entityManager.unwrap(Session.class);
			Query <Note> query;
			//create query ...sort by last name
			if(theStringName != null && theStringName.trim().length()>0) {
			query = session.createQuery("from Note c where lower(c.title) like :theName and c.userid = :theUser "+
																	" order by c.title "
																	,Note.class);
			
			query.setParameter("theName", "%" + theStringName.toLowerCase() +"%");
			query.setParameter("theUser", user);
			}
			else
			{
				query = session.createQuery("from Note where userid = :theUser order by title",Note.class);	
				
				query.setParameter("theUser", user);
			}
			// get result list from query
			
			List <Note> NoteList = query.getResultList();
			System.out.println(NoteList);
			//return list of customers
			
			return NoteList;
		}

}
