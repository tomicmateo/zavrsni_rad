import axios from "axios";
import { apiUrl } from "./Config";

const getUserByEmail = async (email) => {
  try {
    const response = await axios.get(`${apiUrl}/api/profile/user/${email}`);
    console.log(response.data); // Log the entire response data

    return response.data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
};

export { getUserByEmail };
