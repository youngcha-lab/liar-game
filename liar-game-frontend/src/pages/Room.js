import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import { Card, CardHeader } from "@mui/material";
import "../css/Room.css";
import axios from "axios";
import imgAresene from "../img/Arsene.png";

function Room() {
  const [word, setWord] = useState("");
  const [category, setCategory] = useState("");
  const [users, setUsers] = useState("");
  const [userCnt, setUserCnt] = useState(1);
  const [isGameStarted, setIsGamestarted] = useState(null);
  const [isLeader, setIsLeader] = useState(false);

  const location = useLocation();
  const navigate = useNavigate();

  const host = "http://" + window.location.hostname;
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const checkUser = async () => {
    const response = await axios.get(host + `:8080/api/v1/room/${roomCode}`);
    console.log("방조회");
    console.log(response);
    const currentUser = response.data.room.currentUser;
    if (!currentUser || !currentUser.isMember) {
      console.log("current user is not valid");
      navigate("/enter/" + roomCode);
    } else {
      setIsLeader(response.data.room.currentUser.isLeader);
      setIsGamestarted(response.data.room.currentGame);
    }
  };

  useEffect(() => {
    checkUser();
  }, []);

  // useEffect(() => {
  //   const loop = setInterval(() => {
  //     checkUser();
  //   }, 10000);
  // }, []);

  // const randomColor = () => {
  //   let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
  //   return color;
  // };

  const onLinkClick = () => {
    const copyText = host + ":3000" + location.pathname;

    navigator.clipboard.writeText(copyText);
    alert("Copied");
    //const tooltip = document.getElementById("myTooltip");
    // tooltip.innerHTML = "Copied!";
  };

  const onCircleClick = async () => {
    try {
      const roomInfo = await axios.get(host + `:8080/api/v1/room/${roomCode}`);
      console.log("게임 시작");
      console.log(roomInfo);
      setIsLeader(roomInfo.data.room.currentUser.isLeader);
      setIsGamestarted(true);

      const response = await axios.post(
        host + `:8080/api/v1/room/${roomCode}/game/start`
      );
      console.log("게임 정보 조회");
      console.log(response);
      setWord(response.data.keyword);
      setCategory(response.data.category);
      return response;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  const clickEndGame = async () => {
    const response = await axios.delete(
      host + `:8080/api/v1/room/${roomCode}/game/end`
    );
    console.log("게임 종료");
    console.log(response);
    setIsGamestarted(false);
  };

  const gameBoard = (
    <>
      <p>{category}</p>
      <div className="word">{word}</div>
    </>
  );

  let content = null;
  if (!isGameStarted) {
    if (isLeader) {
      content = (
        <div className="circleContainer" onClick={onCircleClick}>
          Start!
        </div>
      );
    } else {
      content = (
        <div className="userBeforeGame">
          <img src={imgAresene} alt="Arsene" />
          <p>게임시작 대기중...</p>
        </div>
      );
    }
  } else {
    if (isLeader) {
      content = (
        <div className="gameBoard">
          {gameBoard}
          <button onClick={clickEndGame}>게임 종료 후 Liar 확인</button>
        </div>
      );
    } else {
      content = <div className="gameBoard">{gameBoard}</div>;
    }
  }

  return (
    <div className="main">
      <div className="sidebar">
        <div className="tooltip">
          {/* <br></br>
          <div className="tooltiptext" id="myTooltip">
            Copy to clipboard
          </div> */}
          <div>
            <div className="inviteButton" onClick={onLinkClick}>
              초대하기
            </div>
          </div>
        </div>
        <div className="playerNumber">플레이어 {userCnt} / 10</div>
        <div className="playerContainer">
          <div className="playerItem">
            <div className="playerThumbnail"></div>
            김승욱
          </div>
        </div>
        {/* <Card sx={{ maxWidth: 345, bgcolor: "#C4C4C4", color: "black" }}>
            <CardHeader
              avatar={<Avatar aria-label="recipe"></Avatar>}
              title="김승욱"
            />
        </Card> */}
        {/* {users.map((user) => (
          <Card sx={{ maxWidth: 345, bgcolor: "#C4C4C4", color: "black" }}>
            <CardHeader
              avatar={<Avatar aria-label="recipe"></Avatar>}
              key={user}
              title={user}
            />
          </Card>
        ))}   */}
        <div className="exit_button">
          <Link to={"/Home"}>나가기</Link>
        </div>
      </div>
      <div className="contents">{content}</div>
    </div>
  );
}

export default Room;
