package %PACKAGE%.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.elveum.container.Container
import com.ch000se.messenger.core.navigation.dsl.ScreenScope
import com.ch000se.messenger.core.navigation.dsl.toolbar.toolbar
import com.ch000se.messenger.core.navigation.dsl.viewModel
import com.ch000se.messenger.core.theme.components.ContainerView
import com.ch000se.messenger.core.theme.previews.PreviewScreenContent
import com.ch000se.messenger.core.theme.previews.ScreenPreview
import %PACKAGE%.presentation.%MODULE_NAME%ViewModel.State
import com.ch000se.messenger.core.theme.components.ProgressButton

fun ScreenScope.%MODULE_NAME_DECAPITALIZED%Screen() {
    val viewModel: %MODULE_NAME%ViewModel = viewModel()
    toolbar {
        titleRes = R.string.%MODULE_NAME_SNAKE%_title
    }
    content {
        val container: Container<State> by viewModel.stateFlow.collectAsState()
        ContainerView(
            container = container,
            modifier = Modifier.fillMaxSize(),
        ) { state ->
            %MODULE_NAME%Content(
                state = state,
                onIncrement = viewModel::increment,
            )
        }
    }
}

@Composable
fun BoxScope.%MODULE_NAME%Content(
    state: State,
    onIncrement: () -> Unit = {},
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${state.title}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "Counter: ${state.counter}",
            style = MaterialTheme.typography.headlineLarge,
        )
        ProgressButton(
            isInProgress = state.actionInProgress,
            text = "Increment",
            onClick = onIncrement,
        )
    }

}

@ScreenPreview
@Composable
private fun %MODULE_NAME%ContentPreview() = PreviewScreenContent {
    Box(Modifier.fillMaxSize()) {
        %MODULE_NAME%Content(State("%MODULE_NAME% Screen Preview"))
    }
}
