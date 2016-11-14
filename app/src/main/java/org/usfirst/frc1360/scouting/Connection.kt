package org.usfirst.frc1360.scouting

import android.app.Activity
import android.app.Notification
import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.*
import android.support.v4.content.ContextCompat

/**
 * Created by Jason on 2016-11-13.
 */
public class Connection {
    private var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    init {
        if (bluetoothAdapter == null)
            throw NullPointerException("Device does not support bluetooth")

        bluetoothAdapter.enable()

    }

    public fun connect() {
        if (bluetoothAdapter.scanMode != BluetoothAdapter.SCAN_MODE_CONNECTABLE) {
            var discoverIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }
        bluetoothAdapter.startDiscovery()
    }
}