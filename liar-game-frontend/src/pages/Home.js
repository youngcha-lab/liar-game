import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/Home.css";

function Home() {
  const navigate = useNavigate();

  const enter = async () => {
    navigate("/enter");
  };

  return (
    <div className="Container">
      <div className="Home_main">
        <div className="Home_header">
          <h1>LIAR GAME</h1>
        </div>
        <div className="Home_body">
          <div className="Home_button">
            <button onClick={enter}>방 생성하기</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
