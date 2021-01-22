package com.mycompany.recipeapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date creationDateTime = new Date();

    @Column(name = "dish_type")
    private String dishType;

    @Column(name = "cooking_instruction")
    private String cookingInstruction;

    @Column(name = "num_of_people")
    private Long noOfPeople;

    @ManyToMany
    private List<IngredientEntity> ingredientEntityList;

    public RecipeEntity(){}

    public List<IngredientEntity> getIngredientEntityList() {
        return ingredientEntityList;
    }

    public void setIngredientEntityList(List<IngredientEntity> ingredientEntityList) {
        this.ingredientEntityList = ingredientEntityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getCookingInstruction() {
        return cookingInstruction;
    }

    public void setCookingInstruction(String cookingInstruction) {
        this.cookingInstruction = cookingInstruction;
    }

    public Long getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(Long noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
