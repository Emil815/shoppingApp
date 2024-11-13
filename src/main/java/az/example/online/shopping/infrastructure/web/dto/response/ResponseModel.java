package az.example.online.shopping.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseModel<T> {
    private HttpStatus httpStatus;
    private T data;
    private String message;

    public static <T> ResponseModel<T> created(String message) {
        return new ResponseModel<>(HttpStatus.CREATED, null, message);
    }

    public static <T> ResponseModel<T> accepted(T data, String message) {
        return new ResponseModel<>(HttpStatus.ACCEPTED, data, message);
    }

    public static <T> ResponseModel<T> ok(T data, String message) {
        return new ResponseModel<>(HttpStatus.OK, data, message);
    }
}
