package com.example.littletoothwallet.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.controller.EditPaymentEntryActivity
import com.example.littletoothwallet.model.dao.BankAccountDAO
import com.example.littletoothwallet.model.dto.BankAccount
import com.google.gson.Gson

class BankAccountsAdapter(private val context: Context, private val bankAccounts: MutableList<BankAccount>) : RecyclerView.Adapter<BankAccountsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.bankaccount_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentAccount = bankAccounts[position]
        holder.bankAccount_title_txt.text = currentAccount.name
        holder.bankAccount_balance_txt.text = currentAccount.balance.toString()

        if (currentAccount.balance < 0) {
            val color = ContextCompat.getColor(context, R.color.red_balance)
            holder.bankAccount_balance_txt.setTextColor(color)
        } else if (currentAccount.balance > 0) {
            val color = ContextCompat.getColor(context, R.color.green_balance)
            holder.bankAccount_balance_txt.setTextColor(color)
        }

        val flagColor = currentAccount.flag.toLong().toInt()
        holder.bankAccount_flag.setBackgroundColor(flagColor)
        val alpha = 0.2f
        val blendedColor = Color.argb((Color.alpha(flagColor) * alpha).toInt(), Color.red(flagColor), Color.green(flagColor), Color.blue(flagColor))
        holder.bankAccount_flag_background.setBackgroundColor(blendedColor)
    }



    override fun getItemCount(): Int {
        return bankAccounts.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val bankAccount_title_txt: TextView = itemView.findViewById(R.id.bankAccount_title_txt)
        val bankAccount_balance_txt: TextView = itemView.findViewById(R.id.bankAccount_balance_txt)
        val bankAccount_flag: View = itemView.findViewById(R.id.bankAccount_flag)
        val bankAccount_flag_background: View = itemView.findViewById(R.id.bankAccount_flag_background)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val currentAccount = bankAccounts[adapterPosition]

            menu?.add(R.string.menu_edit)?.setOnMenuItemClickListener {
                val jsonSelectedAccount: String = Gson().toJson(currentAccount)
                val intent = Intent(context, EditPaymentEntryActivity::class.java)
                intent.putExtra("SelectedAccount", jsonSelectedAccount)
                context.startActivity(intent)
                true
            }
            menu?.add(R.string.menu_delete)?.setOnMenuItemClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(R.string.confirm_delete)
                    .setPositiveButton(
                        R.string.confirm_delete_yes
                    ) { _, _ ->
                        val bankAccountsDAO = BankAccountDAO(context)
                        bankAccountsDAO.deleteBankAccount(currentAccount)
                        bankAccounts.removeAt(position)
                        (context as Activity).runOnUiThread {
                            notifyItemRemoved(position)
                        }
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

