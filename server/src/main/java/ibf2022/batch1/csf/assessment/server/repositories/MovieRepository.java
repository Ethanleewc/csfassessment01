package ibf2022.batch1.csf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Review;

@Repository
public class MovieRepository {

	@Autowired
    private MongoTemplate mongoTemplate;

	private static final String COMMENTS_COL = "comments";

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//db.getCollection("COMMENTS_COL").count({ "title": title })
	public int countComments(String title) {
		int count = 0;

		Query query = new Query();
		
		query.addCriteria(Criteria.where("title").is(title));
	
		count = (int) mongoTemplate.count(query, Review.class, COMMENTS_COL);
	
		return count;
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//db.comments.insertOne(r);

	public Review insertComment(Review r) {
        return mongoTemplate.insert(r, COMMENTS_COL);
    }
}
