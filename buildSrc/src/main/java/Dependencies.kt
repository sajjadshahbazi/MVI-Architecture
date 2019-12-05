
import DepVersion.alerterVersion
import DepVersion.androidKotlinExtVersion1
import DepVersion.androidKotlinExtVersion2
import DepVersion.androidxCoreVersion
import DepVersion.apacheCommonsVersion
import DepVersion.archComponentVersion
import DepVersion.assertJVersion
import DepVersion.biometricVersion
import DepVersion.bottomNavVersion
import DepVersion.constraintLayoutVersion
import DepVersion.curveVersion
import DepVersion.daggerVersion
import DepVersion.espressoVersion
import DepVersion.firebaseCoreVersion
import DepVersion.firebaseMessagingVersion
import DepVersion.glideVersion
import DepVersion.googleMaterialVersion
import DepVersion.imagePickerVersion
import DepVersion.imageSliderVersion
import DepVersion.indicatorVersion
import DepVersion.jUnitVersion
import DepVersion.javaxInjectVersion
import DepVersion.javaxVersion
import DepVersion.jetbrainsAnnotationVersion
import DepVersion.jsonTestVersion
import DepVersion.koshiVersion
import DepVersion.kotlinVersion
import DepVersion.lottieVersion
import DepVersion.mockkVersion
import DepVersion.moshiVersion
import DepVersion.multidexVersion
import DepVersion.objectboxVersion
import DepVersion.okHttpVersion
import DepVersion.okIOVersion
import DepVersion.persianDatePickerVersion
import DepVersion.persianDateVersion
import DepVersion.recyclerViewItemDecorationVersion
import DepVersion.retrofitVersion
import DepVersion.robolectricVersion
import DepVersion.roundCardViewVersion
import DepVersion.roundImageView
import DepVersion.rxAndroidVersion
import DepVersion.rxBindingVersion
import DepVersion.rxBroadCastVersion
import DepVersion.rxJavaVersion
import DepVersion.rxKotlinVersion
import DepVersion.supportCoreVersion
import DepVersion.supportLibraryVersion
import DepVersion.supportMonitorVersion
import DepVersion.supportTestVersion
import DepVersion.tSnackBarVersion
import DepVersion.timberVersion
import DepVersion.viewPager2Version
import DepVersion.zXingVersion

object DepVersion {
    const val jUnitVersion = "4.12"
    const val assertJVersion = "2.9.1"
    const val supportTestVersion = "1.1.0"
    const val supportMonitorVersion = "1.1.1"
    const val supportCoreVersion = "1.0.0"
    const val androidxCoreVersion = "1.0.2"
    const val espressoVersion = "3.1.0-alpha3"
    const val mockkVersion = "1.9"
    const val robolectricVersion = "4.2"


    const val kotlinVersion = "1.3.21"

    const val curveVersion = "0.2.5"

    const val supportLibraryVersion = "1.0.0"
    const val viewPager2Version = "1.0.0-alpha03"
    const val googleMaterialVersion = "1.0.0"
    const val pagingComponentVersion = "1.0.0-alpha6"
    const val constraintLayoutVersion = "1.1.3"
    const val archComponentVersion = "2.0.0-rc01"
    const val multidexVersion = "2.0.0"

    const val androidKotlinExtVersion1 = "1.0.0"
    const val androidKotlinExtVersion2 = "2.0.0"

    const val firebaseMessagingVersion = "17.6.0"
    const val firebaseCrashVersion = "16.2.1"
    const val firebaseCoreVersion = "16.0.4"
    const val crashlyticsVersion = "2.9.5"

    const val daggerVersion = "2.18"
    const val javaxVersion = "1.0"
    const val jetbrainsAnnotationVersion = "15.0"
    const val javaxInjectVersion = "1"

    const val rxJavaVersion = "2.1.6"
    const val rxKotlinVersion = "2.3.0"
    const val rxBindingVersion = "2.0.0"
    const val rxAndroidVersion = "2.0.1"
    const val rxBroadCastVersion = "2.0.0"

