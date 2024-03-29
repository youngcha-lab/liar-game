import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import "../css/Enter.css";
import "../css/mobile/Enter_mobile.css";
import imgAresene from "../img/Arsene.png";

function EnterUser({ isMobile }) {
  const [nickName, setNickName] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const host = "http://" + window.location.hostname + ":8080";
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const submit = async () => {
    if (isValidName(nickName)) {
      const enterStatus = await enterRoom();
      console.log("enterRoom result = " + enterStatus);
      navigate("/room/" + roomCode);
    } else {
      alert("please enter user name");
    }
  };

  const enterRoom = async () => {
    try {
      const response = await axios.post(
        host + `/api/v1/room/${roomCode}/user/join`,
        {
          nickname: nickName,
        }
      );
      console.log(response);
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
    <div className="Enter_container">
      <div className={isMobile ? "Enter_main_mobile" : "Enter_main"}>
        <div className={isMobile ? "Enter_header_mobile" : "Enter_header"}>
          <img src={imgAresene} alt="Arsene" />
          <h1>LIAR GAME</h1>
        </div>
        <div className={isMobile ? "Enter_body_mobile" : "Enter_body"}>
          <label htmlFor="userName">닉네임</label>
          <div className={isMobile ? "Enter_input_mobile" : "Enter_input"}>
            <input
              id="userName"
              type="text"
              value={nickName}
              onChange={onChange}
            />
          </div>
          <div className={isMobile ? "Enter_button_mobile" : "Enter_button"}>
            <button onClick={submit}>방입장</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EnterUser;
