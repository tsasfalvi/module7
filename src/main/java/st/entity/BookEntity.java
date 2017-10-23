package st.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String author;
    @OneToMany(fetch = LAZY, mappedBy = "pk.book", cascade = ALL)
    private Set<BorrowEntity> borrows;

    public BookEntity() {
    }

    public BookEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @OneToMany(fetch = LAZY, mappedBy = "pk")
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
        BookEntity bookEntity = (BookEntity) o;
        return Objects.equals(id, bookEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}