    const val timberVersion = "4.6.0"

    const val retrofitVersion = "2.3.0"
    const val okIOVersion = "2.2.2"
    const val okHttpVersion = "3.9.1"

    const val moshiVersion = "1.8.0"
    const val koshiVersion = "1.0.5"

    const val leakCanaryVersion = "1.6.3"

    const val objectboxVersion = "2.3.1"

    const val zXingVersion = "1.9.8"

    const val glideVersion = "4.8.0"

    const val lottieVersion = "2.7.0"

    const val moBankAuthVersion = "1.0.4"

    const val recyclerViewItemDecorationVersion = "1.0.0"

    const val imagePickerVersion = "1.5"

    const val materialDialogsVersion = "0.9.6.0"
    const val roundCardViewVersion = "1.0.0"
    const val progressVersion = "2.1.3"
    const val roundImageView = "2.3.0"
    const val btmNavVersion = "2.1.0"

    const val jsonTestVersion = "20140107"

    const val bottomNavVersion = "2.1.0"
    const val alerterVersion = "2.0.6"
    const val tSnackBarVersion = "1.1.1"
    const val persianDatePickerVersion = "1.4"
    const val persianDateVersion = "0.4"

    const val apacheCommonsVersion = "2.6"
    const val biometricVersion = "1.0.0-alpha04"

    const val indicatorVersion = "1.0.3"

    const val imageSliderVersion = "1.4.1"

}

object Dep {

    object Testing {
        const val junit = "junit:junit:$jUnitVersion"
        const val assertJ = "org.assertj:assertj-core:$assertJVersion"

        const val mockkUnit = "io.mockk:mockk:$mockkVersion"
        const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

        const val supportJunitExt = "androidx.test.ext:junit:$supportCoreVersion"
        const val supportCore = "androidx.test:core:$supportTestVersion"
        const val supportMonitor = "androidx.test:monitor:$supportMonitorVersion"
        const val supportTestRunner = "androidx.test:runner:$supportTestVersion"
        const val supportTestRule = "androidx.test:rules:$supportTestVersion"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"

        const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

        const val jsonTest = "org.json:json:$jsonTestVersion"
    }

    object ObjectBox {
        const val objectBoxJava = "io.objectbox:objectbox-java-api:$objectboxVersion"
        const val objectBoxRxJava = "io.objectbox:objectbox-rxjava:$objectboxVersion"
        const val objectBoxKotlin = "io.objectbox:objectbox-kotlin:$objectboxVersion"
        const val objectBoxBrowser = "io.objectbox:objectbox-android-objectbrowser:$objectboxVersion"
        const val objectBoxAndroid = "io.objectbox:objectbox-android:$objectboxVersion"
        const val objectBoxWindows = "io.objectbox:objectbox-windows:$objectboxVersion"
    }

    object Kotlin {
        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    }

    object Support {
        const val compatV7 = "androidx.appcompat:appcompat:$supportLibraryVersion"
        const val design = "com.google.android.material:material:$googleMaterialVersion"
        const val cardView = "androidx.cardview:cardview:$supportLibraryVersion"
        const val v4 = "androidx.legacy:legacy-support-v4:$supportLibraryVersion"
        const val annotation = "androidx.annotation:annotation:$supportLibraryVersion"
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable:$supportLibraryVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$supportLibraryVersion"
        const val multiDex = "androidx.multidex:multidex:$multidexVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val biometric = "androidx.biometric:biometric:$biometricVersion"
        const val viewPager2 = "androidx.viewpager2:viewpager2:$viewPager2Version"
        const val customTabs = "androidx.browser:browser:$supportCoreVersion"
        const val androidxCore = "androidx.core:core:$androidxCoreVersion"
    }

    object Google{
        const val maps = "com.google.android.gms:play-services-maps:12.0.1"
        const val googleMap = "com.google.android.gms:play-services:12.0.1"
    }

