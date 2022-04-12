package com.arun.tasklogin;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemsModel implements Parcelable {
    private String courseName;
    private String courseimg;
    private String courseMode;
    private String courseTracks;

    protected ItemsModel(Parcel in) {
        courseName = in.readString();
        courseimg = in.readString();
        courseMode = in.readString();
        courseTracks = in.readString();
    }

    public static final Creator<ItemsModel> CREATOR = new Creator<ItemsModel>() {
        @Override
        public ItemsModel createFromParcel(Parcel in) {
            return new ItemsModel(in);
        }

        @Override
        public ItemsModel[] newArray(int size) {
            return new ItemsModel[size];
        }
    };

    public String getCourseName() { return courseName; }
    public void setCourseName(String value) { this.courseName = value; }

    public String getCourseimg() { return courseimg; }
    public void setCourseimg(String value) { this.courseimg = value; }

    public String getCourseMode() { return courseMode; }
    public void setCourseMode(String value) { this.courseMode = value; }

    public String getCourseTracks() { return courseTracks; }
    public void setCourseTracks(String value) { this.courseTracks = value; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseName);
        parcel.writeString(courseimg);
        parcel.writeString(courseMode);
        parcel.writeString(courseTracks);
    }
}
