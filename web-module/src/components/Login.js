import React, { useState, useContext, useEffect } from 'react';

import {
  Button,
  TextField,
  Box,
  Typography,
  Container,
} from '@material-ui/core';

import { makeStyles } from '@material-ui/core/styles';

import Colors from '../helpers/Colors';
import PasswordInput from './PasswordInput';
import { AppContext } from '../contexts/AppContext';
import { useHistory } from 'react-router';
import { login } from '../services/Service';

export default function Login() {
  const [username, setUsername] = useState(null);
  const [password, setPassword] = useState(null);

  const { authData, setAuth, setUser } = useContext(AppContext);
  const history = useHistory();

  const classes = useStyles();

  const onClickLogin = () => {
    if (!username || !password) {
      alert('Please check your login credentials');
    }
    if (username && password) {
      login({
        username: username,
        password: password,
        grant_type = 'password',
      }).then((response) => {
        setUser(username);
        setAuth(response.access_token);
        history.replace("/search");
      }).catch(() => {
        alert("Invalid Credentials");
      });
      // history.replace("/search");

    }
  }

  return (
    <Box className={ classes.template }>
      <Container
        component="main"
        className={ classes.container }
      >
        
        <Box className={classes.paper}>
          <Box className={classes.form} noValidate>
            <TextField
              variant="outlined"
              margin="normal"
              placeholder="Enter Username"
              fullWidth
              onChange={(event) => setUsername(event.target.value)}
              id="username"
              label="USERNAME"
              name="username"
              className={ classes.usernameInput }
              autoFocus
            />
            <PasswordInput
              onChange={(value) => setPassword(value)}
            />
            
              <Button
                type="submit"
                fullWidth
                variant="contained"
                className={classes.submit}
                onClick={ onClickLogin }
              >
                {"LOGIN"}
              </Button>
          </Box>
        </Box>
      </Container>
    </Box>
  );
}


const useStyles = makeStyles((theme) => ({
  template: {
    minHeight: 600,
    height: '100vh',
    width: '100vw',
    display: 'flex',
    justifyContent: 'flex-start',
    alignItems: 'flex-start',
    backgroundColor: 'rgba(34, 147, 249)',
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    margin: 0,
    padding: 0,
  },
  container: {
    marginTop: '8vh',
    height: '70vh',
    width: '40vw',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  paper: {
    width: '40vw',
    padding: 30,
    borderRadius: 8,
    maxWidth: 600,
    minWidth: 400,
    
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    backgroundColor: 'white',
    minHeight: 250,

  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%',
    marginTop: theme.spacing(1),
  },
  usernameInput: {
    fontFamily: 'RobotoCondensed-Bold',
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
    backgroundColor: Colors.blue,
    color: Colors.white,
    height: 45,
    fontSize: 18,
    boxShadow: 'none',
    fontFamily: 'SFProDisplay-Medium',
    '&:hover': {
      backgroundColor: Colors.blue,
      boxShadow: 'none',
    }
  },
  logo: {
    height: 100,
    width: '30vw',
    objectFit: 'contain',
    marginBottom: 10,
  },
  title: {
    fontSize: 22,
    color: Colors.white,
    fontFamily: 'SFProDisplay-Medium',
  },
  errorText: {
    color: Colors.white,
    fontSize: 17,
    fontFamily: 'SFProDisplay-Regular',
  },
  titleContainer: {
    padding: 5,
    paddingLeft: 40,
    paddingRight: 40,
    backgroundColor: Colors.blue,
    borderRadius: 22,
    marginTop: 5,
    marginBottom: 20,
  },
  alertBox: {
    marginTop: 50,
  },
}));