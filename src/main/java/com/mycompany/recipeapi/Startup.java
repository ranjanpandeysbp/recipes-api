package com.mycompany.recipeapi;

import com.mycompany.recipeapi.entity.IngredientEntity;
import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.repository.IngredientRepository;
import com.mycompany.recipeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Startup implements CommandLineRunner {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void run(String... args) throws Exception {

        IngredientEntity ingredientEntity = new IngredientEntity("Basmati Rice", "A fragrant, long grain rice cultivated in India");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Chickpeas", "A high-protein, high-fiber legume that is a staple in Indian cuisine");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Chapati Flour", "A finely-ground, whole grain flour made from durum wheat.");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Chickpea Flour", "Called besan in Bengali, this high-protein, gluten-free flour made by grinding dried roasted or raw chickpeas is a staple in Indian cooking.");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Jaggery", "Made from unrefined palm sugar or cane sugar and sold in hard discs or chunks");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Kidney Beans", "A large, dark red legume, called rājmā in Hindi and Punjabi.");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Rice Flour", "Finely milled white rice.");
        ingredientRepository.save(ingredientEntity);

        ingredientEntity = new IngredientEntity("Ginger Garlic Paste", "Finely grind paste of fresh ginger and garlic");
        ingredientRepository.save(ingredientEntity);
    }
}
