package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.project.customClasses.UserForAdminCustom;
import ruslan.project.models.AuthorityModel;
import ruslan.project.models.UserModel;
import ruslan.project.repositories.AuthoritiesRepository;
import ruslan.project.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    public UserModel getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public ArrayList<UserForAdminCustom> findAllUsers() {
        ArrayList<UserForAdminCustom> userList = new ArrayList<>();
        ArrayList<UserModel> userModels = userRepository.findAllByUsernameNotNull();
        for(UserModel user: userModels) {
            user.setPassword(user.getPassword().split("}")[1]);
        }
        ArrayList<AuthorityModel> authorityModels = authoritiesRepository.findAllByUsernameNotNull();
        for(UserModel user: userModels) {
            for(AuthorityModel authority: authorityModels) {
                if(user.getUsername().equals(authority.getUsername())) {
                    userList.add(new UserForAdminCustom(user.getUsername(), user.getPassword(), user.getEnabled(), authority.getAuthority()));
                    break;
                }
            }
        }
        return userList;
    }

    public ArrayList<UserForAdminCustom> findUsers() {
        ArrayList<UserForAdminCustom> userList = new ArrayList<>();
        ArrayList<UserModel> userModels = userRepository.findAllByUsernameNotNull();
        for(UserModel user: userModels) {
            user.setPassword(user.getPassword().split("}")[1]);
        }
        ArrayList<AuthorityModel> authorityModels = authoritiesRepository.findByAuthority("ROLE_USER");
        for(UserModel user: userModels) {
            for(AuthorityModel authority: authorityModels) {
                if(user.getUsername().equals(authority.getUsername())) {
                    userList.add(new UserForAdminCustom(user.getUsername(), user.getPassword(), user.getEnabled(), authority.getAuthority()));
                    break;
                }
            }
        }
        return userList;
    }

    public ArrayList<UserForAdminCustom> findAdmins() {
        ArrayList<UserForAdminCustom> userList = new ArrayList<>();
        ArrayList<UserModel> userModels = userRepository.findAllByUsernameNotNull();
        for(UserModel user: userModels) {
            user.setPassword(user.getPassword().split("}")[1]);
        }
        ArrayList<AuthorityModel> authorityModels = authoritiesRepository.findByAuthority("ROLE_ADMIN");
        for(UserModel user: userModels) {
            for(AuthorityModel authority: authorityModels) {
                if(user.getUsername().equals(authority.getUsername())) {
                    userList.add(new UserForAdminCustom(user.getUsername(), user.getPassword(), user.getEnabled(), authority.getAuthority()));
                    break;
                }
            }
        }
        return userList;
    }

    public ArrayList<UserForAdminCustom> findManagers() {
        ArrayList<UserForAdminCustom> userList = new ArrayList<>();
        ArrayList<UserModel> userModels = userRepository.findAllByUsernameNotNull();
        for(UserModel user: userModels) {
            user.setPassword(user.getPassword().split("}")[1]);
        }
        ArrayList<AuthorityModel> authorityModels = authoritiesRepository.findByAuthority("ROLE_MANAGER");
        for(UserModel user: userModels) {
            for(AuthorityModel authority: authorityModels) {
                if(user.getUsername().equals(authority.getUsername())) {
                    userList.add(new UserForAdminCustom(user.getUsername(), user.getPassword(), user.getEnabled(), authority.getAuthority()));
                    break;
                }
            }
        }
        return userList;
    }

    public String deleteUser(String username) {
        authoritiesRepository.deleteByUsername(username);
        userRepository.deleteByUsername(username);
        return "{\"Status\": \"succes\"}";
    }

    public String createUser(UserForAdminCustom user) {
        userRepository.save(new UserModel(user.getUsername(),"{noop}" + user.getPassword(), user.getEnabled()));
        authoritiesRepository.save(new AuthorityModel(user.getUsername(), "ROLE_" + user.getAuthority().toUpperCase()));
        return "{\"Status\": \"succes\"}";
    }

    public String changeUser(UserForAdminCustom user, String oldUsername) {
        UserModel refreshUser = userRepository.findByUsername(oldUsername);
        refreshUser.setUsername(user.getUsername());
        refreshUser.setPassword("{noop}" + user.getPassword());
        refreshUser.setEnabled(user.getEnabled());
        AuthorityModel authorityModel = authoritiesRepository.findByUsername(oldUsername);
        authorityModel.setUsername(user.getUsername());
        authorityModel.setAuthority("ROLE_" + user.getAuthority().toUpperCase());
        userRepository.save(refreshUser);
        authoritiesRepository.save(authorityModel);
        return "{\"Status\": \"succes\"}";
    }
}
