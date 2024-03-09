package bs23.practical.usermanagement.payload;

import bs23.practical.usermanagement.util.DateUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseEntityFactory {

    public ResponseEntity<Object> successResponse(Object object) {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(DateUtil.getStandardCurrentTimeStamp())
                .statusCode(HttpStatus.OK.value())
                .success(Boolean.TRUE)
                .data(object)
                .build();
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> successResponse(Object object, HttpStatus status) {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(DateUtil.getStandardCurrentTimeStamp())
                .statusCode(status.value())
                .success(Boolean.TRUE)
                .data(object)
                .build();
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), status);
    }

    public ResponseEntity<Object> successResponse(Object object, String message) {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(DateUtil.getStandardCurrentTimeStamp())
                .statusCode(HttpStatus.OK.value())
                .success(Boolean.TRUE)
                .data(object)
                .message(message)
                .build();
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Object> successResponse(Object object, String message, HttpStatus status) {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(DateUtil.getStandardCurrentTimeStamp())
                .statusCode(status.value())
                .success(Boolean.TRUE)
                .data(object)
                .message(message)
                .build();
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), status);
    }

    public ResponseEntity<Object> errorResponse(String message, HttpStatus status) {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(DateUtil.getStandardCurrentTimeStamp())
                .statusCode(status.value())
                .success(Boolean.FALSE)
                .message(message)
                .build();
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), status);
    }
}
