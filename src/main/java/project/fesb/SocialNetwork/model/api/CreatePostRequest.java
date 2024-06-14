package project.fesb.SocialNetwork.model.api;

public class CreatePostRequest {

    private Long userId;
    private String content;

    public String getContent() {
        return content;
    }
    public Long getUserId() {
        return userId;
    }
}
