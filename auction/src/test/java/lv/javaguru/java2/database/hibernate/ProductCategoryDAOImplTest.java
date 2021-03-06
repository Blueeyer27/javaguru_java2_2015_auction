package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductCategoryDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Vladislav on 3/6/2015.
 */
public class ProductCategoryDAOImplTest extends SpringIntegrationTest {


    @Autowired
    @Qualifier("ORM_ProductCategoryDAO")
    ProductCategoryDAO productCategoryDAO;
    DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    @After
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    public void testCreate() throws DBException {
        ProductCategory productCategory = createProductCategory("F");

        productCategoryDAO.create(productCategory);

        ProductCategory categoryFromDB = productCategoryDAO.getById(productCategory.getCategoryId());
        assertNotNull(categoryFromDB);
        assertEquals(productCategory.getCategoryId(), categoryFromDB.getCategoryId());
        assertEquals(productCategory.getName(), categoryFromDB.getName());

    }

    @Test
    public void testMultipleCreation() throws DBException {
        ProductCategory productCategory1 = createProductCategory("F1");
        ProductCategory productCategory2 = createProductCategory("F2");
        productCategoryDAO.create(productCategory1);
        productCategoryDAO.create(productCategory2);
        List<ProductCategory> productsCategory = productCategoryDAO.getAll();
        assertEquals(2, productsCategory.size());
    }

    @Test
    public void testGetById() throws DBException {
        ProductCategory productCategory1 = createProductCategory("F1");
        productCategoryDAO.create(productCategory1);

        ProductCategory returnedCategory = productCategoryDAO.getById(productCategory1.getCategoryId());
        assert (productCategory1.getName().equals(returnedCategory.getName()) );
    }

    @Test
    public void testDelete() throws DBException {
        ProductCategory productCategory = createProductCategory("F4");
        productCategoryDAO.create(productCategory);

        productCategoryDAO.delete(productCategory.getCategoryId());
        assert(productCategoryDAO.getAll().size()==0);
    }

    @Test
    public void testUpdate() throws DBException {
        ProductCategory productCategory1 = createProductCategory("F1");
        productCategoryDAO.create(productCategory1);

        productCategory1.setName("F2");

        productCategoryDAO.update(productCategory1);

        assert(productCategory1.getName()=="F2");
    }
    //Feel free to write your own!

    //Customize this one as you like!
    private ProductCategory createProductCategory(String name) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);

        return productCategory;
    }

}
