import React, { useState, useEffect } from 'react';
import Comment from './Comment';
import CreateComment from './CreateComment';
import { getComments } from '../api/Comment';
import { likePost } from '../api/LikePost';


function Post({ post, userData }) {
    const [isLoading, setLoading] = useState(true);
    const [comments, setComments] = useState([]);
    const [likes, setLikes] = useState(post.numberOfLikes || 0);
    const [isLiking, setIsLiking] = useState(false);

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

    const handleLike = async () => {
        if (!userData || !userData.email) {
            console.error('User data is missing or invalid.');
            return;
        }

        setIsLiking(true);

        try {
            const result = await likePost(post.id, userData.email); //mislin da vamo triba poslat userData.email a ne id
            if (result) {
                setLikes(likes + 1);
            } else {
                console.log('Failed to like the post');
            }
        } catch (error) {
            console.error('Error liking the post:', error);
        } finally {
            setIsLiking(false);
        }
    };

    return (
        <div className='post'>
            <div className='userDetails'>
                <img className='userImg' src={post.user.avatar} alt={`User ${post.user.username}`} /> 
                <div className='user'>{post.user.username}</div>
            </div>

            <div className='postText'>{post.content}</div>
            <button 
                className='postLikeButton' 
                onClick={handleLike} 
                disabled={isLiking}
            >
                {isLiking ? 'Liking...' : `LIKE (${likes})`}
            </button>
            <div className='createCommentContainer'><CreateComment post={post} userData={userData} /></div>
            {/* Map over the comments to display them */}
            <div className='commentsContainer'>
                {isLoading ? <div>Loading comments...</div> : (
                    comments.map((comment, index) => (
                        <Comment key={index} comment={comment} />
                    ))
                )}
            </div>
        </div>
    );
}

export default Post;
