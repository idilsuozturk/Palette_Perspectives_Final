package Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String firstName, lastName;
    private String  id;
    private int age;

    // Constructor
    public User(String name, String id, String password, int age) {
        this.firstName = name;
        this.id = id;
        this.age = age;
    }

    protected User(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
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
    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        dest.writeString(id);
        dest.writeString(firstName);
        // Write other fields
    }
}
