package participation8.rlovelett.devogellaandroidsqlitefirst;

/**
 * Created by rlovelett on 3/31/2017.
 *
 * A class the creates Comment Objects to be displayed in activity_main.
 */

public class Comment {
    private long id;
    private String comment;
    private String rating;

    /**
     *     returns the id of the comment object
     *
     *     @return long - return the long id of the comment object selected
     */
    public long getId() {
        return id;
    }

    /**
     *     Sets the id of the desired comment object
     *
     *     @param id - the long id of the comment object selected
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *     returns comments on the comment object
     *
     *     @return string - return the comment tied to the comment object selected
     */
    public String getComment() {
        return comment;
    }

    /**
     *     Writes comments on the selected database object
     *
     *     @param comment - the comments made about the comment object selected
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *     Returns the comments made on the comment object.
     *     Will be used by the ArrayAdapter in the ListView
     *
     *     @return string - return the comments the comment object selected
     */
    @Override
    public String toString() {
        return comment + ", Rating: " + rating;
    }

    /**
     * Set the rating of the current Comment object to the user entered rating.
     *
     * @param rating - the rating or a comment object
     */
    public void setRating(String rating) {this.rating = rating; }

    /**
     * Return the rating of the desired Comment object.
     *
     * @return the rating of the desired Comment object.
     */
    public String getRating() { return rating; }
}