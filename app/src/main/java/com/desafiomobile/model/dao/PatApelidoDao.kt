package com.desafiomobile.model.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.desafiomobile.model.entity.PatApelido

@Dao
interface PatApelidoDao {
    @Insert
    suspend fun inserir(patApelido: PatApelido): Long


    @Query("SELECT * FROM PatApelido WHERE apelido = :apelido")
    suspend fun encontrarPorApelido(apelido: String): PatApelido?
    @Query("SELECT * FROM PatApelido")
    suspend fun obterTodos(): List<PatApelido>
}
