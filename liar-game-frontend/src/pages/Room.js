import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import imgLiar from "../img/liar.jpg";

function Room() {
  const { room_code } = useParams();

  return (
    <div className="Enter">
      <div className="Enter-body">
        <div className="Enter-content">
          <img src={imgLiar} />
        </div>
      </div>
    </div>
  );
}

export default Room;
