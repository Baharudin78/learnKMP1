package data.remote

import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import utils.Constant.BASE_URL
import utils.Constant.TIME_OUT

object FlexiApiClient {
    val createSupabaseClient = createSupabaseClient(
        supabaseUrl = "https://flrflqyxquvzhlvfcbit.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZscmZscXl4cXV2emhsdmZjYml0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ3MjA3MDgsImV4cCI6MjAzMDI5NjcwOH0.HjJVA5yZdXIKHmICMxucgOJqYSz-APT_pYyEKr9FvaE"
    ) {
        install(Auth)
        install(ComposeAuth)
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIME_OUT
            connectTimeoutMillis = TIME_OUT
            socketTimeoutMillis = TIME_OUT
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun loginUser(email: String, password: String) : String{
        val url = BASE_URL + "v1/login"
        val loginRequest = Parameters.build {
            append("email", email)
            append("password", password)
        }
        return client.post(url) {
            body = FormDataContent(loginRequest)
        }.body()
    }
}