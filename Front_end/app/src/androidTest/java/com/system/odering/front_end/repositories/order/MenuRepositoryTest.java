package com.system.odering.front_end.repositories.order;

import android.content.Context;
import android.test.AndroidTestCase;

import com.system.odering.front_end.domain.order.Impl.Category;
import com.system.odering.front_end.domain.order.Impl.FoodItem;
import com.system.odering.front_end.domain.order.Impl.Menu;
import com.system.odering.front_end.repositories.order.Impl.MenuRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by cfebruary on 2016/12/14.
 */
public class MenuRepositoryTest extends AndroidTestCase {
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Context context = getContext();
        IMenuRepository menuRepository = new MenuRepositoryImpl(context);

        // CREATE
        Category category = new Category.Builder()
                .categoryName("Category")
                .build();
        FoodItem foodItem = new FoodItem.Builder()
                .name("Food name")
                .build();
        Menu menu = new Menu.Builder()
                .id(Long.valueOf("789"))
                .category(category)
                .foodItem(foodItem)
                .build();

        Menu insertedEntity = menuRepository.save(menu);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Menu> menuSet = menuRepository.findAll();
        Assert.assertTrue(menuSet.size() > 0);


        // READ ENTITY
        Menu entity = menuRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Menu updateEntity = new Menu.Builder()
                .copy(menu.getId(), menu.getCategory(), menu.getFoodItem())
                .id(Long.valueOf("9874"))
                .build();
        menuRepository.update(updateEntity);
        Menu newEntity = menuRepository.findById(id);
        Assert.assertEquals("9874", newEntity.getId());

        // DELETE ENTITY
        menuRepository.delete(updateEntity);
        Menu deletedEntity = menuRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        menuRepository.deleteAll();
        Set<Menu> deletedUsers = menuRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);


    }
}