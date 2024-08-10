package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.project.models.BookModel;
import ruslan.project.models.CommentsModel;
import ruslan.project.models.UserModel;
import ruslan.project.repositories.BookRepository;
import ruslan.project.repositories.CommentsRepository;
import ruslan.project.repositories.UserRepository;

import java.io.*;
import java.net.URL;
import java.util.*;

@Service
public class BookService {
    private BookRepository bookRepository;
    private CommentsRepository commentsRepository;
    private UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CommentsRepository commentsRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    public List<BookModel> getBooks() {
        return bookRepository.findAll();
    }

    public BookModel getBook(String id) {
        BookModel bookModel = bookRepository.findById(Long.parseLong(id)).get();
        return bookModel;
    }

    public String addBook(String isbn, String publisned, String authors,
                          String capacity, String year, String genres, String descr, String name,
                          String image) {
        BookModel book = new BookModel();
        book.setComments(new ArrayList<>());
        book.setCapacity(Integer.parseInt(capacity));
        book.setAuthor(new String[]{authors});
        book.setGenres(new String[]{genres});
        book.setISBN(isbn);
        book.setDescription(descr);
        book.setImage(image);
        book.setLibrary(new ArrayList<>());
        book.setName(name);
        book.setPublished(publisned);
        book.setYear_published(year);
        bookRepository.save(book);
        return "true";
    }

    public List<CommentsModel> getComments(String id) {
        BookModel bookModel = bookRepository.findById(Long.parseLong(id)).get();
        return bookModel.getComments();
    }

    public String createComment(String username, String Massage, String bookId) {
        BookModel bookModel = bookRepository.findById(Long.parseLong(bookId)).get();
        CommentsModel comment = new CommentsModel();
        comment.setBook_Id(bookModel);
        UserModel user = userRepository.findByUsername(username);
        comment.setUserId(user);
        comment.setText(Massage);
        List<CommentsModel> comments = bookModel.getComments();
        comments.add(comment);
        bookModel.setComments(comments);
        bookRepository.save(bookModel);
        return bookId;
    }

    public String createBookFromISBN(String isbn, String publisher, String author, String capacity, String year, String genres, String descr, String name, String imageLink) throws IOException {
        List<BookModel> books = bookRepository.findAllByISBN(isbn);
        if(books.size() != 0) {
            return "Книга с таким ISBN уже существует!";
        }else{
            BookModel book = new BookModel();
            book.setComments(new ArrayList<>());
            book.setCapacity(Integer.parseInt(capacity));
            book.setAuthor(new String[]{author});
            book.setGenres(new String[]{genres});
            book.setISBN(isbn);
            book.setDescription(descr);
//            book.setImage(image);
            book.setLibrary(new ArrayList<>());
            book.setName(name);
            book.setPublished(publisher);
            book.setYear_published(year);

            System.out.println(imageLink);
            URL url = new URL(imageLink);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            Random ran = new Random();
            int top = 10;
            char data = ' ';
            String dat = "";

            for (int i=0; i<=top; i++) {
                data = (char)(ran.nextInt(25)+97);
                dat = data + dat;
            }
            FileOutputStream fos = new FileOutputStream("D:\\kai\\диплом\\project\\project\\src\\main\\resources\\static\\images\\usersImages\\" + dat + ".jpg");
            fos.write(response);
            fos.close();

            book.setImage(dat + ".jpg");

            bookRepository.save(book);
            return "true";
        }
    }
}
