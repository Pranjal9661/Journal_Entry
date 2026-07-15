package com.Practice.myThirdProject.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Service.UserService;
import com.Practice.myThirdProject.Service.WeatherService;
import com.Practice.myThirdProject.api.response.WeatherResponse;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WeatherService weatherService;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@GetMapping("/list")
	public List<User> getUsers() {
		return userService.listAllEntries();
	}
	
	@PostMapping("/addUsers")
	public void addUsers(@RequestBody User user) {
		try {
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			user.setRoles(Arrays.asList("USER"));
			userService.saveEntry(user);
		}
		catch(Exception e){
		
			
		}
	
	}
	@PostMapping("/login")
	public void login(@RequestBody User user) {

		userService.verify(user);
	}
	
	
	@PutMapping("/updateUser/{userName}")
	public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName) {
		
		try {
			User userInDb = userService.findByUserName(userName);
			if(userInDb!=null) {
				userInDb.setUserName(user.getUserName());
				userInDb.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
				userService.saveEntry(userInDb);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getWeather")
	public ResponseEntity<?> getMapping(){
		WeatherResponse todayWeather = weatherService.getWeather("INDIA");
		return new ResponseEntity<> ("Hi, Today's templearture in C is: " + todayWeather.getCurrent().getTempC() + " and in Farenheit is: " + todayWeather.getCurrent().getTempF(),HttpStatus.OK);
	}
	
	
	
}
