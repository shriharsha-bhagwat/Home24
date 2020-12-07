package com.home24.assignment.presentation.core.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.home24.assignment.BR
import com.home24.assignment.R
import com.home24.assignment.core.utils.NetworkUtils
import com.ironz.binaryprefs.Preferences
import com.tapadoo.alerter.Alerter
import org.koin.java.KoinJavaComponent

abstract class BaseActivity<VM : BaseViewModel, Binding : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity(), BaseView {

    protected abstract val mViewModel: VM?

    protected lateinit var binding: Binding
        private set

    private var loadingDialog: Dialog? = null

    protected val preferences: Preferences by KoinJavaComponent.inject(Preferences::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, mViewModel)
        binding.lifecycleOwner = this

        initView()
        observeViewModel()
    }

    open fun initView() {
        if (!NetworkUtils.isOnline(this)) {
            showAlert("Error", "No Internet Available", null)
        }
    }

    abstract override fun observeViewModel()

    open fun showAlert(
        title: String,
        message: String,
        listner: View.OnClickListener?
    ) {
        val alertObj = Alerter.create(this)
            .setTitle(title)
            .setText(message)
            .setBackgroundColorRes(R.color.colorDarkAccent)
        if (listner != null) alertObj
            .setOnClickListener(listner)
            .show() else alertObj.show()
    }

    /**
     * Shows progress in an activity
     */
    fun showProgressDialog() {
        loadingDialog = setLoadingDialog(this)
        loadingDialog?.show()
    }

    /**
     * Hides progress in an activity
     */
    fun hideProgressDialog() {
        loadingDialog?.dismiss()
    }

    open fun hideKeyboard() {
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // check if no view has focus:
        val currentFocusedView: View? = currentFocus
        currentFocusedView?.let {
            inputManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun setLoadingDialog(context: Context): Dialog {
        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(0.9f)
        }
        return dialog
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
    }
}