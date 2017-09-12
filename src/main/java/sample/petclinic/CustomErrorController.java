package sample.petclinic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@RestController
@RequestMapping("${server.error.path:/error}")
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @Value("${server.error.path:/error}")
    private String serverErrorPath;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping
    public ResponseEntity<Object> handleError(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(response.getStatus()).body(getError(getErrorAttributes(request)));
    }

    @Override
    public String getErrorPath() {
        return serverErrorPath;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, true);
    }

    private Error getError(Map<String, Object> errorAttrributes) {
        Error error = new Error();
        error.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
        error.setMessage((String) errorAttrributes.get("message"));
        error.setError((String) errorAttrributes.get("error"));
        error.setPath((String) errorAttrributes.get("path"));
        return error;
    }

    private static class Error {
        private LocalDateTime timestamp;
        private String error;
        private String message;
        private String path;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getError() {
            return error;
        }

        void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        void setPath(String path) {
            this.path = path;
        }
    }
}
