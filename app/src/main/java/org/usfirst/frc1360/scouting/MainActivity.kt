package org.usfirst.frc1360.scouting

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}
