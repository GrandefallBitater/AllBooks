package ruslan.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ruslan.project.json.StringJson;
import ruslan.project.models.LibraryModel;
import ruslan.project.services.BookService;
import ruslan.project.services.UserService;
import ruslan.project.services.libraryService;

import java.io.IOException;

@Controller
public class BookController {
    private final UserService userService;
    private final BookService bookService;
    private final libraryService libraryService;

    @Autowired
    public BookController(UserService userService, BookService bookService, libraryService libraryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @RequestMapping("/myBook")
    public String myBook(Model model, @RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("user", userService.getUser(currentPrincipalName));
        model.addAttribute("library", libraryService.getLibrary(id));
        return "myBookPage";
    }

    @RequestMapping("/book")
    public String Book(Model model, @RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("user", userService.getUser(currentPrincipalName));
        model.addAttribute("book", bookService.getBook(id));
        model.addAttribute("comments", bookService.getComments(id));
        return "bookPage";
    }

    @RequestMapping("/AllBooks")
    public String AllBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("user", userService.getUser(currentPrincipalName));
        return "allBooksPage";
    }

    @RequestMapping("/createComment")
    public @ResponseBody String createComment(@RequestParam("text") String text,
                                              @RequestParam("bookId") String bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return String.format("{\"message\": \"%s\"}", bookService.createComment(currentPrincipalName, text, bookId));
    }

    @RequestMapping("/createByISBN")
    public @ResponseBody String createByISBN(@RequestParam("isbn") String isbn,
                                             @RequestParam("publisher") String publisher,
                                             @RequestParam("author") String author,
                                             @RequestParam("capacity") String capacity,
                                             @RequestParam("year") String year,
                                             @RequestParam("genres") String genres,
                                             @RequestParam("descr") String descr,
                                             @RequestParam("name") String name,
                                             @RequestBody StringJson imageLink) throws IOException {
        return String.format("{\"message\": \"%s\"}", bookService.createBookFromISBN(isbn, publisher, author,
                capacity, year, genres, descr, name, imageLink.getMessage()));
    }
}
