package com.boilerplate.app.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.boilerplate.app.R
import com.boilerplate.app.view.*
import kotlinx.android.synthetic.main.activity_main.*
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

        btnLocalAction.setOnClickListener {
            myViewModel.doLocalTestAction()
        }

        btnRemoteAction.setOnClickListener {
            myViewModel.doRemoteTestAction()
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
            loader.visibility = View.VISIBLE
            btnRemoteAction.visibility = View.GONE
            btnLocalAction.visibility = View.GONE
        } else {
            Toast.makeText(this, receivedData, Toast.LENGTH_SHORT).show()
            loader.visibility = View.GONE
            btnRemoteAction.visibility = View.VISIBLE
            btnLocalAction.visibility = View.VISIBLE
        }
    }
}
