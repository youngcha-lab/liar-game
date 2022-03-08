import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function EnterLeader() {
  const [room_code, setRoomCode] = useState(null);

  const getRoomCode = async() => {
    try {
      const response = await axios.post("http://localhost:8080/api/v1/room");
      setRoomCode(response.data.room_code);
      console.log(response.data.room_code);
    } catch(e) {
      console.log(e);
    }
  };
  useEffect(()=> {
    getRoomCode();
  },[]);

  const isValidName = (name) => {
    if (name === "") {
      return false;
    } else {
      return true;
    }
  }

  const creatRoom = () => {
    const nickname = document.getElementById('nickname').value;
      
    if (isValidName(nickname)) {
      console.log("nickname: " + nickname);      
    } else {
      alert("Please enter user name");
      return false;
    }    
  };

  return (
    <div className="Enter">
      <div className="Enter-body">
        <div className="Enter-content">
          <h1>LIER GAME</h1>
          <input
            id='nickname'
            placeholder="닉네임을 입력하세요"
          />
          <br />
          <Link to={`/room/${room_code}`}><button onClick={creatRoom}>방생성</button></Link>          
        </div>
      </div>
    </div>
  );
}

// EnterLeader.propTypes = {
//   room_code: PropTypes.string.isRequired,
//   nickname: PropTypes.string.isRequired,
// };

export default EnterLeader;
