import axios from "axios";
import { apiUrl } from "./Config";

const likePost = async (postId, userEmail) => {
  try {
    const response = await axios.post(`${apiUrl}/api/postLike/create`, {
      postId: postId,
      userEmail: userEmail,
    });
    return response.data;
  } catch (error) {
    console.error("Error liking the post:", error);
    throw error;
  }
};

const hasLikedPost = async (postId, userEmail) => {
  try {
    const response = await axios.get(`${apiUrl}/api/postLike/hasLiked`, {
      params: {
        postId: postId,
        userEmail: userEmail,
      },
    });
    return response.data.hasLiked;
  } catch (error) {
    console.error("Error checking if user has liked the post:", error);
    throw error;
  }
};

export { likePost, hasLikedPost };
