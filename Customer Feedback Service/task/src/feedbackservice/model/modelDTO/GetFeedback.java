package feedbackservice.model.modelDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFeedback {
    private String id;
    private int rating;
    private String feedback;
    private String customer;
    private String product;
    private String vendor;
}
