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
    ":features:splash",
    ":features:tracks",
    ":features:artists",
    ":features:profile"
)

//Tests
include(
    ":test:shared-test"
)
