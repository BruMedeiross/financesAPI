package com.brunadev.rapidApi.presenter.Main

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunadev.rapidApi.databinding.ActivityMainBinding
import com.brunadev.rapidApi.extension.hideKeyboard
import com.brunadev.rapidApi.model.ResultApi
import com.brunadev.rapidApi.presenter.Details.DetailActivity
import com.brunadev.rapidApi.presenter.Main.adapter.ListEventAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var listSize: Int = 0
    private var listIndex: List<ResultApi> = emptyList()
    var update = 0
    lateinit var timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvlist.layoutManager = LinearLayoutManager(this@MainActivity)

        setObservers()
        searchEvent()
    }

    private fun filterList(search: String) {

        val searchList = listIndex.filter {
            it.shortName.toString().lowercase().contains(search)
        }

        when {
            searchList.isNotEmpty() -> {
                binding.txtNotFound.visibility = View.GONE
                rvlist.visibility = View.VISIBLE
                showProjects(list = searchList)
            }

            else -> {
                binding.txtNotFound.visibility = View.VISIBLE
                rvlist.visibility = View.GONE
            }
        }
        stopShimmer()
    }

    private fun setObservers() {

        with(viewModel) {
            init()
            funTimer()
            listState.observe(this@MainActivity) { event ->

                event?.marketSummaryAndSparkResponse?.result?.let {
                    listIndex = it
                }
                updateView()
                when {
                    listIndex.isNotEmpty() -> {
                        binding.txtNotFound.visibility = View.GONE
                        rvlist.visibility = View.VISIBLE
                        showProjects(list = listIndex)
                    }

                    else -> {
                        binding.txtNotFound.visibility = View.VISIBLE
                        rvlist.visibility = View.GONE
                    }
                }
                stopShimmer()
            }
        }
    }

    private fun updateView() {

        rvlist.visibility = View.GONE
        binding.shimmerList.startShimmer()
        binding.shimmerList.isShimmerVisible
        hideKeyboard()
    }

    private fun stopShimmer() {

        binding.shimmerList.visibility = View.GONE
        binding.shimmerList.stopShimmer()
    }

    private fun startShimmer() {

        binding.shimmerList.visibility = View.VISIBLE
        binding.shimmerList.startShimmer()
    }

    private fun showProjects(list: List<ResultApi>) {

        if (list.isNotEmpty()) {
            rvlist.adapter = ListEventAdapter(list) { item ->
                setDetailView(item)
            }
        }
    }

    private fun setDetailView(item: ResultApi) {

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EVENT_DTO, item)
        }
        startActivity(intent)
    }

    private fun funTimer() {
        timer = object : CountDownTimer(20000, 20000) {
            override fun onTick(millisUntilFinished: Long) {
                notifyUpdatedList()
            }

            override fun onFinish() {
               setObservers()
            }
        }
        timer.start()
    }

    private fun notifyUpdatedList() {
        update++
        Log.i("TAG","updated list $update")
    }

    private fun searchEvent() {

        binding.searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    timer.cancel()
                    newText?.let { search ->
                        when{
                            search.length >= 3 -> {
                                startShimmer()
                                filterList(search)
                                return true
                            }
                            search.isEmpty() -> {
                                setObservers()
                                return false
                            }
                            else -> return false

                        }
                    }
                    setObservers()
                    return false
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

}

