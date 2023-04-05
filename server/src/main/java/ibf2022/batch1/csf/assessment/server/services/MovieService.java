package ibf2022.batch1.csf.assessment.server.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;

@Service
public class MovieService {

	//@Value("${csfassessment.nytimes.api.url}")
	private String nytimesapiUrl = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	//@Value("${csfassessment.nytimes.api.key}")
	private String nytimesPublicApiKey = "dMyagnJvYOHqCGYpOif4F7jYiGFL6nbZ";

	public Optional<List<Review>> searchReviews(String query) {
		ResponseEntity<String> resp = null;
		List<Review> c = null;
		System.out.println(nytimesPublicApiKey);
		String nytimesMovieApiUrl = UriComponentsBuilder
									  .fromUriString(nytimesapiUrl)
                                      .queryParam("query", query)
                                      .queryParam("api-key", nytimesPublicApiKey.trim())
                                      .toUriString();
		System.out.println(nytimesMovieApiUrl);
		RestTemplate template = new RestTemplate();
		resp = template.getForEntity(nytimesMovieApiUrl,String.class);
		System.out.println(resp);
		try {
            c = Review.create(resp.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(c);
        if(c != null)
            return Optional.of(c);                        
        return Optional.empty();
	}

	

}
