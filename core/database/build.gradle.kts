plugins {
    alias(libs.plugins.spotiwrap.android.library)
    alias(libs.plugins.spotiwrap.android.room.library)
}

android {
    namespace = Config.Core.Database
}
