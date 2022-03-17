import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import EnterLeader from "./pages/EnterLeader";
import EnterUser from "./pages/EnterUser";
import Room from "./pages/Room";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<EnterLeader />} />
        <Route path={`${process.env.PUBLIC_URL}/`} element={<EnterLeader />} />
        <Route path="/enter" element={<EnterLeader />} />
        <Route path="/enterUser/*" element={<EnterUser />} />
        <Route path="/room/*" element={<Room />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
