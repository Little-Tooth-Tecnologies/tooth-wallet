package com.example.littletoothwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.littletoothwallet.R
import com.example.littletoothwallet.model.dto.BankAccount

class BankAccountsAdapter(private val context: Context, private val bankAccounts: List<BankAccount>) : RecyclerView.Adapter<BankAccountsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentAccount = bankAccounts[position]
        holder.bankAccount_id_txt.text = currentAccount.id.toString()
        holder.bankAccount_title_txt.text = currentAccount.name
        holder.bankAccount_balance_txt.text = currentAccount.balance.toString()
        val flagColor = currentAccount.flag.toLong().toInt()
        holder.bankAccount_flag.setBackgroundColor(flagColor)
    }

    override fun getItemCount(): Int {
        return bankAccounts.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bankAccount_id_txt: TextView = itemView.findViewById(R.id.bankAccount_id_txt)
        val bankAccount_title_txt: TextView = itemView.findViewById(R.id.bankAccount_title_txt)
        val bankAccount_balance_txt: TextView = itemView.findViewById(R.id.bankAccount_balance_txt)
        val bankAccount_flag: View = itemView.findViewById(R.id.bankAccount_flag)
    }
}

