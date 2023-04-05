package ibf2022.batch1.csf.assessment.server.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short 
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }


	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review createJson (JsonObject o) {
		Review c = new Review();
		JsonObject t = o.getJsonObject("link");
        String url = t.getString("url");
		//JsonObject y = o.getJsonObject("multimedia");
       // String src = y.getString("src");
        
        c.title = o.getString("display_title");
        c.rating = o.getString("mpaa_rating");
        c.byline = o.getString("byline");
		c.headline = o.getString("headline");
		c.summary = o.getString("summary_short");
		c.reviewURL = url;
		c.image = anyImage("multimedia", o);
        return c;
	}

	private static String anyImage(String fn, JsonObject obj){
		if (!obj.isNull("multimedia")) {
			return obj.get("multimedia").asJsonObject().getString("src");
		}
		return "noimage";
	}

	public static List<Review> create(String json) throws IOException {
        List<Review> rev = new LinkedList<>();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            if(o.getJsonArray("results") != null)
                rev = o.getJsonArray("results").stream()
                    .map(v-> (JsonObject)v)
                    .map(v-> Review.createJson(v))
                    .toList(); 
        }
        return rev;
    }

	public static Review create(Document d) {
        Review c = new Review();
        c.setCommentCount(d.getInteger("title"));
        return c;
    }

	public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("display_title", getTitle())
                .add("mpaa_rating", getRating())
                .add("byline", getByline())
                .add("headline", getHeadline())
				.add("summary_short", getSummary())
				.add("link.url", getReviewURL())
				.add("multimedia.src", getHeadline())
				.add("comment_count", getCommentCount())
                .build();
    }

}
