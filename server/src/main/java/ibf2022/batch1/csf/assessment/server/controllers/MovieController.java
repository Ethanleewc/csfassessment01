package ibf2022.batch1.csf.assessment.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import ibf2022.batch1.csf.assessment.server.services.MoviesService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/api/search", consumes = MediaType.APPLICATION_JSON_VALUE, 
produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	private Logger logger = LoggerFactory.getLogger(MovieController.class);

	private MoviesService moviesSvc = new MoviesService();

	@GetMapping
	public ResponseEntity<String> getMovie(
		@RequestParam(required=true) String query){
		
		JsonArray result = null;
        Optional<List<Review>> or = this.moviesSvc.searchReviews(query);
        List<Review> results = or.get(); 
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Review rv : results)
            arrBuilder.add(rv.toJSON());
        result = arrBuilder.build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());
	}

	@PostMapping(path="/{movieName}")
    public ResponseEntity<String> saveCharacterComment(
        @RequestBody Review review, @PathVariable(required=true) int movieName) {
        logger.info("save comment > : " + movieName);
        Review c= new Review();
        c.setCommentCount(review.getCommentCount());
        c.setCommentCount(movieName);
        Review r = this.moviesSvc.insertComment(c);
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(r.toJSON().toString());
    }

}
