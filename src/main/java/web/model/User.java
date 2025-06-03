package web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    public User() {}

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getId() {        return id;    }
    public void setId(long id) {        this.id = id;    }
    public String getFirstname() {        return firstname;    }
    public void setFirstname(String firstname) {        this.firstname = firstname;    }
    public String getLastname() {        return lastname;    }
    public void setLastname(String lastname) {        this.lastname = lastname;    }


    @Override
    public String toString() {
        return "Пользователь [" +
                "id=" + id +
                ", Фамилия = " + firstname + '\'' +
                ", Год = " + lastname + "]";
    }
}
