package com.project.SmartContactv1.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.SmartContactv1.Entity.User;
import com.project.SmartContactv1.Repository.UserRepository;

import org.springframework.ui.Model;
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		//get username from login page
		String userName = principal.getName();
		System.out.println("UserName"+userName);
		//get userdetails by UserName
		User user = userRepository.getUserByEmail(userName);
		System.out.println("UserDetails"+user);
		
        model.addAttribute("user", user);
		return "user/user_dashboard";
	}

}
