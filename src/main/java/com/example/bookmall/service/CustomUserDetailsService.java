// package com.example.bookmall.service;
//
// import com.example.bookmall.domain.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
//
// import java.util.ArrayList;
// import java.util.List;
//
// @Service
// public class CustomUserDetailsService implements UserDetailsService {
//
//     @Autowired
//     private UserService userService;
//
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // 사용자 정보를 데이터베이스에서 가져와서 UserDetails를 구성하는 코드를 작성하세요.
//         // userService를 사용하여 사용자 정보를 가져오는 예시:
//         User user = userService.getUserById(Long.valueOf(username)); // 문자열을 Long으로 변환
//
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found with username: " + username);
//         }
//
//         // UserDetails 객체를 구성하고 반환하세요.
//         // 여기에서 사용자의 롤 정보를 GrantedAuthority로 변환하여 UserDetails에 추가해야 합니다.
//
//         List<GrantedAuthority> authorities = new ArrayList<>();
//         // 롤 정보를 가져와서 authorities에 추가하는 코드를 작성하세요.
//
//         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//     }
// }
//
