package Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String FirstName, LastName, ID, age, numberOfWorks, profilePictureUrl;

    // Constructor

    public User() {
        // Default constructor is required for Firestore deserialization
    }

    protected User(Parcel in) {
        ID = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        profilePictureUrl = in.readString();
        // Read other fields
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    // Getters and setters for attributes
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String firstName) {
        this.LastName = firstName;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void deleteAccount() {
        //TO-DO delete account from database
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(profilePictureUrl);
        // Write other fields
    }
}
