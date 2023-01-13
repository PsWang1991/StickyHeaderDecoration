package com.example.stickyheadertest.ui.main

/**
 * Created by PS Wang on 2023/1/12
 */
data class NumberSectionData(
    val title: String = "",
    val number: Int = -1,
) : SectionData() {
    override val sectionTitle = title
}
