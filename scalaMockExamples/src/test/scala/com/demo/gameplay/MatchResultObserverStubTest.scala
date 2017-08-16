package com.demo.gameplay

//import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory
import com.demo.caseClass.Player
import com.demo.traits.PlayerDatabase
import com.demo.traits.CountryLearderBoard
import org.scalatest.FlatSpec
import com.demo.caseClass.Matchresult
import com.demo.caseClass.CountryLeaderboardEntry

class MatchResultObserverStubTest extends FlatSpec with MockFactory {
  val winner = Player(111,"Sourav","India")
  val loser = Player(222,"Bob","Korea")
  val winnerCountry = CountryLeaderboardEntry("India",5)
  
  "MatchResultObserver" should "update correct country after a win" in {
  
    val userDetailsServiceStub = stub[PlayerDatabase]
    val countryLeaderBoardStub = stub[CountryLearderBoard]    
       
     //configure stubs
    (userDetailsServiceStub.getPlayerById (_)).when(winner.playerId).returns(winner)
    //(userDetailsServiceMock.getPlayerById(_)).when(loser.playerId).returns(loser)
    
    (countryLeaderBoardStub.addVictoryForCountry _) when("India") returns(winnerCountry.points+1)
    
    //test run of system
    val matchResultObserver = new MatchResultObserver(userDetailsServiceStub,countryLeaderBoardStub)
    val newPoints = matchResultObserver.recordMatchResult(Matchresult(winner.playerId,loser.playerId))
    
    //verifying countryLeaderBoardStub stub
   //(countryLeaderBoardStub.addVictoryForCountry _).verify("India")
     
    assert(newPoints === 6)
   
  }
}
