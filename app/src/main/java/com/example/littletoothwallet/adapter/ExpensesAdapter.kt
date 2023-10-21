package com.example.littletoothwallet.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.controller.EditExpenseActivity
import com.example.littletoothwallet.controller.HomeActivity
import com.example.littletoothwallet.model.dao.BankAccountDAO
import com.example.littletoothwallet.model.dao.OutgoingDAO
import com.example.littletoothwallet.model.dto.Outgoing
import com.google.gson.Gson

class ExpensesAdapter (private val context: Context, val expenses: MutableList<Outgoing>) : RecyclerView.Adapter<ExpensesAdapter.MyViewHolder>() {

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

        holder.expense_date.text = currentExpense.date

        val bankAccountDAO = BankAccountDAO(context)
        val bankAccount = bankAccountDAO.getBankAccount(currentExpense.bankAccountId) // Suponha que você tenha um método na classe BankAccountDAO para obter o BankAccount por id

        holder.expense_bankAccount_title_.text = bankAccount!!.name

        val flagColor = bankAccount.flag.toLong().toInt()
        holder.expense_bankAccount_flag.setBackgroundColor(flagColor)
        val alpha = 0.2f
        //val blendedColor = Color.argb((Color.alpha(flagColor) * alpha).toInt(), Color.red(flagColor), Color.green(flagColor), Color.blue(flagColor))


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

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val currentExpense = expenses[adapterPosition]

            menu?.add(R.string.menu_edit)?.setOnMenuItemClickListener {
                val jsonSelectedExpense: String = Gson().toJson(currentExpense)
                val intent = Intent(context, EditExpenseActivity::class.java)
                intent.putExtra("SelectedExpense", jsonSelectedExpense)
                context.startActivity(intent)
                true
            }
            menu?.add(R.string.menu_delete)?.setOnMenuItemClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(R.string.confirm_delete)
                    .setPositiveButton(
                        R.string.confirm_delete_yes
                    ) { _, _ ->
                        val outgoingDAO = OutgoingDAO(context)
                        outgoingDAO.deleteOutgoing(currentExpense)
                        expenses.removeAt(position)
                        notifyItemRemoved(position)
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    }
                    .setNegativeButton(
                        R.string.confirm_delete_no
                    ) { _, _ ->
                        // nada
                    }
                builder.create().show()
                true
            }
        }

    }

}
