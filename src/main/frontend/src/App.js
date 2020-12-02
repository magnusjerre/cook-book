import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import LoginButton from './login-logout/LoginButton';
import LogoutButton from './login-logout/LogoutButton';
import Profile from './login-logout/Profile';
import { useAuth0 } from '@auth0/auth0-react';
import RecipeOverview from './recipe/RecipeOverview';

function App() {
  const { getAccessTokenSilently } = useAuth0();

  const [yolo, setYolo] = useState(null);
  const [yoloSecured, setYoloSecured] = useState(null);
  const [saveMapResult, setSaveMapResult] = useState(null);
  const [recipes, setRecipes] = useState([]);

  const fetchYolo = () => {
    fetch("/yolo")
        .then(response => response.text())
        .then(yoloText => setYolo(yoloText));
  }

  const fetchYoloSecured2 = () => {
    (async () => {
      try {
        console.log("getting access token silently");
        const token = await getAccessTokenSilently({
          audience: "https://baje-cook-book.herokuap.com",
          scope: "read:yolosecret",
        });
        console.log("fetching yolo-secured");
        const response = await fetch("/yolo-secured", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        const text = await response.text();
        setYoloSecured(text);
      } catch (e) {
        console.log(e);
      }
    })();
  }

  const fetchRecipes = () => {
    (async () => {
      try {
        console.log("getting access token silently");
        const token = await getAccessTokenSilently({
          audience: "https://baje-cook-book.herokuap.com",
          scope: "read:yolosecret",
        });
        console.log("fetching recipes");
        const response = await fetch("/recipes", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        const recipes = await response.json();
        setRecipes(recipes);
        console.log("Recipes", recipes);
      } catch (e) {
        console.log(e);
      }
    })();
  };

  const saveMap = () => {
    (async () => {
      const token = await getAccessTokenSilently({
        audience: "https://baje-cook-book.herokuap.com",
        scope: "read:yolosecret",
      });

      fetch("/yolo-secured", {
        method: "POST",
        body: `{"yo":"lo","no":"no"}`,
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      })
      .then(response => response.text())
      .then(text => setSaveMapResult(text))
      .catch(e => setSaveMapResult("error"));
    }
    )();
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
        <p>{yoloSecured}</p>
        <button onClick={fetchYoloSecured2}>Fetch yolo-secured</button>
        <p>{saveMapResult}</p>
        <button onClick={saveMap}>Save map to backend</button>
        <button onClick={fetchRecipes}>Recipes</button>
        <h1>My Recipes</h1>
        <ul>
          {
            recipes.map(recipe => <RecipeOverview recipe={recipe} />)
          }
        </ul>
        <h2>Wanna login?</h2>
        <LoginButton />
        <LogoutButton />
        <Profile />
      </header>
    </div>
  );
}

export default App;
