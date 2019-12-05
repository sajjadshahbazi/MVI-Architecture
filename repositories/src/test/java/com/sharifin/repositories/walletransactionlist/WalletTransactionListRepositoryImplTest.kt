package com.sharifin.repositories.walletransactionlist

import com.sharifin.servermodel.PaginateModel
import com.sharifin.core.Mapper
import com.sharifin.repositories.walletransactionlist.model.WalletTransactionOutputRepoModel
import com.sharifin.repositories.walletransactionlist.model.WalletTransactionRepoModel
import com.sharifin.repository.Fetcher
import com.sharifin.servermodel.WalletTransactionServerModel
import com.sharifin.testhelper.factories.randomLong
import com.sharifin.testhelper.factories.randomString
import com.sharifin.testhelper.rule.RxTrampolineSchedulerRule
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class WalletTransactionListRepositoryImplTest {

    @get:Rule
    val schedulerRule = RxTrampolineSchedulerRule()

    private val walletTransactionFetcher = mockk<Fetcher<String, Response<PaginateModel<ArrayList<WalletTransactionServerModel>>>>>()
    private val walletTransactionMapper = mockk<Mapper<WalletTransactionServerModel, WalletTransactionRepoModel>>()
    private val repo = WalletTransactionListRepositoryImpl(walletTransactionFetcher, walletTransactionMapper)

    @Before
    fun setUp() {
        clearMocks(walletTransactionFetcher, walletTransactionMapper)
    }

    @Test
    fun `test walletTransaction return success`() {
        //arrange
        val walletID = randomLong().toString()
        val transactionModel =
                WalletTransactionServerModel(
                        randomLong(),
                        randomString(),
                        randomString(),
                        randomLong(),
                        randomLong(),
                        randomString(),
                        randomString(),
                        null,
                        null
                )

//        every {
//            walletTransactionFetcher.fetch(walletID)
//        } returns
//                Single.just(Response.success(PaginateModel(ArrayList(), 0)))

        every {
            walletTransactionMapper.map(
                    transactionModel)
        } returns
                WalletTransactionRepoModel(
                        transactionModel.sign!!,
                        transactionModel.time.toString(),
                        transactionModel.amount.toString(),
                        transactionModel.type.toString(),
                        transactionModel.systemDescription.toString(),
                        null,
                        null,
                        sign="")


        //act
        val testObserver = repo.getWalletTransactionList(walletID)
                .test()
        val values: List<WalletTransactionOutputRepoModel> = testObserver.values()


        //assert
        testObserver.assertComplete()
        Assertions.assertThat(values.size).isEqualTo(1)
        Assertions.assertThat(values).isEqualTo(listOf<WalletTransactionOutputRepoModel>(
                WalletTransactionOutputRepoModel.Success(ArrayList())
        ))
    }
}