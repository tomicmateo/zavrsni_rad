import React from 'react';
// import './Comment.css'; // Import the CSS file for the component

const Comment = ({ comment }) => {
    return (
        <div className='commentContainer'>
            <div className='userComment'>{comment.user.username}: </div>
            <div className='commentText'>{comment.content}</div>
        </div>
    );
}

export default Comment;