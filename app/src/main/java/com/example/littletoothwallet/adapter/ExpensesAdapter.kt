package com.example.littletoothwallet.adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.model.dto.Outgoing

class ExpensesAdapter (private val context: Context, private val expenses: MutableList<Outgoing>) : RecyclerView.Adapter<ExpensesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.outgoing_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentExpense = expenses[position]
        holder.expense_title_txt.text = currentExpense.name
        holder.expense_price_txt.text = currentExpense.price.toString()

        if (currentExpense.price > 0) {
            val color = ContextCompat.getColor(context, R.color.red_balance)
            holder.expense_price_txt.setTextColor(color)
        } else if (currentExpense.price < 0) {
            val color = ContextCompat.getColor(context, R.color.green_balance)
            holder.expense_price_txt.setTextColor(color)
        }

        //val flagColor = currentExpense.flag.toLong().toInt()
        //holder.expense_bankAccount_flag.setBackgroundColor(flagColor)
        //val alpha = 0.2f
        //val blendedColor = Color.argb((Color.alpha(flagColor) * alpha).toInt(), Color.red(flagColor), Color.green(flagColor), Color.blue(flagColor))
        //holder.expense_bankAccount_flag_background.setBackgroundColor(blendedColor)
    }


    override fun getItemCount(): Int {
        return expenses.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val expense_title_txt: TextView = itemView.findViewById(R.id.expense_title_txt)
        val expense_bankAccount_title_: TextView = itemView.findViewById(R.id.expense_bankAccount_title_expense)
        val expense_price_txt: TextView = itemView.findViewById(R.id.expense_price_txt)
        val expense_date: TextView = itemView.findViewById(R.id.expense_date)
        val expense_bankAccount_flag: View = itemView.findViewById(R.id.expense_bankAccount_flag)
        val expense_bankAccount_flag_background: View = itemView.findViewById(R.id.expense_bankAccount_flag_background)


        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            TODO("Not yet implemented")
        }

    }

}
