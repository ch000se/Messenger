package %PACKAGE%.presentation

import com.elveum.container.Container
import com.ch000se.messenger.core.presentation.WithMviState
import com.ch000se.messenger.core.presentation.base.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import %PACKAGE%.domain.Get%MODULE_NAME%UseCase
import %PACKAGE%.domain.Save%MODULE_NAME%UseCase
import kotlinx.coroutines.delay
import java.util.UUID

@HiltViewModel
class %MODULE_NAME%ViewModel @Inject constructor(
    get%MODULE_NAME%UseCase: Get%MODULE_NAME%UseCase,
    private val save%MODULE_NAME%UseCase: Save%MODULE_NAME%UseCase,
) : AbstractViewModel(),
    WithMviState<%MODULE_NAME%ViewModel.State> {

    private val reducer = get%MODULE_NAME%UseCase()
        .containerToReducer(::State, State::copy)
    val stateFlow: StateFlow<Container<State>> = reducer.stateFlow

    fun increment() = launch {
        // example of updating data in domain:
        save%MODULE_NAME%UseCase("Random Title: ${UUID.randomUUID().toString()}")
        // example of local state update:
        delay(1000)
        reducer.updateState { oldState ->
            oldState.copy(counter = oldState.counter + 1)
        }
    }

    data class State(
        val title: String,
        val actionInProgress: Boolean = false,
        val counter: Int = 0,
    )

}
