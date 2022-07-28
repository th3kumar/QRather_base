package Tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.google.android.gms.tasks.Task

@Dao
interface TaskListDao{
    @Query("SELECT * FROM task WHERE status = :status ORDER BY priority DESC")
    fun getTasksByPriority(status : Int): LiveData<List<Tasks.Task>>

    @Query("SELECT * FROM task WHERE status = :status ORDER BY title")
    fun getTasksByTitle(status : Int): LiveData<List<Tasks.Task>>



}