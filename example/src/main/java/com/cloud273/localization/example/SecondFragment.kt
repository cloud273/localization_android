package com.cloud273.localization.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.cloud273.localization.*
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lTitle = "title"
        textInputEdit.lHint = "input"
        changeButton.lText = "change_language"
        changeButton.setOnClickListener {
            val supportedLanguages = CLLocalization.supportedLanguages;
            val list = mutableListOf<String>()
            list.addAll(supportedLanguages.map { it.localized })
            list.add("device_language".localized)
            AlertDialog.Builder(this.requireContext())
                .setNegativeButton("cancel".localized, null)
                .setItems(list.toTypedArray()) { _, index ->
                    if (index < supportedLanguages.size) {
                        CLLocalization.setLanguage(supportedLanguages[index]);
                    } else {
                        CLLocalization.setLanguage(null);
                    }
                }
                .setTitle("change_language".localized)
                .create()
                .show()

        }
    }

}