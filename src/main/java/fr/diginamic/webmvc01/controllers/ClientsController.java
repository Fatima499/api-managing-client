package fr.diginamic.webmvc01.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.diginamic.webmvc01.entities.Client;
import fr.diginamic.webmvc01.repository.CrudClientRepo;


@Controller
@RequestMapping("/client")
public class ClientsController {
	
	@Autowired
	CrudClientRepo cr;
	
	public ClientsController() {
	  
	}
	
	@GetMapping("/clients")
	public String findall(Model model) {
	   model.addAttribute("clients", (List<Client>) cr.findAll());
	   model.addAttribute("titre","Liste des clients");
	   return "clients/Liste";
	}

	
	@GetMapping("/add")
	public String addT(Model model) {
	  model.addAttribute("clientForm", new Client() );
	  model.addAttribute("titre","Ajout client");
	  return "clients/add";
	}
	
	
	//donnee transmis par formulaire
	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("clientForm") Client clientForm)
	{
		cr.save(clientForm);
		return "redirect:/client/clients";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception 
	{
	    Optional<Client> c = cr.findById(pid);
	    if(c.isEmpty()) 
	    {
	       /**
	        * Je déclenche l'exception ClientError
     	    * package fr.diginamic.Rest01.exceptions;
	        * Exception Fonctionnelle = métier
	        */
	        throw( new Exception("Client id :"+pid+" non trouvé !"));
	    }
	    cr.deleteById(pid);
	    return  "redirect:/client/clients";
	}


}
