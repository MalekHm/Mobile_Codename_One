/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Jasser
 */
public class CategoriePlace {
    private int id ;
    private String nameCategoryPlace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategoryPlace() {
        return nameCategoryPlace;
    }

    public void setNameCategoryPlace(String nameCategoryPlace) {
        this.nameCategoryPlace = nameCategoryPlace;
    }

    public CategoriePlace() {
    }

    @Override
    public String toString() {
        return "CategoriePlace{" + "id=" + id + ", nameCategoryPlace=" + nameCategoryPlace + '}';
    }
    
    
}
