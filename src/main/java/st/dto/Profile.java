package st.dto;

import st.entity.BookEntity;
import st.entity.BorrowEntity;

import java.util.Set;

public class Profile {
    private String email;
    private String name;
    private Role role;
    private Set<BookEntity> subscriptions;
    private Set<BorrowEntity> borrows;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
