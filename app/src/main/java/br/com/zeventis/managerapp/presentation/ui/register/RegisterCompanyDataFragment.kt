package br.com.zeventis.managerapp.presentation.ui.register

import android.app.AlertDialog
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import br.com.zeventis.managerapp.presentation.model.register.Company
import br.com.zeventis.managerapp.presentation.model.register.CompanyRegisterSearchPresentation
import com.irozon.sneaker.Sneaker
import kotlinx.android.synthetic.main.activity_register.registerFragmentRegisterStepVp
import kotlinx.android.synthetic.main.custom_dialog_companys.view.dialogCompanyBackBt
import kotlinx.android.synthetic.main.custom_dialog_companys.view.dialogCompanyListRv
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentAddressComplementIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentAddressNumberIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentCepIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentCompanyNameIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentNextBtn
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentPhoneCompanyIl
import org.koin.android.ext.android.inject

class RegisterCompanyDataFragment : BaseFragment(), CompanyRegisterAdapter.CompanyRegisterListener {

    private val registerManager: RegisterManager by inject()
    private val registerViewModel: RegisterViewModel by inject()
    private lateinit var messageBoxView: View
    private lateinit var messageBoxInstance: AlertDialog
    private val getCompanyHandler = Handler()
    private lateinit var getCompanyRunnable: Runnable
    private var companyNameResponse: String = ""

    override fun getContentLayoutId(): Int = R.layout.fragment_register_company_data

    override fun init() {
        initTextChangeListeners()
        initOnClickListeners()
        observeViewModelEvents()
    }

    override fun onClickCompany(company: CompanyRegisterSearchPresentation) {
        handleSelectedCompany(company)
        messageBoxInstance.dismiss()
        activity?.let { hideKeyboard(it) }
    }

    private fun observeViewModelEvents() {
        registerViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is RegisterViewEvents.OnGetCompanySuccess -> handleGetCompanySuccess(it.company)
                is RegisterViewEvents.OnGetCompanyNotFound -> handleCompanyNotFound()
                is RegisterViewEvents.OnGetCompanyFailed -> handleError(
                    this::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun handleCompanyNotFound() {
        hideLoading()
        activity?.let {
            Sneaker.with(it)
                .setTitle("Atenção!")
                .setDuration(4000)
                .setMessage("Não conseguimos encontrar sua empresa, verifique se o nome está correto ou preencha os campos abaixo")
                .sneakWarning() // TODO Fix sneaker size
        }

        toggleFields(true)
    }


    private fun toggleFields(enable: Boolean) {
        registerFragmentPhoneCompanyIl.editText?.isEnabled = enable
        registerFragmentCepIl.editText?.isEnabled = enable
        registerFragmentAddressNumberIl.editText?.isEnabled = enable
        registerFragmentAddressComplementIl.editText?.isEnabled = enable
        registerFragmentPhoneCompanyIl.isEnabled = enable
        registerFragmentCepIl.isEnabled = enable
        registerFragmentAddressNumberIl.isEnabled = enable
        registerFragmentAddressComplementIl.isEnabled = enable
    }

    private fun handleGetCompanySuccess(companyList: List<CompanyRegisterSearchPresentation>) {
        hideLoading()
        messageBoxView =
            LayoutInflater.from(activity).inflate(R.layout.custom_dialog_companys, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)

        initCompanyListAdapter(companyList)

        messageBoxInstance = messageBoxBuilder.show()
        messageBoxView.dialogCompanyBackBt.setOnClickListener { messageBoxInstance.dismiss() }
    }

    private fun initCompanyListAdapter(companyList: List<CompanyRegisterSearchPresentation>) {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        messageBoxView.dialogCompanyListRv.layoutManager = layoutManager

        messageBoxView.dialogCompanyListRv.adapter =
            context?.let { CompanyRegisterAdapter(companyList, it, this) }
    }

    private fun handleSelectedCompany(company: CompanyRegisterSearchPresentation) {
        companyNameResponse = company.name
        registerFragmentCompanyNameIl.editText?.setText(company.name)
        registerFragmentPhoneCompanyIl.editText?.setText(company.phone)
        registerFragmentCepIl.editText?.setText(company.cep)
        registerFragmentAddressNumberIl.editText?.setText(company.addressNumber)
        registerFragmentAddressComplementIl.editText?.setText(company.addressComplement)
    }

    private fun initTextChangeListeners() {
        getCompanyRunnable = Runnable {
            registerViewModel.getCompany(registerFragmentCompanyNameIl.editText?.text.toString())
            showLoading()
        }

        registerFragmentCompanyNameIl.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleFields(false)
                getCompanyHandler.removeCallbacks(getCompanyRunnable)
                if (count > 3 && s.toString().isNotEmpty() && s.toString() != companyNameResponse) {
                    companyNameResponse = ""
                    getCompanyHandler.postDelayed(getCompanyRunnable, 500)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    private fun initOnClickListeners() {
        registerFragmentNextBtn.setOnClickListener { handleNextButton() }
    }

    private fun handleNextButton() {
        startNextRegisterFragment()
        updateRegisterSingleton()
    }

    private fun startNextRegisterFragment() {
        val activity = activity as RegisterActivity
        activity.registerFragmentRegisterStepVp.currentItem = 2
    }

    private fun updateRegisterSingleton() {
        val registerTemp = registerManager.getRegister()
        val companyTemp = Company()
        companyTemp.name = registerFragmentCompanyNameIl.editText?.text.toString()
        companyTemp.phone = registerFragmentPhoneCompanyIl.editText?.text.toString()
        companyTemp.cep = registerFragmentCepIl.editText?.text.toString()
        companyTemp.addressNumber = registerFragmentAddressNumberIl.editText?.text.toString()
        companyTemp.addressComplement =
            registerFragmentAddressComplementIl.editText?.text.toString()
        registerTemp.company = companyTemp
        registerManager.saveRegister(registerTemp)
    }

    companion object {
        fun newInstance() = RegisterCompanyDataFragment()
    }
}
