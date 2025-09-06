package BookMyTrain;

public class User {
    private String userName;
    private String fullName;
    private String contact;
    private String password;

    public User(String userName, String fullName, String contact, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.contact = contact;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return fullName+" ("+userName+") ";
    }
}
