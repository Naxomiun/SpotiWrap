object Config  {
    const val baseApplicationId = "com.wachon.spotiwrap"

    object Feature {
        const val Login = "$baseApplicationId.features.login"
        const val Menu = "$baseApplicationId.features.menu"
        const val Splash = "$baseApplicationId.features.splash"
        const val Tracks = "$baseApplicationId.features.tracks"
        const val Artists = "$baseApplicationId.features.artists"
        const val Profile = "$baseApplicationId.features.profile"
    }

    object Core {
        const val Network = "$baseApplicationId.core.network"
        const val Persistence = "$baseApplicationId.core.persistence"
        const val Auth = "$baseApplicationId.core.auth"
        const val Navigation = "$baseApplicationId.core.navigation"
        const val Common = "$baseApplicationId.core.common"
        const val Design = "$baseApplicationId.core.design"
    }

    object Tests {
        const val SharedTests = "$baseApplicationId.test.sharedTests"
    }

}