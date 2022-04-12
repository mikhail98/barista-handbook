package com.eratart.baristashandbook.app.di

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.eratart.baristashandbook.data.cache.AppCache
import com.eratart.baristashandbook.data.firebase.FirebaseAnalyticsManager
import com.eratart.baristashandbook.data.network.IRetrofitBuilder
import com.eratart.baristashandbook.data.network.RetrofitBuilder
import com.eratart.baristashandbook.data.preferencnes.Preferences
import com.eratart.baristashandbook.data.preferencnes.impl.AppPreferences
import com.eratart.baristashandbook.data.preferencnes.impl.FavoritesPreferences
import com.eratart.baristashandbook.data.preferencnes.impl.OnboardingPreferences
import com.eratart.baristashandbook.data.repository.DishesRepo
import com.eratart.baristashandbook.data.repository.ItemCategoriesRepo
import com.eratart.baristashandbook.data.repository.ItemsRepo
import com.eratart.baristashandbook.data.repository.NewsRepo
import com.eratart.baristashandbook.domain.cache.IAppCache
import com.eratart.baristashandbook.domain.firebase.IFirebaseAnalyticsManager
import com.eratart.baristashandbook.domain.interactor.cache.AppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.cache.IAppCacheInteractor
import com.eratart.baristashandbook.domain.interactor.favorites.FavoritesInteractor
import com.eratart.baristashandbook.domain.interactor.favorites.IFavoritesInteractor
import com.eratart.baristashandbook.domain.interactor.tg.INewsInteractor
import com.eratart.baristashandbook.domain.interactor.tg.NewsInteractor
import com.eratart.baristashandbook.domain.preferences.IAppPreferences
import com.eratart.baristashandbook.domain.preferences.IFavoritesPreferences
import com.eratart.baristashandbook.domain.preferences.IOnboardingPreferences
import com.eratart.baristashandbook.domain.repository.IDishesRepo
import com.eratart.baristashandbook.domain.repository.IItemCategoriesRepo
import com.eratart.baristashandbook.domain.repository.IItemsRepo
import com.eratart.baristashandbook.domain.repository.INewsRepo
import com.eratart.baristashandbook.presentation.artinstructions.di.artInstructionsModule
import com.eratart.baristashandbook.presentation.dishdetails.di.dishDetailsModule
import com.eratart.baristashandbook.presentation.disheslist.di.dishesListModule
import com.eratart.baristashandbook.presentation.favorites.di.favoritesModule
import com.eratart.baristashandbook.presentation.itemdetails.di.itemDetailsModule
import com.eratart.baristashandbook.presentation.itemscategorieslist.di.itemsCategoriesListModule
import com.eratart.baristashandbook.presentation.itemslist.di.itemsListModule
import com.eratart.baristashandbook.presentation.mainmenu.di.mainMenuModule
import com.eratart.baristashandbook.presentation.news_details.di.newsDetailsModule
import com.eratart.baristashandbook.presentation.news_list.di.newsListModule
import com.eratart.baristashandbook.presentation.onboarding.di.onboardingModule
import com.eratart.baristashandbook.presentation.routing.di.routingModule
import com.eratart.baristashandbook.presentation.settings.di.settingsModule
import com.eratart.baristashandbook.tools.navigator.GlobalNavigator
import com.eratart.baristashandbook.tools.navigator.IGlobalNavigator
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    fun getModules(): List<Module> {
        val modulesList = mutableListOf<Module>()
        modulesList.add(appModule)
        modulesList.add(analyticsModule)

        modulesList.add(repoModule)
        modulesList.add(preferencesModule)
        modulesList.add(interactorModule)
        modulesList.add(cacheModule)

        modulesList.add(artInstructionsModule)
        modulesList.add(dishDetailsModule)
        modulesList.add(dishesListModule)
        modulesList.add(favoritesModule)
        modulesList.add(itemDetailsModule)
        modulesList.add(itemsCategoriesListModule)
        modulesList.add(itemsListModule)
        modulesList.add(mainMenuModule)
        modulesList.add(newsListModule)
        modulesList.add(newsDetailsModule)
        modulesList.add(onboardingModule)
        modulesList.add(settingsModule)
        modulesList.add(routingModule)
        return modulesList
    }
}

val appModule = module {
    single<IGlobalNavigator> { GlobalNavigator() }
    single<IRetrofitBuilder> { RetrofitBuilder() }
    single { get<IRetrofitBuilder>().getTgApi() }
}

val analyticsModule = module {
    single { FirebaseAnalytics.getInstance(get()) }
    single<IFirebaseAnalyticsManager> { FirebaseAnalyticsManager(get()) }
}

val repoModule = module {
    single<IItemCategoriesRepo> { ItemCategoriesRepo(get()) }
    single<IDishesRepo> { DishesRepo(get()) }
    single<IItemsRepo> { ItemsRepo(get()) }
    single<INewsRepo> { NewsRepo(get()) }
}

val cacheModule = module {
    single<IAppCache> { AppCache(get(), get(), get()) }
}

val interactorModule = module {
    single<IAppCacheInteractor> { AppCacheInteractor(get()) }
    single<IFavoritesInteractor> { FavoritesInteractor(get()) }
    single<INewsInteractor> { NewsInteractor(get()) }
}

val preferencesModule = module {
    single {
        MasterKey.Builder(get())
            .setKeyGenParameterSpec(Preferences.createAES256GCMKeyGenParameterSpec())
            .build()
    }
    single {
        EncryptedSharedPreferences.create(
            get(),
            Preferences.PREFERENCES_NAME, get<MasterKey>(),
            Preferences.keyEncryptor,
            Preferences.valueEncryptor
        )
    }
    single<IAppPreferences> { AppPreferences(get()) }
    single<IOnboardingPreferences> { OnboardingPreferences(get()) }
    single<IFavoritesPreferences> { FavoritesPreferences(get()) }
}