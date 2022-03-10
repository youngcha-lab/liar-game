import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Enter.css";

function EnterLeader() {
  const [userName, setUserName] = useState("");
  const navigate = useNavigate();

  const submit = async () => {
    if (isValidName(userName)) {
      const roomCode = await createNewRoom();
      console.log("var roomCode = " + roomCode);
      const userCode = await createUserCode(roomCode);
      navigate("/room/" + roomCode);
    } else {
      alert("please enter user name");
    }
  };

  const createNewRoom = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/v1/room");
      console.log("roomCode from server is " + response.data.room_code);
      return response.data.room_code;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  const createUserCode = async (roomCode) => {
    try {
      console.log(roomCode);
      console.log(userName);

      const response = await axios.post("http://localhost:8080/api/v1/user", {
        room_code: roomCode,
        nickname: userName,
      });
      console.log(response);
      return response;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  const isValidName = (name) => {
    if (name === "") {
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
          <h1>LIER GAME</h1>
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
            <button onClick={submit}>방생성</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EnterLeader;
