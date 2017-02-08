package com.sherlock.twoapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sherlock on 16/8/15.
 */
public class Friend implements Parcelable {
	private String id;
	private String name;
	private int age;

	public Friend() {

	}

	protected Friend(Parcel in) {
		id = in.readString();
		name = in.readString();
		age = in.readInt();
	}

	public static final Creator<Friend> CREATOR = new Creator<Friend>() {
		@Override
		public Friend createFromParcel(Parcel in) {
			return new Friend(in);
		}

		@Override
		public Friend[] newArray(int size) {
			return new Friend[size];
		}
	};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeInt(age);
	}
}
