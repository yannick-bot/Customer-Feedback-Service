package feedbackservice.model.modelDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFeedbackDTO {
    private String id;
    private int rating;
    private String feedback;
    private String customer;
    private String product;
    private String vendor;
}
