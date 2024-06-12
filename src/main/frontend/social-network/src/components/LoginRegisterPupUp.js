import React, { useState } from 'react';
import axios from 'axios';
import { getUserByEmail } from '../api/LogIn';
import { registerUser } from "../api/CreateRegister"; // Import the registerUser function

function LoginRegisterPopup({ onClose, onLoginSuccess }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [formType, setFormType] = useState('login');
    const [username, setUsername] = useState(''); // Username state
    const [bio, setBio] = useState(''); // Bio state
    const [profilePicture, setProfilePicture] = useState(null); // Profile picture state
    const [userData, setUserData] = useState(null); // User data state
    const [isLoggedIn, setIsLoggedIn] = useState(false); // Authentication status state

    const handleLogin = async () => {
        try {
            const response = await getUserByEmail(email);
            if (response) {
                if (response.password === password) {
                    console.log('Login successful');
                    setUserData(response);
                    setIsLoggedIn(true);
                    onLoginSuccess(response);
                    onClose();
                } else {
                    console.error('Password is incorrect');
                }
            } else {
                console.error('Email not found');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };


        const handleRegister = async () => {
        try {
            const formData = new FormData();
            formData.append('username', username);
            formData.append('bio', bio);
            formData.append('email', email);
            formData.append('password', password);
    
            if (profilePicture) {
                // Convert profile picture to Blob
                const profilePictureBlob = await fetch(URL.createObjectURL(profilePicture)).then(res => res.blob());
                formData.append('profilePicture', profilePictureBlob);
            }
    
            const response = await registerUser(formData);
    
            if (response) {
                console.log('Registration successful');
                onClose();
            } else {
                console.error('Registration failed');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const toggleForm = (type) => {
        setFormType(type);
    };

    const handleProfilePictureChange = (e) => {
        setProfilePicture(e.target.files[0]);
    };

    return (
        <div className="popup">
            <div className="popup-content">
                {isLoggedIn ? (
                    <h2>Welcome, {userData.username}!</h2>
                ) : (
                    <h2>{formType === 'login' ? 'Login' : 'Register'}</h2>
                )}
                <form>
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="Enter your email"
                            disabled={isLoggedIn}
                        />
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Enter your password"
                            disabled={isLoggedIn}
                        />
                    </div>
                    {formType === 'register' && (
                        <>
                            <div className="form-group">
                                <label>Username:</label>
                                <input
                                    type="text"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    placeholder="Enter your username"
                                />
                            </div>
                            <div className="form-group">
                                <label>Bio:</label>
                                <textarea
                                    value={bio}
                                    onChange={(e) => setBio(e.target.value)}
                                    placeholder="Tell us about yourself"
                                />
                            </div>
                            <div className="form-group">
                                <label>Profile Picture:</label>
                                <input
                                    type="file"
                                    onChange={handleProfilePictureChange}
                                />
                            </div>
                        </>
                    )}
                    <div className="form-actions">
                        {isLoggedIn ? (
                            <>
                                <button type="button" onClick={() => setIsLoggedIn(false)}>
                                    Logout
                                </button>
                            </>
                        ) : (
                            <>
                                {formType === 'login' ? (
                                    <>
                                        <button type="button" onClick={handleLogin}>
                                            Login
                                        </button>
                                        <p>
                                            Don't have an account?{' '}
                                            <span onClick={() => toggleForm('register')}>Register</span>
                                        </p>
                                    </>
                                ) : (
                                    <>
                                        <button type="button" onClick={handleRegister}>
                                            Register
                                        </button>
                                        <p>
                                            Already have an account?{' '}
                                            <span onClick={() => toggleForm('login')}>Login</span>
                                        </p>
                                    </>
                                )}
                            </>
                        )}
                    </div>
                </form>
            </div>
        </div>
    );
}

export default LoginRegisterPopup;