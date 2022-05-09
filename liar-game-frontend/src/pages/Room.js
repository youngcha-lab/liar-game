import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "../css/Room.css";
import axios from "axios";
import "../img/crown.png";
import imgAresene from "../img/Arsene.png";

function Room() {
  const [word, setWord] = useState("");
  const [category, setCategory] = useState("");
  const [leader, setLeader] = useState("");
  const [users, setUsers] = useState("");
  const [userCnt, setUserCnt] = useState(1);
  const [isGameStarted, setIsGamestarted] = useState(null);
  const [isLeader, setIsLeader] = useState(false);
  const [isLiar, setIsLiar] = useState(false);
 
  const location = useLocation();
  const navigate = useNavigate();
  
  const host = "http://" + window.location.hostname;
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const checkUser = async () => {
    const response = await axios.get(host + `:8080/api/v1/room/${roomCode}`);
    setLeader(response.data.room.leader);
    setUsers(response.data.room.users);
    setUserCnt(response.data.room.users.length);
    
    console.log(response);
    const currentUser = response.data.room.currentUser;
    if (!currentUser || !currentUser.isMember) {
      console.log("current user is not valid");
      navigate("/enter/" + roomCode);
    } else {
      setIsLeader(response.data.room.currentUser.isLeader);
      setIsGamestarted(response.data.room.currentGame);
      setIsLiar(response.data.room.currentUser.isLiar);
    }
  };

  useEffect(() => {
    checkUser();
    
    const loop = setInterval(()=> {
      checkUser();      
    }, 10000);
    
    return () => clearInterval(loop)
  }, []);

  const randomColor = () => {    
    let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    return color;
  };

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
      
      setIsLeader(roomInfo.data.room.currentUser.isLeader);
      setIsGamestarted(true);

      const response = await axios.post(
        host + `:8080/api/v1/room/${roomCode}/game/start`
      );
      
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
    
    setIsGamestarted(false);
    setWord("");
    setCategory("");
  };
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
          {users && users.map((user) => (
          <div className="playerItem" key={user}>            
            {
              (leader === user)  
              ? <div className="leaderThumbnail"></div> 
              : <div className="playerThumbnail" style={{ backgroundColor: randomColor()}}></div>   
            }            
            {user}
          </div>
          ))} 
        </div>
        <div className="exit_button">
          <Link to={"/Home"}>나가기</Link>
        </div>
      </div>
      <div className="contents">{category}
      {
        isGameStarted
        ? (isLiar ? <div className="board"><img src={imgAresene} alt="Arsene" />Liar!</div> : <div className="board">{word}</div>) 
        : (isLeader ? (<div className="circleContainer" onClick={onCircleClick}>Start!</div>) 
                    : (<div className="userBeforeGame"><img src={imgAresene} alt="Arsene" /><p>게임시작 대기중...</p></div>))
      } 
      </div>           
      {
        isGameStarted
        ? (isLeader ? <div className="endGameBtn" onClick={clickEndGame}>게임 종료 후 Liar 확인</div> : <></>)
        : <></>
      }      
    </div>
  );
}

export default Room;
