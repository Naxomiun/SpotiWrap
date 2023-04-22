enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "SpotiWrap"

include(
    ":app",
)

//Core
include(
    "core:common",
    "core:network",
    "core:persistence",
    "core:auth",
    "core:navigation",
    "core:design",
)

//Features
include(
    ":features:login",
    ":features:menu",
    ":features:splash"
)
