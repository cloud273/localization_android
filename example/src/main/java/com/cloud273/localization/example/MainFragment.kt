package com.cloud273.localization.example

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import com.cloud273.localization.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lTitle = "title"
        button.lText = "button"
        textView.lText = "label"
        imageView.lImage = "icon"
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        menu.getItem(0).title = "next".localized
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_secondFragment)
        return super.onOptionsItemSelected(item)
    }

}