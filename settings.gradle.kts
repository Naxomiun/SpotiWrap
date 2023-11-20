rootProject.name = "SpotiWrap"

include(
    ":app",
)

//Core
include(
    ":core:common",
    ":core:network",
    ":core:persistence",
    ":core:auth",
    ":core:navigation",
    ":core:design",
    ":core:data",
    ":core:database"
)

//Features
include(
    ":features:login",
    ":features:home",
    ":features:splash",
    ":features:tracks",
    ":features:artists",
    ":features:recently",
    ":features:profile",
    ":features:recommender",
    ":features:collage",
)

//Tests
include(
    ":test:shared-test"
)