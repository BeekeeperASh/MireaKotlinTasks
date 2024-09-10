package alvshinev.application.mireakotlintasks.tools

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    object HomeScreen : Destination()

    @Serializable
    object SearchScreen : Destination()

    @Serializable
    object NewsScreen : Destination()

    @Serializable
    object CameraScreen : Destination()

    @Serializable
    object DatesScreen: Destination()
}
