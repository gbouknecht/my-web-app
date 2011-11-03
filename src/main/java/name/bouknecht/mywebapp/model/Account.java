package name.bouknecht.mywebapp.model;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Account {
    private Integer id;
    private String  userId;
    private String  firstname;
    private String  lastname;

    public Account(Integer id, String userId, String firstname, String lastname) {
        this.id        = id;
        this.userId    = userId;
        this.firstname = firstname;
        this.lastname  = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append(id)
                                                            .append(userId)
                                                            .append(firstname)
                                                            .append(lastname)
                                                            .toString();
    }
}
