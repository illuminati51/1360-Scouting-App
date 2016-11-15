package org.usfirst.frc1360.scouting

public class Report(teamNum: Int, matchNum: Int, autoHighGoal: Boolean, autoLowGoal: Boolean, autoCross: Boolean, lowGoals: Int, lowGoalsMissed: Int, highGoals: Int, highGoalsMissed: Int, chevalDeFrisseCrossed: Int, portcullisCrossed: Int, moatCrossed: Int, rampartsCrossed: Int, drawbridgeCrossed: Int, sallyportCrossed: Int, rockWallCrossed: Int, roughTerrainCrossed: Int, lowBarCrossed: Int) {
    var teamNum: Int = teamNum; get private set
    var matchNum: Int = matchNum; get private set
    var autoHighGoal: Boolean = autoHighGoal; get private set
    var autoLowGoal: Boolean = autoLowGoal; get private set
    var autoCross: Boolean = autoCross; get private set

    var lowGoals: Int = lowGoals; get private set
    var lowGoalsMissed: Int = lowGoalsMissed; get private set
    var highGoals: Int = highGoals; get private set
    var highGoalsMissed: Int = highGoalsMissed; get private set

    var chevalDeFrisseCrossed: Int = chevalDeFrisseCrossed; get private set
    var portcullisCrossed: Int = portcullisCrossed; get private set
    var moatCrossed: Int = moatCrossed; get private set
    var rampartsCrossed: Int = rampartsCrossed; get private set
    var drawbridgeCrossed: Int = drawbridgeCrossed; get private set
    var sallyportCrossed: Int = sallyportCrossed; get private set
    var rockWallCrossed: Int = rockWallCrossed; get private set
    var roughTerrainCrossed: Int = roughTerrainCrossed; get private set
    var lowBarCrossed: Int = lowBarCrossed; get private set
}
