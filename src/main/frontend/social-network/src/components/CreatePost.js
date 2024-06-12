import React, { useState } from 'react';
import { createPost } from '../api/CreatePost';

function CreatePost({ onClose }) {
  const [postText, setPostText] = useState('');

  const handlePostTextChange = (e) => {
    setPostText(e.target.value);
  };

  const handlePostSubmit = () => {
    // Add logic to handle the submission of the post
    console.log('Post text:', postText);

    // Close the popup after posting
    onClose();
  };

  return (
    <div className="popupCreatePost">
      <div className="popupCreatePost-content">
        <h2>Create Post</h2>
        <form>
          <textarea
            placeholder="Write your post..."
            value={postText}
            onChange={handlePostTextChange}
            rows={4}
          />
          <button type="button" onClick={handlePostSubmit}>
            POST
          </button>
        </form>
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
}

export default CreatePost;
