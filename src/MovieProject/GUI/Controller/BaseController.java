package MovieProject.GUI.Controller;

import MovieProject.GUI.Model.MovieModel;

public abstract class BaseController {

        private MovieModel model;

        public void setModel(MovieModel model){this.model = model;}

        public MovieModel getModel(){
            return model;
        }

    public abstract void setup();

    }
