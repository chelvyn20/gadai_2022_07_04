
package id.co.nds.gadai_2022_07_04.globals;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;

@ControllerAdvice
public class GlobalResponseEntityHandler
                extends ResponseEntityExceptionHandler {

        @ExceptionHandler({ ClientException.class })
        public ResponseEntity<Object> handleClientException(ClientException ex,
                        WebRequest request) {

                Map<String, String> data = new HashMap<>();
                data.put("error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("400");
                response.setResponseMessage("ClientException");
                response.setResponseDescription("Something Wrong Your Code");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.badRequest().body(response);

        }

        @ExceptionHandler({ NullPointerException.class })
        public ResponseEntity<Object> handlePointerException(
                        NullPointerException ex, WebRequest request) {

                Map<String, String> data = new HashMap<>();
                data.put("error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("400");
                response.setResponseMessage("NullPointerException");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler({ ParseException.class })
        public ResponseEntity<Object> handleParseException(ParseException ex,
                        WebRequest request) {

                Map<String, String> data = new HashMap<>();
                data.put("error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("400");
                response.setResponseMessage("ParseException");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.badRequest().body(response);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex, HttpHeaders headers,
                        HttpStatus status, WebRequest request) {
                // TODO Auto-generated method stub
                Map<String, List<String>> data = new HashMap<>();
                List<String> errors = ex.getBindingResult().getFieldErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList());
                if (errors.size() > 1) {
                        data.put("error", errors);
                } else {
                        data.put("error", errors);
                }

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("400");
                response.setResponseMessage("MethodArgumentNotValidException");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);
                return ResponseEntity.badRequest().body(response);
        }

        @Override
        protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
                        HttpRequestMethodNotSupportedException ex,
                        HttpHeaders headers, HttpStatus status,
                        WebRequest request) {
                // TODO Auto-generated method stub
                Map<String, String> data = new HashMap<>();
                data.put("error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("400");
                response.setResponseMessage(
                                "HttpRequestMethodNotSupportedException");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler({ DataIntegrityViolationException.class })
        public ResponseEntity<Object> DataIntegrityViolationException(
                        DataIntegrityViolationException ex,
                        WebRequest request) {

                Map<String, String> data = new HashMap<>();
                data.put("Error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("404");
                response.setResponseMessage("DataIntegrityViolationException");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(response);
        }

        @ExceptionHandler({ NotFoundException.class})
        public ResponseEntity<Object> handleNotFoundException(
                        NotFoundException ex, WebRequest request) {

                Map<String, String> data = new HashMap<>();
                data.put("Error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("404");
                response.setResponseMessage("NotFoundException");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(response);

        }

        @Override
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                        Object body, HttpHeaders headers, HttpStatus status,
                        WebRequest request) {
                // TODO Auto-generated method stub
                Map<String, String> data = new HashMap<>();
                data.put("error", ex.getMessage());

                DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                ResponseModel response = new ResponseModel();
                response.setResponseCode("500");
                response.setResponseMessage("Failed Request");
                response.setResponseDescription("Something Wrong Your Input");
                response.setResponseTime(dtf.format(now));
                response.setResponseData(data);

                return ResponseEntity.internalServerError().body(response);
        }

}
