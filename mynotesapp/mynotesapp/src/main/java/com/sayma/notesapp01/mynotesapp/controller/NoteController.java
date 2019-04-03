package com.sayma.notesapp01.mynotesapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sayma.notesapp01.mynotesapp.entity.Note;
import com.sayma.notesapp01.mynotesapp.service.NoteService;


@Controller
@RequestMapping("/note")
public class NoteController {

@Autowired
private NoteService noteService;

@InitBinder
public void initBinder(WebDataBinder dataBinder) {
	
	StringTrimmerEditor ste = new StringTrimmerEditor(true);
	dataBinder.registerCustomEditor(String.class, ste);
} 

//@RequestMapping("/list")
@GetMapping("/list")
public String listCustomers(Model theModel) {
	
	String user=null;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	  user = ((UserDetails)principal).getUsername();
	} else {
	  user = principal.toString();
	}
	
	
	List <Note> notes = noteService.getNotes(user);
	
	theModel.addAttribute("notes",notes);
	
	return "list-notes";
}

@GetMapping("/showFormForAdd")
public String showFormForAdd(Model theModel) {
	
	// create model attribute to bind form data
	
	Note theNote = new Note();
	
	theModel.addAttribute("note", theNote);
	
	return "note-form";
}	

@GetMapping("/showFormForUpdate")
public String showFormForUpdate(@RequestParam("noteId") int theId,Model theModel) {
	
	// create model attribute to bind form data

	Note theNote = noteService.getNote(theId);
	theModel.addAttribute("note", theNote);
	
	return "note-form";
}

@PostMapping("/saveNote")
public String saveNote(@ModelAttribute("note") @Valid Note theNote,BindingResult theBindngResult) {
	
	
	// save the customer using our service
	if(theBindngResult.hasErrors()) {
		return "note-form";
	}
	else
	{   
		
		String user=null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  user = ((UserDetails)principal).getUsername();
		} else {
		  user = principal.toString();
		}
		theNote.setUserid(user);
		noteService.saveNote(theNote);
		
		return "redirect:/note/list";
	}

	
}	

@GetMapping("/delete")
public String deleteCustomer(@RequestParam("noteId") int theId,Model theModel) {
	
	// create model attribute to bind form data

	
	noteService.deleteNote(theId);
	
	
	
	return "redirect:/note/list";
}	

@PostMapping("/search")
public String searchNotes(@RequestParam("theSearchName") String theSearchName,Model theModel) {
	String user=null;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	  user = ((UserDetails)principal).getUsername();
	} else {
	  user = principal.toString();
	}
	
	List <Note> notes = noteService.searchNote(theSearchName,user);

	theModel.addAttribute("notes",notes);
	
	return "list-notes";
	
}		

}







