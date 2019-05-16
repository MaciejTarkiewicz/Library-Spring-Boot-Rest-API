package pl.tarkiewicz.libraryapp.Rate;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tarkiewicz.libraryapp.Library.Book;
import pl.tarkiewicz.libraryapp.Library.LibraryService;
import pl.tarkiewicz.libraryapp.User.User;
import pl.tarkiewicz.libraryapp.User.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class RateController {

    private RateService rateService;
    private LibraryService libraryService;
    private UserService userService;
    private ModelMapper modelMapper;


    @Autowired
    public RateController(RateService rateService, LibraryService libraryService, UserService userService) {
        this.rateService = rateService;
        this.libraryService = libraryService;
        this.userService = userService;
        this.modelMapper = new ModelMapper();

    }

    @GetMapping(value = "/api/library/all/rates/{id}")
    public Integer getAllRate(@PathVariable Long id) {
        return this.rateService.getBookRate(id);

    }


    @PostMapping(value = "/library/book/rate")
    public ResponseEntity<String> rateBook(@RequestBody RateDto rateDto, HttpSession session) {
        Book book = this.libraryService.getBookById((Long)session.getAttribute("book_id"));
        Optional<User> user = this.userService.findById((Long)session.getAttribute("User_id"));
        try{
            Rate rate = modelMapper.map(rateDto, Rate.class);
            rate.setBook(book);
            rate.setUser(user.get());
            this.rateService.addRate(rate);
            return new ResponseEntity<>("Correct!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Invalid!", HttpStatus.BAD_GATEWAY);
        }

    }

    private RateDto convertToDto(Rate rate) {
        return modelMapper.map(rate, RateDto.class);
    }

    private Rate convertToEntity(RateDto rateDto) {
        Rate rate = modelMapper.map(rateDto, Rate.class);
        return rate;
    }

}
