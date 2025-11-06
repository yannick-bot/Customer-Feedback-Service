package feedbackservice.Exceptions.customException;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(String message) {
        super(message);
    }
}
