package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Language implements Parcelable {
    Integer id;
    String language;
    String description;
    boolean status;

    public Language(Integer id, String language, String description, boolean status) {
        this.id = id;
        this.language = language;
        this.description = description;
        this.status = status;
    }

    public Language() {
    }

    protected Language(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        language = in.readString();
        description = in.readString();
        status = in.readByte() != 0;
    }

    public static final Creator<Language> CREATOR = new Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(language);
        dest.writeString(description);
        dest.writeByte((byte) (status ? 1 : 0));
    }
}
