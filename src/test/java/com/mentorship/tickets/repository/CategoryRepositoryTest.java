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

    @BeforeEach
    public void setUp() {
        Category category = new Category();
        category.setName("Category");
        category.setId(1);
        testEntityManager.merge(category);
    }

    @AfterEach
    public void tearDown() {
        Category category = testEntityManager.find(Category.class, 1);
        Optional.ofNullable(category).ifPresent(testEntityManager::remove);
    }

    @Test
    void findByNameReturnsCategory() {
        Optional<Category> category = categoryRepository.findByName("Category");
        assertTrue(category.isPresent());
        assertEquals(1, category.get().getId());
        assertEquals("Category", category.get().getName());
    }

    @Test
    void findByNameReturnsNothing() {
        Optional<Category> category = categoryRepository.findByName("Private");
        assertTrue(category.isEmpty());
    }
}