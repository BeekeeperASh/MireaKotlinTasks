package alvshinev.application.mireakotlintasks.presentation.ui

import alvshinev.application.mireakotlintasks.FirstScreen
import alvshinev.application.mireakotlintasks.SecondScreen
import alvshinev.application.mireakotlintasks.presentation.ui_logic.MainViewModel
import alvshinev.application.mireakotlintasks.tools.Destination
import alvshinev.application.mireakotlintasks.tools.SupportElements.itemsBottomBar
import alvshinev.application.mireakotlintasks.tools.SupportElements.itemsDrawer
import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    context: Context
) {
    val navController = rememberNavController()

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val coroutineScope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    itemsDrawer.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            label = { Text(text = item.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                navController.navigate(item.destination)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        )
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "News App")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        itemsBottomBar.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate(item.destination)
                                },
                                label = {
                                    Text(text = item.title)
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) item.selectedIcon
                                        else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            )
                        }
                    }
                }
            ) { paddingValues ->
                NavHost(
                    modifier = Modifier.padding(paddingValues),
                    navController = navController,
                    startDestination = Destination.HomeScreen
                ) {
                    composable<Destination.HomeScreen> {
                        HomeScreen()
                    }
                    composable<Destination.SearchScreen> {
                        SearchScreen(viewModel)
                    }
                    composable<Destination.NewsScreen> {
                        NewsScreen(viewModel)
                    }
                    composable<Destination.CameraScreen> {
                        CameraScreen(context = context)
                    }
                    composable<Destination.DatesScreen> {
                        DatesScreen(context = context)
                    }
                    composable<Destination.LaunchImageScreen> {
                        LaunchingImage()
                    }
                    composable<Destination.ComposeSample> {
                        ComposeSample()
                    }
                    composable<FirstScreen> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                navController.navigate(
                                    SecondScreen(
                                        arg1 = "Mr. Three",
                                        arg2 = 3
                                    )
                                )
                            }) {
                                Text(text = "Button")
                            }
                        }
                    }
                    composable<SecondScreen> {
                        val args = it.toRoute<SecondScreen>()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "${args.arg1}", fontSize = 40.sp)
                            Text(text = "${args.arg2}", fontSize = 40.sp)
                        }
                    }
                }
            }
        }
    }

}

@Stable
data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Destination
)
