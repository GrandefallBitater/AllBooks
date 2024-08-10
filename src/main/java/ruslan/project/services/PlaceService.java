package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.project.models.PlaceModel;
import ruslan.project.models.UserModel;
import ruslan.project.repositories.PlaceRepository;
import ruslan.project.repositories.UserRepository;

import java.util.List;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    private UserRepository userRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    public String deletePlace(String id, String username) {
        UserModel user = userRepository.findByUsername(username);
        int count = 0;
        for(PlaceModel place: user.getPlaces()) {
            if(place.getId().equals(Long.parseLong(id))) {
                placeRepository.delete(place);
                count++;
                break;
            }
        }
        if (count == 0) {
            return "false";
        }
        return "true";
    }

    public String changePlace(String username, String id, String newName) {
        UserModel user = userRepository.findByUsername(username);
        PlaceModel currentPlace = null;
        for(PlaceModel place: user.getPlaces()) {
            if(place.getId().equals(Long.parseLong(id))) {
                currentPlace = place;
                break;
            }
        }
        if(currentPlace == null) {
            return "false";
        }
        int count = 0;
        for(PlaceModel place: user.getPlaces()) {
            if(place.getName().equals(newName)) {
                count++;
                break;
            }
        }
        if(count > 0) {
            return "false";
        }
        currentPlace.setName(newName);
        placeRepository.save(currentPlace);
        return "true";
    }

    public String createPlace(String placeName, String UserName) {
        UserModel user = userRepository.findByUsername(UserName);
        int count = 0;
        for(PlaceModel place: user.getPlaces()) {
            if(place.getName().equals(placeName)) {
                count++;
                break;
            }
        }
        if(count > 0) {
            return "false";
        }else{
            placeRepository.save(new PlaceModel(placeName, user));
            return "true";
        }
    }

    public List<PlaceModel> getPlaces(String userName) {
        UserModel user = userRepository.findByUsername(userName);
        return user.getPlaces();
    }
}
