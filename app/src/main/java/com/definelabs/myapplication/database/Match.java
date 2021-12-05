package com.definelabs.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "match_table", indices ={ @Index(value = {"match_id","match_name"},unique = true)})
public class Match{
    // room database entity primary key
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "match_id")
    private String match_id;
    @ColumnInfo(name = "match_name")
    private String match_name;
    @ColumnInfo(name = "match_distance")
    private int match_distance;
    @ColumnInfo(name = "match_address")
    private String match_address;
    @ColumnInfo(name = "match_phone")
    private String match_phone;
    private String match_status;

    public String getMatch_status() {
        return match_status;
    }

    public void setMatch_status(String match_status) {
        this.match_status = match_status;
    }
    //
//    public Match(int id, String match_id, String match_name, int match_distance, String match_address, String match_phone) {
//        this.id = id;
//        this.match_id = match_id;
//        this.match_name = match_name;
//        this.match_distance = match_distance;
//        this.match_address = match_address;
//        this.match_phone = match_phone;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getMatch_name() {
        return match_name;
    }

    public void setMatch_name(String match_name) {
        this.match_name = match_name;
    }

    public int getMatch_distance() {
        return match_distance;
    }

    public void setMatch_distance(int match_distance) {
        this.match_distance = match_distance;
    }

    public String getMatch_address() {
        return match_address;
    }

    public void setMatch_address(String match_address) {
        this.match_address = match_address;
    }

    public String getMatch_phone() {
        return match_phone;
    }

    public void setMatch_phone(String match_phone) {
        this.match_phone = match_phone;
    }
}

