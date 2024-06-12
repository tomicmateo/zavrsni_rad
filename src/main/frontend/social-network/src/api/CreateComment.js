import axios from "axios";
import { apiUrl } from "./Config";

const createComment = async (createCommentRequest) => {
    try {
        const response = await axios.post(`${apiUrl}/comment/create`, createCommentRequest);
        console.log(response.data);

        return response.data;
    } catch (error) {
        console.error("Error:", error);

        return null;
    }
};

export { createComment };
