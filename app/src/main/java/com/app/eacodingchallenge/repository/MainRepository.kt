package com.app.eacodingchallenge.repository

import com.app.eacodingchallenge.api.ApiHelper
import com.app.eacodingchallenge.models.Root
import com.app.eacodingchallenge.other.Resource
import com.app.eacodingchallenge.ui.RecyclerViewAdapter
import javax.inject.Inject

public class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getFestival(): Resource<List<Root>> {
        val list = mutableListOf<Root>()
        apiHelper.getFestivals().let {
            if (it.isSuccessful) {
                val result =
                    it.body()?.flatMap { band -> band.bands.map { i -> band to i.recordLabel } }
                        ?.sortedBy { sort -> sort.second }?.distinct()
                        ?.groupBy { group -> group.second }
                result?.map { pair ->
                    val bandList =
                        pair.value.flatMap { band -> band.first.bands.filter { filter -> filter.recordLabel == band.second } }
                            .sortedWith(compareBy { bandItem -> bandItem.name })
                    var initialValue = "dummy"

                    bandList.map { band ->
                        if (pair.key != initialValue) {
                            initialValue = pair.key.toString()
                            list.add(
                                Root(
                                    RecyclerViewAdapter.VIEW_TYPE_ONE,
                                    band.recordLabel,
                                    bands = arrayListOf(band)
                                )
                            )
                        }
                        list.add(
                            Root(
                                RecyclerViewAdapter.VIEW_TYPE_TWO,
                                pair.value.first { root -> root.first.bands.contains(band) }.first.name,
                                bands = arrayListOf(band)
                            )
                        )
                    }
                }
                return Resource.success(list)
            } else {
                return Resource.error(it.errorBody().toString(), null)
            }
        }
    }

}