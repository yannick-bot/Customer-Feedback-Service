package feedbackservice.model.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaginatedFeedbackDTO {
    private long total_documents;
    @JsonProperty("is_first_page")
    private boolean is_first_page ;
    @JsonProperty("is_last_page")
    private boolean is_last_page;
    private List<GetFeedbackDTO> documents;

}
