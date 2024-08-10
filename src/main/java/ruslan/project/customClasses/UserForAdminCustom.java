package ruslan.project.customClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForAdminCustom {
    private String username;
    private String password;
    private Boolean enabled;
    private String authority;

    @JsonCreator
    public UserForAdminCustom(@JsonProperty("username") String username, @JsonProperty("password") String password,
                              @JsonProperty("enabled") Boolean enabled, @JsonProperty("authority") String authority) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authority = authority;
    }
}
