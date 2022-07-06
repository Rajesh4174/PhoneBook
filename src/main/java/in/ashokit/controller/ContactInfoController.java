package in.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.AppConstants;
import in.ashokit.entity.Contact;
import in.ashokit.props.AppProps;
import in.ashokit.service.ContactService;

@Controller
public class ContactInfoController {

	@Autowired
	private AppProps appProps;

	@Autowired
	private ContactService contactService;

	/**
	 * This method is used to load Contact Info Page
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/contact")
	public String loadContactForm(Model model) {
		Contact contactObj = new Contact();
		model.addAttribute("contact", contactObj);
		return AppConstants.CONTACT_INFO;
	}

	@PostMapping("/saveContact")
	public String saveContact(Contact contact, Model model) {
		boolean isSaved = contactService.saveContact(contact);

		Map<String, String> messages = appProps.getMessages();

		if (isSaved) {
			model.addAttribute(AppConstants.SUCCESS, messages.get(AppConstants.SAVE_SUCESS));
		} else {
			model.addAttribute(AppConstants.FAIL, messages.get(AppConstants.SAVE_FAIL));
		}
		return AppConstants.CONTACT_INFO;
	}

	@GetMapping("/viewContacts")
	public String viewAllContacts(Model model) {
		List<Contact> allContacts = contactService.getAllContacts();
		model.addAttribute(AppConstants.CONTACTS, allContacts);
		return AppConstants.CONTACTS;
	}

}
