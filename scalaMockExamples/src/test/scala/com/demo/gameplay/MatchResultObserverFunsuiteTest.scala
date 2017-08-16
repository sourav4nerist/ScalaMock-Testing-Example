package com.demo.gameplay

import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory
import com.demo.caseClass.Player
import com.demo.traits.CountryLearderBoard
import com.demo.traits.PlayerDatabase
import com.demo.caseClass.Matchresult
import org.scalatest.FlatSpec

//import org.scalatest.Matchers
//import org.scalatest.FlatSpec

class MatchResultObserverFunsuiteTest extends FunSuite with MockFactory{
  val winner = Player(111,"Sourav","India")
  val loser = Player(222,"Bob","Korea")
  
    val countryLeaderBoardMock = mock[CountryLearderBoard]
    val playerDatabaseStub = stub[PlayerDatabase]
  
   test("Testing MatchResultObserve with FunSuite"){
  
    // Creating Stub of PlayerDatabase And Mock for CountryLeaderBoard
    (countryLeaderBoardMock.addVictoryForCountry(_)).expects("India")
    
    (playerDatabaseStub.getPlayerById(_)).when(winner.playerId).returns(winner)
    (playerDatabaseStub.getPlayerById(_)).when(loser.playerId).returns(loser)
    
       val matchResulObserver = new MatchResultObserver(playerDatabaseStub,countryLeaderBoardMock)
  matchResulObserver.recordMatchResult(Matchresult(winner.playerId,loser.playerId))
    
    
  }
    
}