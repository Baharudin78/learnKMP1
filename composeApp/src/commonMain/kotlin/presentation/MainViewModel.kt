package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.FlexiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import utils.ResultState

@KoinViewModel
class MainViewModel(
    private val repository : FlexiRepository
) : ViewModel() {

    private val _loginResult by lazy { MutableStateFlow<ResultState<String>>(ResultState.Loading) }
    val login : StateFlow<ResultState<String>> = _loginResult

    fun login(userName : String, password : String) {
        viewModelScope.launch {
            try {
                val response = repository.loginUser(userName, password)
                _loginResult.value = ResultState.Success(response)
            }catch (error : Exception) {
                _loginResult.value = ResultState.Error(error.message.orEmpty())
            }
        }
    }
}