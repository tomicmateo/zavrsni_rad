import React, { useState, useEffect } from 'react';
import Comment from './Comment';
import CreateComment from './CreateComment';
import { getComments } from '../api/Comment';
import { likePost } from '../api/LikePost';
import { followUser } from '../api/FollowPost';

function Post({ post, userData }) {
    const [isLoading, setLoading] = useState(true);
    const [comments, setComments] = useState([]);
    const [likes, setLikes] = useState(post.numberOfLikes || 0);
    const [isLiking, setIsLiking] = useState(false);
    const [profilePicture, setProfilePicture] = useState(null);
    const [isFollowing, setIsFollowing] = useState(false);
    const [follows, setFollows] = useState(post.user.numberOfFollowers || 0);

    useEffect(() => {
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

    // Convert profilePicture from Base64 to data URL
    useEffect(() => {
        if (userData && userData.profilePicture) {
            const base64String = userData.profilePicture;
            setProfilePicture(`data:image/jpeg;base64,${base64String}`);
        }
    }, [userData]);

    

    const handleLike = async () => {
        if (!userData || !userData.email) {
            console.error('User data is missing or invalid.');
            return;
        }

        setIsLiking(true);

        try {
            const result = await likePost(post.id, userData.email);
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

    const handleFollow = async () => {
        if (!userData || !userData.email) {
            console.error('User data is missing or invalid.');
            return;
        }

        setIsFollowing(true);
        console.log("Provjera vrijednosti Id", post.user.id, userData.id)
        try {
            const result = await followUser(post.user.id, userData.id);
            if (result) {
                setFollows(follows + 1);
            } else {
                console.log('Failed to follow the user');
            }
        } catch (error) {
            console.error('Error following the user:', error);
        } finally {
            setIsFollowing(false);
        }
    };

    return (
        <div className='post'>
            <div className='userDetails'>
                <img 
                    className='userImg' 
                    src={profilePicture || post.user.avatar} 
                    alt={`User ${post.user.username}`} 
                />
                <div className='user'>{post.user.username}</div>
                <button 
                    className='followButton'
                    onClick={handleFollow}
                    disabled={isFollowing}
                    >
                        {isFollowing? 'Following...' : `FOLLOW (${follows})`}
                </button>
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
