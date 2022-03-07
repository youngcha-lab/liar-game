import React from "react";

class Room {
  function() {
    return (
      <div className="gameScreen">
        <div className="gameScreenLeft">
          <p className="playersLabel">players:</p>
          <div className="playersList">
            {this.state.players.map((player) => (
              <li key={player}>{player}</li>
            ))}
          </div>
        </div>
        <div className="gameScreenRight">
          {this.state.stage === 0 && ( // Waiting room
            <div>
              <p className="linkLabel">send your friends this link:</p>
              <p className="link">
                {"liar-ga.me" +
                  window.location.pathname
                    .replace("room", "enter")
                    .substring(0, 14)}
              </p>
              <button
                className="block"
                onClick={this.updateStage}
                style={{
                  marginTop: "20px",
                  marginLeft: "auto",
                  marginRight: "auto",
                }}
              >
                start
              </button>
            </div>
          )}
          {this.state.stage === 1 && ( // Game started
            <div>
              <div className="promptLabel">
                category is {this.state.category}
                <br />
                {window.location.pathname.substring(19) !== this.state.liar && (
                  <div>word is {this.state.word}</div>
                )}
                {window.location.pathname.substring(19) === this.state.liar && (
                  <div>you are the liar</div>
                )}
              </div>
              <button
                className="block"
                onClick={this.updateStage}
                style={{
                  marginTop: "20px",
                  marginLeft: "auto",
                  marginRight: "auto",
                }}
              >
                end
              </button>
            </div>
          )}
          {this.state.stage === 2 && ( // Game ended
            <div>
              <div className="liarLabel">{this.state.liar} was the liar!</div>
              <button
                className="block"
                onClick={this.updateStage}
                style={{
                  marginTop: "20px",
                  marginLeft: "auto",
                  marginRight: "auto",
                }}
              >
                play again
              </button>
            </div>
          )}
        </div>
      </div>
    );
  }
}

export default Room;
