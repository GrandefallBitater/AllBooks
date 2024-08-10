package ruslan.project.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StringJson {
    private final String message;

    @JsonCreator
    public StringJson(@JsonProperty("message") String message) {
        this.message = message;
    }
}
