package name.bouknecht.mywebapp.model;

import static javax.persistence.GenerationType.SEQUENCE;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "userId" }))
public class Account {
    private static final int USER_ID_MAX_LENGTH   =  50;
    private static final int FIRSTNAME_MAX_LENGTH = 150;
    private static final int LASTNAME_MAX_LENGTH  = 150;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "account_id_seq")
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq")
    private Integer id;

    @Column(length = USER_ID_MAX_LENGTH, nullable = false)
    @NotNull @Size(max = USER_ID_MAX_LENGTH)
    private String userId;

    @Column(length = FIRSTNAME_MAX_LENGTH, nullable = false)
    @NotNull @Size(max = FIRSTNAME_MAX_LENGTH)
    private String firstname;

    @Column(length = LASTNAME_MAX_LENGTH, nullable = false)
    @NotNull @Size(max = LASTNAME_MAX_LENGTH)
    private String lastname;

    public Account() {
    }

    public Account(String userId, String firstname, String lastname) {
        this.userId    = userId;
        this.firstname = firstname;
        this.lastname  = lastname;
    }

    public Integer getId() {
        return id;
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
