package edu.mum.cs.salmans.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @NotBlank(message = "Please provide your experience")
    @Column(nullable = false)
    private String reviewDescription;

    @NotBlank(message = "Please provide your rating")
    @Column(nullable = false)
    private String reviewRating;

    @Column(nullable = false)
    private LocalDate dateOfReview;
    //should be set dynamically before inserting to DB

    @OneToOne
    @JoinColumn(name = "user_fk")
    private User customer;


    public Review() {
    }

    public Review(String reviewDescription, String reviewRating, LocalDate dateOfReview, User customer) {
        this.reviewDescription = reviewDescription;
        this.reviewRating = reviewRating;
        this.dateOfReview = dateOfReview;
        this.customer = customer;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public LocalDate getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(LocalDate dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
