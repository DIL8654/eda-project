import React from 'react';
import clsx from 'clsx';

import {
  IconButton,
  OutlinedInput,
  InputLabel,
  InputAdornment,
  FormControl,
} from '@material-ui/core';

import { makeStyles } from '@material-ui/core/styles';

import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';

const useStyles = makeStyles((theme) => ({
  margin: {
    marginTop: 20,
  },
  withoutLabel: {
    marginTop: theme.spacing(3),
  },
}));

export default function InputAdornments({ onChange }) {
  const classes = useStyles();
  const [values, setValues] = React.useState({
    amount: '',
    password: '',
    weight: '',
    weightRange: '',
    showPassword: false,
  });

  const handleChange = (prop) => (event) => {
    onChange(event.target.value)
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return (
        <FormControl fullWidth className={clsx(classes.margin, classes.textField)} variant="outlined">
          <InputLabel htmlFor="outlined-adornment-password">PASSWORD</InputLabel>
          <OutlinedInput
            id="outlined-adornment-password"
            type={values.showPassword ? 'text' : 'password'}
            value={values.password}
            onChange={handleChange('password')}
            fullWidth
            placeholder="Enter Password"
            label="PASSWORD"
            endAdornment={
            <InputAdornment position="end">
                <IconButton
                aria-label="toggle password visibility"
                onClick={handleClickShowPassword}
                onMouseDown={handleMouseDownPassword}
                edge="end"
                >
                {values.showPassword ? <Visibility /> : <VisibilityOff />}
                </IconButton>
            </InputAdornment>
            }
            labelWidth={70}
          />
        </FormControl>
  );
}
