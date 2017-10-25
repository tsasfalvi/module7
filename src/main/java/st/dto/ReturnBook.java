package st.dto;

import javax.validation.constraints.NotNull;

public class ReturnBook {
    @NotNull
    private Long bookId;
    @NotNull
    private String user;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
