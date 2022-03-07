import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

axios.defaults.withCredentials = true;

function isValidName(name) {
  if (name === "") {
    return false;
  } else {
    return true;
  }
}

function Enter() {
  const [data, setData] = useState("");
  const [name, setName] = useState("");
  const onChange = (event) => setName(event.target.value);
  const creatRoom = (event) => {
    if (isValidName(name)) {
      console.log("is Valid Name");
    } else {
      alert("Please enter user name");
    }
  };
  const callApi = () => {
    const response = axios
      .get("http://localhost:8080/api/ping")
      .then((response) => {
        setData(response.data);
      });
    console.log(data);
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
          <button onClick={callApi}>방생성</button>
        </div>
      </div>
    </div>
  );
}

export default Enter;
