package com.brunadev.rapidApi.presenter.Details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brunadev.rapidApi.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.brunadev.rapidApi.model.ResultApi

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EVENT_DTO = "EVENT_DTO"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        receiveIntent()
        observeData()
    }

    private fun receiveIntent() {

        val eventSelected = intent.getParcelableExtra<ResultApi?>(EVENT_DTO)
        eventSelected?.let { viewModel.setBookDetail(it) }
    }

    private fun observeData() {

        viewModel.indexDetail.observe(this@DetailActivity) {
            setDetailData(it)
        }
    }

    private fun setDetailData(eventSelected: ResultApi) {

        binding.titleData.text = eventSelected.shortName
        binding.nameData.text = eventSelected.fullExchangeName
        binding.startData.text = eventSelected.spark?.close?.get(0).toString()
        binding.endData.text = (eventSelected.spark?.previousClose ?: "0,00").toString()
    }
}

