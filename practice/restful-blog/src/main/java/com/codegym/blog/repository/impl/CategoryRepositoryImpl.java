package com.codegym.blog.repository.impl;

import com.codegym.blog.model.Category;
import com.codegym.blog.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll(){
        TypedQuery<Category> query = em.createQuery("select c from Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(Long id){
        TypedQuery<Category> query = em.createQuery("select c from Category c where  c.id=:id", Category.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Category model){
        if(model.getId() != null){
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id){
        Category category = findById(id);
        if(category != null){
            em.remove(category);
        }
    }
}
