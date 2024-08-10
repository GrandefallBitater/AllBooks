package ruslan.project.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserJson {
    private final String username;
    private final String password;
    private final Boolean enabled;

    @JsonCreator
    public UserJson(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.enabled = true;
        this.username = username;
        this.password = password;

    }
}
