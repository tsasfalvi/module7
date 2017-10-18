package st.entity;

import st.domain.Role;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String name;
    private Boolean suspended;
    private String password;
    @Enumerated(STRING)
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(email, userEntity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
