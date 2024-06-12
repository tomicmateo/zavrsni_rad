import axios from "axios";
import { apiUrl } from "./Config";

const getComments = async (postId) => {
    try {
        // Include the postId in the URL
        const response = await axios.get(`${apiUrl}/comment/list?postId=${postId}`);
        return response.data;
    } catch (error) {
        // Handle any errors here
        console.error("Error fetching comments:", error);
        throw error;
    }
};

export { getComments };