import React, { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "../css/Room.css";
import axios from "axios";
import "../img/crown.png";
import imgAresene from "../img/Arsene.png";
import toast, { Toaster } from 'react-hot-toast';
import { style } from "@mui/system";

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
      setIsLeader(response.data.room.currentUser.isLeader);
    }
  };

  const refreshRoom = async () => {
    const response = await getRoom();
    const currentGame = response.data.room.currentGame;
    const lastGame = response.data.room.lastGame;

    setLeader(response.data.room.leader);
    setUsers(response.data.room.users);
    setUserCnt(response.data.room.users.length);
    setIsLiar(response.data.room.currentUser.isLiar);

    if (!currentGame && lastGame) {
      setIsGamestarted("after");
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
    refreshRoom();        
    const loop = setInterval(() => {
      refreshRoom();
    }, 500);

    return () => clearInterval(loop);
  }, []);

  const randomColor = () => {
    let color = "#" + Math.round(Math.random() * 0xffffff).toString(16);
    //#FF0000
    return color;
  };

  const onLinkClick = () => {
    const copyText = host + ":3000" + location.pathname;

    navigator.clipboard.writeText(copyText);
    toast('초대 링크가 복사 되었습니다.', {style: {'font-size': '28px', 'maxWidth': '80%', 'padding': '16px',}});
  };

  const onCircleClick = async () => {
    try {
      const roomInfo = await axios.get(host + `:8080/api/v1/room/${roomCode}`);

      const response = await axios
        .post(host + `:8080/api/v1/room/${roomCode}/game/start`)
        .then((response) => {
          setIsGamestarted("ing");
          setIsLiar(response.data.room.currentUser.isLiar);
          setWord(response.data.keyword);
          setCategory(response.data.category);
        })
        .catch((err) => {
          console.log(err);
        });      
      return response;
    } catch (e) {
      console.log(e);
    }
    return null;
  };

  const clickEndGame = async () => {
    try{
      const response = await axios.delete(
        host + `:8080/api/v1/room/${roomCode}/game/end`
      ).then((response) => {
        setIsGamestarted("after");
        setLiar(response.data.liar);
      }).catch((err) => {
        console.log(err);
      });
      return response;      
    } catch (e) {
      console.log(e);
    }
    return null;        
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
      <Toaster toastOptions={{ position: "top-center" }} />
      <div className="sidebar">
        <div className="tooltip">
          {/* <br></br>
          <div className="tooltiptext" id="myTooltip">
            Copy to clipboard
          </div> */}
          <div>
            <button className="inviteButton" onClick={onLinkClick}>
              초대하기
            </button>
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
        <Link to={"/Home"} style={{ textDecoration: 'none' }}>
          <div className="exit_button">
            나가기
          </div>
        </Link>
      </div>
      <div className="contents">{content}</div>
      {/* <div className="contents">
        {category}
        {isGameStarted ? (
          isLiar ? (
            <div className="board">
              <img src={imgAresene} alt="Arsene" />
              Liar!
            </div>
          ) : (
            <div className="board">{word}</div>
          )
        ) : isLeader ? (
          <div className="circleContainer" onClick={onCircleClick}>
            Start!
          </div>
        ) : (
          <div className="userBeforeGame">
            <img src={imgAresene} alt="Arsene" />
            <p>게임시작 대기중...</p>
          </div>
        )}
      </div>
      {isGameStarted ? (
        isLeader ? (
          <div className="endGameBtn" onClick={clickEndGame}>
            게임 종료 후 Liar 확인
          </div>
        ) : (
          <></>
        )
      ) : (
        <></>
      )} */}
    </div>
  );
}

export default Room;
