package com.dicoding.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment


class OptionDialogFragment : DialogFragment() {
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbRen: RadioButton
    private lateinit var rbChen: RadioButton
    private lateinit var rbKun: RadioButton
    private lateinit var rbPark: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbRen = view.findViewById(R.id.rb_ren)
        rbChen = view.findViewById(R.id.rb_chen)
        rbKun = view.findViewById(R.id.rb_kun)
        rbPark = view.findViewById(R.id.rb_park)

        btnChoose.setOnClickListener{
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var nct: String? = when (checkedRadioButtonId) {
                    R.id.rb_ren -> rbRen.text.toString().trim()
                    R.id.rb_chen -> rbChen.text.toString().trim()
                    R.id.rb_kun -> rbKun.text.toString().trim()
                    R.id.rb_park -> rbPark.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(nct)
                dialog?.dismiss()
            }
        }
        btnClose.setOnClickListener{
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if (fragment is DetailCategoryFragment){
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    interface  OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}