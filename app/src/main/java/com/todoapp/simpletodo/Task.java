package com.todoapp.simpletodo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by SagarMutha on 8/13/17.
 */

public class Task implements Parcelable {

    int id;
    String name;
    Date dueDate;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //due date for future versions
    public Task(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.dueDate = date;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeSerializable(dueDate);
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    private Task(Parcel in) {
        id = in.readInt();
        name = in.readString();
        dueDate = (Date) in.readSerializable();
    }
}
