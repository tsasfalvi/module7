package st.entity;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrow")
public class BorrowEntity {
    @EmbeddedId
    private Pk pk = new Pk();

    private LocalDate till;

    public LocalDate getTill() {
        return till;
    }

    public void setTill(LocalDate till) {
        this.till = till;
    }

    @Transient
    public BookEntity getBook() {
        return pk.getBook();
    }

    public void setBook(BookEntity book) {
        pk.setBook(book);
    }

    @Transient
    public UserEntity getUser() {
        return pk.getUser();
    }

    public void setUser(UserEntity user) {
        pk.setUser(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BorrowEntity that = (BorrowEntity) o;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }

    @Embeddable
    public static class Pk implements Serializable {
        @ManyToOne
        private UserEntity user;
        @ManyToOne
        private BookEntity book;

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public BookEntity getBook() {
            return book;
        }

        public void setBook(BookEntity book) {
            this.book = book;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pk pk = (Pk) o;
            return Objects.equals(user, pk.user) && Objects.equals(book, pk.book);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, book);
        }
    }

}
