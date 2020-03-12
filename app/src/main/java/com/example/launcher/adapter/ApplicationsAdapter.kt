package com.example.launcher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.launcher.R
import com.example.launcher.model.AppInfo
import kotlinx.android.synthetic.main.item_application.view.*

class ApplicationsAdapter : RecyclerView.Adapter<ApplicationsAdapter.ViewHolder>() {

    private val applications: MutableList<AppInfo> = mutableListOf()

    var listener: OnApplicationClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_application, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = applications[position]
        holder.name.text = app.title
        holder.icon.setImageDrawable(app.icon)
        holder.app = app
    }

    override fun getItemCount(): Int = applications.size

    fun setApps(apps: List<AppInfo>) {
        applications.clear()
        applications.addAll(0, apps)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tvName
        var icon: ImageView = itemView.ivIcon

        var app: AppInfo? = null

        init {
            itemView.setOnClickListener {
                app?.let {
                    listener?.onApplicationClicked(it)
                }
            }
        }
    }

    interface OnApplicationClickedListener {
        fun onApplicationClicked(appInfo: AppInfo)
    }

}