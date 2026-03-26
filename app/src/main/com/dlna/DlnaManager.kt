package com.dlna

import android.content.Context
import android.util.Log
import org.fourthline.cling.android.AndroidUpnpService
import org.fourthline.cling.android.AndroidUpnpServiceImpl
import org.fourthline.cling.controlpoint.ActionCallback
import org.fourthline.cling.model.action.ActionInvocation
import org.fourthline.cling.model.meta.RemoteDevice
import org.fourthline.cling.model.types.UDAServiceType
import org.fourthline.cling.registry.DefaultRegistryListener
import org.fourthline.cling.registry.Registry
import org.fourthline.cling.support.avtransport.callback.Play
import org.fourthline.cling.support.avtransport.callback.SetAVTransportURI

class DlnaManager(
    private val context: Context,
    private val onDeviceFound: (RemoteDevice) -> Unit
) {

    var upnpService: AndroidUpnpService? = null

    val registryListener = object : DefaultRegistryListener() {
        override fun remoteDeviceAdded(registry: Registry, device: RemoteDevice) {
            if (device.type.displayString.contains("MediaRenderer")) {
                Log.d("DLNA", "Found: ${device.details.friendlyName}")
                onDeviceFound(device)
            }
        }
    }

    fun bindService() {
        context.bindService(
            android.content.Intent(context, AndroidUpnpServiceImpl::class.java),
            object : android.content.ServiceConnection {
                override fun onServiceConnected(
                    name: android.content.ComponentName?,
                    service: android.os.IBinder?
                ) {
                    upnpService = (service as AndroidUpnpServiceImpl.LocalBinder).service
                    upnpService?.registry?.addListener(registryListener)
                    upnpService?.controlPoint?.search()
                }

                override fun onServiceDisconnected(name: android.content.ComponentName?) {
                    upnpService = null
                }
            },
            Context.BIND_AUTO_CREATE
        )
    }

    fun cast(device: RemoteDevice, url: String) {
        val service = device.findService(UDAServiceType("AVTransport")) ?: return

        val setUri = object : SetAVTransportURI(service, url) {
            override fun success(invocation: ActionInvocation<*>) {
                Log.d("DLNA", "URL Set, starting playback")

                val play = object : Play(service) {}
                upnpService?.controlPoint?.execute(play)
            }
        }

        upnpService?.controlPoint?.execute(setUri)
    }
}
