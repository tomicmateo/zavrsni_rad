import React, { useState } from "react";
import { createPost } from "../api/CreatePost";

function CreatePost({ onClose, userData }) {
  //primauser data
  const [postText, setPostText] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handlePostTextChange = (e) => {
    setPostText(e.target.value);
  };

  const handlePostSubmit = async () => {
    if (!userData || !userData.id) {
      console.error("User data is missing or invalid.");

      return;
    }

    setIsSubmitting(true);

    const createPostRequest = {
      content: postText,
      userId: userData.id, // Ensure userData has an id property
    };

    try {
      const result = await createPost(createPostRequest);
      if (result) {
        console.log("Post created successfully:", result);
        // Optionally clear the text area
        setPostText("");
        // Optionally trigger any callback to refresh posts list
      } else {
        console.log("Failed to create post");
      }
    } catch (error) {
      console.error("Error creating post:", error);
    } finally {
      setIsSubmitting(false);
      onClose(); // Close the popup after posting
    }
  };

  return (
    <div className="popupCreatePost">
      <div className="popupCreatePost-content">
        <h2>Create Post</h2>
        <form onSubmit={(e) => e.preventDefault()}>
          <textarea
            placeholder="Write your post..."
            value={postText}
            onChange={handlePostTextChange}
            rows={4}
          />
          <button
            type="button"
            onClick={handlePostSubmit}
            disabled={isSubmitting}
          >
            {isSubmitting ? "Posting..." : "POST"}
          </button>
        </form>
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
}

export default CreatePost;
