import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import axios from 'axios';

function isValidName(name) {
  if (name === "") {
    return false;
  } else {
    return true;
  }
}

function Enter() {
  const [name, setName] = useState("");
  const [data, setData] = useState(null);
  const onChange = (event) => setName(event.target.value);
  const creatRoom = async() => {
    try{
      const response = await axios.get(
        'http://localhost:8080/api/ping',
      )      
    } catch(e) {
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
          <button onClick={creatRoom}>방생성</button>
        </div>
      </div>
    </div>
  );
}

export default Enter;
