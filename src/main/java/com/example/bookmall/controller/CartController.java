//
// package com.example.bookmall.controller;
//
// import com.example.bookmall.service.CartService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
//
// //
// // import com.example.bookmall.domain.Book;
// // import com.example.bookmall.domain.Cart;
// // import com.example.bookmall.domain.CartItem;
// // import com.example.bookmall.exception.BookIdException;
// // import com.example.bookmall.service.BookService;
// // import com.example.bookmall.service.CartService;
// // import jakarta.servlet.http.HttpServletRequest;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.stereotype.Controller;
// // import org.springframework.ui.Model;
// // import org.springframework.web.bind.annotation.*;
// //
// @Controller
// @RequestMapping(value = "/cart")
// public class CartController {
//     private final CartService cartService;
//
//     @Autowired
//     public CartController(CartService cartService) {
//         this.cartService = cartService;
//     }
//     //     @Autowired
//     //     private CartService cartService;
//     //
//     //     @Autowired
//     //     private BookService bookService;
//     //
//     //     // 1. requestCartId() 메서드는 웹 요청 URL이 http://localhost:8080/cart/일 때 요청 처리 메서드
//     //     // 사용자 요청 처리. 세션 ID값을 가져와서 URI cart/sessionid로 리다이렉션
//
//
//     // @GetMapping
//     //     public String requestCartId(HttpServletRequest request) {
//     //         String sessionid = request.getSession(true).getId();
//     //         return "redirect:/cart/" + sessionid;
//     //     }
//     // //
//     //     // 2. craete() 메서드는 웹 요청 URI가 /cart/고 HTTP 메서드가 POST방식이면 매핑되는 메서드
//     //     // 사용자 요청을 처리. Cart 클래스 정보를 HTTP 요청 body로 전달받아 장바구니를 새로 생성하고 HTTP 응답 body로 전달
//     //     @PostMapping
//     //     public @ResponseBody Cart create(@RequestBody Cart cart) {
//     //         return cartService.create(cart);
//     //     }
//     //
//     //     // 3.requestCartList() 메서드는 웹 요청 URI가 /cart/cartId고, HTTP 메서드가 GET 방식이면 매핑되는 요청 처리 메서드
//     //     // 사용자 요청 처리. 요청 URL에서 경로 변수 cartId(장바구니 ID)에 대해 장바구니에 등록된 모든 정보를 읽어 와
//     //     // 커맨드 객체 cart 속성에 등록하고, 뷰 이름을 cart로 반환하므로 html파일은 cart.html이 된다.
//     //
//     //     @GetMapping("/{cartId}")
//     //     public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model) {
//     //         Cart cart = cartService.read(cartId);
//     //         model.addAttribute("cart", cart);
//     //         model.addAttribute("heading", "장바구니");
//     //         model.addAttribute("subheading", "Shopping Cart");
//     //         return "cart";
//     //     }
//     //
//     //     // 4. read() 메서드는 웹요청 URI가 /cart/cartId고, HTTP메서드가 PUT 방식이면 매핑되는 요청 처리 메서드
//     //     // 사용자 요청 처리.read() 메서드는 요청 URL에서 경로 변수인 장바구니 ID(cartId)에 대해 장바구니에 등록된 모든 정보를 가져온다.
//     //     @PutMapping("/{cartId}")
//     //     public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
//     //         return cartService.read(cartId);
//     //     }
//     //
//     //     // 1. addCartByNewItem() 메서드는 HTTP 메서드가 PUT 방식으로 요청 URI가 /cart/add/{bookId}일 때
//     //     // 경로 변수 bookId에 대해 해당 도서를 장바구니에 추가로 등록하고 장바구니를 갱신
//     //     // @PutMapping("/add/{bookId}")
//     //     // @ResponseStatus(value = HttpStatus.NO_CONTENT) // 오류 응답 상태 코드 설정
//     //     // public void addCartByNewItem(@PathVariable Integer id, HttpServletRequest request) {
//     //     //     // 장바구니 ID인 세션 ID 가져오기
//     //     //     String sessionId = request.getSession(true).getId();
//     //     //     Cart cart = cartService.read(sessionId); // 장바구니에 등록된 모든 정보 얻어오기
//     //     //     if (cart == null)
//     //     //         cart = cartService.create(new Cart(sessionId));
//     //     //     // 경로 변수 bookId에 대한 정보 얻어 오기
//     //     //     Book book = bookService.getBookById(id);
//     //     //     if (book == null)
//     //     //         throw new IllegalArgumentException(new BookIdException(id));
//     //     //     // bookId에 대한 도서 정보를 장바구니에 등록하기
//     //     //     cart.addCartItem(new CartItem(book));
//     //     //     cartService.update(sessionId, cart); // 세션 ID에 대한 장바구니 갱신하기
//     //     // }
//     //
//     //     @PutMapping("/remove/{bookId}")
//     //     @ResponseStatus(value=HttpStatus.NO_CONTENT)
//     //     // removeCartByItem() 메서드는 HTTP 메서드가 PUT 방식으로 요청 URI가 /cart/remove/{bookId}일 때
//     //     // 경로 변수 bookId에 대해 해당 도서를 장바구니에서 삭제하고 장바구니를 갱신
//     //     public void removeCartByItem(@PathVariable Integer id, HttpServletRequest request){
//     //         // 장바구니 ID인 세션 ID 가져오기
//     //         String sessionId = request.getSession(true).getId();
//     //         Cart cart = cartService.read(sessionId); // 장바구니에 등록된 모든 정보 얻어오기
//     //         if (cart == null)
//     //             cart = cartService.create(new Cart(sessionId));
//     //         // 경로 변수 bookId에 대한 정보 얻어 오기
//     //         Book book = bookService.getBookById(id);
//     //         if (book == null)
//     //             throw new IllegalArgumentException(new BookIdException(id));
//     //         // bookId에 대한 도서 정보를 장바구니에서 삭제하기
//     //         cart.removeCartItem(new CartItem(book));
//     //         cartService.update(sessionId, cart); // 세션 ID에 대한 장바구니 갱신하기
//     //     }
//     //
//     //     @DeleteMapping("/{cartId}")
//     //     @ResponseStatus(value = HttpStatus.NO_CONTENT)
//     //     // deleteCartList() 메서드는 웹 요청 URI가 /cart/cartId고 HTTP 메서드가 DELETE 방식일 때 매핑되는 요청 처리 메서드로,
//     //     // 사용자 요청을 처리. delete() 메서드는 요청 URL에서 경로 변수인 장바구니 ID(cartId)에 대해 장바구니에 등록된 모든 정보 삭제
//     //     public void deleteCartList(@PathVariable(value="cartId")String cartId){
//     //         cartService.delete(cartId);
//     //     }
// }