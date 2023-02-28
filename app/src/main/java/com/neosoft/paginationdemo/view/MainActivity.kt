package com.neosoft.paginationdemo.view

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.paginationdemo.R
import com.neosoft.paginationdemo.model.Result
import com.neosoft.paginationdemo.paging.LoaderAdapter
import com.neosoft.paginationdemo.paging.QuotePagingAdapter
import com.neosoft.paginationdemo.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(),QuotePagingAdapter.CellClickListener {
    lateinit var quoteViewModel: QuoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: QuotePagingAdapter
    lateinit var clipboardManager: ClipboardManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.quoteList)

        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)

        adapter = QuotePagingAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        quoteViewModel.list.observe(this,  {
            adapter.submitData(lifecycle, it)
        })


    }

    override fun onCellClickListener(item: Result?) {
        clipboardManager.setPrimaryClip(ClipData.newPlainText("Content",item?.content))
        Toast.makeText(this,"Copied to clipboard"+item?.author,Toast.LENGTH_SHORT).show()



    }

}