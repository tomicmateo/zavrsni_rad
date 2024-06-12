import React, { useState, useEffect } from 'react';
import Comment from './Comment';
import CreateComment from './CreateComment';
import { getComments } from '../api/Comment';

function Post({ post, userData }) {
    const [isLoading, setLoading] = useState(true);
    const [comments, setComments] = useState([]);

    useEffect(() => {
        // Pass post.postId as an argument to getComments

        getComments(post.id)
            .then(data => {
                setLoading(false);
                setComments(data);
            })
            .catch(error => {
                console.log("Error happened:");
                console.log(error);
            });
    }, [post.id]);

    return (
        <div className='post'>
            <div className='userDetails'>
                <img className='userImg' src={post.user.avatar} alt={`User ${post.user.username}`} />
                <div className='user'>{post.user.username}</div>
            </div>

            <div className='postText'>{post.postText}</div>
            <button className='postLikeButton'>Da daaaaa</button>
            <div className='createCommentContainer'><CreateComment post={post} userData={userData} /></div>
            {/* Map over the comments to display them */}
            <div className='commentsContainer'>
                {comments.map((comment, index) => (
                    <Comment key={index} comment={comment} />
                ))}
            </div>
        </div>
    );
}

export default Post;
