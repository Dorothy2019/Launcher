package com.example.launcher.fragment

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.launcher.R
import com.example.launcher.adapter.ApplicationsAdapter
import com.example.launcher.model.AppInfo
import kotlinx.android.synthetic.main.fragment_applications.*

class ApplicationsFragment : Fragment(), ApplicationsAdapter.OnApplicationClickedListener {

    private var applications: List<AppInfo> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_applications, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadApplications()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }
    override fun onApplicationClicked(appInfo: AppInfo) {
        requireContext().startActivity(appInfo.intent)
    }

    private fun loadApplications() {
        val packageManager = requireActivity().packageManager

        // creating a list of every application we want to display
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val apps = packageManager.queryIntentActivities(mainIntent, 0)

        // sorting by name, mapping to AppInfo
        applications = apps
            .sortedWith(ResolveInfo.DisplayNameComparator(packageManager))
            .map { app ->
                AppInfo(
                    title = app.loadLabel(packageManager),
                    icon = app.activityInfo.loadIcon(packageManager),
                    className = ComponentName(app.activityInfo.applicationInfo.packageName, app.activityInfo.name)
                )
            }
    }
    private fun setupRecyclerView() {
        val adapter = ApplicationsAdapter()
        adapter.listener = this
        rvApplications.layoutManager = GridLayoutManager(context, 4)
        rvApplications.adapter = adapter
        adapter.setApps(applications)
    }
}