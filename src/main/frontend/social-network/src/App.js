import React, { useState } from 'react';
import './App.css';
import './socialmedia.scss';
import PostsContainer from './components/PostsContainer';
import Header from './components/Header';
import CreatePost from './components/CreatePost';
import LoginRegisterPopup from './components/LoginRegisterPupUp';

function App() {
  const [isUploadPopupVisible, setUploadPopupVisible] = useState(false);
  const [userData, setUserData] = useState(null); // State to store user data
  const [isLoginRegisterPopupVisible, setLoginRegisterPopupVisible] = useState(true); // Set to true initially

  const handleUploadClick = () => {
    setUploadPopupVisible(true);
  };

  const handleCloseUploadPopup = () => {
    setUploadPopupVisible(false);
  };

  const handleLoginSuccess = (user) => {
    setUserData(user); // Store user data
    setLoginRegisterPopupVisible(false); // Hide the login/register popup after successful login
  };

  const handleLogout = () => {
    setUserData(null); // Clear user data
    setLoginRegisterPopupVisible(true); // Show the login/register popup when logging out
  };

  const handleCloseLoginRegisterPopup = () => {
    setLoginRegisterPopupVisible(false);
  };

  return (
    <div className="appContainer">
      <Header onUploadClick={handleUploadClick} userData={userData} />

      {isLoginRegisterPopupVisible && (
        <>
          <div className="popupBackdrop" onClick={handleCloseLoginRegisterPopup}></div>
          <LoginRegisterPopup onClose={handleCloseLoginRegisterPopup} onLoginSuccess={handleLoginSuccess} />
        </>
      )}

      {isUploadPopupVisible && (
        <CreatePost onClose={handleCloseUploadPopup} />
      )}

      <PostsContainer userData={userData} onLogout={handleLogout} />
    </div>
  );
}

export default App;
