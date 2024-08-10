package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ruslan.project.models.*;
import ruslan.project.repositories.*;

import java.util.*;

@Service
public class libraryService {
    private LibraryRepository libraryRepository;
    private AdditionalInformationRepository additionalInformationRepository;
    private UserRepository userRepository;
    private BookService bookService;
    private PlaceService placeService;
    private GroupService groupService;
    private StatusService statusService;


    @Autowired
    public libraryService(LibraryRepository libraryRepository, AdditionalInformationRepository additionalInformationRepository,
                          UserRepository userRepository, BookService bookService,
                          PlaceService placeService, GroupService groupService,
                          StatusService statusService) {
        this.libraryRepository = libraryRepository;
        this.additionalInformationRepository = additionalInformationRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.placeService = placeService;
        this.groupService = groupService;
        this.statusService = statusService;
    }

    public List<LibraryModel> getLibrares(String username) {
        UserModel user = userRepository.findByUsername(username);
        return libraryRepository.findAllByUserId(user);
    }

    public LibraryModel getLibrary(String id) {
        Optional<LibraryModel> library = libraryRepository.findById(Long.parseLong(id));
        return library.get();
    }

    public String deleteLibrary(Long id) {
        Optional<LibraryModel> libraryModel = libraryRepository.findById(id);
        if(libraryModel.isEmpty()) {
            return "что-то пошло не так";
        }else {
            libraryRepository.delete(libraryModel.get());
            return "успешно";
        }
    }

    public String createLibrary(String username, String placeId, String groupId, String statusName, String bookId) {
        UserModel user = userRepository.findByUsername(username);
        LibraryModel libraryModel = new LibraryModel();
        libraryModel.setUserId(user);
        libraryModel.setHidden("false");
        BookModel book = bookService.getBook(bookId);
        libraryModel.setBookId(book);
        if(!placeId.equals("none")) {
            List<PlaceModel> places = placeService.getPlaces(username);
            for (PlaceModel place:places) {
                if (place.getId().equals(Long.parseLong(placeId))) {
                    libraryModel.setPlaceid(place);
                    break;
                }
            }
        }
        if(!groupId.equals("none")) {
            List<ClassModel> groups = groupService.getGroups(username);
            for (ClassModel group:groups) {
                if (group.getId().equals(Long.parseLong(groupId))) {
                    libraryModel.setClassId(group);
                    break;
                }
            }
        }
        Optional<StatusModel> status = statusService.getStatus(statusName);
        libraryModel.setStatusId(status.get());
        try {
            libraryRepository.save(libraryModel);
        }catch (Exception ex) {
            return "Произошла ошибка";
        }
        return "true";
    }

    public String refreshLibrary(String placeId, String groupId, String status, String id,
                                 String notes, String lastPage, String placeInfo, String Username, String hidden) {
        UserModel user = userRepository.findByUsername(Username);
        LibraryModel lyb = libraryRepository.findById(Long.parseLong(id)).get();
        if(!lyb.getPlaceid().getId().equals(Long.parseLong(placeId))) {
            for(PlaceModel place:user.getPlaces()) {
                if(place.getId().equals(Long.parseLong(placeId))) {
                    lyb.setPlaceid(place);
                    break;
                }
            }
        }
        if(!lyb.getClassId().getId().equals(Long.parseLong(groupId))) {
            for(ClassModel group:user.getClasses()) {
                if(group.getId().equals(Long.parseLong(groupId))) {
                    lyb.setClassId(group);
                    break;
                }
            }
        }
        if(!lyb.getStatusId().getId().equals(Long.parseLong(status))) {
            lyb.setStatusId(statusService.getStatus(status).get());
        }
        if(!lyb.getHidden().equals(hidden)) {
            lyb.setHidden(hidden);
        }
        if (lyb.getAdditionalInformationId() != null) {
            if(!Arrays.equals(lyb.getAdditionalInformationId().getNotes(), new String[]{notes})) {
                lyb.getAdditionalInformationId().setNotes(new String[]{notes});
            }
            if(!lyb.getAdditionalInformationId().getLastPage().equals(Integer.parseInt(lastPage))) {
                lyb.getAdditionalInformationId().setLastPage(Integer.parseInt(lastPage));
            }
            if(!lyb.getAdditionalInformationId().getPlaceInfo().equals(placeId)) {
                lyb.getAdditionalInformationId().setPlaceInfo(placeInfo);
            }
        }else {
            AdditionalInformationModel ad = new AdditionalInformationModel();
            ad.setNotes(new String[]{notes});
            ad.setLastPage(Integer.parseInt(lastPage));
            ad.setPlaceInfo(placeInfo);
            lyb.setAdditionalInformationId(ad);
        }
        try {
            libraryRepository.save(lyb);
        } catch (Exception ex) {
            return "false";
        }
        return "true";
    }
}
