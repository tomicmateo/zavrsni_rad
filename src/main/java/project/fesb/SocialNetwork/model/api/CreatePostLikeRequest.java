package project.fesb.SocialNetwork.model.api;

public class CreatePostLikeRequest {
    private String userEmail;
    private Long postId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
