import React, { useState, useEffect } from "react";
import { useParams, Link, useLocation } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import { Card, CardHeader, createChainedFunction, getListSubheaderUtilityClass } from "@mui/material";
import { teal } from "@mui/material/colors";
import "../css/Room.css";
import axios from "axios";

function Room() {
  const [word, setWord] = useState("시작!");
  const [category, setCategory] = useState("");
  const [users, setUsers] = useState([]);
  const location = useLocation();
  const host = "http://" + window.location.hostname;
  const cookie = document.cookie.split(";");
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];
  let categoryTempl = "과일";
    
  useEffect(() => {
    //getCategory();
    const interval = setInterval(() => {
      getUsers();
    },1000);    
  }, [location]);

  const getUsers = async () => {
    try {
      const response = await axios.get(host + ":8080/api/v1/room/" + roomCode);
      console.log(response.data.users);
      setUsers(response.data.users);

      return response;    
    } catch(e) {
      console.log(e);
    }
    
    return null;
  };

  const randomColor = () => {
    let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    return color;
  };

  const onLinkClick = () => {
    const copyText = host + location.pathname;

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
        <h1>플레이어 {users.length} / 10</h1>
        {users.map((user) => (
            <Card sx={{ maxWidth: 345, bgcolor: teal[500], color: "white" }} key={user}>
            <CardHeader
              avatar={<Avatar aria-label="recipe"></Avatar>}
              title={user}
            />
          </Card>
        ))}

        <div className="exit_button">
          <Link to={"/"}>
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
