package com.example.fabrapptrassessment.login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.fabrapptrassessment.MainActivity
import com.example.fabrapptrassessment.R


class CustomDialogFragment() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val dialogView = inflater.inflate(R.layout.alert_dialog, null)
            // wire the ok button to onclick listener
            val okButton = dialogView.findViewById<Button>(R.id.btn_ok) // wire Login button
            val codeApi = dialogView.findViewById<TextView>(R.id.code_from_api) // write the code text view
            val msgApi = dialogView.findViewById<TextView>(R.id.message_from_api) // write the message text view
            val timeApi = dialogView.findViewById<TextView>(R.id.time_from_api) // write the code text view

            okButton.setOnClickListener {
                activity?.startActivity(Intent (activity, MainActivity::class.java))
            }
            builder.setView(dialogView)
            builder.setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    companion object {
        const val TAG = "CustomDialog"
    }
}