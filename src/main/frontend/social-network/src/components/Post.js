import React, { useState, useEffect } from "react";
import Comment from "./Comment";
import CreateComment from "./CreateComment";
import { getComments } from "../api/Comment";
import { likePost, hasLikedPost } from "../api/LikePost";
import { followUser, hasFollowedUser } from "../api/FollowPost";

function Post({ post, userData }) {
  const [isLoading, setLoading] = useState(true);
  const [comments, setComments] = useState([]);
  const [likes, setLikes] = useState(post.numberOfLikes || 0);
  const [isLiking, setIsLiking] = useState(false);
  const [profilePicture, setProfilePicture] = useState(null);
  const [postProfilePicture, setPostProfilePicture] = useState(null);
  const [isFollowing, setIsFollowing] = useState(false);
  const [follows, setFollows] = useState(post.user.numberOfFollowers || 0);

  const [hasLiked, setHasLiked] = useState(false);
  const [hasFollowed, setHasFollowed] = useState(false);

  useEffect(() => {
    const fetchComments = async () => {
      try {
        const data = await getComments(post.id);
        setComments(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching comments:", error);
      }
    };

    fetchComments();
  }, [post.id]);

  useEffect(() => {
    if (userData && userData.profilePicture) {
      const base64String = userData.profilePicture;
      setProfilePicture(`data:image/jpeg;base64,${base64String}`);
    }
  }, [userData]);

  useEffect(() => {
    if (post && post.user.profilePicture) {
      const base64String = post.user.profilePicture;
      setPostProfilePicture(`data:image/jpeg;base64,${base64String}`);
      console.log(post.content + post.user.username + postProfilePicture);
    }
  }, [post]);

  useEffect(() => {
    const checkUserInteractions = async () => {
      if (userData && userData.email) {
        try {
          const liked = await hasLikedPost(post.id, userData.email);
          const followed = await hasFollowedUser(userData.id, post.user.id);
          setHasLiked(liked);
          setHasFollowed(followed);
        } catch (error) {
          console.error("Error checking user interactions:", error);
        }
      }
    };

    checkUserInteractions();
  }, [userData, post.id, post.user.id]);

  const handleLike = async () => {
    if (!userData || !userData.email) {
      console.error("User data is missing or invalid.");
      return;
    }

    if (hasLiked) {
      console.log("You have already liked this post.");
      return;
    }

    setIsLiking(true);

    try {
      const result = await likePost(post.id, userData.email);
      if (result) {
        setLikes(likes + 1);
        setHasLiked(true);
      } else {
        console.log("Failed to like the post");
      }
    } catch (error) {
      console.error("Error liking the post:", error);
    } finally {
      setIsLiking(false);
    }
  };

  const handleFollow = async () => {
    if (!userData || !userData.email) {
      console.error("User data is missing or invalid.");
      return;
    }

    if (hasFollowed) {
      console.log("You have already followed this user.");
      return;
    }

    setIsFollowing(true);

    try {
      const result = await followUser(post.user.id, userData.id);
      if (result) {
        setFollows(follows + 1);
        setHasFollowed(true);
      } else {
        console.log("Failed to follow the user");
      }
    } catch (error) {
      console.error("Error following the user:", error);
    } finally {
      setIsFollowing(false);
    }
  };

  const handleNewComment = async () => {
    setLoading(true);
    try {
      const data = await getComments(post.id);
      setComments(data);
    } catch (error) {
      console.error("Error fetching comments:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="post">
      <div className="userDetails">
        <img
          className="userImg"
          src={postProfilePicture}
          alt={`User ${post.user.username}`}
        />
        <div className="user">{post.user.username}</div>
        <button
          className="followButton"
          onClick={handleFollow}
          disabled={isFollowing || hasFollowed}
        >
          {isFollowing
            ? "Following..."
            : hasFollowed
            ? `FOLLOWED (${follows})`
            : `FOLLOW (${follows})`}
        </button>
      </div>

      <div className="postText">{post.content}</div>
      <button
        className="postLikeButton"
        onClick={handleLike}
        disabled={isLiking || hasLiked}
      >
        {isLiking
          ? "Liking..."
          : hasLiked
          ? `LIKED (${likes})`
          : `LIKE (${likes})`}
      </button>
      <div className="createCommentContainer">
        <CreateComment
          post={post}
          userData={userData}
          onNewComment={handleNewComment}
        />
      </div>
      <div className="commentsContainer">
        {isLoading ? (
          <div>Loading comments...</div>
        ) : (
          comments.map((comment, index) => (
            <Comment key={index} comment={comment} />
          ))
        )}
      </div>
    </div>
  );
}

export default Post;
