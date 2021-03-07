import logo from './logo.svg';
import React from 'react';
import './App.css';
import { BrowserRouter } from "react-router-dom";
import { AppContextProvider } from "./contexts/AppContext";
import { createMuiTheme } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';
import { Routes } from './components/Routes';
import { GlobalStyles } from './styles/GlobalStyles';
import { Auth } from 'aws-amplify';
import { ModalProvider } from 'styled-react-modal'
import { BookNow } from './components/BookNow';


const theme = createMuiTheme({
  typography: {
    fontFamily: [
      'RobotoCondensed-Regular',
    ].join(','),
  },
});

const auth_config = {
  region: 'eu-west-2',
  identityPoolRegion: 'eu-west-2',
  userPoolId: process.env.REACT_APP_USER_POOL_ID,
  userPoolWebClientId: process.env.REACT_APP_USER_POOL_WEB_CLIENT_ID,
}

Auth.configure(auth_config);

function App() {
  return (
    <AppContextProvider>
      <ThemeProvider theme={theme}>
        <ModalProvider>
          <BrowserRouter>
            <GlobalStyles />
            {/* <Navigation /> */}
            {/* <Header /> */}
            <Routes />
            <BookNow />
          </BrowserRouter>
        </ModalProvider>
      </ThemeProvider>
    </AppContextProvider>
  );
}

export default App;
