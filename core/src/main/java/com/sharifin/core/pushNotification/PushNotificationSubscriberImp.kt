package com.sharifin.core.pushNotification

import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class PushNotificationSubscriberImp @Inject constructor()
    : PushNotificationSubscriber{

    override fun subscribe(phone: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(phone)
    }

    override fun unSubscribe(phone: String) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(phone)
    }

}