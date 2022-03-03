import React from "react";
import "./css/Enter.css";
import { useState } from "react";
import { Link } from "react-router-dom";

function isValidName(name) {
  if (name === "") {
    return false;
  } else {
    return true;
  }
}

function Enter() {
  const [name, setName] = useState("");
  const onChange = (event) => setName(event.target.value);
  const creatRoom = (event) => {
    if (isValidName(name)) {
      console.log("is Valid Name");
    } else {
      alert("Please enter user name");
    }
  };
  return (
    <div className="Enter">
      <div className="Enter-body">
        <div className="Enter-content">
          <h1>LIER GAME</h1>
          <input
            onChange={onChange}
            value={name}
            placeholder="닉네임을 입력하세요"
          />
          <br />
          <button onClick={creatRoom}>방생성</button>
        </div>
      </div>
    </div>
  );
}

export default Enter;
