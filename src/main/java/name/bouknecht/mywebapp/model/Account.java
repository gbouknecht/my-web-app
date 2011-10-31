package name.bouknecht.mywebapp.model;

public class Account {
    private String userId;
    private String firstname;
    private String lastname;

    public Account(String userId, String firstname, String lastname) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
