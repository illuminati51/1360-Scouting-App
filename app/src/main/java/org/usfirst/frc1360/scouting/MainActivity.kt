package org.usfirst.frc1360.scouting

import android.app.Activity
import android.os.Bundle
import android.Manifest.permission
import android.util.Log
import android.Manifest.permission_group
import android.annotation.TargetApi
import android.bluetooth.*
import android.content.Context
import android.content.pm.PackageManager
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ScrollView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import org.usfirst.frc1360.scouting.R.layout.activity_main
import java.util.jar.Manifest

internal class MainActivity : Activity() {
    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)
        setContentView(R.layout.activity_main)

        highGoalNum.setText("0")
        lowGoalMissNum.setText("0")
        chevalDeFrisseCrossNum.setText("0")
        portcullisCrossNum.setText("0")
        lowGoalNum.setText("0")
        highGoalMissNum.setText("0")
        moatCrossNum.setText("0")
        rampartsCrossNum.setText("0")
        drawbridgeCrossNum.setText("0")
        sallyportCrossNum.setText("0")
        rockWallCrossNum.setText("0")
        roughTerrainCrossNum.setText("0")
        lowBarCrossNum.setText("0")

        lowBarCrossNumMinus.setOnClickListener {
            lowBarCrossNum.setText((lowBarCrossNum.text.toString().toInt() - 1).toString())
            lowBarCrossNum.requestFocusFromTouch()
        }

        lowBarCrossNumPlus.setOnClickListener {
            lowBarCrossNum.setText((lowBarCrossNum.text.toString().toInt() + 1).toString())
            lowBarCrossNum.requestFocusFromTouch()
        }

        roughTerrainCrossNumMinus.setOnClickListener {
            roughTerrainCrossNum.setText((roughTerrainCrossNum.text.toString().toInt() - 1).toString())
            roughTerrainCrossNum.requestFocusFromTouch()
        }

        roughTerrainCrossNumPlus.setOnClickListener {
            roughTerrainCrossNum.setText((roughTerrainCrossNum.text.toString().toInt() + 1).toString())
            roughTerrainCrossNum.requestFocusFromTouch()
        }

        rockWallCrossNumMinus.setOnClickListener {
            rockWallCrossNum.setText((rockWallCrossNum.text.toString().toInt() - 1).toString())
            rockWallCrossNum.requestFocusFromTouch()
        }

        rockWallCrossNumPlus.setOnClickListener {
            rockWallCrossNum.setText((rockWallCrossNum.text.toString().toInt() + 1).toString())
            rockWallCrossNum.requestFocusFromTouch()
        }

        sallyportCrossNumMinus.setOnClickListener {
            sallyportCrossNum.setText((sallyportCrossNum.text.toString().toInt() - 1).toString())
            sallyportCrossNum.requestFocusFromTouch()
        }

        sallyportCrossNumPlus.setOnClickListener {
            sallyportCrossNum.setText((sallyportCrossNum.text.toString().toInt() + 1).toString())
            sallyportCrossNum.requestFocusFromTouch()
        }

        drawbridgeCrossNumMinus.setOnClickListener {
            drawbridgeCrossNum.setText((drawbridgeCrossNum.text.toString().toInt() - 1).toString())
            drawbridgeCrossNum.requestFocusFromTouch()
        }

        drawbridgeCrossNumPlus.setOnClickListener {
            drawbridgeCrossNum.setText((drawbridgeCrossNum.text.toString().toInt() + 1).toString())
            drawbridgeCrossNum.requestFocusFromTouch()
        }

        rampartsCrossNumMinus.setOnClickListener {
            rampartsCrossNum.setText((rampartsCrossNum.text.toString().toInt() - 1).toString())
            rampartsCrossNum.requestFocusFromTouch()
        }

        rampartsCrossNumPlus.setOnClickListener {
            rampartsCrossNum.setText((rampartsCrossNum.text.toString().toInt() + 1).toString())
            rampartsCrossNum.requestFocusFromTouch()
        }

        moatCrossNumMinus.setOnClickListener {
            moatCrossNum.setText((moatCrossNum.text.toString().toInt() - 1).toString())
            moatCrossNum.requestFocusFromTouch()
        }

        moatCrossNumPlus.setOnClickListener {
            moatCrossNum.setText((moatCrossNum.text.toString().toInt() + 1).toString())
            moatCrossNum.requestFocusFromTouch()
        }

        portcullisCrossNumMinus.setOnClickListener {
            portcullisCrossNum.setText((portcullisCrossNum.text.toString().toInt() - 1).toString())
            portcullisCrossNum.requestFocusFromTouch()
        }

        portcullisCrossNumPlus.setOnClickListener {
            portcullisCrossNum.setText((portcullisCrossNum.text.toString().toInt() + 1).toString())
            portcullisCrossNum.requestFocusFromTouch()
        }

        chevalDeFrisseCrossNumMinus.setOnClickListener {
            chevalDeFrisseCrossNum.setText((chevalDeFrisseCrossNum.text.toString().toInt() - 1).toString())
            chevalDeFrisseCrossNum.requestFocusFromTouch()
        }

        chevalDeFrisseCrossNumPlus.setOnClickListener {
            chevalDeFrisseCrossNum.setText((chevalDeFrisseCrossNum.text.toString().toInt() + 1).toString())
            chevalDeFrisseCrossNum.requestFocusFromTouch()
        }

        lowGoalMissNumMinus.setOnClickListener {
            lowGoalMissNum.setText((lowGoalMissNum.text.toString().toInt() - 1).toString())
            lowGoalMissNum.requestFocusFromTouch()
        }

        lowGoalMissNumPlus.setOnClickListener {
            lowGoalMissNum.setText((lowGoalMissNum.text.toString().toInt() + 1).toString())
            lowGoalMissNum.requestFocusFromTouch()
        }

        lowGoalNumMinus.setOnClickListener {
            lowGoalNum.setText((lowGoalNum.text.toString().toInt() - 1).toString())
            lowGoalNum.requestFocusFromTouch()
        }

        lowGoalNumPlus.setOnClickListener {
            lowGoalNum.setText((lowGoalNum.text.toString().toInt() + 1).toString())
            lowGoalNum.requestFocusFromTouch()
        }

        highGoalMissNumMinus.setOnClickListener {
            highGoalMissNum.setText((highGoalMissNum.text.toString().toInt() - 1).toString())
            highGoalMissNum.requestFocusFromTouch()
        }

        highGoalMissNumPlus.setOnClickListener {
            highGoalMissNum.setText((highGoalMissNum.text.toString().toInt() + 1).toString())
            highGoalMissNum.requestFocusFromTouch()
        }

        highGoalNumMinus.setOnClickListener {
            highGoalNum.setText((highGoalNum.text.toString().toInt() - 1).toString())
            highGoalNum.requestFocusFromTouch()
        }

        highGoalNumPlus.setOnClickListener {
            highGoalNum.setText((highGoalNum.text.toString().toInt() + 1).toString())
            highGoalNum.requestFocusFromTouch()
        }

        submitButton.setOnClickListener {
            submitButton.requestFocusFromTouch()
            var connection = Connection()
            connection.connect()
        }
    }
}
