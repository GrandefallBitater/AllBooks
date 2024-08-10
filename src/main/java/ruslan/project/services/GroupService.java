package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.project.models.ClassModel;
import ruslan.project.models.UserModel;
import ruslan.project.repositories.ClassRepository;
import ruslan.project.repositories.UserRepository;

import java.util.List;

@Service
public class GroupService {
    private ClassRepository classRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupService(ClassRepository classRepository, UserRepository userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    public String createGroup(String groupName, String UserName) {
        UserModel user = userRepository.findByUsername(UserName);
        int count = 0;
        for(ClassModel group: user.getClasses()) {
            if(group.getName().equals(groupName)) {
                count++;
            }
        }
        if(count > 0) {
            return "false";
        }else{
            classRepository.save(new ClassModel(groupName, user));
            return "true";
        }
    }

    public List<ClassModel> getGroups(String userName) {
        UserModel user = userRepository.findByUsername(userName);
        return user.getClasses();
    }

    public String changeGroup(String username, String id, String newName) {
        UserModel user = userRepository.findByUsername(username);
        ClassModel currentGroup = null;
        for(ClassModel group: user.getClasses()) {
            if(group.getId().equals(Long.parseLong(id))) {
                currentGroup = group;
                break;
            }
        }
        if(currentGroup == null) {
            return "false";
        }
        int count = 0;
        for(ClassModel group: user.getClasses()) {
            if(group.getName().equals(newName)) {
                count++;
                break;
            }
        }
        if(count > 0) {
            return "false";
        }
        currentGroup.setName(newName);
        classRepository.save(currentGroup);
        return "true";
    }

    public String deleteGroup(String id, String username) {
        UserModel user = userRepository.findByUsername(username);
        int count = 0;
        for(ClassModel group: user.getClasses()) {
            if(group.getId().equals(Long.parseLong(id))) {
                System.out.println(group.getLibrares());
                classRepository.delete(group);
                count++;
                break;
            }
        }
        if (count == 0) {
            return "false";
        }
        return "true";
    }
}
