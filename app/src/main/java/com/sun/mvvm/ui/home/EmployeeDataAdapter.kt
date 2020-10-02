package com.sun.mvvm.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sun.mvvm.base.BaseViewHolder
import com.sun.mvvm.R
import com.sun.mvvm.data.model.Employee
import com.sun.mvvm.databinding.ItemEmployeeBinding

class EmployeeDataAdapter(private val listener: (Employee) -> Unit) :
    ListAdapter<Employee, EmployeeDataAdapter.EmployeeViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): EmployeeViewHolder =
        EmployeeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_employee,
                viewGroup,
                false
            ), listener
        )

    class EmployeeViewHolder(
        private val employeeListItemBinding: ItemEmployeeBinding,
        onItemClick: (Employee) -> Unit
    ) : BaseViewHolder<Employee, ItemEmployeeBinding>(employeeListItemBinding, onItemClick) {

        override fun onBind(itemData: Employee) {
            super.onBind(itemData)
            employeeListItemBinding.employee = itemData
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean =
                oldItem == newItem
        }
    }
}
