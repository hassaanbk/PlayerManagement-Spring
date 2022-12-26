package com.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepo;

	@GetMapping("/player")
	public String home(Player player) {
		return "add";
	}

	@PostMapping("/add")
	public String add(@Valid Player player, BindingResult result, Model model) {
		if (result.hasErrors())
			return "add";

		playerRepo.save(player);
		model.addAttribute("players", playerRepo.findAll());
		return "index";
	}

	
	@PostMapping("/exists")
	public @ResponseBody String exists(@RequestParam("playerID") int playerId, Model model) {
		if (!playerRepo.existsById(playerId))
			return "Player does not exist";

		model.addAttribute("player",playerRepo.findById(playerId));
		return "exists";
	}
	
}
