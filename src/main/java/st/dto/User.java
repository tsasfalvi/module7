package st.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import st.entity.BookEntity;
import st.entity.BorrowEntity;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class User {
    private String email;
    private String name;
    private boolean suspended;
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

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
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
