package com.wachon.spotiwrap.features.profile.presentation.profilebar

import com.wachon.spotiwrap.core.common.model.UserProfileModel

sealed interface ProfileTopBarState {
    data class Success(val userProfileModel: UserProfileModel) : ProfileTopBarState
    object Loading : ProfileTopBarState
    object Error : ProfileTopBarState
}