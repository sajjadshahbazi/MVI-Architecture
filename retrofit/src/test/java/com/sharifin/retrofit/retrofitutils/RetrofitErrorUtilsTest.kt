package com.sharifin.retrofit.retrofitutils

import org.junit.Assert.assertEquals
import org.junit.Test

class RetrofitErrorUtilsTest{
    @Test
    fun `test generic error parser 1`(){
        val errorBody = "{\"desk\":{\"code\":400},\"data\":{\"code\":\"40001\",\"detail\":\"این سرویس در حال حاضر در دسترس نیست\",\"status_code\":\"400\"}}"
        val error = errorBody.genericErrorParseFirstError()

        assertEquals("این سرویس در حال حاضر در دسترس نیست", error)
    }
}