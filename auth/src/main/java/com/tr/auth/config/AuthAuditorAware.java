package com.tr.auth.config;//package com.tr.auth.config;
//
//import com.tr.auth.util.JwtUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//import java.util.Optional;
//
///**
// * @Author: TR
// */
//@Component
//@Slf4j
//public class AuthAuditorAware implements AuditorAware<Long> {
//
//    @Override
//    public Optional<Long> getCurrentAuditor() {
//        try {
//            Long userId = JwtUtil.getUsername();
//            if (Objects.nonNull(userId)){
//                return Optional.of(userId);
//            }
//        } catch (Exception e) {
//            log.warn(e.getMessage());
//        }
//        return Optional.empty();
//    }
//
//}
