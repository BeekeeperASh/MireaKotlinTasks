package alvshinev.application.mireakotlintasks.presentation.ui_logic

import alvshinev.application.mireakotlintasks.domain.GetArticlesUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class MainViewModel @Inject internal constructor (
    private val getArticlesUseCase: Provider<GetArticlesUseCase>,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.None)
    val state: StateFlow<State> = _state

    fun loadNews(query: String) {
        viewModelScope.launch {
            getArticlesUseCase.get().invoke(query).collect {
                _state.value = it.toState()
            }
        }
    }

}
