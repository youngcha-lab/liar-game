import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import EnterLeader from "./pages/EnterLeader";
import EnterUser from "./pages/EnterUser";
import Room from "./pages/Room";
import "./css/Enter.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<EnterLeader />} />
        <Route path="/enter" element={<EnterLeader />} />
        <Route path="/enter/:room_code" element={<EnterLeader />} />
        <Route path="/enterUser/:room_code" element={<EnterUser />} />
        <Route path="/room/:room_code" element={<Room />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
