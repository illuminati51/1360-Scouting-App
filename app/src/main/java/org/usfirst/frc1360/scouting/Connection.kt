package org.usfirst.frc1360.scouting

import android.app.Activity
import android.app.Notification
import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.ParcelUuid
import android.renderscript.ScriptGroup
import android.support.v4.app.ActivityCompat.*
import android.support.v4.content.ContextCompat
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.view.View
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*
import java.net.NetworkInterface.getNetworkInterfaces



public class Connection {
    private var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var serverSocket: BluetoothServerSocket? = null
    private var socket: BluetoothSocket? = null
    private var device: BluetoothDevice? = null
    private var hostingServer: Boolean = false
    private var connectedToServer: Boolean = false
    private var inStreams: MutableList<InputStream> = arrayListOf()
    private var outStreams: MutableList<OutputStream> = arrayListOf()

    private final var USER_UUID = UUID.fromString("8f5c5934-aaf3-11e6-80f5-76304dec7eb7")
    private final var BUFFER_SIZE: Int = 1024

    init {
        if (bluetoothAdapter == null)
            throw NullPointerException("Device does not support bluetooth")

        bluetoothAdapter.enable()
    }

    public fun connect(context: Context) {
        connectedToServer = true
        var temp: BluetoothSocket? = null
        device = bluetoothAdapter.getRemoteDevice(android.provider.Settings.Secure.getString(context.contentResolver, "bluetooth_address"))

        try {
            temp = device!!.createRfcommSocketToServiceRecord(USER_UUID)
        } catch (e: IOException) { }
        socket = temp

        bluetoothAdapter.cancelDiscovery()

        try {
            socket!!.connect()
        } catch (e: IOException) {
            try {
                socket!!.close()
            } catch (ee: IOException) {}
        }
    }

    public fun createServer() {
        hostingServer = true
        serverSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(android.os.Build.MODEL, USER_UUID)
        var socket: BluetoothSocket
        Thread( Runnable {
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
        }).start()
    }

    public fun cancelConnection() {
        if (hostingServer) serverSocket!!.close() else if (connectedToServer) socket!!.close() else throw Exception("Neither connected nor hosting")
    }

    public fun run() {
        var bondedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices

        if (bondedDevices.size > 0) {
            var devices: Array<BluetoothDevice> = bondedDevices.toTypedArray()

            for (i in 0..devices.size) {
                var device: BluetoothDevice = devices[i]
                var uuids: Array<ParcelUuid> = device.uuids
                var socket: BluetoothSocket = device.createRfcommSocketToServiceRecord(uuids[0].uuid)
                socket.connect()
                inStreams.add(socket.inputStream)
                outStreams.add(socket.outputStream)
            }

            /*var buffer: ByteArray = ByteArray(BUFFER_SIZE)
            var bytes: Int = 0
            var b = BUFFER_SIZE

            for (i in 0..inStreams.size) {
                while (true) {
                    try {
                        bytes = inStreams[i].read(buffer, bytes, BUFFER_SIZE - bytes)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }*/
        }
    }

    public fun write(foo: String) {
        for (i in 0..outStreams.size) {
            outStreams[i].write(foo.toByteArray())
        }
    }

    public fun read(): Array<String> {
        var strings: MutableList<String> = arrayListOf()
        for (i in 0..inStreams.size) {
            strings.add(inStreams[i].read().toString())
        }

        return strings.toTypedArray()
    }

    public fun getBondedDevicesNum(): Int {
        return bluetoothAdapter.bondedDevices.size
    }
}