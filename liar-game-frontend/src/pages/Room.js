import React, { useState, useEffect, useRef } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../css/Room.css";
import "../css/mobile/Room_mobile.css";
import axios from "axios";
import "../img/crown.png";
import imgAresene from "../img/Arsene.png";
import toast, { Toaster } from "react-hot-toast";

function Room({ isMobile }) {
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
  const [isOpen, setMenu] = useState(false);
  const [curUser, setCurUser] = useState("");
  const isGameEnded = useRef();
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
      setCurUser(response.data.room.currentUser);
    }
  };

  const checkLeaderGoOut = async () => {
    const response = await getRoom();
    const users = response.data.room.users;
    const leader = response.data.room.leader;
    let isLeaderGoOut = true;

    users.forEach((user) => {
      if (leader === user.nickname) {
        isLeaderGoOut = false;
      }
    });

    if (isLeaderGoOut) {
      navigate("/home");
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

    if (!currentGame) {
      if (lastGame && isGameEnded.current) {
        setIsGamestarted("after");
        setLiar(response.data.room.lastGame.liar);
        setTimeout(() => {
          isGameEnded.current = false;
        }, 3000);
      } else {
        setIsGamestarted("before");
        setCategory("");
        setWord("");
      }
    } else {
      isGameEnded.current = true;
      setIsGamestarted("ing");
      setCategory(currentGame.category);
      setWord(currentGame.keyword);
    }
  };

  useEffect(() => {
    checkUser();

    const loop = setInterval(() => {
      checkLeaderGoOut();
      refreshRoom();
    }, 500);

    return () => clearInterval(loop);
  }, []);

  const onLinkClick = () => {
    const copyText = host + location.pathname;

    if (navigator.clipboard && window.isSecureContext) {
      navigator.clipboard.writeText(copyText);
    } else {
      let textArea = document.createElement("textarea");
      textArea.value = copyText;
      textArea.style.position = "fixed";
      textArea.style.left = "-999999px";
      textArea.style.top = "-999999px";
      document.body.appendChild(textArea);
      textArea.focus();
      textArea.select();
      new Promise((res, rej) => {
        document.execCommand("copy") ? res() : rej();
        textArea.remove();
      });
    }

    toast("초대 링크가 복사 되었습니다.", {
      style: { fontSize: "28px", maxWidth: "80%", padding: "16px" },
    });
  };

  const onStartClick = async () => {
    try {
      await axios
        .post(host + `:8080/api/v1/room/${roomCode}/game/start`)
        .catch((err) => {
          console.log(err);
        });
    } catch (e) {
      console.log(e);
    }
  };

  const onEndClick = async () => {
    try {
      await axios
        .delete(host + `:8080/api/v1/room/${roomCode}/game/end`)
        .catch((err) => {
          console.log(err);
        });
    } catch (e) {
      console.log(e);
    }
  };

  const onExitClick = async () => {
    try {
      await axios
        .delete(host + `:8080/api/v1/room/${roomCode}/user/leave`)
        .catch((err) => {
          console.log(err);
        });

      navigate("/");
    } catch (e) {
      console.log(e);
    }
  };

  const toggleMenu = () => {
    setMenu(!isOpen);
  };

  const toggleHide = () => {
    setMenu(false);
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
      {isHide ? (
        <div
          className={isMobile ? "blindWordBox_mobile" : "blindWordBox"}
          onMouseDown={wordBoxMounseDown}
          onTouchStart={wordBoxMounseDown}
          onMouseUp={wordBoxMounseUp}
          onTouchEnd={wordBoxMounseUp}
        >
          Tap!
        </div>
      ) : isLiar ? (
        <div
          className={isMobile ? "openWordBox_mobile" : "openWordBox"}
          onMouseDown={wordBoxMounseDown}
          onTouchStart={wordBoxMounseDown}
          onMouseUp={wordBoxMounseUp}
          onTouchEnd={wordBoxMounseUp}
        >
          <img src={imgAresene} alt="Arsene" /> Liar!
        </div>
      ) : (
        <div
          className={isMobile ? "openWordBox_mobile" : "openWordBox"}
          onMouseDown={wordBoxMounseDown}
          onTouchStart={wordBoxMounseDown}
          onMouseUp={wordBoxMounseUp}
          onTouchEnd={wordBoxMounseUp}
        >
          {word}
        </div>
      )}
    </>
  );

  let content = null;
  if (isGameStarted === "before") {
    if (isLeader) {
      content = (
        <button
          className={isMobile ? "circleContainer_mobile" : "circleContainer"}
          onClick={onStartClick}
        >
          Start!
        </button>
      );
    } else {
      content = (
        <div className={isMobile ? "userBeforeGame_mobile" : "userBeforeGame"}>
          <img src={imgAresene} alt="Arsene" />
          <p>게임시작 대기중...</p>
        </div>
      );
    }
  } else if (isGameStarted === "ing") {
    if (isLeader) {
      content = (
        <div className={isMobile ? "gameBoard_mobile" : "gameBoard"}>
          {gameBoard}
          <button
            className={isMobile ? "endGameBtn_mobile" : "endGameBtn"}
            onClick={onEndClick}
          >
            게임 종료 후 Liar 확인
          </button>
        </div>
      );
    } else {
      content = <div className="gameBoard">{gameBoard}</div>;
    }
  } else if (isGameStarted === "after") {
    content = (
      <div className={isMobile ? "userBeforeGame_mobile" : "userBeforeGame"}>
        <img src={imgAresene} alt="Arsene" />
        <p>{liar}</p>
      </div>
    );
  }

  return (
    <div className="main">
      {isMobile ? (
        <div className="menu_mobile">
          <button className="hamburgerBtn" onClick={toggleMenu}>
            참가자 보기
          </button>
          <div className="curUserInfo">
            {curUser.isLeader ? (
              <div className="leaderThumbnail_mobile"></div>
            ) : (
              <div
                className="playerThumbnail_mobile"
                style={{ backgroundColor: curUser.profileColor }}
              ></div>
            )}
            {curUser.nickname} +{userCnt - 1}
          </div>
        </div>
      ) : (
        ""
      )}
      <Toaster toastOptions={{ position: "top-center" }} />
      <div
        className={
          isMobile ? (isOpen ? "show_sidebar" : "hide_sidebar") : "sidebar"
        }
      >
        <div className={isMobile ? "tooltip_mobile" : "tooltip"}>
          <button
            className={isMobile ? "inviteButton_mobile" : "inviteButton"}
            onClick={onLinkClick}
          >
            초대하기
          </button>
        </div>
        <div className={isMobile ? "playerNumber_mobile" : "playerNumber"}>
          플레이어 {userCnt} / 10
        </div>
        <div
          className={isMobile ? "playerContainer_mobile" : "playerContainer"}
        >
          {users &&
            users.map((user) => (
              <div
                className={isMobile ? "playerItem_mobile" : "playerItem"}
                key={user.nickname}
              >
                {leader === user.nickname ? (
                  <div
                    className={
                      isMobile ? "leaderThumbnail_mobile" : "leaderThumbnail"
                    }
                  ></div>
                ) : (
                  <div
                    className={
                      isMobile ? "playerThumbnail_mobile" : "playerThumbnail"
                    }
                    style={{ backgroundColor: user.profileColor }}
                  ></div>
                )}
                {user.nickname}
              </div>
            ))}
        </div>
        <button
          className={isMobile ? "exitBtn_mobile" : "exitBtn"}
          onClick={onExitClick}
        >
          나가기
        </button>
      </div>
      <div className="contents" onClick={toggleHide}>
        {content}
      </div>
    </div>
  );
}

export default Room;
