package domain.repository

import data.remote.FlexiApiClient
import data.repository.FlexiRepository

class FlexiRepositoryImpl : FlexiRepository {
    override suspend fun loginUser(userName: String, password: String): String {
        return FlexiApiClient.loginUser(userName, password)
    }
}