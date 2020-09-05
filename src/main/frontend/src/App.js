import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [yolo, setYolo] = useState(null);

  const fetchYolo = () => {
    fetch("/yolo")
        .then(response => response.text())
        .then(yoloText => setYolo(yoloText));
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <p>{yolo}</p>
        <button onClick={fetchYolo}>Fetch yolo</button>
      </header>
    </div>
  );
}

export default App;
