package st.entity;

import st.dto.Role;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String name;
    private boolean suspended;
    private String password;
    @Enumerated(STRING)
    private Role role;

    @OneToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(name = "subscription", joinColumns = @JoinColumn(name = "user_email"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookEntity> subscriptions;

    @OneToMany(fetch = LAZY, mappedBy = "pk.user", cascade = ALL)
    private Set<BorrowEntity> borrows;

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

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public Set<BookEntity> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<BookEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<BorrowEntity> getBorrows() {
        return borrows;
    }

    public void setBorrows(Set<BorrowEntity> borrows) {
        this.borrows = borrows;
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
