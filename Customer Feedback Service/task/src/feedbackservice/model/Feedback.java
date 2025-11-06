package feedbackservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "feedback")
public class Feedback {
    @Id
    private String id;
    private Integer rating;
    private String feedback;
    private String customer;
    private String product;
    private String vendor;


}
