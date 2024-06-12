import axios from "axios";
import { apiUrl } from "./Config";

const getPosts = async () =>
    await axios.get(`${apiUrl}/post/list`)
        .then(response => response.data);

export { getPosts };