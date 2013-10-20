package extended.example;


import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped   //would use view scope in EE7
public class CompanyController implements Serializable {

    @Inject
    private CompanyRepository repository;

    @Inject
    private Conversation conversation;

    private Company company;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        company = repository.getByName("LazyJoePizza");
    }

    public Company getCompany() {
        return company;
    }

    public void saveCompany() {
        repository.save();
        conversation.end();
    }
}
