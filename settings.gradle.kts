enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "SpotiWrap"

include(
    ":app",
)

//Core
include(
    "core:data",
    "core:navigation",
)

//Features
include(
    ":features:login",
)
