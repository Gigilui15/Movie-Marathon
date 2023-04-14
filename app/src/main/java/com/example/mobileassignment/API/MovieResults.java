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

        public void setId(int id) {
            this.id = id;
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
