package com.boilerplate.app.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.boilerplate.app.R
import com.boilerplate.app.view.Failed
import com.boilerplate.app.view.Loading
import com.boilerplate.app.view.info.InfoActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(ctx: Context): Intent {
            return Intent(ctx, MainActivity::class.java)
        }
    }

    private val myViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
    }

    private fun setupUi() {
        myViewModel.states.observe(this, Observer { state ->
            when (state) {
                is Loading -> showLoader(true)
                is MainViewModel.CallResult -> showLoader(false, state.data)
                is Failed -> showError(state.error)
            }
        })

        findViewById<Button>(R.id.btnLocalAction).setOnClickListener {
            myViewModel.doLocalTestAction()
        }

        findViewById<Button>(R.id.btnRemoteAction).setOnClickListener {
            myViewModel.doRemoteTestAction()
        }

        findViewById<Button>(R.id.btnGetVersion).setOnClickListener {
            startActivity(InfoActivity.getStartIntent(this))
        }
    }

    private fun showError(error: Throwable) {
        AlertDialog
            .Builder(this)
            .setTitle("Error")
            .setMessage(error.message)
            .create()
            .show()
    }

    private fun showLoader(state: Boolean, receivedData: String = "") {
        if (state) {
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<Button>(R.id.btnRemoteAction).visibility = View.GONE
            findViewById<Button>(R.id.btnLocalAction).visibility = View.GONE
            findViewById<Button>(R.id.btnGetVersion).visibility = View.GONE
        } else {
            Toast.makeText(this, receivedData, Toast.LENGTH_SHORT).show()
            findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
            findViewById<Button>(R.id.btnRemoteAction).visibility = View.VISIBLE
            findViewById<Button>(R.id.btnLocalAction).visibility = View.VISIBLE
            findViewById<Button>(R.id.btnGetVersion).visibility = View.VISIBLE
        }
    }
}
