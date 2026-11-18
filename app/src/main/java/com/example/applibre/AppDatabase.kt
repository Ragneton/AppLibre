package com.example.applibre

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokeDAO

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        fun obtener(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                val nueva = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pokedex_db"
                ).fallbackToDestructiveMigration(dropAllTables = true).build()

                instancia = nueva
                nueva
            }
        }
    }
}