 package com.example.bookmall.controller;

 import com.example.bookmall.domain.*;
 import com.example.bookmall.service.BookService;
 import com.example.bookmall.service.CommentService;
 import com.example.bookmall.service.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.annotation.AuthenticationPrincipal;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;
 import java.util.Optional;

 @RestController
 @RequestMapping("/comment")
 public class CommentController {

     @Autowired
     private CommentService commentService;

     @Autowired
     private BookService bookService;

     @Autowired
     private UserService userService;

     @PostMapping("/add/{bookId}")
     public ResponseEntity<Comment> addComment(@PathVariable("bookId") Long id, @RequestBody CommentRequest commentRequest, @AuthenticationPrincipal UserDetails userDetails) {
         String username = userDetails.getUsername();
         Optional<User> user = userService.findByUsername(username);
         Book book = bookService.getBookById(id);
         Comment addedComment = commentService.addComment(book, commentRequest.getContent(),user);
         return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
     }

     @GetMapping("/list/{bookId}")
     @ResponseBody
     public List<Comment> getComments(@PathVariable Long bookId){
         return commentService.getCommentsByBookId(bookId);
     }
     //@GetMapping("/list/{bookId}")
     //public String getComments(@PathVariable Long bookId, Model model) {
     //    List<Comment> comments = commentService.getCommentsByBookId(bookId);
     //    model.addAttribute("comments", comments);
     //    //System.out.println("comments"+comments);
     //    return "book"; // comment_list는 댓글 목록을 표시할 Thymeleaf 템플릿 파일입니다.
     //}
     @PostMapping("/update/{commentId}")
     public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, CommentRequest commentRequest){
         try {
             Comment updatedComment = commentService.updateComment(commentId, commentRequest.getContent());
             return ResponseEntity.ok(updatedComment);
         } catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
     }

     @PostMapping("/delete/{id}")
     @ResponseBody
     public String deleteComment(@PathVariable Long id) {
         commentService.deleteComment(id);
         return "book";
     }
 }
