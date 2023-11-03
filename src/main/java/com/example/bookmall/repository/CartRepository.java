  package com.example.bookmall.repository;

  import com.example.bookmall.domain.Cart;
  import com.example.bookmall.domain.CartItem;
  import com.example.bookmall.domain.User;
  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.stereotype.Repository;

  // 장바구니 정보를 관리하는 퍼시스턴스 계층
  @Repository
  public interface CartRepository extends JpaRepository<Cart,Long> {
      Cart findByUser(User user);

  }
