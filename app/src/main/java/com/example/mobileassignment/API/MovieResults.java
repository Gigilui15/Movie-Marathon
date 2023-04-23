package com.example.mobileassignment.API;

import java.io.Serializable;
import java.util.List;

public class MovieResults implements Serializable {
    //Generated using GSONFormatter Extension

    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public static class ResultsBean implements Serializable{

        private String backdrop_path;
        private int id;
        private String title;
        private String original_language;
        private String overview;
        private String poster_path;
        private double popularity;
        private String release_date;
        private double vote_average;

        public ResultsBean(String backdrop_path, int id, String title, String original_language, String overview, String poster_path, double popularity, String release_date, double vote_average, boolean inList) {
            this.backdrop_path = backdrop_path;
            this.id = id;
            this.title = title;
            this.original_language = original_language;
            this.overview = overview;
            this.poster_path = poster_path;
            this.popularity = popularity;
            this.release_date = release_date;
            this.vote_average = vote_average;
            this.inList = inList;
        }

        //Adding my own variable
        private boolean inList = false;

        public boolean getIsInList() {
            return inList;
        }

        public void setInList(boolean inList) {
            this.inList = inList;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public String getOverview() {
            return overview;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public double getPopularity() {
            return popularity;
        }

        public String getRelease_date() {
            return release_date;
        }

        public double getVote_average() {
            return vote_average;
        }

    }
}
