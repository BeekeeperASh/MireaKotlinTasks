package alvshinev.application.mireakotlintasks.presentation.ui_logic

import alvshinev.application.mireakotlintasks.domain.usecases.ClearArticlesFromDatabaseUseCase
import alvshinev.application.mireakotlintasks.domain.usecases.GetArticlesFromApiUseCase
import alvshinev.application.mireakotlintasks.domain.usecases.GetArticlesFromDbUseCase
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class MainViewModel @Inject internal constructor (
    private val getArticlesFromDbUseCase: Provider<GetArticlesFromDbUseCase>,
    private val getArticlesFromApiUseCase: Provider<GetArticlesFromApiUseCase>,
    private val clearArticlesFromDatabaseUseCase: Provider<ClearArticlesFromDatabaseUseCase>
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.None)
    val state: StateFlow<State> = _state

    fun loadNewsFromApi(query: String) {
        viewModelScope.launch {
            clearArticlesFromDatabaseUseCase.get().invoke()
            getArticlesFromApiUseCase.get().invoke(query).collect {
                _state.value = it.toState()
            }
        }
    }

    fun loadNewsFromDatabase() {
        viewModelScope.launch {
            getArticlesFromDbUseCase.get().invoke().collect {
                _state.value = it.toState()
            }
        }
    }

    fun clearNewsDatabase() {
        viewModelScope.launch {
            clearArticlesFromDatabaseUseCase.get().invoke()
        }
    }

    fun saveRequestToPrefs(request: String, context: Context) {
        val prefs = context.getSharedPreferences("request_shared_preferences", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("last_request", request)
        editor.apply()
    }

    fun getRequestFromPrefs(context: Context) : String {
        val prefs = context.getSharedPreferences("request_shared_preferences", Context.MODE_PRIVATE)
        return prefs.getString("last_request", "android") ?: "news"
    }

}
