package com.mentorship.tickets.repository;

import com.mentorship.tickets.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@EntityScan(basePackages = "com.mentorship.tickets.entity")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findByNameReturnsCategory() {
        Optional<Category> category = categoryRepository.findByName("Category");
        if (category.isEmpty()) {
            Category newCategory = new Category();
            newCategory.setName("Category");
            testEntityManager.persist(newCategory);
            testEntityManager.flush();
            category = categoryRepository.findByName("Category");
        }
        assertTrue(category.isPresent());
        assertEquals(1, category.get().getId());
        assertEquals("Category", category.get().getName());
        testEntityManager.remove(category.get());
    }

    @Test
    void findByNameReturnsNothing() {
        Optional<Category> category = categoryRepository.findByName("Private");
        assertTrue(category.isEmpty());
    }
}