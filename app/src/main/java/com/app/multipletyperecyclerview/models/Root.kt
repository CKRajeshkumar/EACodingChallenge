package com.app.multipletyperecyclerview.models

data class Root(
    var viewType: Int = 1,
    var displayName: String? = null,
    var name: String? = null,
    var bands: ArrayList<Band>
)