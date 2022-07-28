package Tasks
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.google.android.gms.tasks.Task
//
//@Dao
//interface TaskDetailDao{
//    @Query("SELECT * FROM task WHERE 'id'= :id")
//    fun getTask(id: Long): LiveData<List<Tasks.Task>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertTask(task: Tasks.Task): Long
//
//    @Update
//    suspend fun updateTask(task: Tasks.Task)
//
//    @Delete
//    suspend fun deleteTask(task: Tasks.Task)
//}