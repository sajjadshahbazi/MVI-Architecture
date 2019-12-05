package com.sharifin.repositories.newwallet

import com.sharifin.core.Mapper
import com.sharifin.repositories.newwallet.model.NewWalletOutputRepoModel
import com.sharifin.repositories.newwallet.model.NewWalletRepoModel
import com.sharifin.repository.Fetcher
import com.sharifin.servermodel.WalletServerModel
import com.sharifin.servermodel.requestmodels.NewWalletRequestModel
import com.sharifin.testhelper.factories.randomInt
import com.sharifin.testhelper.factories.randomLong
import com.sharifin.testhelper.factories.randomString
import com.sharifin.testhelper.rule.RxTrampolineSchedulerRule
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class NewWalletRepositoryImplTest {

    @get:Rule
    val schedulerRule = RxTrampolineSchedulerRule()

    private val newWalletFetcher = mockk<Fetcher<NewWalletRequestModel, Response<WalletServerModel>>>()
    private val newWalletMapper = mockk<Mapper<WalletServerModel, NewWalletRepoModel>>()
    private val repo = NewWalletRepositoryImpl(newWalletFetcher, newWalletMapper)

    @Before
    fun setUp() {
        clearMocks(newWalletMapper, newWalletFetcher)
    }

    @Test
    fun `test createNewWallet return success`() {
        //arrange
        val id = randomString()
        val type = randomInt()
        val name = randomString()
        val walletModel =
                WalletServerModel(
                        id,
                        randomString(),
                        randomString(),
                        randomLong(),
                        false,
                        randomLong(),
                        randomString(),
                        randomLong(),
                        randomString(),
                        null,
                        randomInt())

        every {
            newWalletFetcher.fetch(
                    NewWalletRequestModel(
                            type = type, name = name
                    ))
        } returns
                Single.just(Response.success(walletModel))

        every {
            newWalletMapper.map(
                    walletModel)
        } returns
                NewWalletRepoModel(id , name)


        //act
        val testObserver = repo.createNewWallet(name, type)
                .test()
        val values: List<NewWalletOutputRepoModel> = testObserver.values()


        //assert
        testObserver.assertComplete()
        assertThat(values.size).isEqualTo(1)
        assertThat(values).isEqualTo(listOf<NewWalletOutputRepoModel>(
                NewWalletOutputRepoModel.Success(NewWalletRepoModel(id , name))
        ))
    }
}
