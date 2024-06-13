import axios from "axios";
import { apiUrl } from "./Config";

const likePost = async (postId, userEmail) => {
    try {
        const response = await axios.post(`${apiUrl}/api/postLike/create`, {
            postId: postId,
            userEmail: userEmail
        });
        return response.data;
    } catch (error) {
        console.error("Error liking the post:", error);
        throw error;
    }
};

export { likePost };
