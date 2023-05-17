package com.app.eacodingchallenge.models

data class Root(
    var viewType: Int = 1,
    val name: String? = null,
    val bands: ArrayList<Band>
)