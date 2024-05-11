package di

import data.repository.FlexiRepository
import domain.repository.FlexiRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single { FlexiRepositoryImpl() }
    single {

    }
}