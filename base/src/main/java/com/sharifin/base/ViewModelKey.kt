package com.sharifin.base


import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention()
@MapKey
annotation class ViewModelKey(val clazz: KClass<out ViewModel>)
