import React, { useState, useEffect } from 'react';
import { getPosts } from '../api/Posts';
import Post from './Post';

const PostsContainer = ({ userData }) => {
    const [isLoading, setLoading] = useState(true);
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        getPosts()
            .then(data => {
                setLoading(false);
                setPosts(data);
            })
            .catch(error => {
                console.log("error happened:");
                console.log(error);
                // TODO: toast error - react toastify
            })
    }, []);

    return (
        <div className='postContainer'>
            {posts.map((post) => (
                <Post post={post} userData={userData} />
            ))}
        </div>
    );
}

export default PostsContainer;