package ruslan.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
    @OneToMany(mappedBy = "userId")
    private List<LibraryModel> library;
    @OneToMany(mappedBy = "userId")
    private List<ClassModel> classes;
    @OneToMany(mappedBy = "userId")
    private List<RequestsModel> requests;
    @OneToMany(mappedBy = "userId")
    private List<PlaceModel> places;
    @OneToMany(mappedBy = "userId")
    private List<CommentsModel> comments;

    public UserModel() {}

    public UserModel(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
