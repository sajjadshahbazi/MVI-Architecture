package com.sharifin.base

interface UserSessionManager{
    var userSessionValid: Boolean
}
/**
    this flag indicate if app has been survived after being in background.
    didAppSurvived is used as a singleton among app.
    is set to false as app starts.
    will set to true after transmission successful.
    will set to false after app is being killed since it stored in memory.
*/