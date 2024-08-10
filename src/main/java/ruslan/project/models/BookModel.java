package ruslan.project.models;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@TypeDef(name = "array-string", typeClass = StringArrayType.class)
@Table(name = "book")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "year_published")
    private String year_published;

    @Type(type = "array-string")
    @Column(name = "genres", columnDefinition = "text[]")
    private String[] genres;

    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    private List<LibraryModel> library;

    @Column(name = "published")
    private String published;

    @Column(name = "ISBN", unique = true)
    private String ISBN;

    @OneToMany(mappedBy = "book_Id", cascade = CascadeType.ALL)
    private List<CommentsModel> comments = new ArrayList<>();

    @Type(type = "array-string")
    @Column(name = "author", columnDefinition = "text[]")
    private String[] author;

    public BookModel() {}

    public BookModel(String name, Integer capacity, String image, String description) {
        this.capacity = capacity;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
