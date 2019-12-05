package com.sharifin.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import com.sharifin.navigation.model.*
import com.sharifin.navigation.model.cardtocard.CardFinalLocalModel
import com.sharifin.navigation.model.cardtocard.CardListLocalModel
import com.sharifin.navigation.model.cardtocard.WalletTransferLocalModel
import com.sharifin.navigation.model.contactlist.ContactListLocalModel
import com.sharifin.navigation.model.payment.InvoiceLocalModel
import com.sharifin.navigation.model.wallet.WalletDetailLocalModel
import com.sharifin.navigation.model.wallet.WalletReportLocalModel
import com.sharifin.navigation.model.wallet.WalletTransferLocalModelInWallet
import com.sharifin.navigation.model.wallet.WalletWithdrawLocalModel
import kotlinx.android.parcel.Parcelize


//tagged screens can be jumped to
interface IsTagged

//cleans entire Android stack (not the navigation stack)
interface Cleaner

interface NoStack

sealed class Screen(
    open val activityIntentAction: String,
    open val bundle: Bundle = Bundle.EMPTY
) : Parcelable {

    open abstract class OtherScreens(
        override val activityIntentAction: String,
        override val bundle: Bundle
    ) : Screen(
            activityIntentAction,
            bundle
    )

    sealed class Home(
        activityIntentAction: String,
        bundle: Bundle = Bundle.EMPTY
    ) : Screen(
            activityIntentAction,
            bundle
    ) {
        @Parcelize
        data class MainScreen(
            val mainActivityLocal: MainActivityLocalModel,
            val notificationBundle: Bundle = Bundle.EMPTY
        ) : Home(
                NavigationActionConstants.INTENT_ACTION_MAIN,
                bundleOf(
                        BundleConstants.EXTRA_MAIN_SCREEN to
                                mainActivityLocal
                ).apply {
                    putAll(notificationBundle)
                }
        ), IsTagged

        @Parcelize
        data class FilteredActivityHistory(
            val model: FilteredActivityHistoryLocalModel
        ) : Home(
                NavigationActionConstants.INTENT_ACTION_FILTERED_ACTIVITY_HISTORY,
                bundleOf(
                        BundleConstants.EXTRA_ACTIVITY_HISTORY_MODEL to
                                model
                )
        ), IsTagged
    }


    sealed class Authentication(
        override val activityIntentAction: String,
        override val bundle: Bundle = Bundle.EMPTY
    ) : Screen(
            activityIntentAction,
            bundle
    ), Parcelable {
        @Parcelize
        data class LoginScreen(
            val loginLocal: LoginLocalModel,
            val notificationBundle: Bundle = Bundle.EMPTY
        ) : Authentication(
                NavigationActionConstants.INTENT_ACTION_LOGIN,
                bundleOf(
                        BundleConstants.EXTRA_LOGIN_SCREEN to
                                loginLocal
                ).apply {
                    putAll(notificationBundle)
                }
        ), Cleaner

        @Parcelize
        data class OTPScreen(
            val otpLocal: OtpLocalModel,
            val notificationBundle: Bundle = Bundle.EMPTY
        ) : Authentication(
                NavigationActionConstants.INTENT_ACTION_OTP,
                bundleOf(
                        BundleConstants.EXTRA_OTP_SCREEN to
                                otpLocal
                ).apply {
                    putAll(notificationBundle)
                }
        )

        @Parcelize
        class RegisterScreen
            : Authentication(
                NavigationActionConstants.INTENT_ACTION_REGISTER
        )

    }


    sealed class CardToCard(
        override val activityIntentAction: String,
        override val bundle: Bundle = Bundle.EMPTY
    ) : Screen(
            activityIntentAction,
            bundle
    ), Parcelable {
        @Parcelize
        class CardToCardScreen : CardToCard(
                NavigationActionConstants.INTENT_ACTION_CARD_TO_CARD
        )

        @Parcelize
        data class CardListScreen(
            private val cardListLocalModel: CardListLocalModel
        ) : CardToCard(
                NavigationActionConstants.INTENT_ACTION_CARD_LIST,
                bundleOf(
                        BundleConstants.EXTRA_CARD_LIST_SCREEN to
                                cardListLocalModel
                ))

        @Parcelize
        data class CardInformationScreen(
            private val cardFinalLocalModel: CardFinalLocalModel
        ) : CardToCard(
                NavigationActionConstants.INTENT_ACTION_CARD_INFORMATION,
                bundleOf(
                        BundleConstants.EXTRA_CARD_INFORMATION_SCREEN to cardFinalLocalModel
                ))
    }

    sealed class Deposit(
        override val activityIntentAction: String,
        override val bundle: Bundle = Bundle.EMPTY
    ) : Screen(
            activityIntentAction,
            bundle
    ) {
        @Parcelize
        class DepositScreen
            : Deposit(
                NavigationActionConstants.INTENT_ACTION_DEPOSIT
        )

        @Parcelize
        data class WalletToWallet(
            private val localModel: WalletTransferLocalModel
        ) : Deposit(
                NavigationActionConstants.INTENT_ACTION_WALLET_TO_WALLET,
                bundleOf(
                        BundleConstants.EXTRA_WALLET_TRANSFER to localModel
                )
        )
    }

    @Parcelize
    class ContactList(
        val localModel: ContactListLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_CONTACT_LIST_ACTIVITY,
            bundleOf(
                    BundleConstants.CONTACT_LIST to localModel
            )
    )

    @Parcelize
    class ContactUs : Screen(
            NavigationActionConstants.INTENT_ACTION_CONTACT_US_ACTIVITY
    )

    @Parcelize
    class PenaltyBill : Screen(
            NavigationActionConstants.INTENT_ACTION_INQUIRY_PENALTY_BILL
    )

    @Parcelize
    class ChargePackage : Screen(
            NavigationActionConstants.INTENT_ACTION_CHARGE_PACKAGE
    )

    @Parcelize
    class InternetPackage : Screen(
            NavigationActionConstants.INTENT_ACTION_INTERNET_PACKAGE
    )

    @Parcelize
    class InvoicePayment(
        private val localModel: InvoiceLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_INVOICE_PAYMENT,
            bundleOf(
                    BundleConstants.EXTRA_INVOICE_MODEL to localModel
            )
    )

    @Parcelize
    class WalletPayment(
        private val localModel: WalletLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_WALLET_PAYMENT,
            bundleOf(
                    BundleConstants.EXTRA_WALLET_MODEL to localModel
            )
    )

    @Parcelize
    class PhoneBill : Screen(
            NavigationActionConstants.INTENT_ACTION_PHONE_BILL
    )

    @Parcelize
    class Bills : Screen(
            NavigationActionConstants.INTENT_ACTION_BILLS
    )

    @Parcelize
    class QRPayment : Screen(
            NavigationActionConstants.INTENT_ACTION_QR_PAYMENT
    )

    @Parcelize
    class ChangePass : Screen(
            NavigationActionConstants.INTENT_ACTION_CHANGE_PASS
    )

    @Parcelize
    class Setting : Screen(
            NavigationActionConstants.INTENT_ACTION_SETTING
    )

    @Parcelize
    class ChangeProfileSetting : Screen(
            NavigationActionConstants.INTENT_ACTION_ChANGE_PROFILE_SETTING
    )

    @Parcelize
    class FingerPrint(
        private val phone: String
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_FINGERPRINT,
            bundleOf(
                    BundleConstants.PHONENUMBER to phone
            )
    )

    @Parcelize
    class WalletDetail(
        private val walletLocalModel: WalletDetailLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_WALLET_DETAIL,
            bundleOf(
                    BundleConstants.EXTRA_WALLET_DETAIL_MODEL to walletLocalModel
            )
    ), IsTagged

    @Parcelize
    class WalletReport(
        private val walletReportLocalModel: WalletReportLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_WALLET_REPORT,
            bundleOf(
                    BundleConstants.EXTRA_Wallet_REPORT_MODEL to walletReportLocalModel
            )
    )

    @Parcelize
    class WalletTransfer(
        private val walletReportLocalModel: WalletTransferLocalModelInWallet
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_WALLET_TRANSFER,
            bundleOf(
                    BundleConstants.EXTRA_WALLET to walletReportLocalModel
            )
    )

    @Parcelize
    class WalletWithDraw(
        private val walletWithDrawLocalModel: WalletWithdrawLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_WALLET_WITH_DRAW,
            bundleOf(
                    BundleConstants.EXTRA_WALLET to walletWithDrawLocalModel
            )
    )

    @Suppress("PLUGIN_WARNING")
    @Parcelize
    class RequestMoney : Screen(
            NavigationActionConstants.INTENT_ACTION_REQUEST_MONEY
    )

    @Parcelize
    class Notification(
        private val model: NotificationActivityLocalModel? = null
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_NOTIFICATION,
            bundleOf(
                    BundleConstants.EXTRA_NOTIFICATION to model
            )
    )

    @Parcelize
    class InvestMain : Screen(
            NavigationActionConstants.INTENT_ACTION_INVEST_MAIN

    )

    @Parcelize
    class MyQr : Screen(
            NavigationActionConstants.INTENT_ACTION_MY_QR
    )

    @Parcelize
    class WebPage(
        private val localModel: WebLocalModel
    ) : Screen(
            NavigationActionConstants.INTENT_ACTION_WEB,
            bundleOf(
                    WebLocalModel.EXTRA_WEB_LOCAL to localModel
            )
    )

    @Parcelize
    class NewWallet : Screen(
            NavigationActionConstants.INTENT_NEW_WALLET
    )

    @Parcelize
    class PushHandler (
            val extra : Bundle
    ): Screen(
            NavigationActionConstants.INTENT_ACTION_PUSH_ACTION_HANDLER,
            extra
    ), NoStack

    @Parcelize
    class About (
    ): Screen(
            NavigationActionConstants.INTENT_ACTION_ABOUT
    ), NoStack

    @Parcelize
    class Investment : Screen(
            NavigationActionConstants.INTENT_ACTION_INVESTMENT
    ), NoStack

    @Parcelize
    class Tourism : Screen(
            NavigationActionConstants.INTENT_ACTION_TOURISM
    ), NoStack

}