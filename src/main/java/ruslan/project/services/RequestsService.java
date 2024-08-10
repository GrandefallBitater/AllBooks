package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ruslan.project.models.RequestsModel;
import ruslan.project.models.UserModel;
import ruslan.project.repositories.RequestRepository;
import ruslan.project.repositories.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequestsService {
    private RequestRepository requestRepository;
    private UserRepository userRepository;
    private BookService bookService;

    @Autowired
    public RequestsService(RequestRepository requestRepository, UserRepository userRepository, BookService bookService) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }
    public String createRequest(String isbn, String publisned, String authors, String capacity,
                                String year, String genres, String descr, String name,
                                MultipartFile image, String username) {
        RequestsModel request = new RequestsModel();
        UserModel user = userRepository.findByUsername(username);
        request.setUserId(user);
        request.setStatus("В обработке");
        request.setAuthor(new String[]{authors});
        request.setGenres(new String[]{genres});
        request.setName(name);
        request.setDescription(descr);
        request.setPublished(publisned);
        request.setYear_published(year);
        request.setCapacity(Integer.parseInt(capacity));
        request.setISBN(isbn);
        request.setDate(new Date());

        String fileName = image.getOriginalFilename();
        String filePath = "D:\\kai\\диплом\\project\\project\\src\\main\\resources\\static\\images\\users\\" + fileName;
        File dest = new File(filePath);
        try {
            image.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return "File uploaded failed:" + fileName;
        }
        request.setImage(fileName);
        try {
            requestRepository.save(request);
        } catch (Exception ex) {
            return "false";
        }
        return "true";
    }

    public String refreshRequest(String id) {
        RequestsModel request = requestRepository.findById(Long.parseLong(id)).get();
        request.setStatus("Одобрено");
        requestRepository.save(request);
        return "true";
    }

    public String deleteRequest(String id) {
        RequestsModel request = requestRepository.findById(Long.parseLong(id)).get();
        request.setStatus("Отклонено");
        requestRepository.save(request);
        return "true";
    }

    public String createBookFromRequest(String isbn, String publisned, String authors,
                                        String capacity, String year, String genres, String descr,
                                        String name, String id) {
        RequestsModel request = requestRepository.findById(Long.parseLong(id)).get();
        if(bookService.addBook(isbn, publisned, authors, capacity, year, genres, descr, name, request.getImage()).equals("true")) {
            File file = new File("D:\\kai\\диплом\\project\\project\\src\\main\\resources\\static\\images\\users\\" + request.getImage());
            if(file.renameTo
                    (new File("D:\\kai\\диплом\\project\\project\\src\\main\\resources\\static\\images\\usersImages\\" + request.getImage())))
            {
                file.delete();
            }
            else
            {
                System.out.println("Failed to move the file");
            }
        }
        if(refreshRequest(id).equals("true")) {
            return "true";
        }else {
            return "false";
        }
    }

    public List<RequestsModel> getRequestsByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        return requestRepository.findAllByUserId(user);
    }

    public List<RequestsModel> getAllRequests() {
        return requestRepository.findAll();
    }
}
