package com.daeut.daeut.auth.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.auth.dto.CustomUser;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.dto.Review;
import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.partner.service.ReviewService;
import com.daeut.daeut.reservation.dto.Cart;
import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.reservation.service.CartService;
import com.daeut.daeut.reservation.service.ChatRoomService;
import com.daeut.daeut.reservation.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/userMypage")
    public String userMypage(HttpSession session, Model model) throws Exception {
        log.info("/user/userMypage");

        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "/user/userMypage";
    }

    // 사용자 마이페이지 수정
    @GetMapping("/userMypageUpdate")
    public String userMypageUpdate(HttpSession session, Model model) throws Exception {
        log.info("/user/userMypageUpdate");

        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "/user/userMypageUpdate";
    }

    // 사용자 마이페이지 수정 처리
    @PostMapping("/userMypageUpdateDone")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userMypageUpdateDone(HttpSession session, @RequestParam("action") String action, @ModelAttribute Users user) throws Exception {
        Users sessionUser = (Users) session.getAttribute("user");
        if (sessionUser == null) {
            // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        if ("delete".equals(action)) {
            int result = userService.delete(sessionUser);
            log.info("Delete result: " + result);
            if (result > 0) {
                session.invalidate(); // 세션 무효화
                return "redirect:/index";  // 탈퇴 처리 후 리다이렉트할 페이지
            } else {
                return "redirect:/user/userMypage";
            }
        } else if ("update".equals(action)) {
            int result = userService.update(user);
            log.info("Update result: " + result);
            if (result > 0) {
                session.setAttribute("user", user); // 세션 업데이트
                return "redirect:/user/userMypage";
            } else {
                return "redirect:/user/userMypageUpdate";
            }
        }
        return "redirect:/user/userMypage"; // 기본적으로 리다이렉트할 페이지
    }
    
    // 사용자 예약 화면
    @GetMapping("/userReservation")
    public String userReservation(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        log.info("/user/userReservation");

        
        String userId = customUser.getUsername();
        if(userId == null) {
            return "redirect:/index";
        }
        log.info(userId);


        List<Orders> orders = userService.selectOrdersByUserId(userId);
        model.addAttribute("orders", orders);

        return "user/userReservation";
    }

    @PostMapping("/OrdersDelete")
    public String OrdersDelete(@RequestParam("ordersNo") String ordersNo) throws Exception {
        orderService.OrdersDelete(ordersNo);
        return "redirect:/user/userReservation";
    }
    
    
    // 사용자 작성 리뷰
    @GetMapping("/user/userReview")
    public String showReviewForm(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // 사용자 번호가 없으면 로그인 페이지로 리디렉션
        }
        int userNo = user.getUserNo();
        model.addAttribute("payments", reviewService.getUserPayments(userNo));
        return "userReview";
    }

    @PostMapping("/user/userReviewDone")
    public String submitReview(Review review, HttpSession session) {
        Integer userNo = (Integer) session.getAttribute("userNo");
        if (userNo == null) {
            return "redirect:/login"; // 사용자 번호가 없으면 로그인 페이지로 리디렉션
        }
        review.setUserNo(userNo);
        reviewService.saveReview(review);
        return "redirect:/user/userReview";
    }

     /**
     * 유저 채팅방 생성
     * @param chatRoom
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/userChatRoom")
    public String createChatRoom(int partnerNo, Model model, HttpSession session) throws Exception{    
        ChatRooms chatRoom = new ChatRooms();
        chatRoom.setPartnerNo(partnerNo);

        Users user = (Users) session.getAttribute("user");
        int userNo = user.getUserNo();
        chatRoom.setUserNo(userNo);

        chatRoomService.merge(chatRoom);

        return "redirect:/user/userChatRoom";
    }

    /**
     * 유저 채팅 내역 - GET
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/userChatRoom")
    public String userChatRooms(Model model, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        int userNo = user.getUserNo();

        List<ChatRooms> chatRoomList = chatRoomService.selectByUserNo(userNo);

        model.addAttribute("user", user);
        model.addAttribute("chatRoomList", chatRoomList);
        return "user/userChatRoom";
    }

    // 장바구니
    @GetMapping("/userCart")
    public String userCart(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        int userNo = user.getUserNo();
        Partner partner;
        
        // 사용자의 장바구니 목록을 서비스를 통해 가져옴
        List<Cart> cartList;
        try {
            cartList = cartService.cartList(userNo);
            partner = userService.selectPartner(userNo);
            
            model.addAttribute("cartList", cartList);
            model.addAttribute("user", user);
            model.addAttribute("partner", partner);
        } catch (Exception e) {
            log.info("장바구니 조회 중 에러 발생...");
            e.printStackTrace();
        }
    
        return "/user/userCart";
    }

    // 유저, 파트너 신청 화면
    @GetMapping("/userPartner")
    public String userPartner(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        log.info("/user/userPartner");
    
        Partner partner = userService.selectUserAndPartnerDetails(customUser.getUser().getUserNo());
        model.addAttribute("partner", partner);
    
        return "user/userPartner";
    }
    
    // 파트너 신청 처리
    @PostMapping("/request-partner")
    public String insertPartner(@ModelAttribute Partner partner, @AuthenticationPrincipal CustomUser customUser) throws Exception {
        Partner partnerDetails = userService.selectUserAndPartnerDetails(customUser.getUser().getUserNo()); // 사용자 정보를 가져옴
        if (partnerDetails != null) {
            partner.setUserNo(partnerDetails.getUserNo());
            userService.insertPartner(partner);
            userService.updateUserStatus(partnerDetails.getUserNo());
        }
        return "redirect:/user/userPartnerDone";
    }
    
    // 관리자가 파트너 신청을 승인하는 엔드포인트
    @PostMapping("/approve-partner")
    public String approvePartner(@RequestParam String userId) {
        try {
            // userService.approvePartner(userId);
        } catch (Exception e) {
            log.error("Error approving partner status", e);
        }
        return "redirect:/user/partnerApprovalDone";
    }

    // 파트너 신청 완료 페이지
    @GetMapping("/userPartnerDone")
    public String userPartnerDone() {
        log.info("/user/userPartnerDone");
        return "/user/userPartnerDone";
    }
      
}
