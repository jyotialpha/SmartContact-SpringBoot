package com.project.SmartContactv1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.SmartContactv1.Entity.User;
import com.project.SmartContactv1.ErrorHelper.Message;
import com.project.SmartContactv1.Repository.UserRepository;
import com.project.SmartContactv1.Service.CountryPhoneCodeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//Note:-dont use here Rest controller u will not redirect to html page
@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	// for home page
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Home-smart contact manager");
		return "home";
	}

	// for About page
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-smart contact manager");
		return "about";
	}

	// For signup page
	private final CountryPhoneCodeService service;

	public HomeController(CountryPhoneCodeService service) {
		this.service = service;
	}

	@RequestMapping("/signup")
	public String getCountryPhoneCodes(Model model) {
		// use in signup page for countrycode dropdown
		model.addAttribute("countryPhoneCodes", service.getCountryPhoneCodes());
		model.addAttribute("title", "Signup-smart contact manager");
		model.addAttribute("user", new User());

		return "signup"; // This should match the name of your Thymeleaf template file
	}

	// Forloginpage
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login-smart contact manager");
		return "login";
	}

	// for Registring User

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		model.addAttribute("countryPhoneCodes", service.getCountryPhoneCodes());
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("user", user);
				return "signup";
			}
			
			if (!agreement) {
				System.out.println("you have not agreed the term and condition");
				throw new Exception("you have not agreed the term and condition");
			}
			
			
			user.setEnable("true");
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement:" + agreement);
			System.out.println("User:" + user);

			this.userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Signup Successfully!!", "alert-success"));
			System.out.println("Message set successfully!"); // Add this line for debugging

			// Check if the message is being set correctly
			Object msg = session.getAttribute("message");
			System.out.println("Message: " + msg);

			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something Went wrong" + e.getMessage(), "alert-danger"));
			System.out.println("Error occurred. Message not set."); // Add this line for debugging
			return "signup";
		}
		
	}
}