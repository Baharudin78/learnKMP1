package data.repository

import org.koin.core.annotation.Single

@Single
interface FlexiRepository {
    suspend fun loginUser(userName : String, password : String) : String
}