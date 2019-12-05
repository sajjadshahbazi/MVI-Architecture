package com.sharifin.core.pushNotification

interface PushNotificationSubscriber {
    fun subscribe(phone : String)
    fun unSubscribe(phone : String)
}