package com.example.consciente_te.state

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationUiState(
    val logged: Boolean = false,
    val icon: ImageVector? = null,
    val registry:Boolean=false,
    @StringRes val iconDescription: Int? = null,
    val user: User? = null,
)