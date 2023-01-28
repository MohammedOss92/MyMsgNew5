package com.sarrawi.msgs.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "msg_types_table")
data class MsgsTypesModel(
    // // sweilem edit

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id:Int ,

    @ColumnInfo(name = "MsgTypes")
    @SerializedName("MsgTypes")
    val MsgTypes:String ,

    @ColumnInfo(name = "new_msg")
    @SerializedName("new_msg")
    val new_msg:Int

                          )
