package uz.mobiler.therickmorty.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.mobiler.therickmorty.models.Result

@Dao
interface ResultDao {
    @Insert
    fun insertResult(result: Result)

    @Query("select * from result")
    fun getAllResult(): List<Result>

    @Query("select * from result where id=:id")
    fun getResultById(id:Int): Result
}