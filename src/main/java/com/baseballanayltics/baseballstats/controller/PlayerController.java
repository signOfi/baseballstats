package com.baseballanayltics.baseballstats.controller;

import com.baseballanayltics.baseballstats.entity.Player;
import com.baseballanayltics.baseballstats.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/players")
public class PlayerController {

    private final PlayerServiceImpl playerService;

    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/list")
    public String listPlayers(Model model) {
        /*
         * Get Players from Database
         * Add attributes to MVC
         */
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "player-list";
    }

    @GetMapping("/formForAdd")
    public String formForAdd(Model model) {
        Player player = new Player();
        model.addAttribute("player", player);
        return "player-form";
    }

    @GetMapping("/search")
    public String searchById(
            @RequestParam Integer id,
            Model model
    ) {

        // Get the player by id
        Optional<Player> player = playerService.getPlayerById(id);

        // If player exist pass else give null
        if (player.isPresent()) {
            model.addAttribute("player", player.get());
            return "player";
        } else {
            return "redirect:/players/list";
        }
    }

    @PostMapping("/save")
    public String savePlayer(
            @ModelAttribute("player") Player player
    ) {
        playerService.createNewPlayer(player);
        return "redirect:/players/list";
    }

    @GetMapping("formForUpdate")
    public String updateForm(
            @RequestParam("playerId") int id,
            Model model) {

        Optional<Player> player = playerService.getPlayerById(id);
        model.addAttribute("player", player);

        return "player-form";

    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam("playerId") int id
    ) {

        playerService.deletePlayer(id);
        return "redirect:/players/list";
    }



}
