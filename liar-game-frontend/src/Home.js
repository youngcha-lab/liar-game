import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div
      style={{
        backgroundColor: "rgba(196, 196, 196, 1)",
        width: "100%",
        height: "100%",
        textAlign: "center",
      }}
    >
      <div
        style={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: "382px",
        }}
      >
        <h1 style={{ marginBottom: "200px", fontSize: "72px" }}>LIER GAME</h1>
        <input
          style={{
            marginBottom: "30px",
            width: "100%",
            fontSize: "24px",
            padding: "16px",
          }}
        />
        <br />
        <button style={{ width: "100%", fontSize: "24px", padding: "16px" }}>
          방생성
        </button>
      </div>
    </div>
  );
}

export default Home;
