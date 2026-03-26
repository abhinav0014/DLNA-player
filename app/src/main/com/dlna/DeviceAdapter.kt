package com.dlna

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.player.databinding.ItemDeviceBinding
import org.fourthline.cling.model.meta.RemoteDevice

class DeviceAdapter(
    private val devices: List<RemoteDevice>,
    private val onClick: (RemoteDevice) -> Unit
) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDeviceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = devices[position]
        holder.binding.deviceName.text = device.details.friendlyName

        holder.itemView.setOnClickListener {
            onClick(device)
        }
    }
}
