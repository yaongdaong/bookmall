// package com.example.bookmall.domain;
//
// import org.springframework.security.core.parameters.P;
//
// import java.io.Serializable;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Objects;
//
// public class Cart implements Serializable {
//     private static final long getSerialVersionUID = 2155125089108199199L;
//     private String cartId; // 장바구니 ID
//     private Map<String, CartItem> cartItems; // 장바구니 항목
//     private int grandTotal; // 총액
//     private static final long serialVersionUID = 3636831123198280235L;
//
//     // 1.generate constructors from superclass
//     public Cart(){
//         cartItems = new HashMap<String, CartItem>();
//         grandTotal = 0;
//     }
//
//     // 2.generate constructor using fields
//     public Cart(String cartId){
//         this();
//         this.cartId = cartId;
//     }
//
//     // 3.generate getters and setters
//     public String getCartId(){
//         return cartId;
//     }
//
//     public void setCartId(String cartId){
//         this.cartId = cartId;
//     }
//
//     public Map<String, CartItem> getCartItems(){
//         return cartItems;
//     }
//
//     public void setCartItems(Map<String, CartItem> cartItems){
//         this.cartItems = cartItems;
//     }
//
//     public int getGrandTotal(){
//         return grandTotal;
//     }
//
//     // 4.updateGrandTotal() 메서드는 장바구니에 등록되는 도서 가격의 총액을 산출
//     public void updateGrandTotal(){
//         grandTotal = 0;
//         for (CartItem item : cartItems.values()){
//             grandTotal = grandTotal + item.getTotalPrice();
//         }
//     }
//
//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || getClass() != o.getClass()) return false;
//         Cart cart = (Cart) o;
//         return Objects.equals(cartId, cart.cartId);
//     }
//
//     @Override
//     public int hashCode() {
//         return Objects.hash(cartId);
//     }
//
//     public void addCartItem(CartItem item){
//         String bookId = item.getBook().getBookId();// 현재 등록하기 위한 도서 ID 가져오기
//         // 도서 ID가 cartItems 객체에 등록되어 있는지 여부 확인
//         if(cartItems.containsKey(bookId)){
//             CartItem cartItem = cartItems.get(bookId); // 등록된 도서 ID에 대한 정보 가져오기
//             // 등록된 도서 ID의 개수 추가 저장
//             cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());
//             cartItems.put(bookId,cartItem); // 등록된 도서 ID에 대한 변경 정보(cartItem)저장
//         } else{
//             cartItems.put(bookId, item); // 도서 ID에 대한 도서 정보(item) 저장
//         }
//         updateGrandTotal(); // 총액 갱신
//     }
//
//     public void removeCartItem(CartItem item){
//         String bookId = item.getBook().getBookId();
//         cartItems.remove(bookId); // bookId 도서 삭제
//         updateGrandTotal(); // 총액 갱신
//     }
// }
