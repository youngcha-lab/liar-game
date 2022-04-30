import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import { Card, CardHeader } from "@mui/material";
import "../css/Room.css";
import axios from "axios";

function Room() {
  const [word, setWord] = useState("Start!");
  const [category, setCategory] = useState("");
  const location = useLocation();
  const navigate = useNavigate();
  const host = "http://" + window.location.hostname;
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const checkUser = async () => {
    const response = await axios.get(host + `:8080/api/v1/room/${roomCode}`);
    const currentUser = response.data.room.currentUser;
    if (!currentUser || !currentUser.isMember) {
      console.log("current user is not valid");
      navigate("/enter/" + roomCode);
    }
  };

  useEffect(() => {
    checkUser();
    //getCategory();
  }, [location]);

  // const randomColor = () => {
  //   let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
  //   return color;
  // };

  const onLinkClick = () => {
    const copyText = host + ":3000" + location.pathname;

    navigator.clipboard.writeText(copyText);

    const tooltip = document.getElementById("myTooltip");
    tooltip.innerHTML = "Copied!";
  };

  const onCircleClick = async () => {
    try {
      const response = await axios.post(
        host + `:8080/api/v1/room/${roomCode}/game/start`
      );
      console.log(response);
      setWord(response.data.keyword);
      setCategory(response.data.category);
      return response;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  // const onMouseDown = () => {
  //   setWord("사과");
  //   setCategory("카테고리: " + categoryTempl);
  // };

  return (
    <div className="main">
      <div className="sidebar">
        <div className="tooltip">
          <br></br>
          <div className="tooltiptext" id="myTooltip">
            Copy to clipboard
          </div>
          <div>
            <button className="invitation" onClick={onLinkClick}>
              초대하기
            </button>
          </div>
        </div>
        <h4>플레이어 1 / 10</h4>
        <Card sx={{ maxWidth: 345, bgcolor: "#C4C4C4", color: "black" }}>
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
      <div className="contents">
        <div className="circleContainer" onClick={onCircleClick}>
          {word}
        </div>
        <div className="category">{category}</div>
      </div>
    </div>
  );
}

export default Room;
