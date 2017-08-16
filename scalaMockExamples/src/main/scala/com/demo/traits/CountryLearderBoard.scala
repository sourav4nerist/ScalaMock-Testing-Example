package com.demo.traits

import com.demo.caseClass.CountryLeaderboardEntry

trait CountryLearderBoard {
  def addVictoryForCountry(country: String):Int
  def getTopCountries():List[CountryLeaderboardEntry]
}