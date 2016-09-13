package lab.dao.jpa;

import lab.dao.CountryDao;
import lab.model.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

    @Override
    public void save(Country country) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(country);
        transaction.commit();

        em.close();
    }

    @Override
    public List<Country> getAllCountries() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<Country> query = em.createQuery("from lab.model.Country", Country.class);
            return query.getResultList();
        } finally {
            assert em != null;
            em.close();
        }
    }// getAllcountries()

    @Override
    public Country getCountryByName(String name) {
//		TODO: Implement it

        return null;
    }

}
