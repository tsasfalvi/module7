package st.domain;

public class User {
    private String email;
    private String name;
    private boolean suspended;
    private boolean librarian;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isLibrarian() {
        return librarian;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public void setLibrarian(boolean librarian) {
        this.librarian = librarian;
    }
}
