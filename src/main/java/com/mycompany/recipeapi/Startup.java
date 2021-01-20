package com.mycompany.recipeapi;

import com.mycompany.recipeapi.entity.Ingredient;
import com.mycompany.recipeapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Startup implements CommandLineRunner {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void run(String... args) throws Exception {
        Ingredient ingredient = new Ingredient("Basmati Rice", "A fragrant, long grain rice cultivated in India");
        ingredientRepository.save(ingredient);

        ingredient = new Ingredient("Chickpeas", "A high-protein, high-fiber legume that is a staple in Indian cuisine");
        ingredientRepository.save(ingredient);

        ingredient = new Ingredient("Chapati Flour", "A finely-ground, whole grain flour made from durum wheat.");
        ingredientRepository.save(ingredient);

        ingredient = new Ingredient("Chickpea Flour", "Called besan in Bengali, this high-protein, gluten-free flour made by grinding dried roasted or raw chickpeas is a staple in Indian cooking.");
        ingredientRepository.save(ingredient);

        ingredient = new Ingredient("Jaggery", "Made from unrefined palm sugar or cane sugar and sold in hard discs or chunks");
        ingredientRepository.save(ingredient);

        ingredient = new Ingredient("Kidney Beans", "A large, dark red legume, called rājmā in Hindi and Punjabi.");
        ingredientRepository.save(ingredient);

        ingredient = new Ingredient("Rice Flour", "Finely milled white rice.");
        ingredientRepository.save(ingredient);
    }
}
