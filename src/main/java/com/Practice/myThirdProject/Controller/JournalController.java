package com.Practice.myThirdProject.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Practice.myThirdProject.Entity.Journal;
import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Service.JournalService;
import com.Practice.myThirdProject.Service.UserService;

@RestController
@RequestMapping("/api/auth/journal")
public class JournalController {
	
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/id/{myid}")
	public ResponseEntity<?> getById(@PathVariable String myid){
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 String userName = authentication.getName();
			 User user = userService.findByUserName(userName);
			 List<Journal>  collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
			 if(!collect.isEmpty()) {
				 Optional<Journal> journalEntry = journalService.getentryByid(myid);
				 if(journalEntry.isPresent()) {
						return new ResponseEntity<>(journalEntry, HttpStatus.OK);
				 }
			 }
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/add/{userName}")
	public ResponseEntity<?> addJournal(@RequestBody Journal entryJournal,@PathVariable String userName) {
		journalService.saveEntry(entryJournal,userName);
		return new ResponseEntity<>(HttpStatus.OK);
	
	}
	
	  @GetMapping("/list/{userName}") 
	  public ResponseEntity<?> getUserEntries(@PathVariable String userName){
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String userNameinContext = authentication.getName();
		  User byUserName = userService.findByUserName(userNameinContext);
		  if(byUserName!=null) {
			  List<Journal> list = byUserName.getJournalEntries();
			  return new ResponseEntity<>(list,HttpStatus.OK); 
		  }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	  
	
		  @PutMapping("/updateDescription/{id}/{userName}") 
		  public ResponseEntity<?>update(@PathVariable String id,@RequestBody Journal entry) {
			  try {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String userNameinContext = authentication.getName();
		  User user = userService.findByUserName(userNameinContext);
			 List<Journal>  collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
			 if(!collect.isEmpty()) {
				 Optional<Journal> journalEntry = journalService.getentryByid(id);
				 if(journalEntry.isPresent()) {  
						Journal j= journalEntry.get();
					  j.setDescription((entry.getDescription() != null &&
					  !entry.getDescription().equals("") )? entry.getDescription() :
					  j.getDescription()); j.setTitle((entry.getTitle() != null &&
					  !entry.getTitle().equals("")) ? entry.getTitle() : j.getTitle());
					  return new ResponseEntity<>(j,HttpStatus.OK);
					  }
			 } 
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		  } 
			  catch (Exception e) {
				  // TODO: handle exception return new
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND); } 
		  }
	
	  
	  @DeleteMapping("/delete/{id}") 
	  public ResponseEntity<?> delete(@PathVariable String id) {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String userNameinContext = authentication.getName();  
		  boolean removed =   journalService.deleteEntryById(id,userNameinContext);
		  if(removed) {
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

}
