import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import "../css/Enter.css";

function EnterUser() {
  const [userName, setUserName] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const host = "http://" + window.location.hostname + ":8080";
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const submit = async () => {
    if (isValidName(userName)) {
      const userCode = await createUserCode(roomCode);
      console.log("userCode : " + userCode);
      navigate("/room/" + roomCode);
    } else {
      alert("please enter user name");
    }
  };

  const createUserCode = async (roomCode) => {
    try {
      const response = await axios.post(host + "/api/v1/user", {
        room_code: roomCode,
        nickname: userName,
      });
      document.cookie = "lguc=" + response.data.user_code;

      return response.data.user_code;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  const isValidName = (name) => {
    if (!name) {
      return false;
    } else {
      return true;
    }
  };

  const onChange = (event) => setUserName(event.target.value);

  return (
    <div className="Container">
      <div className="Enter_main">
        <div className="Enter_header">
          <h1>LIAR GAME</h1>
        </div>
        <div className="Enter_body">
          <div className="Enter_input">
            <label htmlFor="userName">닉네임</label>
            <input
              id="userName"
              type="text"
              value={userName}
              onChange={onChange}
            />
          </div>
          <div className="Enter_button">
            <button onClick={submit}>방입장</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EnterUser;
