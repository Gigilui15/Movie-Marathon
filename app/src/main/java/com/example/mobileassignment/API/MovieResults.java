package com.example.mobileassignment.API;

import java.io.Serializable;
import java.util.List;

public class MovieResults implements Serializable {
    // Generated using GSONFormatter Extension

    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    /**
     * Get the list of movie results.
     * @return The list of movie results.
     */
    public List<ResultsBean> getResults() {
        return results;
    }

    public static class ResultsBean implements Serializable {

        private String backdrop_path;
        private int id;
        private String title;
        private String original_language;
        private String overview;
        private String poster_path;
        private double popularity;
        private String release_date;
        private double vote_average;

        /**
         * Constructor for ResultsBean.
         *
         * @param backdrop_path     The backdrop path of the movie.
         * @param id                The ID of the movie.
         * @param title             The title of the movie.
         * @param original_language The original language of the movie.
         * @param overview          The overview or description of the movie.
         * @param poster_path       The poster path of the movie.
         * @param popularity        The popularity rating of the movie.
         * @param release_date      The release date of the movie.
         * @param vote_average      The average vote rating of the movie.
         */
        public ResultsBean(String backdrop_path, int id, String title, String original_language, String overview, String poster_path, double popularity, String release_date, double vote_average) {
            this.backdrop_path = backdrop_path;
            this.id = id;
            this.title = title;
            this.original_language = original_language;
            this.overview = overview;
            this.poster_path = poster_path;
            this.popularity = popularity;
            this.release_date = release_date;
            this.vote_average = vote_average;
        }

        /**
         * Constructor for ResultsBean with only the title.
         * @param title The title of the movie.
         */
        public ResultsBean(String title) {
            this.title = title;
        }

        // Adding my own variable
        private boolean inList = false;

        /**
         * Check if the movie is in the user's list.
         * @return True if the movie is in the user's list, false otherwise.
         */
        public boolean getIsInList() {
            return inList;
        }

        /**
         * Set the movie's list status.
         * @param inList The list status of the movie.
         */
        public void setInList(boolean inList) {
            this.inList = inList;
        }

        /**
         * Get the backdrop path of the movie.
         * @return The backdrop path of the movie.
         */
        public String getBackdrop_path() {
            return backdrop_path;
        }

        /**
         * Get the ID of the movie.
         * @return The ID of the movie.
         */
        public int getId() {
            return id;
        }

        /**
         * Get the title of the movie.
         * @return The title of the movie.
         */
        public String getTitle() {
            return title;
        }

        /**
         * Get the original language of the movie.
         * @return The original language of the movie.
         */
        public String getOriginal_language() {
            return original_language;
        }

        /**
         * Get the overview or description of the movie.
         * @return The overview or description of the movie.
         */
        public String getOverview() {
            return overview;
        }

        /**
         * Get the poster path of the movie.
         * @return The poster path of the movie.
         */
        public String getPoster_path() {
            return poster_path;
        }

        /**
         * Set the backdrop path of the movie.
         * @param backdrop_path The backdrop path of the movie.
         */
        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        /**
         * Set the ID of the movie.
         * @param id The ID of the movie.
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Set the title of the movie.
         * @param title The title of the movie.
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Set the original language of the movie.
         * @param original_language The original language of the movie.
         */
        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        /**
         * Set the overview or description of the movie.
         * @param overview The overview or description of the movie.
         */
        public void setOverview(String overview) {
            this.overview = overview;
        }

        /**
         * Set the poster path of the movie.
         * @param poster_path The poster path of the movie.
         */
        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        /**
         * Set the popularity rating of the movie.
         * @param popularity The popularity rating of the movie.
         */
        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        /**
         * Set the release date of the movie.
         * @param release_date The release date of the movie.
         */
        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        /**
         * Set the average vote rating of the movie.
         * @param vote_average The average vote rating of the movie.
         */
        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        /**
         * Get the popularity rating of the movie.
         * @return The popularity rating of the movie.
         */
        public double getPopularity() {
            return popularity;
        }

        /**
         * Get the release date of the movie.
         * @return The release date of the movie.
         */
        public String getRelease_date() {
            return release_date;
        }

        /**
         * Get the average vote rating of the movie.
         * @return The average vote rating of the movie.
         */
        public double getVote_average() {
            return vote_average;
        }
    }
}