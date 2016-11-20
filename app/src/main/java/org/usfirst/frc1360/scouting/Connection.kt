package org.usfirst.frc1360.scouting

import android.app.Activity
import android.app.Notification
import android.bluetooth.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.net.Uri
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.ParcelUuid
import android.renderscript.ScriptGroup
import android.support.v4.app.ActivityCompat.*
import android.support.v4.content.ContextCompat
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Toast
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*
import java.net.NetworkInterface.getNetworkInterfaces
import kotlin.concurrent.thread


public class Connection : BroadcastReceiver() {
    private var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var serverSocket: BluetoothServerSocket? = null
    private var socket: BluetoothSocket? = null
    private var device: BluetoothDevice? = null
    private var hostingServer: Boolean = false
    private var connectedToServer: Boolean = false
    private var inStream: InputStream? = null
    private var outStream: OutputStream? = null

    private final var USER_UUID = UUID.fromString("8f5c5934-aaf3-11e6-80f5-76304dec7eb7")
    private final var BUFFER_SIZE: Int = 1024

    init {
        if (bluetoothAdapter == null)
            throw NullPointerException("Device does not support bluetooth")

        bluetoothAdapter.enable()
    }

    override fun onReceive(context: Context, intent: Intent) {
        var action: String = intent.action
        if (BluetoothAdapter.ACTION_DISCOVERY_STARTED == action) {

        }
        if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
            1
        }
        if (BluetoothDevice.ACTION_FOUND == action) {
            device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            device!!.createBond()
            Thread( Runnable {
                socket = device!!.createRfcommSocketToServiceRecord(device!!.uuids[0].uuid)
                socket!!.connect()
                outStream = socket!!.outputStream
                inStream = socket!!.inputStream
            }).start()
        }
    }

    public fun unpairAllDevices() {
        for (i in 0..bluetoothAdapter.bondedDevices.size) {
            bluetoothAdapter.bondedDevices.toTypedArray()[i]
        }
    }

    public fun broadcast(context: Context) {
        var discoverIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        discoverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(context, discoverIntent, discoverIntent.extras)
    }

    public fun connect(context: Context) {
        var intentFilter: IntentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        context.registerReceiver(this, intentFilter)
        bluetoothAdapter.startDiscovery()
    }

    public fun run(): Int {
        var bondedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
        var buffer: ByteArray = ByteArray(BUFFER_SIZE)
        var bytes: Int = 0

        while (true) {
            try {
                bytes = inStream!!.read(buffer, bytes, BUFFER_SIZE - bytes)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return bytes
    }

    public fun write(foo: String) {
        outStream!!.write(foo.toByteArray())
    }

    public fun getBondedDevicesNum(): Int {
        return bluetoothAdapter.bondedDevices.size
    }
}