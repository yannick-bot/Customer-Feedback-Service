package feedbackservice.controller;

import feedbackservice.Exceptions.customException.CustomErrorMessage;
import feedbackservice.Exceptions.customException.FeedbackNotFoundException;
import feedbackservice.common.constant.PaginationConstant;
import feedbackservice.model.Feedback;
import feedbackservice.model.modelDTO.CreateFeedbackDTO;
import feedbackservice.model.modelDTO.FeedbackFilterDTO;
import feedbackservice.model.modelDTO.GetFeedbackDTO;
import feedbackservice.model.modelDTO.GetPaginatedFeedbackDTO;
import feedbackservice.service.FeedbackService;
import feedbackservice.service.FeedbackServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackService feedbackService;
    private ModelMapper modelMapper;

    public FeedbackController(FeedbackService feedbackService, ModelMapper modelMapper) {
        this.feedbackService = feedbackService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CreateFeedbackDTO> addFeedback(@Valid @RequestBody CreateFeedbackDTO feedbackDTO) {
        Feedback response = feedbackService.addFeedback(feedbackDTO);
        // Ajouter le header Location à la réponse
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/feedback/"+ response.getId()));
        return new ResponseEntity<>(modelMapper.map(response, CreateFeedbackDTO.class), headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetFeedbackDTO> findFeedbackById(@PathVariable String id) {
        return new ResponseEntity<>(feedbackService.findFeedbackById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetPaginatedFeedbackDTO> findAllFeedback(
            @ModelAttribute FeedbackFilterDTO feedbackFilter,
            @RequestParam(defaultValue = PaginationConstant.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE) int perPage
    ) {
        return new ResponseEntity<>(feedbackService.findAllFeedback(feedbackFilter, page, perPage), HttpStatus.OK);
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleFeedbackNotFound(
            FeedbackNotFoundException e,
            WebRequest request
    ) {
        CustomErrorMessage body = new CustomErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
