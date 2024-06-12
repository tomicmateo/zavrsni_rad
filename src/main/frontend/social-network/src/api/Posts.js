// Posts.js

import axios from "axios";
import { apiUrl } from "./Config";

const getPosts = async () => {
    try {
        const response = await axios.get(`${apiUrl}/api/post/list`);
        return response.data;
    } catch (error) {
        console.error("Error fetching posts:", error);
        throw error; // Re-throw the error for handling in the component
    }
};

export { getPosts };
