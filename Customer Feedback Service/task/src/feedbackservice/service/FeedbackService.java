package feedbackservice.service;

import feedbackservice.Exceptions.customException.FeedbackNotFoundException;
import feedbackservice.model.Feedback;
import feedbackservice.model.modelDTO.CreateFeedbackDTO;
import feedbackservice.model.modelDTO.FeedbackFilterDTO;
import feedbackservice.model.modelDTO.GetFeedbackDTO;
import feedbackservice.model.modelDTO.GetPaginatedFeedbackDTO;
import jakarta.validation.Valid;

public interface FeedbackService {
    Feedback addFeedback(@Valid CreateFeedbackDTO feedbackDTO);

    GetFeedbackDTO findFeedbackById(String id) ;

    GetPaginatedFeedbackDTO findAllFeedback(FeedbackFilterDTO feedbackFilter, int page, int perPage);

}
