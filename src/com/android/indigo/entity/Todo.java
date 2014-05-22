package com.android.indigo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
	private String date;
	private String title;
	private String content;

	public Todo() {}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
    
	protected Todo(Parcel in) {
		this.date = in.readString();
		this.title = in.readString();
		this.content = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(date);
		dest.writeString(title);
		dest.writeString(content);
	}
}
