import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function isValidName(name) {
  if (name === "") {
    return false;
  } else {
    return true;
  }
}

function EnterUser() {
  const [name, setName] = useState("");
  const [data, setData] = useState(null);

  const onChange = (event) => setName(event.target.value);
  const creatRoom = async () => {
    try {
      if (isValidName(name)) {
        console.log("is Valid Name");
        const response = await axios.post("http://localhost:8080/api/v1/room");
        setData(response.data);
        console.log(response.data);
      } else {
        alert("Please enter user name");
      }
    } catch (e) {
      console.log(e);
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
          <button onClick={creatRoom}>방입장</button>
        </div>
      </div>
    </div>
  );
}

export default EnterUser;
