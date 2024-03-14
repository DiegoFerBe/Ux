package com.example.consciente_te.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.consciente_te.components.NavigationDrawer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.example.consciente_te.R
import com.example.consciente_te.pages.AppPages
import com.example.consciente_te.pages.CreateAlarmPage
import com.example.consciente_te.pages.CreateTaskPage
import com.example.consciente_te.pages.HomePage
import com.example.consciente_te.pages.ListAlarmPage
import com.example.consciente_te.pages.MediaPage
import com.example.consciente_te.pages.RegistryPage
import com.example.consciente_te.state.User
import com.example.consciente_te.ui.theme.ConscienteteTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: NavigationViewModel = viewModel(),
){
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val route: String = backStackEntry?.destination?.route ?: AppPages.HomePage.route
    val context= LocalContext.current
    NavigationDrawer(
        navController = navController,
        drawerState = drawerState,
    ){
        Scaffold(
            topBar={
                ConcienteteBar(
                    icon = uiState.icon,
                    iconDescription = uiState.iconDescription,
                    route = route,
                    navController = navController,
                    drawerState = drawerState
                )
            }
        ){innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppPages.HomePage.route,
                modifier = modifier.padding(innerPadding)
            ){
                composable(route = AppPages.HomePage.route) {
                    viewModel.setIconMenu(null)
                    HomePage(
                        onClickIngressButton = {
                            navController.navigate(route = AppPages.MediaPage.route)
                            viewModel.logIn(User.Collector)
                        },
                        onClickforgetButton = {
                            navController.navigate(route = AppPages.Registry.route)
                            viewModel.inRegistry(User.Guest)
                        }
                    )
                }
                composable(route = AppPages.Registry.route) {
                    viewModel.setIconMenu(null)
                    RegistryPage(
                        onClickCancelButton={
                            navController.navigate(route = AppPages.HomePage.route)
                            viewModel.logIn(User.Collector)
                        },
                        onSignUpButton={
                            navController.navigate(route = AppPages.MediaPage.route)
                            viewModel.logIn(User.Collector)
                        }
                    )
                }
                composable(route = AppPages.MediaPage.route) {
                    viewModel.setIconMenu(Icons.Filled.Menu)
                    MediaPage(
                    )
                }
                composable(route = AppPages.CreateTask.route) {
                    viewModel.setIconMenu(Icons.Filled.Menu)
                    CreateTaskPage(
                        onClickCancelButton={
                            navController.navigate(route = AppPages.MediaPage.route)
                            viewModel.logIn(User.Collector)
                        },
                        onSaveButton={
                            navController.navigate(route = AppPages.MediaPage.route)
                            viewModel.logIn(User.Collector)
                            Toast.makeText(context,"Task created successful",Toast.LENGTH_LONG).show()
                        }
                    )
                }
                composable(route = AppPages.CreateAlarm.route) {
                    viewModel.setIconMenu(Icons.Filled.Menu)
                    CreateAlarmPage(
                        onClickCancelButton={
                            navController.navigate(route = AppPages.MediaPage.route)
                            viewModel.logIn(User.Collector)
                        },
                        onSaveButton={
                            navController.navigate(route = AppPages.ListAlarm.route)
                            viewModel.logIn(User.Collector)
                            Toast.makeText(context,"Alarm created successful",Toast.LENGTH_LONG).show()

                        }
                    )
                }
                composable(route = AppPages.ListAlarm.route) {
                    viewModel.setIconMenu(Icons.Filled.Menu)
                    ListAlarmPage(
                        onCreateAlarm={
                            navController.navigate(route = AppPages.CreateAlarm.route)
                        }
                    )
                }



            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    ConscienteteTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcienteteBar(
    icon: ImageVector?,
    iconDescription: Int?,
    route: String,
    navController: NavController,
    drawerState: DrawerState,
    modifier: Modifier = Modifier
){
    val currentScreen = with(route) {
        when {
            equals(AppPages.HomePage.route) -> stringResource(id = R.string.home_title)
            equals(AppPages.Registry.route) -> stringResource(id = R.string.create_account)
            equals(AppPages.MediaPage.route) -> stringResource(id = R.string.health_tittle)
            equals(AppPages.CreateAlarm.route) -> stringResource(id = R.string.create_alarm)
            equals(AppPages.CreateTask.route) -> stringResource(id = R.string.create_task)
            equals(AppPages.ListAlarm.route) -> stringResource(id = R.string.list_alarms)
            else -> stringResource(id = R.string.home_title)
        }
    }
    if (icon == Icons.Filled.ArrowBack) {
        TopAppBar(
            title = {
                Text(text = currentScreen, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = iconDescription?.let { stringResource(id = it) }
                    )
                }
            },
            modifier = modifier,
        )
        return
    }

    if (icon == null) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = currentScreen, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold )
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = Color.Black,
            ),
            modifier = modifier,
        )
        return
    }
    CenterAlignedTopAppBar(
        title = {
            Text(text = currentScreen, color = MaterialTheme.colorScheme.onPrimary, fontWeight =FontWeight.Bold )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = Color.Black,
        ),
        navigationIcon = {

            NavigationIcon(
                icon = icon,
                route = route,
                drawerState = drawerState,
                iconDescription = iconDescription
            )
        },
        modifier = modifier,
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationIcon(
    icon: ImageVector,
    iconDescription: Int?,
    route: String,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    IconButton(onClick = {
        scope.launch {
            if (route == AppPages.HomePage.route) return@launch
            drawerState.open()
        }
    }) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription?.let { stringResource(id = it) }
        )
    }
}