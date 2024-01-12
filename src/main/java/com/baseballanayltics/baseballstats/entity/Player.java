package com.baseballanayltics.baseballstats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table
@Data
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // Identity works well with mySQL
    private Integer id;                                         // Primary Key
    private String name;
    private String team;
    private String pos;
    private Integer age;
    private Integer gamesPlayed;
    private Integer atBats;
    private Integer runsScoredAllowed;
    private Integer hitsHitsAllowed;
    private Integer doubles;
    private Integer triples;
    private Integer homeRunsHit;
    private Integer runBattedIn;
    private Integer stolenBases;
    private Integer caughtStealing;
    private Integer baseballOnWalks;
    private Integer strikeOuts;
    private Integer shutOuts;
    private Integer sacrificeFlies;
    private Integer timesHitByPitch;
    private Double average;
    private Double obp;
    private Double totalBasesAtBat;
    private Double onBaseSlug;

    public Player() {/* No Args constructor */}

    /* All args exception PK constructor */
    public Player(String name, String team, String pos, int age, int gamesPlayed, int atBats,
                  int runsScoredAllowed, int hitsHitsAllowed, int doubles, int triples, int homeRunsHit,
                  int runBattedIn, int stolenBases, int caughtStealing, int baseballOnWalks, int strikeOuts,
                  int shutOuts, int sacrificeFlies, int timesHitByPitch, double average, double obp,
                  double totalBasesAtBat, double onBaseSlug) {
        this.name = name;
        this.team = team;
        this.pos = pos;
        this.age = age;
        this.gamesPlayed = gamesPlayed;
        this.atBats = atBats;
        this.runsScoredAllowed = runsScoredAllowed;
        this.hitsHitsAllowed = hitsHitsAllowed;
        this.doubles = doubles;
        this.triples = triples;
        this.homeRunsHit = homeRunsHit;
        this.runBattedIn = runBattedIn;
        this.stolenBases = stolenBases;
        this.caughtStealing = caughtStealing;
        this.baseballOnWalks = baseballOnWalks;
        this.strikeOuts = strikeOuts;
        this.shutOuts = shutOuts;
        this.sacrificeFlies = sacrificeFlies;
        this.timesHitByPitch = timesHitByPitch;
        this.average = average;
        this.obp = obp;
        this.totalBasesAtBat = totalBasesAtBat;
        this.onBaseSlug = onBaseSlug;
    }


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
