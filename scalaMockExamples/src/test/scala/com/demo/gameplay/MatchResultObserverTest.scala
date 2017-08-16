package com.demo.gameplay

//import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory
import com.demo.caseClass.Player
import com.demo.traits.PlayerDatabase
import com.demo.traits.CountryLearderBoard
import org.scalatest.FlatSpec
import com.demo.caseClass.Matchresult
import com.demo.caseClass.CountryLeaderboardEntry

class MatchResultObserverTest extends FlatSpec with MockFactory {
  val winner = Player(111,"Sourav","India")
  val loser = Player(222,"Bob","Korea")
  val winnerCountry = CountryLeaderboardEntry("India",5)
  
  "MatchResultObserver" should "update correct country after a win" in {
  
    val userDetailsServiceMock = mock[PlayerDatabase]
    val countryLeaderBoardMock = mock[CountryLearderBoard]
    
    //set expectation for the mock
     (countryLeaderBoardMock.addVictoryForCountry _) expects("India") returns(winnerCountry.points+1)
    
    
          
     //configure stubs
    (userDetailsServiceMock.getPlayerById (_)) expects(winner.playerId) returns(winner)
    //(userDetailsServiceMock.getPlayerById(_)).when(loser.playerId).returns(loser)
    
    //test run of system
    val matchResultObserver = new MatchResultObserver(userDetailsServiceMock,countryLeaderBoardMock)
    val newPoints =matchResultObserver.recordMatchResult(Matchresult(winner.playerId,loser.playerId))
    assert(newPoints === 6)
   
  }
}