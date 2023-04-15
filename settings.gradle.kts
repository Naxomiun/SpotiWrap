enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "SpotiWrap"

include(
    ":app",
)

//Core
include(
    "core:network",
    "core:persistence",
    "core:auth",
    "core:navigation",
)

//Features
include(
    ":features:login",
    ":features:menu"
)
