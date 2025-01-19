package id.usereal.suitproject.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.usereal.suitproject.data.local.remote_mediator.RemoteKeys
import id.usereal.suitproject.data.local.remote_mediator.RemoteKeysDao
import id.usereal.suitproject.data.model.DataItem

@Database(entities = [DataItem::class, RemoteKeys::class], version = 2, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}