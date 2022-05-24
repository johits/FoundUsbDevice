package com.example.foundusbdevice

import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findDeviceInfo()

        val btnRefresh = findViewById<Button>(R.id.btn_refresh)
        btnRefresh.setOnClickListener {
            findDeviceInfo()
        }

    }


    fun findDeviceInfo() {
        val manager = this.getSystemService(USB_SERVICE) as UsbManager
        val deviceList = manager.deviceList
        val deviceIterator: Iterator<UsbDevice> = deviceList.values.iterator()
        while (deviceIterator.hasNext()) {
            val device = deviceIterator.next()
            for (i in 0 until device.interfaceCount) {
                if (device.getInterface(i).interfaceClass == 7) { //mClass가 7일 때 장비 이름 구하기
                    Log.w("USB 장비 이름 : ", device.productName.toString())
                    break
                }
            }
        }
    }
}