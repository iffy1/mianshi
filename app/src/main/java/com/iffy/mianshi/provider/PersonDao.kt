package com.iffy.mianshi.provider

import androidx.room.*

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)

    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE p_id IN (:id)")
    fun loadAllByIds(id: Int): List<Person>

    @Query("SELECT * FROM person WHERE name LIKE :name")
    fun findByName(name: String): List<Person>

    @Query("DELETE FROM person")
    fun deleteAll()

    @Query("DELETE FROM person where p_id IN(:id)")
    fun deleteAPerson(id:Int)
}