package com.sun.mvvm.data.source.local.database.dao

import androidx.room.*
import com.sun.mvvm.data.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User) : Single<Long>

    @Query(value = "select * from user where email= :email and password= :password limit 1")
    fun isUser(email:String, password:String): Single<User?>
}
