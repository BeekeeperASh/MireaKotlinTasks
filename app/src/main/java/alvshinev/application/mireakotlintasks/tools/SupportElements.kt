package alvshinev.application.mireakotlintasks.tools

import alvshinev.application.mireakotlintasks.presentation.ui.NavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search

object SupportElements {

    val itemsBottomBar = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            destination = Destination.HomeScreen
        ),
        NavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            destination = Destination.SearchScreen
        ),
        NavigationItem(
            title = "News",
            selectedIcon = Icons.Filled.MoreVert,
            unselectedIcon = Icons.Outlined.MoreVert,
            destination = Destination.NewsScreen
        ),
    )

    val itemsDrawer = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            destination = Destination.HomeScreen
        ),
        NavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            destination = Destination.SearchScreen
        ),
        NavigationItem(
            title = "News",
            selectedIcon = Icons.Filled.MoreVert,
            unselectedIcon = Icons.Outlined.MoreVert,
            destination = Destination.NewsScreen
        ),
        NavigationItem(
            title = "Photo",
            selectedIcon = Icons.Filled.Camera,
            unselectedIcon = Icons.Outlined.Camera,
            destination = Destination.CameraScreen
        ),
        NavigationItem(
            title = "Dates",
            selectedIcon = Icons.Filled.CalendarMonth,
            unselectedIcon = Icons.Outlined.CalendarMonth,
            destination = Destination.DatesScreen
        ),
    )

}
