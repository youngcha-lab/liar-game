import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import { Card, CardHeader } from "@mui/material";
import { teal } from "@mui/material/colors";
import { Cookies } from "react-cookie";
import "../css/Room.css";
import axios from "axios";

function Room() {
  const [word, setWord] = useState("시작!");
  const [category, setCategory] = useState("");
  const location = useLocation();
  const navigate = useNavigate();
  let categoryTempl = "과일";
  const host = "http://" + window.location.hostname;
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const checkUser = async () => {
    const response = await axios.get(host + ":8080/api/v1/room/" + roomCode);
    console.log(response);
  };
  const checkCookie = () => {
    const cookie = new Cookies();
    console.log("cookie = " + cookie.get("lguc"));
    if (!cookie.get("lguc")) {
      console.log("cookie is empty");
      navigate("/enter/" + roomCode);
    }
  };

  useEffect(() => {
    checkUser();
    //getCategory();
  }, [location]);

  const randomColor = () => {
    let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    return color;
  };

  const onLinkClick = () => {
    const copyText = host + ":3000" + location.pathname;

    navigator.clipboard.writeText(copyText);

    const tooltip = document.getElementById("myTooltip");
    tooltip.innerHTML = "Copied!";
  };

  const onCircleClick = () => {
    setWord("시작!");
    setCategory("");
  };

  const onMouseDown = () => {
    setWord("사과");
    setCategory("카테고리: " + categoryTempl);
  };

  return (
    <div className="room">
      <div className="nav">
        <h1>플레이어 1 / 10</h1>
        <Card sx={{ maxWidth: 345, bgcolor: teal[500], color: "white" }}>
          <CardHeader
            avatar={<Avatar aria-label="recipe"></Avatar>}
            title="김승욱"
          />
        </Card>

        <div className="exit_button">
          <Link to={"/Home"}>
            <button>나가기</button>
          </Link>
        </div>
      </div>
      <div className="section">
        <div className="link_button">
          <div className="tooltip">
            <div className="tooltiptext" id="myTooltip">
              Copy to clipboard
            </div>
            <br></br>
            <button onClick={onLinkClick}>링크복사</button>
          </div>
        </div>

        <div
          className="circle"
          onClick={onCircleClick}
          onMouseDown={onMouseDown}
        >
          {word}
        </div>
        <div className="category">{category}</div>
      </div>
    </div>
  );
}

export default Room;