package feedbackservice.service;

import feedbackservice.Exceptions.customException.FeedbackNotFoundException;
import feedbackservice.common.constant.PaginationConstant;
import feedbackservice.model.Feedback;
import feedbackservice.model.modelDTO.CreateFeedbackDTO;
import feedbackservice.model.modelDTO.FeedbackFilterDTO;
import feedbackservice.model.modelDTO.GetFeedbackDTO;
import feedbackservice.model.modelDTO.GetPaginatedFeedbackDTO;
import feedbackservice.repository.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private ModelMapper modelMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
    }

    public Feedback addFeedback(CreateFeedbackDTO feedbackDTO) {
        return feedbackRepository.save(modelMapper.map(feedbackDTO, Feedback.class));

    }

    public GetFeedbackDTO findFeedbackById(String id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with id :" + id));

        return modelMapper.map(feedback, GetFeedbackDTO.class);
    }

    public GetPaginatedFeedbackDTO findAllFeedback(FeedbackFilterDTO feedbackFilter, int page, int perPage) {

        // vérifier que page et perPage sont valides
        if (page < Integer.parseInt(PaginationConstant.DEFAULT_PAGE)) {
            page = Integer.parseInt(PaginationConstant.DEFAULT_PAGE);
        }
        if (perPage < Integer.parseInt(PaginationConstant.MIN_PAGE_SIZE) ||
        perPage > Integer.parseInt(PaginationConstant.MAX_PAGE_SIZE)) {
            perPage = Integer.parseInt(PaginationConstant.DEFAULT_PAGE_SIZE);
        }

        // query by example pour les filtres de la requête
        Feedback feedbackObject = modelMapper.map(feedbackFilter, Feedback.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues();
        Example<Feedback> example = Example.of(feedbackObject, matcher);



        Sort sortOrder = Sort.by(Sort.Direction.DESC, "id");

        Pageable feedbackPage = PageRequest.of(page-1, perPage, sortOrder);
        Page<Feedback> feedbackPageData = feedbackRepository.findAll(example, feedbackPage);
        List<Feedback> feedbackList = feedbackPageData.getContent();

        List<GetFeedbackDTO> feedbackDTOs = feedbackList.stream()
                .map(feedback -> modelMapper.map(feedback, GetFeedbackDTO.class))
                .toList();

        GetPaginatedFeedbackDTO response = new GetPaginatedFeedbackDTO();
        response.setDocuments(feedbackDTOs);
        response.setTotal_documents(feedbackPageData.getTotalElements());
        response.set_first_page(feedbackPageData.isFirst());
        response.set_last_page(feedbackPageData.isLast());


        return response;
    }
}
