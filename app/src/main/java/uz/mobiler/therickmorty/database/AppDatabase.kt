package uz.mobiler.therickmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.mobiler.therickmorty.models.Result

@Database(entities = [Result::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        private var inctance: AppDatabase? = null

        @Synchronized
        fun getAppDatabase(context: Context): AppDatabase {
            if (inctance == null) {
                inctance = Room.databaseBuilder(context, AppDatabase::class.java, "result.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return inctance!!
        }
    }

}