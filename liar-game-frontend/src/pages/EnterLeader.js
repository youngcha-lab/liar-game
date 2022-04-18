import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Enter.css";

function EnterLeader() {
  const [nickName, setNickName] = useState("");
  const navigate = useNavigate();
  const host = "http://" + window.location.hostname + ":8080";

  const submit = async () => {
    if (isValidName(nickName)) {
      const roomCode = await createNewRoom();
      const createUserStatus = await createUserCode(roomCode);
      console.log("create userCode result = " + createUserStatus);
      // if creatUserResponse != 204(success) navigate to error page
      navigate("/room/" + roomCode);
    } else {
      alert("please enter user name");
    }
  };

  const createNewRoom = async () => {
    try {
      const response = await axios.post(host + "/api/v1/room");
      return response.data.roomCode;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  const createUserCode = async (roomCode) => {
    try {
      const response = await axios.post(
        host + `/api/v1/room/${roomCode}/user`,
        {
          nickname: nickName,
        }
      );
      return response.status;
    } catch (e) {
      console.log(e);
    }
    return -1;
  };

  const isValidName = (name) => {
    if (!name) {
      return false;
    } else {
      return true;
    }
  };

  const onChange = (event) => setNickName(event.target.value);

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
              value={nickName}
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
