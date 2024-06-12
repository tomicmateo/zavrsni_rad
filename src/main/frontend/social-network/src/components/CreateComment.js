import React, { useState } from 'react';
import { createComment } from '../api/CreateComment';

const CreateComment = ({ post, userData }) => {
    const [comment, setComment] = useState('');
    const handleCommentChange = (event) => {
        setComment(event.target.value);
    };

    const handlePostComment = async () => {
        if (comment.trim() !== '') {
            const commentData = {
                commentText: comment,
                userEmail: userData.email,
                postId: post.id,
            };

            try {
                const response = await createComment(commentData);
                if (response) {
                    console.log('Comment posted successfully');
                } else {
                    console.error('Failed to post comment');
                }
            } catch (error) {
                console.error('Error:', error);
            }
            setComment('');
        }
    };

    return (
        <div className='createComment'>
            <textarea
                className='commentInput'
                placeholder='Write a comment...'
                value={comment}
                onChange={handleCommentChange}
            />
            <button className='postButton' onClick={handlePostComment}>
                Post
            </button>
        </div>
    );
}

export default CreateComment;
