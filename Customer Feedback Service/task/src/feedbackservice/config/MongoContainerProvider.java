package feedbackservice.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.MongoDBContainer;

import java.util.List;

@Component
public class MongoContainerProvider {
    private MongoDBContainer container;

    @Value("${mongodb.image}")
    private String mongodbImage;

    @PostConstruct
    public void init() {
        container = new MongoDBContainer(mongodbImage); //imge name
        container.withCreateContainerCmdModifier(cmd ->
                cmd.withName("feedback-service")); // container name
        container.addEnv("MONGO_INITDB_DATABASE", "feedback_db"); // init database feedback_db
        container.setPortBindings(List.of("27017:27017")); // expose port 27017
        container.start();
    }

    @PreDestroy
    public void tearDown() {
        if (container != null) {
            container.stop();
        }
    }


}

