package project.fesb.SocialNetwork.model.api;

public class CreateCommentRequest {
    private String commentText;
    private String userEmail;
    private Long postId;

    public String getCommentText() {
        return commentText;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public Long getPostId() {
        return postId;
    }
}