    object RxBroadcast {
        const val rxBroadcast = "com.cantrowitz:rxbroadcast:$rxBroadCastVersion"

    }

    object ApiPhone {
        const val apiPhone = "com.google.android.gms:play-services-auth-api-phone:12.0.1"

    }

    object ArchitectureComp {
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:$archComponentVersion"
        const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:$archComponentVersion"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$archComponentVersion"
        const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams:$archComponentVersion"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
        const val okIO = "com.squareup.okio:okio:$okIOVersion"
    }

    object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxKotlinVersion"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
        const val rxPreferences = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"
        const val rxJavaRetrofit = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
        const val rxBinding = "com.jakewharton.rxbinding2:rxbinding:$rxBindingVersion"
        const val rxBindingKotlin = "com.jakewharton.rxbinding2:rxbinding-kotlin:$rxBindingVersion"
        const val rxBindingCompat = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:$rxBindingVersion"
        const val rxBindingCompatKotlin = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:$rxBindingVersion"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
        const val moshiRetrofit = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        const val kotshi = "se.ansman.kotshi:api:$koshiVersion"
        const val kotshiCompiler = "se.ansman.kotshi:compiler:$koshiVersion"
        const val gson = "com.google.code.gson:gson:2.8.0"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val daggerAndroid = "com.google.dagger:dagger-android-support:$daggerVersion"
        const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"

