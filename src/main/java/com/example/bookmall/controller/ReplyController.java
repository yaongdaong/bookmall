// package com.example.bookmall.controller;
//
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// public class ReplyController {
//
//     final ReplyService replyService;
//
//     public ReplyController(ReplyService replyService) {
//         this.replyService = replyService;
//     }
//
//     // 댓글 쓰기
//     @PostMapping("/replyWrite")
//     public ReplyDTO replyWrite(Long bno, String reply, String replyer) {
//         ReplyDTO replyDTO = new ReplyDTO();
//         replyDTO.setRno(replyDTO.getRno());
//         replyDTO.setBno(bno);
//         replyDTO.setReply(reply);
//         replyDTO.setReplyer(replyer);
//         replyDTO.setReplyDate(new Date());
//         replyService.replyWrite(replyDTO);
//         return replyDTO;
//     }
//
//     // 댓글 읽기
//     @RequestMapping("/replyList")
//     public List<ReplyDTO> replyList(@RequestParam(value = "bno") int bno) {
//         List<ReplyDTO> replyList = replyService.replyList(bno);
//         return replyList;
//     }
//
//     // 댓글 수정
//     @PostMapping("/replyModify")
//     public ReplyDTO replyModify(Long rno, String reply) {
//         System.out.println(reply);
//         ReplyDTO replyDTO = new ReplyDTO();
//         replyDTO.setRno(rno);
//         replyDTO.setReply(reply);
//         replyService.replyModify(replyDTO);
//
//         return replyDTO;
//     }
//
//     // 댓글 삭제
//     @PostMapping("/replyDelete")
//     public void replyDelete(Long rno) {
//         replyService.replyDelete(rno);
//     }
// }