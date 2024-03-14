package com.example.consciente_te.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.example.consciente_te.R
import com.example.consciente_te.state.NavigationUiState
import com.example.consciente_te.state.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NavigationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NavigationUiState(logged = false))
    val uiState: StateFlow<NavigationUiState> = _uiState.asStateFlow()

    fun setIconMenu(icon: ImageVector?) {
        _uiState.update { currentState ->
            currentState.copy(icon = icon,iconDescription= R.string.principal_menu_icon)
        }
    }
    fun logIn(user: User){
        _uiState.update{
            currentState->
            currentState.copy(logged = true,user=user)
        }
    }

    fun inRegistry(user:User){
        _uiState.update{
            currentState->
            currentState.copy(registry = true,user=user)
        }
    }


}