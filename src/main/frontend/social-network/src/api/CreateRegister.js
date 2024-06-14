import axios from 'axios';

export const registerUser = async (formData) => {
    try {
        const response = await axios.post('http://localhost:8080/api/profile/createUser', formData);
        return response.data;
    } catch (error) {
        console.error('Error registering user:', error);
        throw error;
    }
};
