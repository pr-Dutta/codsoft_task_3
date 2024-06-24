package com.example.codsoft_task_3_alarm_clock_app.EditAlarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.codsoft_task_3_alarm_clock_app.CreateAlarm.TimePickerUtil
import com.example.codsoft_task_3_alarm_clock_app.R
import com.example.codsoft_task_3_alarm_clock_app.ViewModel.AlarmViewModel
import com.example.codsoft_task_3_alarm_clock_app.databinding.FragmentEditBinding
import com.google.android.material.button.MaterialButtonToggleGroup

class EditFragment : Fragment() {

    private lateinit var binding : FragmentEditBinding
    private val args : EditFragmentArgs by navArgs()
    private lateinit var viewModel: AlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        TimePickerUtil.setHour(binding.timePicker2, args.Alarm.hour)
        TimePickerUtil.setMinute(binding.timePicker2, args.Alarm.minute)

        if (args.Alarm.mon) {
            binding.ToggleGroup.check(R.id.btnMon)
        }
        if (args.Alarm.tue) {
            binding.ToggleGroup.check(R.id.btnThu)
        }
        if (args.Alarm.wed) {
            binding.ToggleGroup.check(R.id.btnWed)
        }
        if (args.Alarm.thu) {
            binding.ToggleGroup.check(R.id.btnThu)
        }
        if (args.Alarm.fri) {
            binding.ToggleGroup.check(R.id.btnFri)
        }
        if (args.Alarm.sat) {
            binding.ToggleGroup.check(R.id.btnSat)
        }
        if (args.Alarm.sun) {
            binding.ToggleGroup.check(R.id.btnSun)
        }

        binding.ToggleGroup.addOnButtonCheckedListener(
            MaterialButtonToggleGroup.OnButtonCheckedListener
        { group, checkedId, isChecked ->

            when(checkedId) {
                binding.btnMon.id -> args.Alarm.mon = isChecked
                binding.btnTue.id -> args.Alarm.tue = isChecked
                binding.btnWed.id -> args.Alarm.wed = isChecked
                binding.btnThu.id -> args.Alarm.thu = isChecked
                binding.btnFri.id -> args.Alarm.fri = isChecked
                binding.btnSat.id -> args.Alarm.sat = isChecked
                binding.btnSun.id -> args.Alarm.sun = isChecked
            }

        })
    }

    override fun onDestroy() {
        if (args.Alarm.start) {
            args.Alarm.cancel(requireContext())
        }
        args.Alarm.hour = TimePickerUtil.getHour(binding.timePicker2)
        args.Alarm.minute = TimePickerUtil.getMinute(binding.timePicker2)
        args.Alarm.schedule(requireContext())
        viewModel.update(alarmItem = args.Alarm)
        super.onDestroy()
    }
}







