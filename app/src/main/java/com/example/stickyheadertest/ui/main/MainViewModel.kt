package com.example.stickyheadertest.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val mockData: List<NumberSectionData> by lazy {

        val dataList = mutableListOf<NumberSectionData>()

        (0..5).forEach { tens ->

            (tens * 10..tens * 10 + 9).forEach {

                dataList.add(NumberSectionData(
                    if (tens == 1) "Super loooooooooooooooooooooooooooooooooooooooooong" else (tens * 10).toString(),
                    it
                ))
            }
        }

        dataList
    }
}