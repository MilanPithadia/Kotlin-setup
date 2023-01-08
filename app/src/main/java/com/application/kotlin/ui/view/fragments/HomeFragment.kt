package com.ketan.app.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.kotlin.databinding.FragmentHomeBinding
import com.application.kotlin.ui.view.LoginActivity
import com.application.kotlin.utils.PreferenceHelper
import com.ketan.app.Inquiry.InquiryListActivity
import com.ketan.app.Sales.SalesListActivity
import com.ketan.app.attendance.AttendanceActivity
import com.ketan.app.controls.Const
import com.ketan.app.controls.PreferenceHelper
import com.ketan.app.databinding.FragmentHomeBinding
import com.ketan.app.login.LoginActivity
import com.ketan.app.network.BackService
import com.ketan.app.receipt.ReceiptListActivity
import com.ketan.app.report.ReportActivity
import com.ketan.app.schemes.SchemesActivity
import com.ketan.app.stock.StockActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        binding.llAttendance.setOnClickListener {
            //startActivity(Intent(activity, AttendanceActivity :: class.java))
        }

        binding.llLogout.setOnClickListener {

            val alert = AlertDialog.Builder(activity)
            alert.setMessage("Are you sure you want to log out?")
            alert.setNegativeButton("Cancel") { dialog, p1 ->
                dialog.dismiss()
            }
            alert.setPositiveButton("Logout") { dialog, p1 ->
                dialog.dismiss()
                //activity!!.stopService(Intent(activity,BackService::class.java))
                val i = Intent(activity, LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            }
            alert.create().show()
        }
        return binding.root
    }
}