package org.usfirst.frc1360.scouting

import android.app.Activity
import android.app.Notification
import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.os.ParcelUuid
import android.renderscript.ScriptGroup
import android.support.v4.app.ActivityCompat.*
import android.support.v4.content.ContextCompat
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
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
    private var inStreams: List<InputStream> = ArrayList()
    private var outStreams: List<OutputStream> = ArrayList()

    private final var BUFFER_SIZE: Int = 1024
    private final var TOAST: Int = 5
    // I like toast

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

    public fun run() {
        var bondedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices

        if (bondedDevices.size > 0) {
            var devices: Array<BluetoothDevice> = bondedDevices.toTypedArray()

            for (i in 0..devices.size) {
                var device: BluetoothDevice = devices[i]
                var uuids: Array<ParcelUuid> = device.uuids
                var socket: BluetoothSocket = device.createRfcommSocketToServiceRecord(uuids[0].uuid)
                socket.connect()
                inStreams.plus(socket.inputStream)
                outStreams.plus(socket.outputStream)
            }

            var buffer: ByteArray = ByteArray(BUFFER_SIZE)
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
            }
        }
    }

    public fun write(foo: String) {
        for (i in 0..outStreams.size) {
            outStreams[i].write(foo.toByteArray())
        }
    }
}