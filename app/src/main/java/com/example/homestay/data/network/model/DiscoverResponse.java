package com.example.homestay.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscoverResponse {
    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("data")
    private Discover data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Discover getData() {
        return data;
    }

    public void setData(Discover data) {
        this.data = data;
    }

    public class Discover {
        @Expose
        @SerializedName("finest")
        private String finest;

        @Expose
        @SerializedName("top_deals")
        private String topDeals;

        @Expose
        @SerializedName("popular_place")
        private String popularPlace;

        @Expose
        @SerializedName("promotion")
        private String promotion;

        @Expose
        @SerializedName("sg_title")
        private String SGtitle;

        @Expose
        @SerializedName("hn_title")
        private String HNtitle;

        @Expose
        @SerializedName("dl_title")
        private String DLtitle;

        @Expose
        @SerializedName("vt_title")
        private String VTtitle;

        public String getFinest() {
            return finest;
        }

        public void setFinest(String finest) {
            this.finest = finest;
        }

        public String getTopDeals() {
            return topDeals;
        }

        public void setTopDeals(String topDeals) {
            this.topDeals = topDeals;
        }

        public String getPopularPlace() {
            return popularPlace;
        }

        public void setPopularPlace(String popularPlace) {
            this.popularPlace = popularPlace;
        }

        public String getPromotion() {
            return promotion;
        }

        public void setPromotion(String promotion) {
            this.promotion = promotion;
        }

        public String getSGtitle() {
            return SGtitle;
        }

        public void setSGtitle(String SGtitle) {
            this.SGtitle = SGtitle;
        }

        public String getHNtitle() {
            return HNtitle;
        }

        public void setHNtitle(String HNtitle) {
            this.HNtitle = HNtitle;
        }

        public String getDLtitle() {
            return DLtitle;
        }

        public void setDLtitle(String DLtitle) {
            this.DLtitle = DLtitle;
        }

        public String getVTtitle() {
            return VTtitle;
        }

        public void setVTtitle(String VTtitle) {
            this.VTtitle = VTtitle;
        }
    }

    public class Item {
        @Expose
        @SerializedName("photo")
        private String photo;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("sub_title")
        private String subTitle;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }
    }

    public class House {

        @Expose
        @SerializedName("photo")
        private String photo;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("original_price")
        private String originalPrice;

        @Expose
        @SerializedName("price")
        private String price;

        @Expose
        @SerializedName("discount")
        private int discount;

        @Expose
        @SerializedName("star")
        private int star;

        @Expose
        @SerializedName("num_review")
        private int numReview;

        @Expose
        @SerializedName("type")
        private int type;

        @Expose
        @SerializedName("address")
        private String address;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getNumReview() {
            return numReview;
        }

        public void setNumReview(int numReview) {
            this.numReview = numReview;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public class PopularPlace{
        @Expose
        @SerializedName("photo")
        private String photo;

        @Expose
        @SerializedName("title")
        private String title;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
