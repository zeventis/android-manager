package br.com.zeventis.producer.presentation.ui.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zeventis.producer.R
import br.com.zeventis.producer.presentation.model.register.CompanyRegisterSearchPresentation
import kotlinx.android.synthetic.main.item_company.view.itemCompanyRegisterNameTv

class CompanyRegisterAdapter(
    private var companyList: List<CompanyRegisterSearchPresentation>,
    private val context: Context,
    private val listener: CompanyRegisterListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = companyList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CompanyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_company, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindEvents(position, holder)
    }

    private fun bindEvents(position: Int, holder: RecyclerView.ViewHolder) {
        val company = companyList[position]
        val viewHolder = holder as CompanyViewHolder

        viewHolder.bindView(company, listener)
    }

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            company: CompanyRegisterSearchPresentation,
            listener: CompanyRegisterListener
        ) {
            itemView.itemCompanyRegisterNameTv.text = company.name
            itemView.setOnClickListener { listener.onClickCompany(company) }
        }
    }

    interface CompanyRegisterListener {
        fun onClickCompany(company: CompanyRegisterSearchPresentation)
    }
}