package com.baseballanayltics.baseballstats.controller;

import ch.qos.logback.core.model.Model;
import com.baseballanayltics.baseballstats.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/list")
    public String listPlayers(Model model) {

        return null;
    }




}
