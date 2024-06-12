package com.project.SmartContactv1.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.SmartContactv1.Entity.Contact;
import com.project.SmartContactv1.Entity.User;
import com.project.SmartContactv1.Repository.ContactRepository;
import com.project.SmartContactv1.Repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = userRepository.getUserByEmail(userName);
		model.addAttribute("user", user);
	}

	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		return "user/user_dashboard";
	}

	@GetMapping("/add-contact")
	public String addContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "user/addContact_form";
	}

	@PostMapping("/process-contact")
	public String processContact(@Valid @ModelAttribute Contact contact,
			@RequestParam("profileImage") MultipartFile multipartFile, BindingResult bindingResult, Principal principal,
			Model model, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// If there are validation errors, return to the form view with error messages
			model.addAttribute("title", "Add Contact");
			return "user/addContact_form";
		}

		try {
			// Retrieve the user
			String name = principal.getName();

			// .................upload the contact image...............
			contact.setImage(multipartFile.getOriginalFilename());
			File saveFile = new ClassPathResource("static/img").getFile();
			// generate unique file name
			long uniquename = System.currentTimeMillis();
			Path path = Paths
					.get(saveFile.getPath() + File.pathSeparator + multipartFile.getOriginalFilename() + uniquename);

			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//			System.out.println("image is uploaded");
			// ................End upload the contact image..............

			User user = this.userRepository.getUserByEmail(name);

			// Set the user for the contact
			contact.setUser(user);

			// Save the contact
			this.contactRepository.save(contact);

			redirectAttributes.addFlashAttribute("message", "Contact added successfully");
			return "redirect:/user/add-contact"; // Redirect to the add-contact page after successful submission
		} catch (Exception e) {
			// Handle exceptions here
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "An error occurred while adding the contact");
			return "user/addContact_form";
		}
	}

	//view all contact by useer id
	@GetMapping("/viewcontact/{page}")
	public String viewContact(@PathVariable("page") Integer page, Model model, Principal principal) {
	    model.addAttribute("title", "ViewContact");

	    String email = principal.getName();
	    User user = userRepository.getUserByEmail(email);

	    PageRequest pageable = PageRequest.of(page, 1); // Note: page number starts from 0
	    Page<Contact> contacts = contactRepository.findByUserId(user.getId(), pageable);
	    
	    model.addAttribute("contacts", contacts);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", contacts.getTotalPages());

	    return "user/viewcontact";
	}

}
