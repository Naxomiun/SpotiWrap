object Config  {
    const val baseApplicationId = "com.wachon.spotiwrap"

    object Feature {
        const val loginNamespace = "$baseApplicationId.features.login"
    }

    object Core {
        const val dataNamespace = "$baseApplicationId.core.data"
        const val navigationNamespace = "$baseApplicationId.core.navigation"
    }

}