package com.demo.gameplay

import com.demo.traits.CountryLearderBoard
import com.demo.traits.PlayerDatabase
import com.demo.caseClass.Matchresult

class MatchResultObserver(playerDatabase: PlayerDatabase, countryLeaderBoard: CountryLearderBoard) {
  
  def recordMatchResult(result:Matchresult):Int={
    val player = playerDatabase.getPlayerById(result.winner)
    countryLeaderBoard.addVictoryForCountry(player.country)
  }
}