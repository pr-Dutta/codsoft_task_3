package com.example.codsoft_task_3_alarm_clock_app.CreateAlarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmItem
import com.example.codsoft_task_3_alarm_clock_app.R
import com.example.codsoft_task_3_alarm_clock_app.ViewModel.AlarmViewModel
import com.example.codsoft_task_3_alarm_clock_app.databinding.FragmentCreateBinding
import com.google.android.material.button.MaterialButtonToggleGroup

class CreateFragment : Fragment() {

    lateinit var binding : FragmentCreateBinding
    lateinit var viewModel: AlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var alarm = AlarmItem(1, 10, 20)
        viewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        binding.ToggleGroup.addOnButtonCheckedListener(MaterialButtonToggleGroup.OnButtonCheckedListener
        { group, checkedId, isChecked ->

            when(checkedId) {
                binding.btnMon.id -> alarm.mon = isChecked
                binding.btnTue.id -> alarm.tue = isChecked
                binding.btnWed.id -> alarm.wed = isChecked
                binding.btnThu.id -> alarm.thu = isChecked
                binding.btnFri.id -> alarm.fri = isChecked
                binding.btnSat.id -> alarm.sat = isChecked
                binding.btnSun.id -> alarm.sun = isChecked
            }

        })

        binding.btnCreate.setOnClickListener {
            alarm.hour = TimePickerUtil.getHour(binding.timePicker2)
            alarm.minute = TimePickerUtil.getMinute(binding.timePicker2)
            alarm.schedule(requireContext())
            alarm.start = true
            viewModel.insert(alarm)
            Navigation.findNavController(binding.btnCreate).navigate(R.id.action_createFragment_to_alarmListFragment)
        }
    }
}








