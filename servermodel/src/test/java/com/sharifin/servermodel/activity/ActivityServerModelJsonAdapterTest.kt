package com.sharifin.servermodel.activity

import com.sharifin.servermodel.InvoiceServerModel
import com.sharifin.servermodel.ServerModelsJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.buffer
import okio.source
import org.assertj.core.api.Assertions.assertThat
import org.intellij.lang.annotations.Language
import org.junit.Before
import org.junit.Test
import java.io.File

class ActivityServerModelJsonAdapterTest {

    private val moshi = Moshi
            .Builder()
            .add(ServerModelsJsonAdapterFactory.INSTANCE)
            .add(ActivityServerModelJsonAdapterFactory())
            .build()

    @Before
    fun setUp() {
    }

    @Test
    fun `moshi parses single activity json correctly`() {
        @Language("JSON") val json = "{\n  \"created_at\": \"2019-05-16T08:06:12.766376Z\",\n  \"metadata\": {\n    \"paid_with\": \"ipg\",\n    \"operator\": \"MCI\",\n    \"invoice\": {\n      \"sign\": \"d7bb8e8a2c0d039b62c0593add6be6ac114\",\n      \"id\": 114,\n      \"amount\": 10000,\n      \"type\": \"buy\"\n    },\n    \"ipg\": {\n      \"gateway\": \"fake\",\n      \"res_num\": \"1410281590\",\n      \"ref_num\": \"rAOKWdoBDoBteq1PogdnTTlIACR7v58A\",\n      \"gateway_verbose_name\": \"آزمایشی\"\n    },\n    \"payment_token\": \"d7bb8e8a2c0d039b62c0593add6be6ac114\",\n    \"package_description\": \"شارژ مستقیم 10000 ریالی\",\n    \"phone\": \"+989909388682\",\n    \"amount\": 10000\n  },\n  \"type\": \"mobile_charge\",\n  \"aggregation_key\": \"TYKDK9vQ5v\",\n  \"amount\": 10000,\n  \"tags\": [\n    \"mobile_charge\",\n    \"transaction\"\n  ]\n}"
        val expectedActivity = ActivityServerModel(
                createdAt = "2019-05-16T08:06:12.766376Z",
                type = "mobile_charge",
                aggregationKey = "TYKDK9vQ5v",
                amount = 10000,
                tags = listOf(
                        "mobile_charge",
                        "transaction"
                ),
                metadata = ActivityMetaDataServerModel.MobileCharge(
                        amount = 10000,
                        operator = "MCI",
                        paymentToken = "d7bb8e8a2c0d039b62c0593add6be6ac114",
                        packageDescription = "شارژ مستقیم 10000 ریالی",
                        phone = "+989909388682",
                        paidWith = PaymentMethodServerModel.WithIPG(
                                IPGServerModel(
                                        resNum = "1410281590",
                                        refNum = "rAOKWdoBDoBteq1PogdnTTlIACR7v58A",
                                        gatewayName = "fake",
                                        gatewayPersianName = "آزمایشی"
                                )
                        ),
                        invoice = InvoiceServerModel(
                                sign = "d7bb8e8a2c0d039b62c0593add6be6ac114",
                                id = 114,
                                amount = 10000,
                                type = "buy",
                                title = "",
                                resNum = "",
                                state = "",
                                description = "",
                                createdAt = 0,
                                isPayable = false,
                                qrCodeUrl = "",
                                payee = null,
                                payer = null,
                                create = null
                        )
                )
        )
        val activity = moshi.adapter<ActivityServerModel>(ActivityServerModel::class.java).fromJson(json)!!

        assertThat(activity)
                .isEqualToComparingFieldByFieldRecursively(expectedActivity)
    }

    @Test
    fun `parses entire list correctly`() {
        val url = this.javaClass.classLoader.getResource("activityHistories.json")
        val file = File(url.toURI())
        val json = file.source().buffer().readUtf8()

        val type = Types.newParameterizedType(List::class.java, ActivityServerModel::class.java)
        val jsonConverted = moshi.adapter<List<ActivityServerModel>>(type).fromJson(json)!!

        jsonConverted.forEach {

        }
    }


}