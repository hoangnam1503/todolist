package com.android.indigo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
	private String date;
	private String title;
	private String content;

	public Note() {}
	
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

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    
	protected Note(Parcel in) {
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
