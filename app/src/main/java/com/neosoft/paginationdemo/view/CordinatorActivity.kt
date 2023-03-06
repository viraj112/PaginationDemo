package com.neosoft.paginationdemo.view

import android.os.Bundle
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.neosoft.paginationdemo.databinding.ActivityCordinatorBinding


class CordinatorActivity : AppCompatActivity() {
    lateinit var binding: ActivityCordinatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, com.neosoft.paginationdemo.R.layout.activity_cordinator)

        binding.fab.setOnClickListener {
            Snackbar.make(it, "Simple Snack Bar Example", Snackbar.LENGTH_LONG).show();
        }

        binding.toolbar.title = "Basic Componant"
        binding.toolbar.inflateMenu(
            com.neosoft.paginationdemo.R.menu.main_menu
        )
        binding.toolbar.setOnMenuItemClickListener(object :OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {

                when (item?.getItemId()) {
                    com.neosoft.paginationdemo.R.id.simple_snackbar -> showSimpleSnackBar()
                    com.neosoft.paginationdemo.R.id.snackbar_action_callback -> showSnackBarWithActionCallBack()
                    com.neosoft.paginationdemo.R.id.custom_sackbar -> showSnackBarWithColoredText()
                }
                return false
               }


        })
    }

    private fun showSnackBarWithColoredText() {
        val snackbar = Snackbar.make(binding.myLayout, "File Deleted Successfully", Snackbar.LENGTH_LONG)
        snackbar.setAction(
            "UNDO"
        ) { Snackbar.make(binding.myLayout, "File Recovered Successfully", Snackbar.LENGTH_SHORT).show() }
        snackbar.show()
    }

    private fun showSnackBarWithActionCallBack() {
    }

    private fun showSimpleSnackBar() {
        Snackbar.make(binding.myLayout, "Simple Snack Bar Example", Snackbar.LENGTH_LONG).show();
    }
}