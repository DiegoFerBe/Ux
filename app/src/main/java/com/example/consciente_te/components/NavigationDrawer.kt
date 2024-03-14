package com.example.consciente_te.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.consciente_te.R
import com.example.consciente_te.pages.AppPages
import kotlinx.coroutines.launch

data class NavigationItem(@StringRes val label: Int, val iconOptions: ImageVector, val action: () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit
) {

    val scope = rememberCoroutineScope()

    val menus = mutableListOf(
        NavigationItem(R.string.health_tittle,Icons.Filled.PermMedia) { navController.navigate(route = AppPages.MediaPage.route) },
        NavigationItem(R.string.create_task,Icons.Filled.EditCalendar) { navController.navigate(route = AppPages.CreateTask.route) },
        NavigationItem(R.string.create_alarm,Icons.Filled.AccessAlarm) { navController.navigate(route = AppPages.CreateAlarm.route) },
        NavigationItem(R.string.list_alarms,Icons.Filled.FormatListNumbered) { navController.navigate(route = AppPages.ListAlarm.route) },
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = navController.currentBackStackEntry?.destination?.route != AppPages.HomePage.route,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.Center){
                        NavigationDrawerItem(
                            label = { Text(text = stringResource(id = R.string.select_action),fontWeight = FontWeight.Bold) },
                            selected = false,
                            onClick={},

                            )
                    }

                    menus.forEach {
                        NavigationDrawerItem(
                            label = { Text(text = stringResource(id = it.label)) },
                            selected = false,
                            onClick = {
                                it.action()
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon={Icon(it.iconOptions,contentDescription = "")}
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 24.dp)
                ) {
                    VinylsButton(
                        onClick = {
                            navController.navigate(route = AppPages.HomePage.route)
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        type = ButtonType.TERTIARY,
                        label = "Sign out",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 64.dp, vertical = 0.dp)
                    )
                }
            }
        }
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NavigationDrawerPreview() {
    NavigationDrawer(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
        rememberNavController()
    ) {}
}