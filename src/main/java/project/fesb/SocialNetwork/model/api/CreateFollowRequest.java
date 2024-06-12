package project.fesb.SocialNetwork.model.api;

public class CreateFollowRequest {

    private String followerEmail;
    private String followeeEmail;

    public String getFollowerEmail() {
        return followerEmail;
    }

    public String getFolloweeEmail() {
        return followeeEmail;
    }

    public void setFollowerEmail(String followerEmail) {
        this.followerEmail = followerEmail;
    }

    public void setFolloweeEmail(String followeeEmail) {
        this.followeeEmail = followeeEmail;
    }
}
