package xyz.zzyitj.ils.interceptor;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.zzyitj.ils.enums.ResultEnum;
import xyz.zzyitj.ils.util.IPUtils;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<?> globalException(HttpServletRequest request, Exception e) {
        // e.printStackTrace();
        logger.error("ip: {}, url: {}, message: {}",
                IPUtils.getIpAddr(request), request.getRequestURI(), e.getMessage());
        if (e instanceof UnauthorizedException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResultEnum.UNAUTHORIZED_ERROR.toString());
        }
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = TipException.class)
    @ResponseBody
    public ResponseEntity<String> tipException(HttpServletRequest request, TipException e) {
//        e.printStackTrace();
        logger.error("ip: {} url: {}, message: {}",
                IPUtils.getIpAddr(request), request.getRequestURI(), e.getResultEnum().toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getResultEnum().toString());
    }
}
