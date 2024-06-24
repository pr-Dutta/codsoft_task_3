package com.example.codsoft_task_3_alarm_clock_app.AlarmList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.codsoft_task_3_alarm_clock_app.Model.AlarmItem
import com.example.codsoft_task_3_alarm_clock_app.databinding.ItemAlarmBinding

class AdapterAlarm : Adapter<AdapterAlarm.AlarmViewHolder>() {

    private var mList = ArrayList<AlarmItem>()
    private var onToggleListener: OnToggleListener? = null
    private var onClickAlarmListener: OnClickAlarmListener? = null

    inner class AlarmViewHolder(var binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        fun bind(alarmItem: AlarmItem) {
            binding.txtTime.text = alarmItem.getTime()
            binding.txtWeek.text = alarmItem.getRepeat()
            binding.swStart.isChecked = alarmItem.start
            binding.swStart.setOnCheckedChangeListener {
                btnView, isCheck ->

                alarmItem.start = isCheck
                onToggleListener?.onToggle(alarmItem)
            }
        }

        override fun onClick(v: View?) {
            onClickAlarmListener?.onClick(mList.get(adapterPosition))
        }

        override fun onLongClick(v: View?): Boolean {
            onClickAlarmListener?.onLongClick(mList.get(adapterPosition))
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            ItemAlarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(mList.get(position))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(it: List<AlarmItem>?) {
        mList = it as ArrayList<AlarmItem>
        notifyDataSetChanged()
    }

    fun addOnToggleListener(onToggleListener: OnToggleListener) {
        this.onToggleListener = onToggleListener
    }

    fun addOnClickAlarmListener(onClickAlarmListener: OnClickAlarmListener) {
        this.onClickAlarmListener = onClickAlarmListener
    }

}




