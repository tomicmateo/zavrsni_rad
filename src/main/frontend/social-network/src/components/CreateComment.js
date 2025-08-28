import React, { useState } from "react";
import { createComment } from "../api/CreateComment";

const CreateComment = ({ post, userData, onNewComment }) => {
  const [comment, setComment] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleCommentChange = (event) => {
    setComment(event.target.value);
  };

  const handlePostComment = async () => {
    if (comment.trim() !== "") {
      const commentData = {
        commentText: comment,
        userEmail: userData.email,
        postId: post.id,
      };

      setIsSubmitting(true);

      try {
        const response = await createComment(commentData);
        if (response) {
          console.log("Comment posted successfully");
          onNewComment(); // Notify the parent component
          setComment(""); // Clear the input
        } else {
          console.error("Failed to post comment");
        }
      } catch (error) {
        console.error("Error:", error);
      } finally {
        setIsSubmitting(false);
      }
    }
  };
  return (
    <div className="createComment">
      <textarea
        className="commentInput"
        placeholder="Write a comment..."
        value={comment}
        onChange={handleCommentChange}
        disabled={isSubmitting}
      />
      <button
        className="postButton"
        onClick={handlePostComment}
        disabled={isSubmitting}
      >
        {isSubmitting ? "Posting..." : "Post"}
      </button>
    </div>
  );
};
export default CreateComment;
