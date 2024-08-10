package ruslan.project.models;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@TypeDef(name = "array-string", typeClass = StringArrayType.class)
@Setter
@AllArgsConstructor
@Table(name = "additional_Information")
public class AdditionalInformationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "array-string")
    @Column(name = "notes", columnDefinition = "text[]")
    private String[] notes;

    @Column(name = "place_Info")
    private String placeInfo;

    @Column(name = "last_Page")
    private Integer lastPage;

    @OneToMany(mappedBy = "additionalInformationId")
    private List<LibraryModel> library;

    public AdditionalInformationModel() {}
}
