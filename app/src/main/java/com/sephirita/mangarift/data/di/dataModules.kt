package com.sephirita.mangarift.data.di

import com.sephirita.mangarift.data.remote.ServiceImpl
import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.data.repository.MangaDexRepositoryImpl
import com.sephirita.mangarift.domain.repository.MangaDexRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModules = module {
    factory<Service> {
        ServiceImpl(
            client = get()
        )
    }

    factory<MangaDexRepository> {
        MangaDexRepositoryImpl(
            api = get()
        )
    }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json   { ignoreUnknownKeys = true })
            }
        }
    }
}
