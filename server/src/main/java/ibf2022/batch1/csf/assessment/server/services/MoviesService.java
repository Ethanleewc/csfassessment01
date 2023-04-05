package ibf2022.batch1.csf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;

@Service
public class MoviesService {

    @Autowired
    private MovieService movieSvc;

    @Autowired 
    MovieRepository movieRepo;

    public Optional<List<Review>> searchReviews(String query){
        Review cc = null;
        Optional<List<Review>> c  = movieSvc.searchReviews(query);
        cc = (Review) c.get();
        cc.setCommentCount(this.countComments(query));
        return movieSvc.searchReviews(query);
    }

    public int countComments(String title){
        return movieRepo.countComments(title);
    }

    public Review insertComment(Review r){
        return movieRepo.insertComment(r);
    }
    
}
