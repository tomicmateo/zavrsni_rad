import axios from "axios";
import { apiUrl } from "./Config";

const followUser = async (user1Id, user2Id) => {

    console.log("Provjera vrijednosti2 Id", user1Id, user2Id)

    if (!user1Id || !user2Id) {
        console.error("Error: Both user IDs must be provided");
        return;
    }

    try {
        const response = await axios.post(`${apiUrl}/api/follow/createFollow`, {
            followerId: user1Id,
            followeeId: user2Id
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error("Error following the user:", error);
        throw error;
    }
};

export { followUser };
