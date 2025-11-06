package feedbackservice.model.modelDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFeedbackDTO {
    @Min(1)
    @Max(5)
    private int rating;
    private String feedback;
    private String customer;
    @NotBlank
    private String product;
    @NotBlank
    private String vendor;
}
