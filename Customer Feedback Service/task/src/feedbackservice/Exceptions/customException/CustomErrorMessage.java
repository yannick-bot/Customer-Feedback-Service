package feedbackservice.Exceptions.customException;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomErrorMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    String messge;
    String description;
}
