import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Enter from "./Enter";
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/enter" element={<Enter />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
