import axios from "axios";
import { apiUrl } from "./Config";

const getUserByEmail = async (email) => {
    try {
        const response = await axios.get(`${apiUrl}/api/profile/user/${email}`);
        console.log(response.data); // Log the entire response data

        return response.data;
    } catch (error) {
        // Handle any errors here, e.g., log them or return a default value
        console.error("Error:", error);
        
        return null; // Return null or an appropriate default value
    }
};

export { getUserByEmail };
