package ruslan.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "place")
public class PlaceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "placeid", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    private List<LibraryModel> library = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userId;

    public PlaceModel() {}

    public PlaceModel(String name, UserModel userId) {
        this.name = name;
        this.userId = userId;
    }
}
