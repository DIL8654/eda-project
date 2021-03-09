import React from 'react';

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
  }
  if (body) {
    requestParams.body = JSON.stringify(body);
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
