import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import EnterLeader from "./pages/EnterLeader";
import EnterUser from "./pages/EnterUser";
import Room from "./pages/Room";
import Home from "./pages/Home";
import axios from "axios";
import {useMediaQuery} from 'react-responsive';

axios.defaults.withCredentials = true;

function App() {
  const isMobile = useMediaQuery({
    query: "(max-width:768px)"
  });

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<EnterLeader isMobile={isMobile}/>} />
          <Route path={`${process.env.PUBLIC_URL}/`} element={<EnterLeader isMobile={isMobile}/>} />
          <Route path="/enter" element={<EnterLeader isMobile={isMobile}/>} />
          <Route path="/enter/*" element={<EnterUser isMobile={isMobile}/>} />
          <Route path="/room/*" element={<Room isMobile={isMobile}/>} />
          <Route path="/home" element={<Home isMobile={isMobile}/>} />
        </Routes>
      </BrowserRouter>      
    </>
  );
}

export default App;
