package MovieProject.GUI.Controller;

import MovieProject.GUI.Model.CategoriesModel;
import MovieProject.GUI.Model.MovieModel;

public abstract class BaseController {

        private MovieModel model;
        private CategoriesModel categoriesModel;

        public void setModel(MovieModel model){this.model = model;}

        public void setCategoriesModel(CategoriesModel categoriesModel) {
            this.categoriesModel = categoriesModel;
        }

        public MovieModel getModel(){
            return model;
        }

    public abstract void setup();

    }
