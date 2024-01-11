package com.baseballanayltics.baseballstats.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // Identity works well with mySQL
    private Integer id;                                         // Primary Key

    private String name;
    private String team;
    private String pos;
    private int age;
    private int gamesPlayed;
    private int atBats;
    private int runsScoredAllowed;
    private int hitsHitsAllowed;
    private int doubles;
    private int triples;
    private int homeRunsHit;
    private int runBattedIn;
    private int stolenBases;
    private int caughtStealing;
    private int baseballOnWalks;
    private int strikeOuts;
    private int shutOuts;
    private int sacrificeFlies;
    private int timesHitByPitch;
    private double average;
    private double obp;
    private double totalBasesAtBat;
    private double onBaseSlug;

    public void updateFields(Player player) {
        /* This method updates fields */
        this.name = player.name;
        this.team = player.team;
        this.pos = player.pos;
        this.age = player.age;
        this.gamesPlayed = player.gamesPlayed;
        this.atBats = player.atBats;
        this.runsScoredAllowed = player.runsScoredAllowed;
        this.hitsHitsAllowed = player.hitsHitsAllowed;
        this.doubles = player.doubles;
        this.triples = player.triples;
        this.homeRunsHit = player.homeRunsHit;
        this.runBattedIn = player.runBattedIn;
        this.stolenBases = player.stolenBases;
        this.caughtStealing = player.caughtStealing;
        this.baseballOnWalks = player.baseballOnWalks;
        this.strikeOuts = player.strikeOuts;
        this.shutOuts = player.shutOuts;
        this.sacrificeFlies = player.sacrificeFlies;
        this.timesHitByPitch = player.timesHitByPitch;
        this.average = player.average;
        this.obp = player.obp;
        this.totalBasesAtBat = player.totalBasesAtBat;
        this.onBaseSlug = player.onBaseSlug;
    }

}
