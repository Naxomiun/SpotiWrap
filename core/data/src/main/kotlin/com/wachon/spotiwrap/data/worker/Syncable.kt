package com.wachon.spotiwrap.data.worker

interface Syncable {
    suspend fun sync(): Result<Boolean>
}