import React from "react";
import "./css/Home.css";
import { useState } from "react";

function isValidName(name) {
  if (name === "") {
    return false;
  } else {
    return true;
  }
}

function Home() {
  const [name, setName] = useState("");
  const onChange = (event) => setName(event.target.value);
  const creatRoom = (event) => {
    console.log(isValidName(name));
  };
  return (
    <div className="Home">
      <div className="Home-body">
        <div className="Home-content">
          <h1>LIER GAME</h1>
          <input onChange={onChange} value={name} />
          <br />
          <button onClick={creatRoom}>방생성</button>
        </div>
      </div>
    </div>
  );
}

export default Home;
