import React from 'react';
let base64 = require('base-64');
// const BASE_URL = process.env.REACT_APP_API_URL;

const HEADERS = {
  Accept: 'application/json',
  'Content-Type': 'application/json',
};

const request = (token, baseUrl, method, path, body, isFullPath = false) => new Promise((resolve, reject) => {

  let url = `${baseUrl}${path}`;

  const requestParams = {
    method,
    headers: HEADERS,
    body: undefined,
  };

  if (token) {
    requestParams.headers.Authorization = 'Bearer ' + token;
    requestParams.headers['Content-Type']='application/json';
  }
  else{
    requestParams.headers.Authorization = 'Basic ' + base64.encode('web' + ":" + 'webpass');
    requestParams.headers['Content-Type'] = 'application/x-www-form-urlencoded';
    // requestParams.headers['Content-Length'] = 0;
    
  }
  if (body) {
    if (token){
      requestParams.body = JSON.stringify(body);
    }
    else{
      requestParams.body = body;
    }
    
  }
  url = isFullPath ? path : url;

  fetch(url, requestParams)
    .then((response) => {
      if (response.status === 401) reject(Error('401'));
      if (response.status < 300) {
        if (response._bodyInit === '' || method === 'PUT') {
          return resolve(true);
        } else if (response.status === 204) {
          return resolve(true);
        } else {
          console.log('Response JSON', response);
          const json = response.json();
          return resolve(json);
        }
      } else {
        response.json().then((error) => {
          console.log('error', error);
          return reject(error);
        });
      }
    }).catch((err) => {
      reject(err);
    });

});

export default request;
