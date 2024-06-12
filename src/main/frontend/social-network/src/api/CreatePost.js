import axios from "axios";
import { apiUrl } from "./Config";

const createPost = async (createPostRequest) => {
    try {
        const response = await axios.post(`${apiUrl}/api/post/create`, createPostRequest);
        console.log(response.data);

        return response.data;
    } catch (error) {
        console.error("Error:", error);

        return null;
    }
};

export { createPost };
