package extended.model;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.Serializable;

@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class CompanyRepository implements Serializable {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private Company company;

    public Company getById(long id) {
        this.company = entityManager.find(Company.class, id);
        return this.company;
    }

    public Company getByName(String name) {
        this.company = entityManager.createNamedQuery("byName", Company.class).
                setParameter("name", name).
                getSingleResult();
        return this.company;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {
        //flushes the transaction to the entityManager
    }
}
