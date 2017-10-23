package st.dto;

import st.validator.BorrowLengthValid;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Borrow {
    @NotNull
    private Long bookId;
    @NotNull
    @BorrowLengthValid
    private LocalDate till;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getTill() {
        return till;
    }

    public void setTill(LocalDate till) {
        this.till = till;
    }
}
