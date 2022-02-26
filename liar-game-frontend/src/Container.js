import React from "react";
import styled from "styled-components";

const StyledContainer = styled.div`
  background: rgba(196, 196, 196, 1);
  font-size: 50px;
  text-align: center;
`;

function Container({ children }) {
  return <StyledContainer>{children}</StyledContainer>;
}

export default Container;