        const val javaxAnnotation = "javax.annotation:jsr250-api:$javaxVersion"
        const val glassfishAnnotation = "org.glassfish:javax.annotation:10.0-b28"
        const val jetbrainsAnnotation = "org.jetbrains:annotations-java5:$jetbrainsAnnotationVersion"
        const val injectAnnotation = "javax.inject:javax.inject:$javaxInjectVersion"
    }

    object Ui {
        const val roundCardView = "com.github.captain-miao:optroundcardview:$roundCardViewVersion"
        const val roundImageVIew = "com.makeramen:roundedimageview:$roundImageView"
        const val recyclerViewtemDecoration = "com.bignerdranch.android:simple-item-decoration:$recyclerViewItemDecorationVersion"
        const val discreteScrollView = "com.yarolegovich:discrete-scrollview:1.4.9"
        const val lottie = "com.airbnb.android:lottie:$lottieVersion"
        const val bottomBar = "com.aurelhubert:ahbottomnavigation:$bottomNavVersion"
        const val alerter = "com.tapadoo.android:alerter:$alerterVersion"
        const val tSnackbar = "com.androidadvance:topsnackbar:$tSnackBarVersion"
        const val pagerIndicator = "com.romandanylyk:pageindicatorview:$indicatorVersion@aar"
        const val sunDatePicker = "com.alirezaafkar:sundatepicker:2.1.1"
        const val sectionedRecyclerView = "com.github.IntruderShanky:Sectioned-RecyclerView:2.1.1"
        const val materialRatingBar = "me.zhanghai.android.materialratingbar:library:1.3.1"
        const val circularIndicator = "me.relex:circleindicator:1.2.2@aar"
        const val expandableLayout = "net.cachapa.expandablelayout:expandablelayout:2.9.2"
        const val materialDialogs = "com.afollestad.material-dialogs:core:0.9.6.0"
        const val bannerSlider = "com.ss.bannerslider:bannerslider:2.0.0"
        const val materialRangeBar = "com.appyvet:materialrangebar:1.4.4"
    }

    object androidKTS {
        const val coreExt = "androidx.core:core-ktx:$androidKotlinExtVersion1"
        const val fragmentExt = "androidx.fragment:fragment-ktx:$androidKotlinExtVersion1"
        const val collectionExt = "androidx.collection:collection-ktx:$androidKotlinExtVersion1"
        const val viewModelExt = "androidx.lifecycle:lifecycle-viewmodel-ktx:$androidKotlinExtVersion2"

    }

    object callenderUtils {
        const val persianDatePicker = "com.github.aliab:Persian-Date-Picker-Dialog:$persianDatePickerVersion"
        const val persianDateUtil = "com.github.samanzamani.persiandate:PersianDate:$persianDateVersion"
    }

    object Firebase {
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:$firebaseMessagingVersion"
        const val firebaseCore = "com.google.firebase:firebase-core:$firebaseCoreVersion"
    }


    object Utils {
        const val timber = "com.jakewharton.timber:timber:$timberVersion"

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val picasso = "com.squareup.picasso:picasso:2.5.2"

        const val curve25519 = "org.whispersystems:curve25519-android:$curveVersion"

        const val qrScanner = "me.dm7.barcodescanner:zxing:$zXingVersion"

        const val imagePicker = "com.linchaolong.android:imagepicker:$imagePickerVersion"

        const val apacheCommons = "commons-lang:commons-lang:$apacheCommonsVersion"

        const val imageSlider = "com.github.firdausmaulan:GlideSlider:$imageSliderVersion"

        const val easyPrefs = "com.pixplicity.easyprefs:library:1.9.0"

        const val calligraphy = "uk.co.chrisjenx:calligraphy:2.2.0"

        const val epoxy = "com.airbnb.android:epoxy:3.6.0"
        const val epoxyCompiler = "com.airbnb.android:epoxy-processor:3.6.0"
    }

    object Modules {
        const val core = ":core"
        const val base = ":base"
        const val widgets = ":widgets"
        const val receipt = ":receipt"
        const val settings = ":settings"
        const val walletReport = ":walletreport"
        const val security = ":security"
        const val deposit = ":deposit"
        const val myQrCode = ":myqrcode"
        const val securityImpl = ":securityimpl"
        const val billPaymentPenalties = ":billpaymentpenalties"
        const val retrofit = ":retrofit"
        const val transactionResult = ":transactionresult"
        const val repository = ":repository"
        const val serverModel = ":servermodel"
        const val twoButtonDialog = ":twobuttondialog"
        const val singleButtonDialog = ":singlebuttondialog"
        const val payment = ":payment"
        const val qrPayment = ":qrpayment"
        const val repositories = ":repositories"
        const val chooseContact = ":choosecontacts"
        const val requestMoney = ":requestmoney"
        const val resources = ":resources"
        const val newWallet = ":newwallet"
        const val walletDetail = ":walletdeatil"
        const val walletTransferWithdraw = ":wallettransferwithdraw"
        const val walletTransfer = ":wallettransferwithdraw"
        const val testHelper = ":testhelper"
        const val calendar = ":calendar"
        const val main = ":main"
        const val imageSlider = ":imageslider"

        const val investPanther = ":investment:Panther"
        const val investApp = ":investment:invest_app"
        const val notifications = ":notifications"
        const val barcodeScanner = ":barcodescanner"
        const val bills = ":bills"
        const val internetPackages = ":internetpackages"
        const val chargePackages = ":chargepackages"
        const val phoneBill = ":phonebill"
        const val contactUs = ":contactus"
        const val twobuttonwithedittextdialog = ":twobuttonwithedittextdialog"
        const val walletTransactionAdapter = ":wallettransactionadapter"
        const val twoButtonCheckboxDialog = ":twobuttoncheckboxdailog"
        const val authentication = ":authentication"
        const val fingerprint = ":fingerprint"
        const val tourismApp = ":tourism:tourism-app"
        const val tourismBarcodeScanner = ":tourism:tourism-barcodescanner"
        const val tourismFine = ":tourism:tourism-fine"
        const val tourismResoureces = ":tourism:tourism_resources"
        const val navigation = ":navigation"
        const val cardToCard = ":cardtocard"
        const val stickyHeaders = ":stickyheaders"
        const val databaseRepo = ":databaserepo"
        const val optionsdialog = ":optionsdialog"

    }
}
