package com.demo.traits

import com.demo.caseClass.Player

trait PlayerDatabase {
  def getPlayerById(playerId: Int): Player
}