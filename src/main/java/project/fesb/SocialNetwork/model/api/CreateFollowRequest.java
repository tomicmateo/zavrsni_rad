package project.fesb.SocialNetwork.model.api;

public class CreateFollowRequest {

    private Long followId;
    private Long followerId;
    private Long followeeId;

    public Long getFollowId() {
        return followId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public Long getFolloweeId() {
        return followeeId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public void setFolloweeId(Long followeeId) {
        this.followeeId = followeeId;
    }
}
