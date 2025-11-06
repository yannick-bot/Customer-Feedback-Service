package feedbackservice.model.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackFilterDTO {
    private Integer rating;
    private String customer;
    private String product;
    private String vendor;

}
