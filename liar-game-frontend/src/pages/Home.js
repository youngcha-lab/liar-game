import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/Home.css";
import "../css/mobile/Home_mobile.css";
import imgAresene from "../img/Arsene.png";

function Home({isMobile}) {
  const navigate = useNavigate();

  const enter = async () => {
    navigate("/enter");
  };

  return (
    <div className="Home_container">
      <div className={isMobile ? "Home_main_mobile" : "Home_main"}>
        <div className={isMobile ? "Home_header_mobile" : "Home_header"}>
          <img src={imgAresene} alt="Arsene" />
          <h1>LIAR GAME</h1>
        </div>
        <div className={isMobile ? "Home_body_mobile" : "Home_body"}>
          <div className={isMobile ? "Home_button_mobile" : "Home_button"}>
            <button onClick={enter}>방 생성하기</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
