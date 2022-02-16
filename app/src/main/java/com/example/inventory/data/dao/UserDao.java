package com.example.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory.data.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert()
    long insert(User user);

    @Update()
    void update(User user);

    @Delete()
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user ORDER BY email ASC")
    List<User> selectAll();

    @Query("SELECT * FROM user WHERE email=:email AND password=:password")
    User login(String email, String password);
}
