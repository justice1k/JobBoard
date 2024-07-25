package com.justice1k.JobBoard.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);

        if (reviews != null){
            return new ResponseEntity<> (reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>(reviews, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){

        if (reviewService.addReview(companyId, review))
            return new ResponseEntity<>("Review added", HttpStatus.OK);
        return new ResponseEntity<>("Review not added" , HttpStatus.NOT_FOUND);

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId, reviewId);

        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public  ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                                 @PathVariable Long reviewId,
                                                 @RequestBody Review updatedReview ){

        if (reviewService.updateReview(companyId, reviewId, updatedReview))
            return new ResponseEntity<>("Review updated", HttpStatus.OK);

        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        if (reviewService.deleteReview(companyId, reviewId))
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }

}
