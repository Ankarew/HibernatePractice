package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.example.models.Person;
import org.example.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Read
    public List<Product> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);
        cq.select(root);

        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    //https://www.baeldung.com/spring-data-criteria-queries
    public Product getById (UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);
        //Predicate uuidPredicative = cb.equal(root.get("id"), id);
        cq.where(cb.equal(root.get("id"), id));

        TypedQuery<Product> query = entityManager.createQuery(cq);

        return query.getSingleResult();
    }
    //update
    public Product update (Product product) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Product> cu = cb.createCriteriaUpdate(Product.class);
        Root<Product> root = cu.from(Product.class);
        if (getById(product.getId()) != null) {
            cu.set("name", product.getName());
            cu.set("description", product.getDescription());
            cu.set("type", product.getType());
            cu.set("price", product.getPrice());
            cu.set("in_storage", product.getInStorage());
        }
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createQuery(cu).executeUpdate();
        entityTransaction.commit();
        return product;
    }
    //delete
    public void delete (UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Product> cd = cb.createCriteriaDelete(Product.class);
        Root<Product> root = cd.from(Product.class);
        cd.where(cb.equal(root.get("id"), id));
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createQuery(cd).executeUpdate();
        entityTransaction.commit();
    }
    //No create for criteria api?
    public Product create(Product product) {
        entityManager.persist(product);
        return product;
    }

}
