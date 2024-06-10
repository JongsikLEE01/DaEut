package com.daeut.daeut.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;
import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.main.service.FileService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.mapper.PartnerMapper;
import com.daeut.daeut.reservation.dto.Orders;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PartnerMapper partnerMapper;

    @Autowired
    private FileService fileService;

    private static final int THUMBNAIL_FILE_CODE = 1;

    // 로그인
    @Override
    public boolean login(Users user) throws Exception {
        // // 💍 토큰 생성
        String username = user.getUserId();    // 아이디
        String password = user.getUserPassword();    // 암호화되지 않은 비밀번호
        UsernamePasswordAuthenticationToken token 
            = new UsernamePasswordAuthenticationToken(username, password);
        
    // 토큰을 이용하여 인증
    Authentication authentication = authenticationManager.authenticate(token);

    // 인증 여부 확인
    boolean result = authentication.isAuthenticated();

    // 시큐리티 컨텍스트에 등록
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return result;
    }

    // 아이디 찾기
    @Override
    public String findUserByDetails(String userName, String userEmail, String userPhone) throws Exception {
        return userMapper.findUserByDetails(userName, userEmail, userPhone);
    }

    // 비밀번호 찾기 비밀번호 재설정
    @Transactional
    @Override
    public int updatePw(Users user) throws Exception {
        int result = userMapper.updatePw(user);
        return result;
    }

    // id로 조회
    @Override
    public Users select(String username) throws Exception {
        Users user = userMapper.select(username);
        return user;
    }

    // email 로 조회
    @Override
    public Users findUserByEmail(String userEmail) throws Exception {
        Users user = userMapper.findUserByEmail(userEmail);
        return user;
    }

    // 회원가입
    @Override
    public int join(Users user) throws Exception {
        String username = user.getUserId();
        String password = user.getUserPassword();
        String encodedPassword = passwordEncoder.encode(password);  // 🔒 비밀번호 암호화
        user.setUserPassword(encodedPassword);

        // 회원 등록
        int result = userMapper.join(user);

        if( result > 0 ) {
            Users joinedUser = userMapper.select(username);
            int userNo = joinedUser.getUserNo();
            // 회원 기본 권한 등록
            UserAuth userAuth = new UserAuth();
            userAuth.setUserNo(userNo);
            userAuth.setAuth("ROLE_USER");
            result = userMapper.insertAuth(userAuth);
        }
        return result;
    }

    // 회원 권한 등록
    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {
        int result = userMapper.insertAuth(userAuth);
        return result;
    }

    // 파트너 신청
    @Override
    public int insertPartner(Partner partner) throws Exception {

        String parentTable = "partner";
        int parentNo = partnerMapper.maxPk();

        // 썸네일 업로드
        // - 부모 테이블, 부모 번호, 멀티파트파일, 파일 코드(1)-> 썸네일
        MultipartFile thumbnailFile = partner.getThumbnail();

        // 썸네일 파일 업로드한 경우만 추가
        if(thumbnailFile != null && !thumbnailFile.isEmpty()){
            Files thumbnail = new Files();
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFile(thumbnailFile);
            thumbnail.setFileCode(THUMBNAIL_FILE_CODE);   // 썸네일 파일 코드(1)
            fileService.upload(thumbnail);                // 썸네일 파일 업로드
        }
        
        // 파일 업로드
        // List<MultipartFile> fileList = partner.getFile();
        // if( !fileList.isEmpty() ){
        //     for (MultipartFile file : fileList) {
        //         if (file.isEmpty()) continue;

        //         // 파일 정보 등록
        //         Files  uploadFile = new Files();
        //         uploadFile.setParentTable(parentTable);
        //         uploadFile.setParentNo(parentNo);
        //         uploadFile.setFile(file);
        //         uploadFile.setFileCode(0);
        //         fileService.upload(uploadFile);
        //     }
        // }

        return userMapper.insertPartner(partner);
    }

    // 파트너 신청 대기
    @Override
    public int updateUserStatus(int userNo) throws Exception {
        return userMapper.updateUserStatus(userNo);
    }

    // ----------------------------------------------------------------------------

    // user 및 partner 테이블에서 정보를 조회
    @Override
    public Partner selectUserAndPartnerDetails(int userNo) throws Exception {
        return userMapper.selectUserAndPartnerDetails(userNo);
    }

    // 회원 수정
    @Transactional
    @Override
    public int update(Users user) throws Exception {
        int result = userMapper.update(user);
        return result;
    }

    // 회원 탈퇴
    @Transactional
    @Override
    public int delete(Users user) throws Exception {
        int result = userMapper.delete(user);
        return result;
    }

    // 예약
    @Override
    public List<Orders> selectOrdersByUserId(String userId) throws Exception {
        return userMapper.selectOrdersByUserId(userId);
    }

    // 파트너 찾기
    @Override
    public Partner selectPartner(int userNo) throws Exception {
        Partner partner = userMapper.selectPartner(userNo);
        return partner;

    }

    // 번호 유저찾기
    @Override
    public Users selectByUserNo(int userNo) throws Exception {
        return userMapper.selectByUserNo(userNo);
    }

    // 유저 이름으로 찾기
    @Override
    public Users findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    private String saveFile(MultipartFile file) {
        return "c:/upload";
    }

    @Override
    public Users findUserById(int userNo) throws Exception {
        return userMapper.findUserById(userNo);
    }

    
    
  
}
