package MovieProject.GUI.Model;

import MovieProject.BE.Categories;
import MovieProject.BLL.CategoriesManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoriesModel {
    private ObservableList<Categories> categoriesToBeViewed;
    private CategoriesManager categoriesManager;

    public CategoriesModel() throws Exception {
        categoriesManager = new CategoriesManager();
        categoriesToBeViewed = FXCollections.observableArrayList();
        categoriesToBeViewed.addAll(categoriesManager.getAllCategories());
    }
    public ObservableList<Categories> getCategoriesToBeViewed() {
        return categoriesToBeViewed;
    }
    public void addCategories(String name) throws Exception {
        categoriesManager.addCategories(name);
    }
    public void deletedCategories(Categories deletedCategories) throws Exception{
        categoriesManager.deletedCategories(deletedCategories);
    }
}