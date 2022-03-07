import React, { Component } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Enter from "./pages/Enter";
import Room from "./pages/Enter";
import "./css/Enter.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Enter />} />
        <Route path="/enter/*" element={<Enter />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
