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
  const [isGameStarted, setIsGamestarted] = useState("before");
  const [isLeader, setIsLeader] = useState(null);
  const [isLiar, setIsLiar] = useState(null);
  const [liar, setLiar] = useState("");
  const [isHide, setIsHide] = useState(true);
  const location = useLocation();
  const navigate = useNavigate();

  const host = "http://" + window.location.hostname;
  const url = location.pathname.split("/");
  const roomCode = url[url.length - 1];
  
  const getRoom = async () => {
    return await axios.get(host + `:8080/api/v1/room/${roomCode}`);
  };

  const checkUser = async () => {
    const response = await getRoom();
    const currentUser = response.data.room.currentUser;
    if (!currentUser || !currentUser.isMember) {
      console.log("current user is not valid");
      navigate("/enter/" + roomCode);
    } else {
      setUsers(response.data.room.users);
      setUserCnt(response.data.room.users.length);
      setIsLeader(response.data.room.currentUser.isLeader);
      setLeader(response.data.room.leader);
    }
  };

  const refreshRoom = async () => {
    const response = await getRoom();
    const currentGame = response.data.room.currentGame;
    const lastGame = response.data.room.lastGame;
        
    setUsers(response.data.room.users);
    setUserCnt(response.data.room.users.length);
    setLeader(response.data.room.leader);
    setIsLiar(response.data.room.currentUser.isLiar);

    if (!currentGame && lastGame) {
      setIsGamestarted("after");
      // setTimeout(() => {
      //   setIsGamestarted("before");
      // }, 3000);
      setLiar(response.data.room.lastGame.liar);      
    } else if(!currentGame) {
      setIsGamestarted("before");
      setCategory("");
      setWord("");
    } else {
      setIsGamestarted("ing");
      setCategory(currentGame.category);
      setWord(currentGame.keyword);
    }   
  };

  useEffect(() => {
    checkUser();
            
    const loop = setInterval(() => {
      refreshRoom();
    }, 500);

    return () => clearInterval(loop);
  }, []);

  const randomColor = () => {
    let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    return color;
  };

  const onLinkClick = () => {
    const copyText = host + ":3000" + location.pathname;

    navigator.clipboard.writeText(copyText);
    alert("Copied");   
  };

  const onCircleClick = async () => {
    try {
      await axios.post(host + `:8080/api/v1/room/${roomCode}/game/start`)
      .catch((err) => {
        console.log(err);
      });     
    } catch (e) {
      console.log(e);
    }    
  };

  const clickEndGame = async () => {
    try{
      await axios.delete(host + `:8080/api/v1/room/${roomCode}/game/end`)
      .catch((err) => {
        console.log(err);
      });            
    } catch (e) {
      console.log(e);
    }            
  };

  const wordBoxMounseDown = () => {
    setIsHide(false);
  };

  const wordBoxMounseUp = () => {
    setIsHide(true);
  }; 

  const gameBoard = (
    <>
      <p>{category}</p>
      {isHide ? 
      (
        <div
          className="blindWordBox"
          onMouseDown={wordBoxMounseDown}
          onMouseUp={wordBoxMounseUp}
        >
          Tap!
        </div>
      ) : 
      isLiar ?
      (        
        <div
          className="openWordBox"
          onMouseDown={wordBoxMounseDown}
          onMouseUp={wordBoxMounseUp}
        >
          <img src={imgAresene} alt="Arsene" /> Liar!
        </div>
      ) : 
      (
        <div
          className="openWordBox"
          onMouseDown={wordBoxMounseDown}
          onMouseUp={wordBoxMounseUp}
        >
          {word}
        </div>
      )
      }
    </>
  );
  
  let content = null;  
  if (isGameStarted === "before") {
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
  } else if(isGameStarted === "ing"){
    if (isLeader) {
      content = (
        <div className="gameBoard">
          {gameBoard}
          <div className="endGameBtn" onClick={clickEndGame}>
            게임 종료 후 Liar 확인
          </div>
        </div>
      );
    } else {
      content = <div className="gameBoard">{gameBoard}</div>;
    }
  } else {
    content = <div className="userBeforeGame"><img src={imgAresene} alt="Arsene" /><p>{liar}</p></div>
  }

  return (
    <div className="main">
      <div className="sidebar">
        <div className="tooltip">
          <div>
            <div className="inviteButton" onClick={onLinkClick}>
              초대하기
            </div>
          </div>
        </div>
        <div className="playerNumber">플레이어 {userCnt} / 10</div>
        <div className="playerContainer">
          {users &&
            users.map((user) => (
              <div className="playerItem" key={user}>
                {leader === user ? (
                  <div className="leaderThumbnail"></div>
                ) : (
                  <div
                    className="playerThumbnail"
                    //style={{ backgroundColor: randomColor() }}
                  ></div>
                )}
                {user}
              </div>
            ))}
        </div>
        <div className="exit_button">
          <Link to={"/Home"}>나가기</Link>
        </div>
      </div>
      <div className="contents">{content}</div>
      {/* <div className="contents">
        {isGameStarted === 'before' ? 
        (
          isLeader ? 
          (
            <div className="circleContainer" onClick={onCircleClick}>
              Start!
            </div>
          ) : 
          (
            <div className="userBeforeGame">
              <img src={imgAresene} alt="Arsene" /><p>게임시작 대기중...</p>
            </div>
          )
        ) : isGameStarted === 'ing' ? 
        (
          isLeader ? 
          (
            <div className="gameBoard">
              {gameBoard}
              <div className="endGameBtn" onClick={clickEndGame}>
                게임 종료 후 Liar 확인
              </div>
            </div>
          ) : 
          (
            <div className="gameBoard">{gameBoard}</div> 
          )         
        ) : (
          <div className="userBeforeGame"><img src={imgAresene} alt="Arsene" /><p>{liar}</p></div>
        )}
      </div>     */}
    </div>
  );
}

export default Room;
