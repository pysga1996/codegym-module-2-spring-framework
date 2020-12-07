package com.codegym.blog.repository.impl;

import com.codegym.blog.model.Article;
import com.codegym.blog.model.Category;
import com.codegym.blog.repository.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Article> findAll(){
        TypedQuery<Article> query = em.createQuery("select c from Article c", Article.class);
        return query.getResultList();
    }

    @Override
    public Article findById(Long id){
        TypedQuery<Article> query = em.createQuery("select c from Article c where  c.id=:id", Article.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Article model){
        if(model.getId() != null){
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id){
        Article article = findById(id);
        if(article != null){
            em.remove(article);
        }
    }

    @Override
    public List<Article> findAllByCategory(Category category){
        TypedQuery<Article> query = em.createQuery("select c from Article c where  c.category=:category", Article.class);
        query.setParameter("category", category);
        try {
            return query.getResultList();
        } catch (NoResultException e){
            return null;
        }
    }
}
