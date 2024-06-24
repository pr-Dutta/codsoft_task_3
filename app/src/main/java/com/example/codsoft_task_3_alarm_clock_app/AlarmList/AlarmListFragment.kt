package com.example.codsoft_task_3_alarm_clock_app.AlarmList

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmItem
import com.example.codsoft_task_3_alarm_clock_app.R
import com.example.codsoft_task_3_alarm_clock_app.Service.AlarmService
import com.example.codsoft_task_3_alarm_clock_app.ViewModel.AlarmViewModel
import com.example.codsoft_task_3_alarm_clock_app.databinding.FragmentAlarmListBinding

class AlarmListFragment : Fragment(), OnToggleListener, OnClickAlarmListener {

    private lateinit var binding : FragmentAlarmListBinding
    private lateinit var adapter : AdapterAlarm
    private lateinit var viewModel: AlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)
        adapter = AdapterAlarm()
        adapter.addOnToggleListener(this)
        adapter.addOnClickAlarmListener(this)

        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(binding.floatingActionButton).navigate(R.id.action_alarmListFragment_to_createFragment)
        }
    }

    override fun onToggle(alarmItem: AlarmItem) {
        if (alarmItem.start) {
            alarmItem.schedule(requireContext())
        }else {
            alarmItem.cancel(requireContext())
        }
    }

    override fun onClick(alarmItem: AlarmItem) {
        val action = AlarmListFragmentDirections.actionAlarmListFragmentToEditFragment(alarmItem)
        this.findNavController().navigate(action)
    }

    override fun onLongClick(alarmItem: AlarmItem) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete Item")
            .setMessage("Do you want to delete?")
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, which ->
                if (alarmItem.start) {
                    alarmItem.cancel(requireContext())
                }
                viewModel.delete(alarmItem)

            }
        dialog.show()
    }
}






