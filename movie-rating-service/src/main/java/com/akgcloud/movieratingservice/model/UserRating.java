package com.akgcloud.movieratingservice.model;

import java.util.List;

public class UserRating {

    List<Rating> userRating;

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

}
