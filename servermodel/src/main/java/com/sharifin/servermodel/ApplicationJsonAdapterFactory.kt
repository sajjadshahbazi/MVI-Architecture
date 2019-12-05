package com.sharifin.servermodel


import com.sharifin.servermodel.activity.ActivityServerModelJsonAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type


class ApplicationJsonAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? =
        AppJsonAdapterFactory.INSTANCE.create(type, annotations, moshi)
            ?: AppJsonAdapterFactory.INSTANCE.create(type, annotations, moshi)
            ?: ActivityServerModelJsonAdapterFactory().create(type, annotations, moshi)


    companion object {
        val INSTANCE: ApplicationJsonAdapterFactory = ApplicationJsonAdapterFactory()
    }
}