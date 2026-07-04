package com.Practice.myThirdProject.Service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Practice.myThirdProject.Entity.Journal;
import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Repository.JournalRepository;

@Service
public class JournalService {
	
	
	@Autowired
	private JournalRepository repo;
	
	@Autowired
	private UserService userService;
	
	public Optional<Journal> getentryByid(String id) {
		
		return repo.findById(id);
	}

	@Transactional
	public void saveEntry(Journal entryJournal, String userName) {
		
		try {
			User byUserName = userService.findByUserName(userName);
			Journal save = repo.save(entryJournal);
			byUserName.getJournalEntries().add(save);
			userService.saveEntry(byUserName);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw new RuntimeErrorException(null," Some Error occured while exceuting");
		}
		
		
	}
	public void saveEntry(Journal entryJournal) {
		// TODO Auto-generated method stub
		repo.save(entryJournal);
	}

	public List<Journal> listAllEntries() {
		// TODO Auto-generated method stub
		return repo.findAll();
		
	}
	@Transactional
	public Boolean deleteEntryById(String id, String userName ) {
		// TODO Auto-generated method stub
		try {
			boolean removed = false;
			User user = userService.findByUserName(userName);
			  removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
			 if(removed) {
				 userService.saveEntry(user);
					repo.deleteById(id); 
			 }
			 return removed;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw new RuntimeException("An error Occured while removing the entry: " ,e);
		}
		  
	}

}
