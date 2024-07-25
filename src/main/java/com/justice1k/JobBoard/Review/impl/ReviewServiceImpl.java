package com.justice1k.JobBoard.Review.impl;

import com.justice1k.JobBoard.Company.Company;
import com.justice1k.JobBoard.Company.CompanyService;
import com.justice1k.JobBoard.Review.Review;
import com.justice1k.JobBoard.Review.ReviewController;
import com.justice1k.JobBoard.Review.ReviewRepository;
import com.justice1k.JobBoard.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {

        Company company = companyService.getCompanyById(companyId);
        if (company == null){
            return null;
        }

        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {

        Company company = companyService.getCompanyById(companyId);
        if (company == null){
            return null;
        }

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {

        Company company = companyService.getCompanyById(companyId);
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (company != null && review != null){
            updatedReview.setCompany(company);
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }


        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (company != null && review != null){
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }

        return false;

    }
}
