package ruslan.project.models;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@TypeDef(name = "array-string", typeClass = StringArrayType.class)
@Table(name = "requests")
public class RequestsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "image")
    private String image;

    @Column(name = "ISBN", unique = true)
    private String ISBN;

    @Column(name = "published")
    private String published;

    @Column(name = "description")
    private String description;

    @Column(name = "year_published")
    private String year_published;

    @Column(name = "date")
    private Date date;

    @Type(type = "array-string")
    @Column(name = "genres", columnDefinition = "text[]")
    private String[] genres;

    @Type(type = "array-string")
    @Column(name = "author", columnDefinition = "text[]")
    private String[] author;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userId;


    public RequestsModel() {}
}
