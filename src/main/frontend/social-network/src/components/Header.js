import React from 'react';

function Header({ onUploadClick, userData }) {
  return (
    <div className="header">
      <div className="title">SocialMedia</div>
      <div className="username">
        {userData ? (
          <span>@{userData.username}</span>
        ) : null}
      </div>
      <button className="uploadButton" onClick={onUploadClick}>
        Upload post
      </button>
    </div>
  );
}

export default Header;
