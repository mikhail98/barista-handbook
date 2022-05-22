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
import com.eratart.baristashandbook.tools.navigator.GlobalNavigator
import com.eratart.baristashandbook.tools.navigator.IGlobalNavigator
import com.eratart.baristashandbook.tools.resources.IResourceManager
import com.eratart.baristashandbook.tools.resources.ResourceManager
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

        return modulesList
    }
}

val appModule = module {
    single<IGlobalNavigator> { GlobalNavigator() }
    single<IRetrofitBuilder> { RetrofitBuilder() }
    single<IResourceManager> { ResourceManager(get()) }
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
    single<IAppCache> { AppCache(get(), get(), get(), get()) }
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