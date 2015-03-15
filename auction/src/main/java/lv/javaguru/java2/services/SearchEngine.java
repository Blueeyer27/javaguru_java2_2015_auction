package lv.javaguru.java2.services;

import lv.javaguru.java2.domain.Product;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Vladislav on 3/15/2015.
 */
@Component
@Transactional
public class SearchEngine {
    @Autowired
    SessionFactory sessionFactory;


    public List<Product> searchForProductsBy(String keywords, int startFrom, int productCount){
        Session session =  sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        SearchFactory searchFactory = fullTextSession.getSearchFactory();

        QueryBuilder productQB = searchFactory.buildQueryBuilder().forEntity(Product.class).get();

        Query luceneQuery = productQB.keyword().onFields("description", "name").matching(keywords).createQuery();
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Product.class);

        fullTextQuery.setFirstResult(startFrom); //start from the N-th element
        fullTextQuery.setMaxResults(productCount); //return K elements

        return fullTextQuery.list();
    }

}