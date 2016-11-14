package org.usfirst.frc1360.scouting

import android.app.Activity
import android.app.Notification
import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.*
import android.support.v4.content.ContextCompat
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import java.io.IOException
import java.util.*

/**
 * Created by Jason on 2016-11-13.
 */
public class Connection : Activity() {
    private var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var serverSocket: BluetoothServerSocket? = null
    private var socket: BluetoothSocket? = null
    private var device: BluetoothDevice? = null
    private var hostingServer: Boolean = false
    private var connectedToServer: Boolean = false

    init {
        if (bluetoothAdapter == null)
            throw NullPointerException("Device does not support bluetooth")

        bluetoothAdapter.enable()
    }

    public fun connect(bluetoothDevice: BluetoothDevice) {
        var temp: BluetoothSocket? = null
        device = bluetoothDevice

        try {
            temp = device!!.createRfcommSocketToServiceRecord(UUID.randomUUID())
        } catch (e: IOException) { }
        socket = temp

        bluetoothAdapter.cancelDiscovery()

        try {
            socket!!.connect()
        } catch (e: IOException) {
            try {
                socket!!.close()
            } catch (ee: IOException) {}
            return
        }

        //data transfer
    }

    public fun createServer() {
        hostingServer = true
        serverSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(android.os.Build.MODEL, UUID.randomUUID())
        var socket: BluetoothSocket
        while (true) {
            try {
                socket = serverSocket!!.accept()
            } catch (e: IOException) {
                break
            }

            if (socket != null) {
                //data transfer
                serverSocket!!.close()
                break
            }
        }
    }

    public fun cancelConnection() {
        if (hostingServer) serverSocket!!.close() else if (connectedToServer) /**/ else throw Exception("Neither connected nor hosting")
    }
}