// package com.example.bookmall.repository;
//
// import com.example.bookmall.domain.Book;
// import com.example.bookmall.exception.BookIdException;
// import com.example.bookmall.service.BookService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;
//
// import java.util.*;
//
// @Repository
// public class BookRepositoryImpl implements BookRepository {
//     // @Autowired
//     // BookRepository bookRepository;
//     // @Autowired
//     // public void BookServiceImpl(BookRepository bookRepository) {
//     //     this.bookRepository = bookRepository;
//     // }
//     private List<Book> listOfBooks = new ArrayList<Book>();
//
//     // 1번.기본생성자:도메인 객체에서 정의된 모든 필드 값을 설정한다.
//     public BookRepositoryImpl() {
//         Book book1 = new Book("ISBN1234", "C#교과서", 30000);
//         book1.setAuthor("박용준");
//         book1.setDescription("C#교과서는 생애 첫 프로그래밍 언어로 C#을 시작하는 독자를 대상으로 한다. 특히 응용 프로그래머를 위한 C# 입문서로, C#을 사용하여 게임(유니티), 웹, 모바일, IoT 등을 개발할 때 필요한 C# 기초 문법을 익히고 기본기를 탄탄하게 다지는 것이 목적이다.");
//         book1.setPublisher("길벗");
//         book1.setCategory("IT전문서");
//         book1.setUnitsInStock(1000);
//         book1.setReleaseDate("2020/05/29");
//
//         Book book2 = new Book("ISBN1235", "Node.js 교과서", 36000);
//         book2.setAuthor("조현영");
//         book2.setDescription("이 책은 프런트부터 서버, 데이터베이스, 배포까지 아우르는 광범위한 내용을 다룬다. 군더더기 없는 직관적인 설명으로 기본 개념을 확실히 이해하고, 노드의 기능과 생태계를 사용해 보면서 실제로 동작하는 서버를 만들어보자. 예제와 코드는 최신 문법을 사용했고 실무에 참고하거나 당장 적용할 수 있다.");
//         book2.setPublisher("길벗");
//         book2.setCategory("IT전문서");
//         book2.setUnitsInStock(1000);
//         book2.setReleaseDate("2020/07/25");
//
//         Book book3 = new Book("ISBN1236", "어도비 XD CC 2020", 25000);
//         book3.setAuthor("김두한");
//         book3.setDescription("어도비 XD 프로그램을 통해 UI/UX 디자인을 배우고자 하는 예비 디자이너의 눈높이에 맞게 기본적인 도구를 활용한 아이콘 디자인과 웹&앱 페이지 디자인, UI 디자인, 앱디자인에 애니메이션과 인터랙션을 적용한 프로토타이핑을 학습합니다.");
//         book3.setPublisher("길벗");
//         book3.setCategory("IT활용서");
//         book3.setUnitsInStock(1000);
//         book3.setReleaseDate("2019/05/29");
//         listOfBooks.add(book1);
//         listOfBooks.add(book2);
//         listOfBooks.add(book3);
//     }
//
//     // 2번.도서목록 가져오기 : 저장된 도서 목록의 정보를 가져오는 getAllBookList()메서드를 작성한다.
//     @Override
//     public List<Book> getAllBookList() {
//         return listOfBooks;
//     }
//
//     //  getBookListByCategory() 메서드는 웹 요청 URL로 전송된 경로 변수의 값과
//     //  도서 목록의 도서 분야(category 필드 값)를 비교하여 일치하는 도서 정보를 저장한 후 이를 반환
//     //  1.도서 분야와 일치하는 도서 목록을 가져오는 getBookListByCategory()메서드
//     public List<Book> getBookListByCategory(String category) {
//         //  2.일치하는 도서 분야를 저장하는 객체 변수 booksByCategory를 선언
//         List<Book> booksByCategory = new ArrayList<Book>();
//         for (int i = 0; i < listOfBooks.size(); i++) {
//             //  3.book에 도서 목록의 i번째 도서 정보를 저장
//             Book book = listOfBooks.get(i);
//             //  4. 대소문자와 관계없이 매개변수 category와 도서 분야가 일치하는 도서 목록 i번째의 도서 정보를 booksByCategory에 저장
//             if (category.equalsIgnoreCase(book.getCategory()))
//                 booksByCategory.add(book);
//         }
//         //  5.매개변수 category와 일치하는 도서 목록을 반환
//         return booksByCategory;
//     }
//
//     public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
//         Set<Book> booksByPublisher = new HashSet<Book>();
//         Set<Book> booksByCategory = new HashSet<Book>();
//         Set<String> booksByFilter = filter.keySet();
//         // 1. 매트릭스 변수 증 publisher를 포함하는 경우에 실행
//         // 전체 도서 목록 중에서 publisher 필드 값과 일치하는 도서를 검색하여 booksByPublisher 객체에 등록
//         if (booksByFilter.contains("publisher")) {
//             for (int j = 0; j < filter.get("publisher").size(); j++) {
//                 String publisherName = filter.get("publisher").get(j);
//                 for (int i = 0; i < listOfBooks.size(); i++) {
//                     Book book = listOfBooks.get(i);
//                     if (publisherName.equalsIgnoreCase(book.getPublisher()))
//                         booksByPublisher.add(book);
//
//
//                 }
//             }
//         }
//         // 2. 매트리스 변수 중 category를 포함하는 경우에 실행
//         // 전체 도서 목록 중 category 값과 일치하는 도서를 검색하여 booksByCategory 객체에 등록
//         if (booksByFilter.contains("category")) {
//             for (int i = 0; i < filter.get("category").size(); i++) {
//                 String category = filter.get("category").get(i);
//                 List<Book> list = getBookListByCategory(category);
//                 booksByCategory.addAll(list);
//             }
//         }
//         // 3. booksByCategory 객체에 등록된 도서와 booksByPublisher에 등록된 도서 목록 중 중복되는 도서만 남기고
//         // 나머지는 삭제한 후 booksByCategory 객체로 반환
//         booksByCategory.retainAll(booksByPublisher);
//         return booksByCategory;
//     }
//
//     public Book getBookById(String bookId) {
//         Book bookInfo = null;
//         for (int i = 0; i < listOfBooks.size(); i++) {
//             Book book = listOfBooks.get(i);
//             if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
//                 bookInfo = book;
//                 break;
//             }
//         }
//         if (bookInfo == null)
//             throw new BookIdException(bookId);
//         return bookInfo;
//     }
//
//     // 신규 도서 정보 저장
//     public void setNewBook(Book book){
//         // 신규 도서 정보를 listOfBooks에 추가로 저장
//         // listOfBooks.add(book);
//         bookRepository.save(book);
//     }
//
// }
