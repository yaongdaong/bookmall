//function addToCart(action){
//    document.addForm.action = action;
//    document.addForm.submit();
//    alert("도서가 장바구니에 추가되었습니다.")
//}
function addToCart(action) {
    var form = document.forms.addForm; // 폼 요소 가져오기
    form.action = action;
    form.submit();
    alert("도서가 장바구니에 추가되었습니다.");
}

//
//
//        function addToCart() {
//            // 폼을 JavaScript로 직접 제출
//            document.getElementById('addToCartForm').submit();
//              alert("도서가 장바구니에 추가되었습니다.")
//        }

function removeFormCart(action){
    document.removeForm.action = action;
    document.removeForm.submit();
    window.location.reload();
}

function clearCart(){
    document.clearForm.submit();
    window.location.reload();
}