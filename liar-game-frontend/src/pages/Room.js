import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "../css/Room.css";
import axios from "axios";
import "../img/crown.png";

function Room() {
  const [word, setWord] = useState("");
  const [category, setCategory] = useState("");
  const [leader, setLeader] = useState("");
  const [users, setUsers] = useState("");
  const [userCnt, setUserCnt] = useState(1);
  
  const location = useLocation();
  const navigate = useNavigate();
 
  const host = "http://" + window.location.hostname;
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];

  const checkUser = async () => {
    const response = await axios.get(host + `:8080/api/v1/room/${roomCode}`);
    console.log(response);
    
    setLeader(response.data.room.leader);
    setUsers(response.data.room.users);
    setUserCnt(response.data.room.users.length);
           
    const currentUser = response.data.room.currentUser;
    if (!currentUser || !currentUser.isMember) {
       console.log("current user is not valid");
       navigate("/enter/" + roomCode);
    }
  };

  useEffect(() => {
    checkUser();
    
    const loop = setInterval(()=> {
      checkUser();      
    }, 1000);         
  }, []);

  const randomColor = () => {
    let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    console.log("@@randomColor: " + color);
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
              : <div className="playerThumbnail"></div>   
            }            
            {user}
          </div>
          ))} 
        </div>
        <div className="exit_button">
          <Link to={"/Home"}>
            나가기
          </Link>
        </div>
      </div>
      <div className="contents">
        <div className="circleContainer" onClick={onCircleClick}>
          Start!
        </div>        
      </div>
    </div>
  );
}

export default Room;
