package ruslan.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "authorities")
public class AuthorityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name = "username")
    String username;

    @Column(name = "authority")
    String authority;

    public AuthorityModel() {}

    public AuthorityModel(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
